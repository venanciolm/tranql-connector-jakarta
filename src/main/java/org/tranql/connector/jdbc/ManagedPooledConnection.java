/*
 * Copyright (c) 2004 - 2007, Tranql project contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.tranql.connector.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.resource.NotSupportedException;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.LocalTransaction;
import jakarta.resource.spi.LocalTransactionException;
import jakarta.resource.spi.ManagedConnectionFactory;
import jakarta.resource.spi.ManagedConnectionMetaData;
import jakarta.resource.spi.ResourceAdapterInternalException;
import javax.security.auth.Subject;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.transaction.xa.XAResource;

import org.tranql.connector.AbstractManagedConnection;
import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.UserPasswordManagedConnectionFactory;

/**
 * @version $Revision: 805 $ $Date: 2010-11-11 15:06:35 -0800 (Thu, 11 Nov 2010) $
 */
public class ManagedPooledConnection extends AbstractManagedConnection<Connection, ConnectionHandle> {
    private final CredentialExtractor credentialExtractor;
    private final LocalTransactionImpl localTx;
    private final LocalTransactionImpl localClientTx;
    private final PooledConnection pooledConnection;

    public ManagedPooledConnection(ManagedConnectionFactory mcf, PooledConnection pooledConnection, CredentialExtractor credentialExtractor, ExceptionSorter exceptionSorter) throws SQLException {
        super(mcf, pooledConnection.getConnection(), exceptionSorter);
        this.pooledConnection = pooledConnection;
        pooledConnection.addConnectionEventListener(new ConnectionEventListener() {
            public void connectionClosed(ConnectionEvent event) {
                //we should be handling this independently
            }

            public void connectionErrorOccurred(ConnectionEvent event) {
                Exception e = event.getSQLException();
                unfilteredConnectionError(e);
            }
        });
        this.credentialExtractor = credentialExtractor;
        localTx = new LocalTransactionImpl(true);
        localClientTx = new LocalTransactionImpl(false);
    }

    public boolean matches(ManagedConnectionFactory mcf, Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceAdapterInternalException {
        return credentialExtractor.matches(subject, connectionRequestInfo, (UserPasswordManagedConnectionFactory) mcf);
    }

    public LocalTransaction getClientLocalTransaction() {
        return localClientTx;
    }

    public LocalTransaction getLocalTransaction() throws ResourceException {
        return localTx;
    }

	protected void localTransactionStart(boolean isSPI) throws ResourceException {
        Connection c = physicalConnection;
        try {
            c.setAutoCommit(false);
        } catch (SQLException e) {
            throw new LocalTransactionException("Unable to disable autoCommit", e);
        }
        super.localTransactionStart(isSPI);
    }

	protected void localTransactionCommit(boolean isSPI) throws ResourceException {
        Connection c = physicalConnection;
        try {
            // according to the JDBC spec, reenabling autoCommit commits any current transaction
            // we need to do both here, so we rely on this behaviour in the driver as otherwise
            // commit followed by setAutoCommit(true) may result in 2 commits in the database
            if (mcf instanceof AutocommitSpecCompliant
                    && (((AutocommitSpecCompliant) mcf).isCommitBeforeAutocommit() != null)
                    && ((AutocommitSpecCompliant) mcf).isCommitBeforeAutocommit().booleanValue()) {
                c.commit();
            }
            c.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                if (log != null) {
                    e.printStackTrace(log);
                }
            }
            throw new LocalTransactionException("Unable to commit", e);
        }
        super.localTransactionCommit(isSPI);
    }

	protected void localTransactionRollback(boolean isSPI) throws ResourceException {
        Connection c = physicalConnection;
        try {
            c.rollback();
        } catch (SQLException e) {
            throw new LocalTransactionException("Unable to rollback", e);
        }
        super.localTransactionRollback(isSPI);
        try {
            c.setAutoCommit(true);
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Unable to enable autoCommit after rollback", e);
        }
    }

    public XAResource getXAResource() throws ResourceException {
        throw new NotSupportedException("XAResource not available from a LocalTransaction connection");
    }

	public void cleanup() throws ResourceException {
        super.cleanup();
        Connection c = physicalConnection;
        try {
            //TODO reset tx isolation level
            if (!c.getAutoCommit()) {
                c.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new ResourceException("Could not reset autocommit when returning to pool", e);
        }
    }

	protected void closePhysicalConnection() throws ResourceException {
        try {
            pooledConnection.close();
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Error attempting to destroy managed connection", e);
        }
    }

    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        return null;
    }

}