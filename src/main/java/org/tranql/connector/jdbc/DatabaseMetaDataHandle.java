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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

/**
 * Shouldn't the dbmd be obtained from the connection on each call? What if someone
 * caches one of these yet it remains attached to a physical connection that is closed..
 * meanwhile the connection handle has been associated to a different live physical connection.
 *
 * @version $Revision: 861 $ $Date: 2012-01-10 16:37:49 -0800 (Tue, 10 Jan 2012) $
 */
public class DatabaseMetaDataHandle implements DatabaseMetaData {
    private final ConnectionHandle c;
    private final DatabaseMetaData dbmd;

    public DatabaseMetaDataHandle(ConnectionHandle c, DatabaseMetaData dbmd) {
        this.c = c;
        this.dbmd = dbmd;
    }

    public Connection getConnection() throws SQLException {
        return c;
    }

    public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getBestRowIdentifier(catalog, schema, table, scope, nullable));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getCatalogs() throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getCatalogs());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getColumnPrivileges(catalog, schema, table, columnNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getCrossReference(String primaryCatalog, String primarySchema, String primaryTable, String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getCrossReference(primaryCatalog, primarySchema, primaryTable, foreignCatalog, foreignSchema, foreignTable));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getExportedKeys(catalog, schema, table));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getImportedKeys(catalog, schema, table));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getIndexInfo(catalog, schema, table, unique, approximate));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getPrimaryKeys(catalog, schema, table));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getProcedures(catalog, schemaPattern, procedureNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getSchemas() throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getSchemas());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getSuperTables(catalog, schemaPattern, tableNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getSuperTypes(catalog, schemaPattern, typeNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getTablePrivileges(catalog, schemaPattern, tableNamePattern));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String types[]) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getTables(catalog, schemaPattern, tableNamePattern, types));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getTableTypes() throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getTableTypes());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getTypeInfo() throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getTypeInfo());
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getUDTs(catalog, schemaPattern, typeNamePattern, types));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
        try {
            return new ResultSetHandle(null, dbmd.getVersionColumns(catalog, schema, table));
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean allProceduresAreCallable() throws SQLException {
        try {
            return dbmd.allProceduresAreCallable();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean allTablesAreSelectable() throws SQLException {
        try {
            return dbmd.allTablesAreSelectable();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        try {
            return dbmd.dataDefinitionCausesTransactionCommit();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        try {
            return dbmd.dataDefinitionIgnoredInTransactions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean deletesAreDetected(int type) throws SQLException {
        try {
            return dbmd.deletesAreDetected(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        try {
            return dbmd.doesMaxRowSizeIncludeBlobs();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getCatalogSeparator() throws SQLException {
        try {
            return dbmd.getCatalogSeparator();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getCatalogTerm() throws SQLException {
        try {
            return dbmd.getCatalogTerm();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getDatabaseMajorVersion() throws SQLException {
        try {
            return dbmd.getDatabaseMajorVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getDatabaseMinorVersion() throws SQLException {
        try {
            return dbmd.getDatabaseMinorVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getDatabaseProductName() throws SQLException {
        try {
            return dbmd.getDatabaseProductName();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getDatabaseProductVersion() throws SQLException {
        try {
            return dbmd.getDatabaseProductVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getDefaultTransactionIsolation() throws SQLException {
        try {
            return dbmd.getDefaultTransactionIsolation();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getDriverMajorVersion() {
        return dbmd.getDriverMajorVersion();
    }

    public int getDriverMinorVersion() {
        return dbmd.getDriverMinorVersion();
    }

    public String getDriverName() throws SQLException {
        try {
            return dbmd.getDriverName();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getDriverVersion() throws SQLException {
        try {
            return dbmd.getDriverVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getExtraNameCharacters() throws SQLException {
        try {
            return dbmd.getExtraNameCharacters();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getIdentifierQuoteString() throws SQLException {
        try {
            return dbmd.getIdentifierQuoteString();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getJDBCMajorVersion() throws SQLException {
        try {
            return dbmd.getJDBCMajorVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getJDBCMinorVersion() throws SQLException {
        try {
            return dbmd.getJDBCMinorVersion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxBinaryLiteralLength() throws SQLException {
        try {
            return dbmd.getMaxBinaryLiteralLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxCatalogNameLength() throws SQLException {
        try {
            return dbmd.getMaxCatalogNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxCharLiteralLength() throws SQLException {
        try {
            return dbmd.getMaxCharLiteralLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnNameLength() throws SQLException {
        try {
            return dbmd.getMaxColumnNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnsInGroupBy() throws SQLException {
        try {
            return dbmd.getMaxColumnsInGroupBy();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnsInIndex() throws SQLException {
        try {
            return dbmd.getMaxColumnsInIndex();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnsInOrderBy() throws SQLException {
        try {
            return dbmd.getMaxColumnsInOrderBy();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnsInSelect() throws SQLException {
        try {
            return dbmd.getMaxColumnsInSelect();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxColumnsInTable() throws SQLException {
        try {
            return dbmd.getMaxColumnsInTable();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxConnections() throws SQLException {
        try {
            return dbmd.getMaxConnections();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxCursorNameLength() throws SQLException {
        try {
            return dbmd.getMaxCursorNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxIndexLength() throws SQLException {
        try {
            return dbmd.getMaxIndexLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxProcedureNameLength() throws SQLException {
        try {
            return dbmd.getMaxProcedureNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxRowSize() throws SQLException {
        try {
            return dbmd.getMaxRowSize();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxSchemaNameLength() throws SQLException {
        try {
            return dbmd.getMaxSchemaNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxStatementLength() throws SQLException {
        try {
            return dbmd.getMaxStatementLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxStatements() throws SQLException {
        try {
            return dbmd.getMaxStatements();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxTableNameLength() throws SQLException {
        try {
            return dbmd.getMaxTableNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxTablesInSelect() throws SQLException {
        try {
            return dbmd.getMaxTablesInSelect();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getMaxUserNameLength() throws SQLException {
        try {
            return dbmd.getMaxUserNameLength();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getNumericFunctions() throws SQLException {
        try {
            return dbmd.getNumericFunctions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getProcedureTerm() throws SQLException {
        try {
            return dbmd.getProcedureTerm();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getResultSetHoldability() throws SQLException {
        try {
            return dbmd.getResultSetHoldability();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getSchemaTerm() throws SQLException {
        try {
            return dbmd.getSchemaTerm();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getSearchStringEscape() throws SQLException {
        try {
            return dbmd.getSearchStringEscape();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getSQLKeywords() throws SQLException {
        try {
            return dbmd.getSQLKeywords();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public int getSQLStateType() throws SQLException {
        try {
            return dbmd.getSQLStateType();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getStringFunctions() throws SQLException {
        try {
            return dbmd.getStringFunctions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getSystemFunctions() throws SQLException {
        try {
            return dbmd.getSystemFunctions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getTimeDateFunctions() throws SQLException {
        try {
            return dbmd.getTimeDateFunctions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getURL() throws SQLException {
        try {
            return dbmd.getURL();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public String getUserName() throws SQLException {
        try {
            return dbmd.getUserName();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean insertsAreDetected(int type) throws SQLException {
        try {
            return dbmd.insertsAreDetected(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean isCatalogAtStart() throws SQLException {
        try {
            return dbmd.isCatalogAtStart();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean isReadOnly() throws SQLException {
        try {
            return dbmd.isReadOnly();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean locatorsUpdateCopy() throws SQLException {
        try {
            return dbmd.locatorsUpdateCopy();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean nullPlusNonNullIsNull() throws SQLException {
        try {
            return dbmd.nullPlusNonNullIsNull();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean nullsAreSortedAtEnd() throws SQLException {
        try {
            return dbmd.nullsAreSortedAtEnd();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean nullsAreSortedAtStart() throws SQLException {
        try {
            return dbmd.nullsAreSortedAtStart();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean nullsAreSortedHigh() throws SQLException {
        try {
            return dbmd.nullsAreSortedHigh();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean nullsAreSortedLow() throws SQLException {
        try {
            return dbmd.nullsAreSortedLow();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean othersDeletesAreVisible(int type) throws SQLException {
        try {
            return dbmd.othersDeletesAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean othersInsertsAreVisible(int type) throws SQLException {
        try {
            return dbmd.othersInsertsAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean othersUpdatesAreVisible(int type) throws SQLException {
        try {
            return dbmd.othersUpdatesAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean ownDeletesAreVisible(int type) throws SQLException {
        try {
            return dbmd.ownDeletesAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean ownInsertsAreVisible(int type) throws SQLException {
        try {
            return dbmd.ownInsertsAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean ownUpdatesAreVisible(int type) throws SQLException {
        try {
            return dbmd.ownUpdatesAreVisible(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesLowerCaseIdentifiers() throws SQLException {
        try {
            return dbmd.storesLowerCaseIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        try {
            return dbmd.storesLowerCaseQuotedIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesMixedCaseIdentifiers() throws SQLException {
        try {
            return dbmd.storesMixedCaseIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            return dbmd.storesMixedCaseQuotedIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesUpperCaseIdentifiers() throws SQLException {
        try {
            return dbmd.storesUpperCaseIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        try {
            return dbmd.storesUpperCaseQuotedIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        try {
            return dbmd.supportsAlterTableWithAddColumn();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        try {
            return dbmd.supportsAlterTableWithDropColumn();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        try {
            return dbmd.supportsANSI92EntryLevelSQL();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsANSI92FullSQL() throws SQLException {
        try {
            return dbmd.supportsANSI92FullSQL();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        try {
            return dbmd.supportsANSI92IntermediateSQL();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsBatchUpdates() throws SQLException {
        try {
            return dbmd.supportsBatchUpdates();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        try {
            return dbmd.supportsCatalogsInDataManipulation();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        try {
            return dbmd.supportsCatalogsInIndexDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        try {
            return dbmd.supportsCatalogsInPrivilegeDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        try {
            return dbmd.supportsCatalogsInProcedureCalls();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        try {
            return dbmd.supportsCatalogsInTableDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsColumnAliasing() throws SQLException {
        try {
            return dbmd.supportsColumnAliasing();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsConvert() throws SQLException {
        try {
            return dbmd.supportsConvert();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsConvert(int fromType, int toType) throws SQLException {
        try {
            return dbmd.supportsConvert(fromType, toType);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCoreSQLGrammar() throws SQLException {
        try {
            return dbmd.supportsCoreSQLGrammar();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsCorrelatedSubqueries() throws SQLException {
        try {
            return dbmd.supportsCorrelatedSubqueries();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        try {
            return dbmd.supportsDataDefinitionAndDataManipulationTransactions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        try {
            return dbmd.supportsDataManipulationTransactionsOnly();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        try {
            return dbmd.supportsDifferentTableCorrelationNames();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsExpressionsInOrderBy() throws SQLException {
        try {
            return dbmd.supportsExpressionsInOrderBy();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsExtendedSQLGrammar() throws SQLException {
        try {
            return dbmd.supportsExtendedSQLGrammar();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsFullOuterJoins() throws SQLException {
        try {
            return dbmd.supportsFullOuterJoins();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsGetGeneratedKeys() throws SQLException {
        try {
            return dbmd.supportsGetGeneratedKeys();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsGroupBy() throws SQLException {
        try {
            return dbmd.supportsGroupBy();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsGroupByBeyondSelect() throws SQLException {
        try {
            return dbmd.supportsGroupByBeyondSelect();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsGroupByUnrelated() throws SQLException {
        try {
            return dbmd.supportsGroupByUnrelated();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        try {
            return dbmd.supportsIntegrityEnhancementFacility();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsLikeEscapeClause() throws SQLException {
        try {
            return dbmd.supportsLikeEscapeClause();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsLimitedOuterJoins() throws SQLException {
        try {
            return dbmd.supportsLimitedOuterJoins();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMinimumSQLGrammar() throws SQLException {
        try {
            return dbmd.supportsMinimumSQLGrammar();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        try {
            return dbmd.supportsMixedCaseIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        try {
            return dbmd.supportsMixedCaseQuotedIdentifiers();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMultipleOpenResults() throws SQLException {
        try {
            return dbmd.supportsMultipleOpenResults();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMultipleResultSets() throws SQLException {
        try {
            return dbmd.supportsMultipleResultSets();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsMultipleTransactions() throws SQLException {
        try {
            return dbmd.supportsMultipleTransactions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsNamedParameters() throws SQLException {
        try {
            return dbmd.supportsNamedParameters();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsNonNullableColumns() throws SQLException {
        try {
            return dbmd.supportsNonNullableColumns();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        try {
            return dbmd.supportsOpenCursorsAcrossCommit();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        try {
            return dbmd.supportsOpenCursorsAcrossRollback();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        try {
            return dbmd.supportsOpenStatementsAcrossCommit();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        try {
            return dbmd.supportsOpenStatementsAcrossRollback();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOrderByUnrelated() throws SQLException {
        try {
            return dbmd.supportsOrderByUnrelated();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsOuterJoins() throws SQLException {
        try {
            return dbmd.supportsOuterJoins();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsPositionedDelete() throws SQLException {
        try {
            return dbmd.supportsPositionedDelete();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsPositionedUpdate() throws SQLException {
        try {
            return dbmd.supportsPositionedUpdate();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
        try {
            return dbmd.supportsResultSetConcurrency(type, concurrency);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsResultSetHoldability(int holdability) throws SQLException {
        try {
            return dbmd.supportsResultSetHoldability(holdability);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsResultSetType(int type) throws SQLException {
        try {
            return dbmd.supportsResultSetType(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSavepoints() throws SQLException {
        try {
            return dbmd.supportsSavepoints();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSchemasInDataManipulation() throws SQLException {
        try {
            return dbmd.supportsSchemasInDataManipulation();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        try {
            return dbmd.supportsSchemasInIndexDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        try {
            return dbmd.supportsSchemasInPrivilegeDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        try {
            return dbmd.supportsSchemasInProcedureCalls();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        try {
            return dbmd.supportsSchemasInTableDefinitions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSelectForUpdate() throws SQLException {
        try {
            return dbmd.supportsSelectForUpdate();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsStatementPooling() throws SQLException {
        try {
            return dbmd.supportsStatementPooling();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        try {
            return dbmd.getRowIdLifetime();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public ResultSet getSchemas(String s, String s1) throws SQLException {
        try {
            return dbmd.getSchemas(s, s1);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        try {
            return dbmd.supportsStoredFunctionsUsingCallSyntax();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        try {
            return dbmd.autoCommitFailureClosesAllResultSets();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        try {
            return dbmd.getClientInfoProperties();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
        try {
            return dbmd.getFunctions(s, s1, s2);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
        try {
            return dbmd.getFunctionColumns(s, s1, s2, s3);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsStoredProcedures() throws SQLException {
        try {
            return dbmd.supportsStoredProcedures();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSubqueriesInComparisons() throws SQLException {
        try {
            return dbmd.supportsSubqueriesInComparisons();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSubqueriesInExists() throws SQLException {
        try {
            return dbmd.supportsSubqueriesInExists();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSubqueriesInIns() throws SQLException {
        try {
            return dbmd.supportsSubqueriesInIns();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        try {
            return dbmd.supportsSubqueriesInQuantifieds();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsTableCorrelationNames() throws SQLException {
        try {
            return dbmd.supportsTableCorrelationNames();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
        try {
            return dbmd.supportsTransactionIsolationLevel(level);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsTransactions() throws SQLException {
        try {
            return dbmd.supportsTransactions();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsUnion() throws SQLException {
        try {
            return dbmd.supportsUnion();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean supportsUnionAll() throws SQLException {
        try {
            return dbmd.supportsUnionAll();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean updatesAreDetected(int type) throws SQLException {
        try {
            return dbmd.updatesAreDetected(type);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean usesLocalFilePerTable() throws SQLException {
        try {
            return dbmd.usesLocalFilePerTable();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    public boolean usesLocalFiles() throws SQLException {
        try {
            return dbmd.usesLocalFiles();
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public <T> T unwrap(Class<T> tClass) throws SQLException {
        if (tClass.isInstance(this)) {
            return tClass.cast(this);
        }
        try {
            return dbmd.unwrap(tClass);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        if (aClass.isInstance(this)) {
            return true;
        }
        try {
            return dbmd.isWrapperFor(aClass);
        } catch (SQLException e) {
            c.connectionError(e);
            throw e;
        }
    }
}
