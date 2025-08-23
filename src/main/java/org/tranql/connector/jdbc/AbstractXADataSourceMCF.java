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

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.InvalidPropertyException;
import javax.security.auth.Subject;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.ManagedConnectionHandle;
import org.tranql.connector.UserPasswordManagedConnectionFactory;

/**
 * @version $Revision: 845 $ $Date: 2011-03-08 15:46:00 -0800 (Tue, 08 Mar 2011) $
 */
public abstract class AbstractXADataSourceMCF<T extends XADataSource> implements UserPasswordManagedConnectionFactory, AutocommitSpecCompliant {
    private static final long serialVersionUID = -1531351804704240389L;
    protected final T xaDataSource;
    protected final ExceptionSorter exceptionSorter;
    private int transactionIsolationLevel = -1;

    protected PrintWriter log;

    protected AbstractXADataSourceMCF(T xaDataSource, ExceptionSorter exceptionSorter) {
        this.xaDataSource = xaDataSource;
        this.exceptionSorter = exceptionSorter;
    }

    public Object createConnectionFactory() throws ResourceException {
        throw new NotSupportedException("ConnectionManager is required");
    }

    public Object createConnectionFactory(ConnectionManager connectionManager) throws ResourceException {
        return new TranqlDataSource(this, connectionManager);
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        CredentialExtractor credentialExtractor = new CredentialExtractor(subject, connectionRequestInfo, this);

        XAConnection sqlConnection = getPhysicalConnection(credentialExtractor);
        try {
            return new ManagedXAConnection(this, sqlConnection, credentialExtractor, exceptionSorter);
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Could not set up ManagedXAConnection", e);
        }
    }

    protected XAConnection getPhysicalConnection(CredentialExtractor credentialExtractor) throws ResourceException {
        try {
            return xaDataSource.getXAConnection(credentialExtractor.getUserName(), credentialExtractor.getPassword());
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Unable to obtain physical connection to " + xaDataSource, e);
        }
    }

    public ManagedConnection matchManagedConnections(Set set, Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        for (Object o : set) {
            if (o instanceof ManagedConnectionHandle) {
                ManagedConnectionHandle mc = (ManagedConnectionHandle) o;
                if (mc.matches(this, subject, connectionRequestInfo)) {
                    return mc;
                }
            }
        }
        return null;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        try {
            return xaDataSource.getLogWriter();
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException(e.getMessage(), e);
        }
    }

    public void setLogWriter(PrintWriter log) throws ResourceException {
        try {
            xaDataSource.setLogWriter(log);
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException(e.getMessage(), e);
        }
    }

    public Integer getLoginTimeout() {
        int timeout;
        try {
            timeout = xaDataSource.getLoginTimeout();
        } catch (SQLException e) {
            timeout = 0;
        }
        return timeout;
    }

    public void setLoginTimeout(Integer timeout) throws ResourceException {
        try {
            xaDataSource.setLoginTimeout(timeout);
        } catch (SQLException e) {
            throw new InvalidPropertyException(e.getMessage());
        }
    }

    /**
     * Assume xa implementations are spec compliant.  Override to return TRUE if they are not (e.g. oracle)
     *
     * @return
     */
    public Boolean isCommitBeforeAutocommit() {
        return Boolean.FALSE;
    }

    public int getTransactionIsolationLevel() {
        return transactionIsolationLevel;
    }

    public void setTransactionIsolationLevel(int transactionIsolationLevel) {
        this.transactionIsolationLevel = transactionIsolationLevel;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AbstractXADataSourceMCF) {
            AbstractXADataSourceMCF other = (AbstractXADataSourceMCF) obj;
            return this.xaDataSource.equals(other.xaDataSource);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return xaDataSource.hashCode();
    }

    @Override
    public String toString() {
        return "AbstractXADataSourceMCF[" + xaDataSource + "]";
    }

}
