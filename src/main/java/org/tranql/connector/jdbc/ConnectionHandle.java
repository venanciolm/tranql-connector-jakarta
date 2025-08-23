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
import java.util.concurrent.Executor;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.LazyAssociatableConnectionManager;
import jakarta.resource.spi.LocalTransaction;
import jakarta.resource.spi.ManagedConnectionFactory;

import org.tranql.connector.DissociatableConnectionHandle;
import org.tranql.connector.ManagedConnectionHandle;

/**
 *
 *
 * @version $Revision: 861 $ $Date: 2012-01-10 16:37:49 -0800 (Tue, 10 Jan 2012) $
 */
public class ConnectionHandle implements Connection, DissociatableConnectionHandle<Connection, ConnectionHandle> {
    protected final LazyAssociatableConnectionManager cm;
    protected final ManagedConnectionFactory mcf;
    protected final ConnectionRequestInfo cri;

    protected ManagedConnectionHandle<Connection, ConnectionHandle> mc;
    protected boolean closed;

    public ConnectionHandle(LazyAssociatableConnectionManager cm, ManagedConnectionFactory mcf, ConnectionRequestInfo cri) {
        this.cm = cm;
        this.mcf = mcf;
        this.cri = cri;
    }

    protected ManagedConnectionHandle<Connection, ConnectionHandle> getManagedConnection() throws SQLException {
        if (closed) {
            throw new SQLException("Connection has been closed");
        }

        if (mc == null && cm != null) {
            try {
                cm.associateConnection(this, mcf, cri);
            } catch (ResourceException e) {
                if (e.getCause() instanceof SQLException) {
                    throw (SQLException) e.getCause();
                } else {
                    throw (SQLException) new SQLException("Failed lazy association with ManagedConnection").initCause(e);
                }
            }
            if (mc == null) {
                throw new SQLException("Failed lazy association with ManagedConnection");
            }
        }
        assert mc != null;
        return mc;
    }

    public void setAssociation(ManagedConnectionHandle<Connection, ConnectionHandle> mc) {
        this.mc = mc;
    }

    public ManagedConnectionHandle<Connection, ConnectionHandle> getAssociation() {
        return mc;
    }

    public boolean isClosed() throws SQLException {
        return closed;
    }

    public void close() throws SQLException {
        if (closed) {
            return;
        }
        if (mc != null) {
            mc.connectionClosed(this);
        }
        closed = true;
    }

    void connectionError(SQLException e) {
        if (mc != null) {
            mc.connectionError(e);
        }
    }

    public void commit() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        Connection c = mc.getPhysicalConnection();
        if (c.getAutoCommit()) {
            return;
        }

        try {
            LocalTransaction tx = mc.getClientLocalTransaction();
            tx.commit();
            tx.begin();
        } catch (ResourceException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            } else {
                throw (SQLException) new SQLException().initCause(e);
            }
        }
    }

    public void rollback() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        Connection c = mc.getPhysicalConnection();
        if (c.getAutoCommit()) {
            return;
        }

        try {
            LocalTransaction tx = mc.getClientLocalTransaction();
            tx.rollback();
            tx.begin();
        } catch (ResourceException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            } else {
                throw (SQLException) new SQLException().initCause(e);
            }
        }
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        Connection c = mc.getPhysicalConnection();
        if (autoCommit == c.getAutoCommit()) {
            // nothing to do
            return;
        }

        try {
            LocalTransaction tx = mc.getClientLocalTransaction();
            if (autoCommit) {
                // reenabling autoCommit - JDBC spec says current transaction is committed
                tx.commit();
            } else {
                // disabling autoCommit
                tx.begin();
            }
        } catch (ResourceException e) {
            if (e.getCause() instanceof SQLException) {
                throw (SQLException) e.getCause();
            } else {
                throw (SQLException) new SQLException().initCause(e);
            }
        }
    }

    public boolean getAutoCommit() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getAutoCommit();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    protected Statement wrapStatement(Statement s) {
        return new StatementHandle<Statement>(this, s);
    }

    protected PreparedStatement wrapPreparedStatement(PreparedStatement ps) {
        return new PreparedStatementHandle<PreparedStatement>(this, ps);
    }

    protected CallableStatement wrapCallableStatement(CallableStatement cs) {
        return new CallableStatementHandle<CallableStatement>(this, cs);
    }

    protected DatabaseMetaData wrapMetaData(DatabaseMetaData dbmd) {
        return new DatabaseMetaDataHandle(this, dbmd);
    }

    public Statement createStatement() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapStatement(mc.getPhysicalConnection().createStatement());
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapStatement(mc.getPhysicalConnection().createStatement(resultSetType, resultSetConcurrency));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapStatement(mc.getPhysicalConnection().createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql, autoGeneratedKeys));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql, int columnIndexes[]) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql, columnIndexes));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql, String columnNames[]) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql, columnNames));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Clob createClob() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createClob();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Blob createBlob() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createBlob();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public NClob createNClob() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createNClob();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createSQLXML();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().isValid(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        try {
            ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
            mc.getPhysicalConnection().setClientInfo(s, s1);
        } catch (SQLClientInfoException e) {
            connectionError(e);
            throw e;
        } catch (SQLException e) {
            connectionError(e);
            throw (SQLClientInfoException)new SQLClientInfoException().initCause(e);
        }
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        try {
            ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
            mc.getPhysicalConnection().setClientInfo(properties);
        } catch (SQLClientInfoException e) {
            connectionError(e);
            throw e;
        } catch (SQLException e) {
            connectionError(e);
            throw (SQLClientInfoException)new SQLClientInfoException().initCause(e);
        }
    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getClientInfo(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getClientInfo();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createArrayOf(s, objects);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().createStruct(s, objects);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql, resultSetType, resultSetConcurrency));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapPreparedStatement(mc.getPhysicalConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapCallableStatement(mc.getPhysicalConnection().prepareCall(sql));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapCallableStatement(mc.getPhysicalConnection().prepareCall(sql, resultSetType, resultSetConcurrency));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapCallableStatement(mc.getPhysicalConnection().prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return wrapMetaData(mc.getPhysicalConnection().getMetaData());
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public String getCatalog() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getCatalog();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setCatalog(String catalog) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setCatalog(catalog);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getHoldability() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getHoldability();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setHoldability(int holdability) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setHoldability(holdability);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getTransactionIsolation() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getTransactionIsolation();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setTransactionIsolation(int level) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setTransactionIsolation(level);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getTypeMap();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setTypeMap(map);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public SQLWarning getWarnings() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getWarnings();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void clearWarnings() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().clearWarnings();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean isReadOnly() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().isReadOnly();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setReadOnly(readOnly);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Savepoint setSavepoint() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().setSavepoint();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().setSavepoint(name);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
     */
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().releaseSavepoint(savepoint);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#rollback(java.sql.Savepoint)
     */
    public void rollback(Savepoint savepoint) throws SQLException {
        // rollback(Savepoint) simply delegates as this does not interact with the transaction
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().rollback(savepoint);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#nativeSQL(java.lang.String)
     */
    public String nativeSQL(String sql) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().nativeSQL(sql);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public <T> T unwrap(Class<T> tClass) throws SQLException {
        if (tClass.isInstance(this)) {
            return tClass.cast(this);
        }
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().unwrap(tClass);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        if (aClass.isInstance(this)) {
            return true;
        }
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().isWrapperFor(aClass);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }
    
    // Adding!!!

	@Override
	public void setSchema(String schema) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
             mc.getPhysicalConnection().setSchema(schema);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}

	@Override
	public String getSchema() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getSchema();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}

	@Override
	public void abort(Executor executor) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().abort(executor);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            mc.getPhysicalConnection().setNetworkTimeout(executor, milliseconds);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
        ManagedConnectionHandle<Connection, ConnectionHandle> mc = getManagedConnection();
        try {
            return mc.getPhysicalConnection().getNetworkTimeout();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}
}
