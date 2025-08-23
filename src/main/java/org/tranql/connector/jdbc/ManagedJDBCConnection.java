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

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.LocalTransactionException;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import org.tranql.connector.AbstractManagedConnection;
import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.UserPasswordManagedConnectionFactory;
import org.tranql.connector.ExceptionSorter;

/**
 * Implementation of ManagedConnection that manages a physical JDBC connection.
 *
 * @version $Revision: 805 $ $Date: 2010-11-11 15:06:35 -0800 (Thu, 11 Nov 2010) $
 */
class ManagedJDBCConnection extends AbstractManagedConnection<Connection, ConnectionHandle> {
    private final CredentialExtractor credentialExtractor;
    private final AbstractManagedConnection.LocalTransactionImpl localTx;
    private final AbstractManagedConnection.LocalTransactionImpl localClientTx;
    private final boolean commitBeforeAutoCommit;

    /**
     * Constructor for initializing the manager.
     *
     * @param mcf the ManagedConnectionFactory that created this ManagedConnection
     * @param physicalConnection the connection to manage
     * @param credentialExtractor credential extractor
     * @param exceptionSorter the ExceptionSorter to use for classifying Exceptions raised on the physical connection
     * @param commitBeforeAutoCommit whether a commit should be performed before enabling JDBC auto-commit
     */
    ManagedJDBCConnection(UserPasswordManagedConnectionFactory mcf, Connection physicalConnection, CredentialExtractor credentialExtractor, ExceptionSorter exceptionSorter, boolean commitBeforeAutoCommit) {
        super(mcf, physicalConnection, exceptionSorter);
        this.credentialExtractor = credentialExtractor;
        localTx = new AbstractManagedConnection.LocalTransactionImpl(true);
        localClientTx = new AbstractManagedConnection.LocalTransactionImpl(false);
        this.commitBeforeAutoCommit = commitBeforeAutoCommit;
    }

    public boolean matches(ManagedConnectionFactory mcf, Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceAdapterInternalException {
        return credentialExtractor.matches(subject,  connectionRequestInfo, (UserPasswordManagedConnectionFactory)mcf);
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
            if (commitBeforeAutoCommit) {
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
        Connection c = (Connection) physicalConnection;
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
        Connection c = (Connection) physicalConnection;
        try {
            c.close();
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Error attempting to destroy managed connection", e);
        }
    }

    public ManagedConnectionMetaData getMetaData() throws ResourceException {
        return null;
    }

	protected void attemptRollback() {
        Connection c = (Connection) physicalConnection;
        try {
            c.rollback();
        } catch (SQLException e) {
            //ignore.... presumably the connection is actually dead
        }
    }
}
