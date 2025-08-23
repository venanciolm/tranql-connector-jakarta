/*
 * Copyright (c) 2004 - 2013, Tranql project contributors
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
import java.sql.SQLException;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.security.auth.Subject;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;

public abstract class AbstractPSCachedXADataSourceMCF<T extends XADataSource> extends AbstractXADataSourceMCF<T> {

    private int preparedStatementCacheSize = 0;

    public AbstractPSCachedXADataSourceMCF(T xaDataSource,
			ExceptionSorter exceptionSorter) {
		super(xaDataSource, exceptionSorter);
	}

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        CredentialExtractor credentialExtractor = new CredentialExtractor(subject, connectionRequestInfo, this);

        XAConnection sqlConnection = getPhysicalConnection(credentialExtractor);
        try {
            ManagedXAConnection mxac = null;
            if (preparedStatementCacheSize > 0) {
                Connection pc = new ConnectionWrapper(sqlConnection.getConnection(), preparedStatementCacheSize);
                mxac = new ManagedXAConnection(this, sqlConnection, sqlConnection.getXAResource(), pc, credentialExtractor, exceptionSorter);
            } else {
                mxac = new ManagedXAConnection(this, sqlConnection, credentialExtractor, exceptionSorter);
            }
            return mxac;
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("Could not set up ManagedXAConnection", e);
        }
    }

    /*
     * 
     */
    public Integer getPreparedStatementCacheSize() {
        return preparedStatementCacheSize;
    }

    public void setPreparedStatementCacheSize(Integer preparedStatementCacheSize) {
        this.preparedStatementCacheSize = preparedStatementCacheSize;
    }


	
}
