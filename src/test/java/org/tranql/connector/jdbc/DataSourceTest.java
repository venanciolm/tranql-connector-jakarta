/**
 *
 *  Copyright 2004 Jeremy Boynes
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tranql.connector.jdbc;

import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.security.auth.Subject;

import org.tranql.connector.UserPasswordHandleFactoryRequestInfo;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionFactory;
import junit.framework.TestCase;

/**
 *
 *
 * @version $Revision: 861 $ $Date: 2012-01-11 01:37:49 +0100 (Wed, 11 Jan 2012)
 *          $
 */
public class DataSourceTest extends TestCase {
	private TranqlDataSource ds;
	private MockConnectionManager cm;
	private MockManagedConnectionFactory mcf;

	public void testGetConnectionContainer() {
		try {
			cm.c = new MockConnection();
			Connection c = ds.getConnection();
			assertSame(cm.c, c);
			assertSame(cm.mcf, mcf);
		} catch (SQLException e) {
			fail();
		}
	}

	public void testGetConnectionContainerResourceEx() {
		try {
			cm.ex = new ResourceException("RES");
			ds.getConnection();
			fail();
		} catch (SQLException e) {
			assertSame(cm.ex, e.getCause());
		}
	}

	public void testGetConnectionContainerSQLEx() {
		try {
			cm.ex = new ResourceException("RES", new SQLException("SQL"));
			ds.getConnection();
			fail();
		} catch (SQLException e) {
			assertSame(cm.ex.getCause(), e);
		}
	}

	public void testGetConnectionComponent() {
		try {
			cm.c = new MockConnection();
			Connection c = ds.getConnection("user", "password");
			assertSame(cm.c, c);
			assertSame(cm.mcf, mcf);
			assertNotNull(cm.cri);
			assertEquals("user", cm.cri.getUser());
			assertEquals("password", cm.cri.getPassword());
		} catch (SQLException e) {
			fail();
		}
	}

	public void testGetConnectionComponentResourceEx() {
		try {
			cm.ex = new ResourceException("RES");
			ds.getConnection("user", "password");
			fail();
		} catch (SQLException e) {
			assertSame(cm.ex, e.getCause());
		}
	}

	public void testGetConnectionComponentSQLEx() {
		try {
			cm.ex = new ResourceException("RES", new SQLException("SQL"));
			ds.getConnection("user", "password");
			fail();
		} catch (SQLException e) {
			assertSame(cm.ex.getCause(), e);
		}
	}

	public void testGetLoginTimout() {
		try {
			assertEquals(0, ds.getLoginTimeout());
		} catch (SQLException e) {
			fail();
		}
	}

	public void testGetLogWriter() {
		try {
			assertSame(mcf.log, ds.getLogWriter());
		} catch (SQLException e) {
			fail();
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		mcf = new MockManagedConnectionFactory();
		cm = new MockConnectionManager();
		ds = new TranqlDataSource(mcf, cm);
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private class MockConnectionManager implements ConnectionManager {
		private ResourceException ex;
		private Object c;
		private ManagedConnectionFactory mcf;
		private UserPasswordHandleFactoryRequestInfo cri;

		public Object allocateConnection(ManagedConnectionFactory managedConnectionFactory,
				ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
			this.mcf = managedConnectionFactory;
			this.cri = (UserPasswordHandleFactoryRequestInfo) connectionRequestInfo;
			if (ex != null) {
				throw ex;
			} else {
				return c;
			}
		}
	}

	@SuppressWarnings("serial")
	private class MockManagedConnectionFactory implements ManagedConnectionFactory {
		private PrintWriter log = new PrintWriter(System.out);

		public Object createConnectionFactory() throws ResourceException {
			throw new UnsupportedOperationException();
		}

		public Object createConnectionFactory(ConnectionManager connectionManager) throws ResourceException {
			throw new UnsupportedOperationException();
		}

		public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo)
				throws ResourceException {
			throw new UnsupportedOperationException();
		}

		public ManagedConnection matchManagedConnections(@SuppressWarnings("rawtypes") Set set, Subject subject,
				ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
			throw new UnsupportedOperationException();
		}

		public PrintWriter getLogWriter() throws ResourceException {
			return log;
		}

		public void setLogWriter(PrintWriter printWriter) throws ResourceException {
			throw new UnsupportedOperationException();
		}
	}

	private class MockConnection implements Connection {
		public void clearWarnings() throws SQLException {
		}

		public void close() throws SQLException {
		}

		public void commit() throws SQLException {
		}

		public Statement createStatement() throws SQLException {
			return null;
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return null;
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return null;
		}

		public boolean getAutoCommit() throws SQLException {
			return false;
		}

		public String getCatalog() throws SQLException {
			return null;
		}

		public int getHoldability() throws SQLException {
			return 0;
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return null;
		}

		public int getTransactionIsolation() throws SQLException {
			return 0;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Map getTypeMap() throws SQLException {
			return null;
		}

		public SQLWarning getWarnings() throws SQLException {
			return null;
		}

		public boolean isClosed() throws SQLException {
			return false;
		}

		public boolean isReadOnly() throws SQLException {
			return false;
		}

		public String nativeSQL(String sql) throws SQLException {
			return null;
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			return null;
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return null;
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql, int columnIndexes[]) throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql, String columnNames[]) throws SQLException {
			return null;
		}

		public Clob createClob() throws SQLException {
			return null;
		}

		public Blob createBlob() throws SQLException {
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return null;
		}

		public boolean isValid(int timeout) throws SQLException {
			return false;
		}

		@Override
		public void setClientInfo(String s, String s1) throws SQLClientInfoException {
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
		}

		public String getClientInfo(String name) throws SQLException {
			return null;
		}

		public Properties getClientInfo() throws SQLException {
			return null;
		}

		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return null;
		}

		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return null;
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return null;
		}

		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		}

		public void rollback() throws SQLException {
		}

		public void rollback(Savepoint savepoint) throws SQLException {
		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
		}

		public void setCatalog(String catalog) throws SQLException {
		}

		public void setHoldability(int holdability) throws SQLException {
		}

		public void setReadOnly(boolean readOnly) throws SQLException {
		}

		public Savepoint setSavepoint() throws SQLException {
			return null;
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			return null;
		}

		public void setTransactionIsolation(int level) throws SQLException {
		}

		public void setTypeMap(@SuppressWarnings("rawtypes") Map map) throws SQLException {
		}

		public <T> T unwrap(Class<T> iface) throws SQLException {
			return null;
		}

		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return false;
		}
		// adding!!

		@Override
		public void setSchema(String schema) throws SQLException {
		}

		@Override
		public String getSchema() throws SQLException {
			return null;
		}

		@Override
		public void abort(Executor executor) throws SQLException {
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return 0;
		}
	}
}