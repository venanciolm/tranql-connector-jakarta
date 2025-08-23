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

import javax.sql.XADataSource;
import org.tranql.connector.NoExceptionsAreFatalSorter;

/**
 * @version $Rev: 873 $ $Date: 2013-04-05 11:58:17 -0700 (Fri, 05 Apr 2013) $
 */
public class PSCachedXADataSourceWrapper extends AbstractPSCachedXADataSourceMCF<XADataSource> {

	private static final long serialVersionUID = 7337002583527424790L;

	private String userName;
    private String password;

    public PSCachedXADataSourceWrapper(XADataSource xaDataSource) {
        super(xaDataSource, new NoExceptionsAreFatalSorter()
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
