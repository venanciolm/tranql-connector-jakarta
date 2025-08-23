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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.RefAddr;
import javax.naming.Name;
import javax.naming.Context;
import javax.naming.spi.ObjectFactory;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LazyAssociatableConnectionManager;
import javax.resource.spi.ManagedConnectionFactory;

import org.tranql.connector.DissociatableConnectionHandle;
import org.tranql.connector.DissociatableConnectionHandleFactory;
import org.tranql.connector.UserPasswordHandleFactoryRequestInfo;

/**
 * DataSource connection factory for JDBC Connections.
 *
 * @version $Revision: 805 $ $Date: 2010-11-11 15:06:35 -0800 (Thu, 11 Nov 2010) $
 */
public class TranqlDataSource implements javax.sql.DataSource, javax.naming.Referenceable, javax.resource.Referenceable {
    private final ManagedConnectionFactory mcf;
    private final ConnectionManager cm;
    private final UserPasswordHandleFactoryRequestInfo<Connection, ConnectionHandle> containerRequestInfo;
    private Reference ref;
    private final DissociatableConnectionHandleFactory<Connection, ConnectionHandle> handleFactory;

    public TranqlDataSource(ManagedConnectionFactory mcf, ConnectionManager connectionManager) {
        this.mcf = mcf;
        this.cm = connectionManager;
        handleFactory = new HandleFactory(cm, mcf);
        containerRequestInfo = new UserPasswordHandleFactoryRequestInfo<Connection, ConnectionHandle>(handleFactory, null, null);
    }

    public Connection getConnection() throws SQLException {
        try {
            return (Connection) cm.allocateConnection(mcf, containerRequestInfo);
        } catch (ResourceException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            } else {
                throw (SQLException) new SQLException().initCause(e);
            }
        }
    }

    public Connection getConnection(String user, String password) throws SQLException {
        try {
            UserPasswordHandleFactoryRequestInfo cri = new UserPasswordHandleFactoryRequestInfo<Connection, ConnectionHandle>(handleFactory, user, password);
            return (Connection) cm.allocateConnection(mcf, cri);
        } catch (ResourceException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            } else {
                throw (SQLException) new SQLException().initCause(e);
            }
        }
    }

    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    public PrintWriter getLogWriter() throws SQLException {
        try {
            return mcf.getLogWriter();
        } catch (ResourceException e) {
            throw (SQLException) new SQLException().initCause(e);
        }
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        // throw an unchecked exception here as code should not be calling this
        throw new UnsupportedOperationException("Cannot set loginTimeout on a connection factory");
    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        // throw an unchecked exception here as code should not be calling this
        throw new UnsupportedOperationException("Cannot set logWriter on a connection factory");
    }

    public Reference getReference() throws NamingException {
        if (ref == null) {
            ref = new SelfReference(this);
        }
        return ref;
    }

    public void setReference(Reference reference) {
        this.ref = reference;
    }

    private static class HandleFactory implements DissociatableConnectionHandleFactory<Connection, ConnectionHandle> {

        private final LazyAssociatableConnectionManager connectionManager;
        private final ManagedConnectionFactory managedConnectionFactory;

        HandleFactory(ConnectionManager connectionManager, ManagedConnectionFactory managedConnectionFactory) {
            if (connectionManager instanceof LazyAssociatableConnectionManager) {
                this.connectionManager = (LazyAssociatableConnectionManager)connectionManager;
            } else {
                this.connectionManager = null;
            }
            this.managedConnectionFactory = managedConnectionFactory;
        }

            public DissociatableConnectionHandle<Connection, ConnectionHandle> newHandle(ConnectionRequestInfo connectionRequestInfo) {
            return new ConnectionHandle(connectionManager, managedConnectionFactory, connectionRequestInfo);
        }

    }

    private static class SelfReference extends Reference {
		private static final long serialVersionUID = -354056139412012348L;
		private final TranqlDataSource self;
        private static final Enumeration<RefAddr> EMPTY_ENUMERATION = new Enumeration<RefAddr> () {

                    public boolean hasMoreElements() {
                return false;
            }

                    public RefAddr nextElement() {
                return null;
            }
        };

        public SelfReference(TranqlDataSource self) {
            super("");
            this.self = self;
        }

    		public String getClassName() {
            return TranqlDataSource.class.getName();
        }

    		public String getFactoryClassName() {
            return SelfObjectFactory.class.getName();
        }

    		public String getFactoryClassLocation() {
            return null;
        }

    		public RefAddr get(String s) {
            return null;
        }

    		public RefAddr get(int i) {
            return null;
        }

    		public Enumeration<RefAddr> getAll() {
            return EMPTY_ENUMERATION;
        }

    		public int size() {
            return 0;
        }

    		public void add(RefAddr refAddr) {
            throw new UnsupportedOperationException("no ref addr");
        }

    		public void add(int i, RefAddr refAddr) {
            throw new UnsupportedOperationException("no ref addr");
        }

        public TranqlDataSource getContent() {
            return self;
        }
    }

    public static class SelfObjectFactory implements ObjectFactory {

            public Object getObjectInstance(Object o, Name name, Context context, Hashtable hashtable) throws Exception {
            if (o instanceof SelfReference) {
                return ((SelfReference)o).getContent();
            }
            return null;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
}
