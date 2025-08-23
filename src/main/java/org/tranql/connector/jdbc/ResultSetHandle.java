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
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

/**
 *
 *
 * @version $Revision: 861 $ $Date: 2012-01-10 16:37:49 -0800 (Tue, 10 Jan 2012) $
 */
public class ResultSetHandle implements ResultSet {
    protected final StatementHandle<?> s;
    protected final ResultSet rs;

    public ResultSetHandle(StatementHandle<?> s, ResultSet rs) {
        this.s = s;
        this.rs = rs;
    }

    private void connectionError(SQLException e) {
        ((ConnectionHandle) s.getConnection()).connectionError(e);
    }

    public Statement getStatement() throws SQLException {
        return s;
    }

    public boolean absolute(int row) throws SQLException {
        try {
            return rs.absolute(row);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void afterLast() throws SQLException {
        try {
            rs.afterLast();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void beforeFirst() throws SQLException {
        try {
            rs.beforeFirst();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void cancelRowUpdates() throws SQLException {
        try {
            rs.cancelRowUpdates();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void clearWarnings() throws SQLException {
        try {
            rs.clearWarnings();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void close() throws SQLException {
        try {
            rs.close();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void deleteRow() throws SQLException {
        try {
            rs.deleteRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int findColumn(String columnName) throws SQLException {
        try {
            return rs.findColumn(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean first() throws SQLException {
        try {
            return rs.first();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Array getArray(String colName) throws SQLException {
        try {
            return rs.getArray(colName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Array getArray(int i) throws SQLException {
        try {
            return rs.getArray(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        try {
            return rs.getAsciiStream(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public InputStream getAsciiStream(String columnName) throws SQLException {
        try {
            return rs.getAsciiStream(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        try {
            return rs.getBigDecimal(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Deprecated
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        try {
            return rs.getBigDecimal(columnIndex, scale);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        try {
            return rs.getBigDecimal(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Deprecated
    public BigDecimal getBigDecimal(String columnName, int scale) throws SQLException {
        try {
            return rs.getBigDecimal(columnName, scale);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        try {
            return rs.getBinaryStream(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public InputStream getBinaryStream(String columnName) throws SQLException {
        try {
            return rs.getBinaryStream(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Blob getBlob(String colName) throws SQLException {
        try {
            return rs.getBlob(colName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Blob getBlob(int i) throws SQLException {
        try {
            return rs.getBlob(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        try {
            return rs.getBoolean(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean getBoolean(String columnName) throws SQLException {
        try {
            return rs.getBoolean(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public byte getByte(int columnIndex) throws SQLException {
        try {
            return rs.getByte(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public byte getByte(String columnName) throws SQLException {
        try {
            return rs.getByte(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        try {
            return rs.getBytes(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public byte[] getBytes(String columnName) throws SQLException {
        try {
            return rs.getBytes(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        try {
            return rs.getCharacterStream(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Reader getCharacterStream(String columnName) throws SQLException {
        try {
            return rs.getCharacterStream(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Clob getClob(String colName) throws SQLException {
        try {
            return rs.getClob(colName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Clob getClob(int i) throws SQLException {
        try {
            return rs.getClob(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getConcurrency() throws SQLException {
        try {
            return rs.getConcurrency();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public String getCursorName() throws SQLException {
        try {
            return rs.getCursorName();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Date getDate(int columnIndex) throws SQLException {
        try {
            return rs.getDate(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        try {
            return rs.getDate(columnIndex, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Date getDate(String columnName) throws SQLException {
        try {
            return rs.getDate(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Date getDate(String columnName, Calendar cal) throws SQLException {
        try {
            return rs.getDate(columnName, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public double getDouble(int columnIndex) throws SQLException {
        try {
            return rs.getDouble(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public double getDouble(String columnName) throws SQLException {
        try {
            return rs.getDouble(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getFetchDirection() throws SQLException {
        try {
            return rs.getFetchDirection();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getFetchSize() throws SQLException {
        try {
            return rs.getFetchSize();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public float getFloat(int columnIndex) throws SQLException {
        try {
            return rs.getFloat(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public float getFloat(String columnName) throws SQLException {
        try {
            return rs.getFloat(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getInt(int columnIndex) throws SQLException {
        try {
            return rs.getInt(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getInt(String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public long getLong(int columnIndex) throws SQLException {
        try {
            return rs.getLong(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public long getLong(String columnName) throws SQLException {
        try {
            return rs.getLong(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        try {
            return rs.getMetaData();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.ResultSet#getObject(java.lang.String, java.util.Map)
	 */
	public Object getObject(String colName, Map<String, Class<?>> map) throws SQLException {
        try {
            return rs.getObject(colName, map);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Object getObject(int columnIndex) throws SQLException {
        try {
            return rs.getObject(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Object getObject(String columnName) throws SQLException {
        try {
            return rs.getObject(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

	/* (non-Javadoc)
	 * @see java.sql.ResultSet#getObject(int, java.util.Map)
	 */
	public Object getObject(int i, Map<String, Class<?>> map) throws SQLException {
        try {
            return rs.getObject(i, map);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Ref getRef(String colName) throws SQLException {
        try {
            return rs.getRef(colName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Ref getRef(int i) throws SQLException {
        try {
            return rs.getRef(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getRow() throws SQLException {
        try {
            return rs.getRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public short getShort(int columnIndex) throws SQLException {
        try {
            return rs.getShort(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public short getShort(String columnName) throws SQLException {
        try {
            return rs.getShort(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public String getString(int columnIndex) throws SQLException {
        try {
            return rs.getString(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public String getString(String columnName) throws SQLException {
        try {
            return rs.getString(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Time getTime(int columnIndex) throws SQLException {
        try {
            return rs.getTime(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        try {
            return rs.getTime(columnIndex, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Time getTime(String columnName) throws SQLException {
        try {
            return rs.getTime(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Time getTime(String columnName, Calendar cal) throws SQLException {
        try {
            return rs.getTime(columnName, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        try {
            return rs.getTimestamp(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        try {
            return rs.getTimestamp(columnIndex, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(String columnName) throws SQLException {
        try {
            return rs.getTimestamp(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public Timestamp getTimestamp(String columnName, Calendar cal) throws SQLException {
        try {
            return rs.getTimestamp(columnName, cal);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public int getType() throws SQLException {
        try {
            return rs.getType();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Deprecated
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        try {
            return rs.getUnicodeStream(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Deprecated
    public InputStream getUnicodeStream(String columnName) throws SQLException {
        try {
            return rs.getUnicodeStream(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public URL getURL(int columnIndex) throws SQLException {
        try {
            return rs.getURL(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public URL getURL(String columnName) throws SQLException {
        try {
            return rs.getURL(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public SQLWarning getWarnings() throws SQLException {
        try {
            return rs.getWarnings();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void insertRow() throws SQLException {
        try {
            rs.insertRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean isAfterLast() throws SQLException {
        try {
            return rs.isAfterLast();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean isBeforeFirst() throws SQLException {
        try {
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean isFirst() throws SQLException {
        try {
            return rs.isFirst();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean isLast() throws SQLException {
        try {
            return rs.isLast();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean last() throws SQLException {
        try {
            return rs.last();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void moveToCurrentRow() throws SQLException {
        try {
            rs.moveToCurrentRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void moveToInsertRow() throws SQLException {
        try {
            rs.moveToInsertRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean next() throws SQLException {
        try {
            return rs.next();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean previous() throws SQLException {
        try {
            return rs.previous();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void refreshRow() throws SQLException {
        try {
            rs.refreshRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean relative(int rows) throws SQLException {
        try {
            return rs.relative(rows);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean rowDeleted() throws SQLException {
        try {
            return rs.rowDeleted();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean rowInserted() throws SQLException {
        try {
            return rs.rowInserted();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean rowUpdated() throws SQLException {
        try {
            return rs.rowUpdated();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setFetchDirection(int direction) throws SQLException {
        try {
            rs.setFetchDirection(direction);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void setFetchSize(int rows) throws SQLException {
        try {
            rs.setFetchSize(rows);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        try {
            rs.updateArray(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateArray(String columnName, Array x) throws SQLException {
        try {
            rs.updateArray(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public RowId getRowId(int i) throws SQLException {
        try {
            return rs.getRowId(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public RowId getRowId(String s) throws SQLException {
        try {
            return rs.getRowId(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateRowId(int i, RowId rowId) throws SQLException {
        try {
            rs.updateRowId(i, rowId);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateRowId(String s, RowId rowId) throws SQLException {
        try {
            rs.updateRowId(s, rowId);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            return rs.getHoldability();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            return rs.isClosed();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNString(int i, String s) throws SQLException {
        try {
            rs.updateNString(i, s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNString(String s, String s1) throws SQLException {
        try {
            rs.updateNString(s, s1);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(int i, NClob nClob) throws SQLException {
        try {
            rs.updateNClob(i, nClob);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(String s, NClob nClob) throws SQLException {
        try {
            rs.updateNClob(s, nClob);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public NClob getNClob(int i) throws SQLException {
        try {
            return rs.getNClob(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public NClob getNClob(String s) throws SQLException {
        try {
            return rs.getNClob(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(int i) throws SQLException {
        try {
            return rs.getSQLXML(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(String s) throws SQLException {
        try {
            return rs.getSQLXML(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {
        try {
            rs.updateSQLXML(i, sqlxml);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {
        try {
            rs.updateSQLXML(s, sqlxml);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public String getNString(int i) throws SQLException {
        try {
            return rs.getNString(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public String getNString(String s) throws SQLException {
        try {
            return rs.getNString(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(int i) throws SQLException {
        try {
            return rs.getNCharacterStream(i);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(String s) throws SQLException {
        try {
            return rs.getNCharacterStream(s);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {
        try {
            rs.updateNCharacterStream(i, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {
        try {
            rs.updateNCharacterStream(s, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateAsciiStream(i, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateBinaryStream(i, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {
        try {
            rs.updateCharacterStream(i, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(String s, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateAsciiStream(s, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(String s, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateBinaryStream(s, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {
        try {
            rs.updateCharacterStream(s, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBlob(int i, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateBlob(i, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBlob(String s, InputStream inputStream, long l) throws SQLException {
        try {
            rs.updateBlob(s, inputStream, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateClob(int i, Reader reader, long l) throws SQLException {
        try {
            rs.updateClob(i, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateClob(String s, Reader reader, long l) throws SQLException {
        try {
            rs.updateClob(s, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(int i, Reader reader, long l) throws SQLException {
        try {
            rs.updateNClob(i, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(String s, Reader reader, long l) throws SQLException {
        try {
            rs.updateNClob(s, reader, l);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(int i, Reader reader) throws SQLException {
        try {
            rs.updateNCharacterStream(i, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(String s, Reader reader) throws SQLException {
        try {
            rs.updateNCharacterStream(s, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {
        try {
            rs.updateAsciiStream(i, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {
        try {
            rs.updateBinaryStream(i, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(int i, Reader reader) throws SQLException {
        try {
            rs.updateCharacterStream(i, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(String s, InputStream inputStream) throws SQLException {
        try {
            rs.updateAsciiStream(s, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(String s, InputStream inputStream) throws SQLException {
        try {
            rs.updateBinaryStream(s, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(String s, Reader reader) throws SQLException {
        try {
            rs.updateCharacterStream(s, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBlob(int i, InputStream inputStream) throws SQLException {
        try {
            rs.updateBlob(i, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateBlob(String s, InputStream inputStream) throws SQLException {
        try {
            rs.updateBlob(s, inputStream);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateClob(int i, Reader reader) throws SQLException {
        try {
            rs.updateClob(i, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateClob(String s, Reader reader) throws SQLException {
        try {
            rs.updateClob(s, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(int i, Reader reader) throws SQLException {
        try {
            rs.updateNClob(i, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    @Override
    public void updateNClob(String s, Reader reader) throws SQLException {
        try {
            rs.updateNClob(s, reader);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            rs.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateAsciiStream(String columnName, InputStream x, int length) throws SQLException {
        try {
            rs.updateAsciiStream(columnName, x, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        try {
            rs.updateBigDecimal(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBigDecimal(String columnName, BigDecimal x) throws SQLException {
        try {
            rs.updateBigDecimal(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            rs.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBinaryStream(String columnName, InputStream x, int length) throws SQLException {
        try {
            rs.updateBinaryStream(columnName, x, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        try {
            rs.updateBlob(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBlob(String columnName, Blob x) throws SQLException {
        try {
            rs.updateBlob(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        try {
            rs.updateBoolean(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBoolean(String columnName, boolean x) throws SQLException {
        try {
            rs.updateBoolean(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        try {
            rs.updateByte(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateByte(String columnName, byte x) throws SQLException {
        try {
            rs.updateByte(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBytes(int columnIndex, byte x[]) throws SQLException {
        try {
            rs.updateBytes(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateBytes(String columnName, byte x[]) throws SQLException {
        try {
            rs.updateBytes(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        try {
            rs.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateCharacterStream(String columnName, Reader reader, int length) throws SQLException {
        try {
            rs.updateCharacterStream(columnName, reader, length);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        try {
            rs.updateClob(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateClob(String columnName, Clob x) throws SQLException {
        try {
            rs.updateClob(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        try {
            rs.updateDate(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateDate(String columnName, Date x) throws SQLException {
        try {
            rs.updateDate(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        try {
            rs.updateDouble(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateDouble(String columnName, double x) throws SQLException {
        try {
            rs.updateDouble(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        try {
            rs.updateFloat(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateFloat(String columnName, float x) throws SQLException {
        try {
            rs.updateFloat(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        try {
            rs.updateInt(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateInt(String columnName, int x) throws SQLException {
        try {
            rs.updateInt(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        try {
            rs.updateLong(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateLong(String columnName, long x) throws SQLException {
        try {
            rs.updateLong(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateNull(int columnIndex) throws SQLException {
        try {
            rs.updateNull(columnIndex);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateNull(String columnName) throws SQLException {
        try {
            rs.updateNull(columnName);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        try {
            rs.updateObject(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
        try {
            rs.updateObject(columnIndex, x, scale);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateObject(String columnName, Object x) throws SQLException {
        try {
            rs.updateObject(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateObject(String columnName, Object x, int scale)
            throws SQLException {
        try {
            rs.updateObject(columnName, x, scale);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        try {
            rs.updateRef(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateRef(String columnName, Ref x) throws SQLException {
        try {
            rs.updateRef(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateRow() throws SQLException {
        try {
            rs.updateRow();
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        try {
            rs.updateShort(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateShort(String columnName, short x) throws SQLException {
        try {
            rs.updateShort(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        try {
            rs.updateString(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateString(String columnName, String x) throws SQLException {
        try {
            rs.updateString(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        try {
            rs.updateTime(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateTime(String columnName, Time x) throws SQLException {
        try {
            rs.updateTime(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        try {
            rs.updateTimestamp(columnIndex, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public void updateTimestamp(String columnName, Timestamp x) throws SQLException {
        try {
            rs.updateTimestamp(columnName, x);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }

    public boolean wasNull() throws SQLException {
        try {
            return rs.wasNull();
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
        try {
            return rs.unwrap(tClass);
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
        try {
            return rs.isWrapperFor(aClass);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
    }
    
    // Adding!!

	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        try {
            return rs.getObject(columnIndex, type);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        try {
            return rs.getObject(columnLabel, type);
        } catch (SQLException e) {
            connectionError(e);
            throw e;
        }
	}
    
    
}
