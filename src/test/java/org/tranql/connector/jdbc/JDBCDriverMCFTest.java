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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.axiondb.jdbc.AxionDataSource;
import org.tranql.connector.DissociatableConnectionHandle;
import org.tranql.connector.DissociatableConnectionHandleFactory;
import org.tranql.connector.UserPasswordHandleFactoryRequestInfo;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionRequestInfo;
import junit.framework.TestCase;

/**
 *
 *
 * @version $Revision: 93 $ $Date: 2004-06-15 01:35:58 +0200 (Tue, 15 Jun 2004)
 *          $
 */
public class JDBCDriverMCFTest extends TestCase {
	protected DataSource ds;
	private JDBCDriverMCF mcf;

	public void testConnection() throws ResourceException {
		mcf.setDriver("org.axiondb.jdbc.AxionDriver");
		mcf.setConnectionURL("jdbc:axiondb:testdb");
		UserPasswordHandleFactoryRequestInfo<?, ?> cri = new UserPasswordHandleFactoryRequestInfo<>(null, null, null);
		ManagedJDBCConnection mc = (ManagedJDBCConnection) mcf.createManagedConnection(null, cri);
		assertNotNull(mc.getPhysicalConnection());
		mc.closePhysicalConnection();
	}

	public void testTransaction() throws ResourceException, SQLException {
		mcf.setDriver("org.axiondb.jdbc.AxionDriver");
		mcf.setConnectionURL("jdbc:axiondb:testdb");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		UserPasswordHandleFactoryRequestInfo<?,?> cri = new UserPasswordHandleFactoryRequestInfo<>(
				new DissociatableConnectionHandleFactory() {
					public DissociatableConnectionHandle newHandle(ConnectionRequestInfo connectionRequestInfo) {
						// with no cri, connection should work but matching won't
						return new ConnectionHandle(null, mcf, null);
					}
				}, null, null);
		ManagedJDBCConnection mc = (ManagedJDBCConnection) mcf.createManagedConnection(null, cri);
		Connection c = (Connection) mc.getConnection(null, cri);
		assertTrue(c.getAutoCommit());
		Statement s = c.createStatement();
		s.execute("CREATE TABLE test(k integer, v varchar(50))");
		s.execute("INSERT INTO test VALUES(1, 'Hello')");
		assertTrue(c.getAutoCommit());
		ResultSet rs = s.executeQuery("SELECT v FROM test WHERE k = 1");
		assertTrue(rs.next());
		assertEquals("Hello", rs.getString(1));
		rs.close();
		c.rollback();
		rs = s.executeQuery("SELECT v FROM test WHERE k = 1");
		assertTrue(rs.next());
		assertEquals("Hello", rs.getString(1));
		rs.close();
		s.execute("UPDATE test SET v = 'World'");
		c.rollback();
		rs = s.executeQuery("SELECT v FROM test WHERE k = 1");
		assertTrue(rs.next());
		assertEquals("World", rs.getString(1));
		rs.close();

		c.setAutoCommit(false);
		assertFalse(c.getAutoCommit());
		s.execute("UPDATE test SET v = 'Hello'");
		rs = s.executeQuery("SELECT v FROM test WHERE k = 1");
		assertTrue(rs.next());
		assertEquals("Hello", rs.getString(1));
		rs.close();

		c.rollback();
		rs = s.executeQuery("SELECT v FROM test WHERE k = 1");
		assertTrue(rs.next());
		assertEquals("World", rs.getString(1));
		rs.close();

		s.close();
		c.close();
	}

	protected void setUp() throws Exception {
		super.setUp();
		ds = new AxionDataSource("jdbc:axiondb:testdb");

		mcf = new JDBCDriverMCF();
	}

	protected void tearDown() throws Exception {
		Connection c = ds.getConnection();
		c.createStatement().execute("SHUTDOWN");
		super.tearDown();
	}
}