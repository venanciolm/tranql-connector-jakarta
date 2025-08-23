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
import jakarta.resource.NotSupportedException;
import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ResourceAdapterInternalException;
import jakarta.resource.spi.InvalidPropertyException;
import javax.security.auth.Subject;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.ManagedConnectionHandle;
import org.tranql.connector.UserPasswordManagedConnectionFactory;

/**
 * @version $Revision: 845 $ $Date: 2011-03-08 15:46:00 -0800 (Tue, 08 Mar 2011)
 *          $
 */
public abstract class AbstractPooledConnectionDataSourceMCF<T extends ConnectionPoolDataSource>
		implements UserPasswordManagedConnectionFactory, AutocommitSpecCompliant {
	private static final long serialVersionUID = 1900317507397273416L;
	protected final T connectionPoolDataSource;
	protected final ExceptionSorter exceptionSorter;
	private int transactionIsolationLevel = -1;

	protected AbstractPooledConnectionDataSourceMCF(T connectionPoolDataSource, ExceptionSorter exceptionSorter) {
		this.connectionPoolDataSource = connectionPoolDataSource;
		this.exceptionSorter = exceptionSorter;
	}

	public Object createConnectionFactory() throws ResourceException {
		throw new NotSupportedException("ConnectionManager is required");
	}

	public Object createConnectionFactory(ConnectionManager connectionManager) throws ResourceException {
		return new TranqlDataSource(this, connectionManager);
	}

	public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo)
			throws ResourceException {
		CredentialExtractor credentialExtractor = new CredentialExtractor(subject, connectionRequestInfo, this);

		PooledConnection sqlConnection = getPhysicalConnection(subject, credentialExtractor);
		try {
			return new ManagedPooledConnection(this, sqlConnection, credentialExtractor, exceptionSorter);
		} catch (SQLException e) {
			throw new ResourceAdapterInternalException("Could not set up ManagedPooledConnection", e);
		}
	}

	protected PooledConnection getPhysicalConnection(Subject subject, CredentialExtractor credentialExtractor)
			throws ResourceException {
		try {
			return connectionPoolDataSource.getPooledConnection(credentialExtractor.getUserName(),
					credentialExtractor.getPassword());
		} catch (SQLException e) {
			throw new ResourceAdapterInternalException(
					"Unable to obtain physical connection to " + connectionPoolDataSource, e);
		}
	}

	public ManagedConnection matchManagedConnections(@SuppressWarnings("rawtypes") Set set, Subject subject,
			ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
		for (Object o : set) {
			if (o instanceof ManagedConnectionHandle) {
				@SuppressWarnings("unchecked")
				ManagedConnectionHandle<T, ?> mc = (ManagedConnectionHandle<T, ?>) o;
				if (mc.matches(this, subject, connectionRequestInfo)) {
					return mc;
				}
			}
		}
		return null;
	}

	public PrintWriter getLogWriter() throws ResourceException {
		try {
			return connectionPoolDataSource.getLogWriter();
		} catch (SQLException e) {
			throw new ResourceAdapterInternalException(e.getMessage(), e);
		}
	}

	public void setLogWriter(PrintWriter log) throws ResourceException {
		try {
			connectionPoolDataSource.setLogWriter(log);
		} catch (SQLException e) {
			throw new ResourceAdapterInternalException(e.getMessage(), e);
		}
	}

	public Integer getLoginTimeout() {
		int timeout;
		try {
			timeout = connectionPoolDataSource.getLoginTimeout();
		} catch (SQLException e) {
			timeout = 0;
		}
		return timeout;
	}

	public void setLoginTimeout(Integer timeout) throws ResourceException {
		try {
			connectionPoolDataSource.setLoginTimeout(timeout);
		} catch (SQLException e) {
			throw new InvalidPropertyException(e.getMessage());
		}
	}

	public int getTransactionIsolationLevel() {
		return transactionIsolationLevel;
	}

	public void setTransactionIsolationLevel(int transactionIsolationLevel) {
		this.transactionIsolationLevel = transactionIsolationLevel;
	}

	/**
	 * Assume connection pooled datasource implementations are spec compliant.
	 * Override to return TRUE if they are not (e.g. oracle)
	 * 
	 * @return
	 */
	public Boolean isCommitBeforeAutocommit() {
		return Boolean.FALSE;
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
		if (obj instanceof AbstractPooledConnectionDataSourceMCF) {
			@SuppressWarnings("unchecked")
			AbstractPooledConnectionDataSourceMCF<T> other = (AbstractPooledConnectionDataSourceMCF<T>) obj;
			return this.connectionPoolDataSource.equals(other.connectionPoolDataSource);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return connectionPoolDataSource.hashCode();
	}

	@Override
	public String toString() {
		return "AbstractXADataSourceMCF[" + connectionPoolDataSource + "]";
	}

}