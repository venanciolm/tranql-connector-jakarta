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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 *
 *
 * @version $Revision: 861 $ $Date: 2012-01-10 16:37:49 -0800 (Tue, 10 Jan 2012) $
 */
public class CallableStatementHandle<T extends CallableStatement> extends PreparedStatementHandle<T> implements CallableStatement {
    public CallableStatementHandle(ConnectionHandle c, T s) {
        super(c, s);
    }

    public Array getArray(int i) throws SQLException {
        try {
            return s.getArray(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Array getArray(String parameterName) throws SQLException {
        try {
            return s.getArray(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        try {
            return s.getBigDecimal(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.CallableStatement#getBigDecimal(int, int)
	 */
    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        try {
            return s.getBigDecimal(parameterIndex, scale);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        try {
            return s.getBigDecimal(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Blob getBlob(int i) throws SQLException {
        try {
            return s.getBlob(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Blob getBlob(String parameterName) throws SQLException {
        try {
            return s.getBlob(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean getBoolean(int parameterIndex) throws SQLException {
        try {
            return s.getBoolean(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        try {
            return s.getBoolean(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public byte getByte(int parameterIndex) throws SQLException {
        try {
            return s.getByte(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public byte getByte(String parameterName) throws SQLException {
        try {
            return s.getByte(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
        try {
            return s.getBytes(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        try {
            return s.getBytes(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Clob getClob(int i) throws SQLException {
        try {
            return s.getClob(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Clob getClob(String parameterName) throws SQLException {
        try {
            return s.getClob(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Date getDate(int parameterIndex) throws SQLException {
        try {
            return s.getDate(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        try {
            return s.getDate(parameterIndex, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Date getDate(String parameterName) throws SQLException {
        try {
            return s.getDate(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        try {
            return s.getDate(parameterName, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public double getDouble(int parameterIndex) throws SQLException {
        try {
            return s.getDouble(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public double getDouble(String parameterName) throws SQLException {
        try {
            return s.getDouble(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public float getFloat(int parameterIndex) throws SQLException {
        try {
            return s.getFloat(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public float getFloat(String parameterName) throws SQLException {
        try {
            return s.getFloat(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getInt(int parameterIndex) throws SQLException {
        try {
            return s.getInt(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getInt(String parameterName) throws SQLException {
        try {
            return s.getInt(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public long getLong(int parameterIndex) throws SQLException {
        try {
            return s.getLong(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public long getLong(String parameterName) throws SQLException {
        try {
            return s.getLong(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        try {
            return s.getObject(i, map);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Object getObject(int parameterIndex) throws SQLException {
        try {
            return s.getObject(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Object getObject(String parameterName) throws SQLException {
        try {
            return s.getObject(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        try {
            return s.getObject(parameterName, map);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Ref getRef(int i) throws SQLException {
        try {
            return s.getRef(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Ref getRef(String parameterName) throws SQLException {
        try {
            return s.getRef(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public short getShort(int parameterIndex) throws SQLException {
        try {
            return s.getShort(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public short getShort(String parameterName) throws SQLException {
        try {
            return s.getShort(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getString(int parameterIndex) throws SQLException {
        try {
            return s.getString(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getString(String parameterName) throws SQLException {
        try {
            return s.getString(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Time getTime(int parameterIndex) throws SQLException {
        try {
            return s.getTime(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        try {
            return s.getTime(parameterIndex, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Time getTime(String parameterName) throws SQLException {
        try {
            return s.getTime(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        try {
            return s.getTime(parameterName, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        try {
            return s.getTimestamp(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        try {
            return s.getTimestamp(parameterIndex, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
        try {
            return s.getTimestamp(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        try {
            return s.getTimestamp(parameterName, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public URL getURL(int parameterIndex) throws SQLException {
        try {
            return s.getURL(parameterIndex);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public URL getURL(String parameterName) throws SQLException {
        try {
            return s.getURL(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public RowId getRowId(int i) throws SQLException {
        try {
            return s.getRowId(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public RowId getRowId(String parameterName) throws SQLException {
        try {
            return s.getRowId(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setRowId(String parameterName, RowId rowId) throws SQLException {
        try {
            s.setRowId(parameterName, rowId);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNString(String parameterName, String s1) throws SQLException {
        try {
            s.setNString(parameterName, s1);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader reader, long l) throws SQLException {
        try {
            s.setNCharacterStream(parameterName, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(String parameterName, NClob nClob) throws SQLException {
        try {
            s.setNClob(parameterName, nClob);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader, long l) throws SQLException {
        try {
            s.setClob(parameterName, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream, long l) throws SQLException {
        try {
            s.setBlob(parameterName, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader, long l) throws SQLException {
        try {
            s.setNClob(parameterName, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public NClob getNClob(int i) throws SQLException {
        try {
            return s.getNClob(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public NClob getNClob(String parameterName) throws SQLException {
        try {
            return s.getNClob(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setSQLXML(String parameterName, SQLXML sqlxml) throws SQLException {
        try {
            s.setSQLXML(parameterName, sqlxml);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(int i) throws SQLException {
        try {
            return s.getSQLXML(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(String parameterName) throws SQLException {
        try {
            return s.getSQLXML(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public String getNString(int i) throws SQLException {
        try {
            return s.getNString(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public String getNString(String parameterName) throws SQLException {
        try {
            return s.getNString(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(int i) throws SQLException {
        try {
            return s.getNCharacterStream(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(String parameterName) throws SQLException {
        try {
            return s.getNCharacterStream(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getCharacterStream(int i) throws SQLException {
        try {
            return s.getCharacterStream(i);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getCharacterStream(String parameterName) throws SQLException {
        try {
            return s.getCharacterStream(parameterName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBlob(String parameterName, Blob blob) throws SQLException {
        try {
            s.setBlob(parameterName, blob);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClob(String parameterName, Clob clob) throws SQLException {
        try {
            s.setClob(parameterName, clob);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream inputStream, long l) throws SQLException {
        try {
            s.setAsciiStream(parameterName, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream inputStream, long l) throws SQLException {
        try {
            s.setBinaryStream(parameterName, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader, long l) throws SQLException {
        try {
            s.setCharacterStream(parameterName, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setAsciiStream(String parameterName, InputStream inputStream) throws SQLException {
        try {
            s.setAsciiStream(parameterName, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBinaryStream(String parameterName, InputStream inputStream) throws SQLException {
        try {
            s.setBinaryStream(parameterName, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        try {
            s.setCharacterStream(parameterName, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(String parameterName, Reader reader) throws SQLException {
        try {
            s.setNCharacterStream(parameterName, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClob(String parameterName, Reader reader) throws SQLException {
        try {
            s.setClob(parameterName, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        try {
            s.setBlob(parameterName, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(String parameterName, Reader reader) throws SQLException {
        try {
            s.setNClob(parameterName, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        try {
            s.registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        try {
            s.registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        try {
            s.registerOutParameter(parameterName, sqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        try {
            s.registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            s.registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void registerOutParameter(int paramIndex, int sqlType, String typeName) throws SQLException {
        try {
            s.registerOutParameter(paramIndex, sqlType, typeName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            s.setAsciiStream(parameterName, x, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        try {
            s.setBigDecimal(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        try {
            s.setBinaryStream(parameterName, x, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
        try {
            s.setBoolean(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setByte(String parameterName, byte x) throws SQLException {
        try {
            s.setByte(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBytes(String parameterName, byte x[]) throws SQLException {
        try {
            s.setBytes(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        try {
            s.setCharacterStream(parameterName, reader, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDate(String parameterName, Date x) throws SQLException {
        try {
            s.setDate(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        try {
            s.setDate(parameterName, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDouble(String parameterName, double x) throws SQLException {
        try {
            s.setDouble(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setFloat(String parameterName, float x) throws SQLException {
        try {
            s.setFloat(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setInt(String parameterName, int x) throws SQLException {
        try {
            s.setInt(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setLong(String parameterName, long x) throws SQLException {
        try {
            s.setLong(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
        try {
            s.setNull(parameterName, sqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        try {
            s.setNull(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(String parameterName, Object x) throws SQLException {
        try {
            s.setObject(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        try {
            s.setObject(parameterName, x, targetSqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        try {
            s.setObject(parameterName, x, targetSqlType, scale);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setShort(String parameterName, short x) throws SQLException {
        try {
            s.setShort(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setString(String parameterName, String x) throws SQLException {
        try {
            s.setString(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTime(String parameterName, Time x) throws SQLException {
        try {
            s.setTime(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        try {
            s.setTime(parameterName, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        try {
            s.setTimestamp(parameterName, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        try {
            s.setTimestamp(parameterName, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setURL(String parameterName, URL val) throws SQLException {
        try {
            s.setURL(parameterName, val);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean wasNull() throws SQLException {
        try {
            return s.wasNull();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }
}
