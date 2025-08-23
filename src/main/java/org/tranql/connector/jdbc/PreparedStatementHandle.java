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
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 *
 * @version $Revision: 861 $ $Date: 2012-01-10 16:37:49 -0800 (Tue, 10 Jan 2012) $
 */
public class PreparedStatementHandle<T extends PreparedStatement> extends StatementHandle<T> implements PreparedStatement {
    public PreparedStatementHandle(ConnectionHandle c, T s) {
        super(c, s);
    }

    public ResultSet executeQuery() throws SQLException {
        try {
            return new ResultSetHandle(this, s.executeQuery());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        try {
            return s.getMetaData();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        try {
            return s.getParameterMetaData();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setRowId(int i, RowId rowId) throws SQLException {
        try {
            s.setRowId(i, rowId);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNString(int i, String string) throws SQLException {
        try {
            s.setNString(i, string);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {
        try {
            s.setNCharacterStream(i, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(int i, NClob nClob) throws SQLException {
        try {
            s.setNClob(i, nClob);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {
        try {
            s.setClob(i, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {
        try {
            s.setBlob(i, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(int i, Reader reader, long l) throws SQLException {
        try {
            s.setNClob(i, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
        try {
            s.setSQLXML(i, sqlxml);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void addBatch() throws SQLException {
        try {
            s.addBatch();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void clearParameters() throws SQLException {
        try {
            s.clearParameters();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean execute() throws SQLException {
        try {
            return s.execute();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int executeUpdate() throws SQLException {
        try {
            return s.executeUpdate();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setArray(int i, Array x) throws SQLException {
        try {
            s.setArray(i, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            s.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        try {
            s.setBigDecimal(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            s.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBlob(int i, Blob x) throws SQLException {
        try {
            s.setBlob(i, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        try {
            s.setBoolean(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        try {
            s.setByte(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setBytes(int parameterIndex, byte x[]) throws SQLException {
        try {
            s.setBytes(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        try {
            s.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setClob(int i, Clob x) throws SQLException {
        try {
            s.setClob(i, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        try {
            s.setDate(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        try {
            s.setDate(parameterIndex, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        try {
            s.setDouble(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        try {
            s.setFloat(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        try {
            s.setInt(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        try {
            s.setLong(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        try {
            s.setNull(parameterIndex, sqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        try {
            s.setNull(paramIndex, sqlType, typeName);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        try {
            s.setObject(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        try {
            s.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        try {
            s.setObject(parameterIndex, x, targetSqlType, scale);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
        try {
            s.setAsciiStream(i, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
        try {
            s.setBinaryStream(i, inputStream, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {
        try {
            s.setCharacterStream(i, reader, l);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
        try {
            s.setAsciiStream(i, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
        try {
            s.setBinaryStream(i, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {
        try {
            s.setCharacterStream(i, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {
        try {
            s.setNCharacterStream(i, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setClob(int i, Reader reader) throws SQLException {
        try {
            s.setClob(i, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {
        try {
            s.setBlob(i, inputStream);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public void setNClob(int i, Reader reader) throws SQLException {
        try {
            s.setNClob(i, reader);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setRef(int i, Ref x) throws SQLException {
        try {
            s.setRef(i, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        try {
            s.setShort(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setString(int parameterIndex, String x) throws SQLException {
        try {
            s.setString(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        try {
            s.setTime(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        try {
            s.setTime(parameterIndex, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        try {
            s.setTimestamp(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        try {
            s.setTimestamp(parameterIndex, x, cal);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.PreparedStatement#setUnicodeStream(int, java.io.InputStream, int)
	 */
    @Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        try {
            s.setUnicodeStream(parameterIndex, x, length);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
	}

    public void setURL(int parameterIndex, URL x) throws SQLException {
        try {
            s.setURL(parameterIndex, x);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }
}
