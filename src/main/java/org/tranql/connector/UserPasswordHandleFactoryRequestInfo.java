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
package org.tranql.connector;

/**
 * @version $Revision: 801 $ $Date: 2010-11-02 17:01:34 -0700 (Tue, 02 Nov 2010)
 *          $
 */
public class UserPasswordHandleFactoryRequestInfo<T, U> extends ConnectionHandleFactoryRequestInfo<T, U> {
	private final String user;
	private final String password;

	public UserPasswordHandleFactoryRequestInfo(DissociatableConnectionHandleFactory<T, U> connectionHandleFactory,
			String user, String password) {
		super(connectionHandleFactory);
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserPasswordHandleFactoryRequestInfo) {
			@SuppressWarnings("unchecked")
			UserPasswordHandleFactoryRequestInfo<T, U> other = (UserPasswordHandleFactoryRequestInfo<T, U>) obj;
			return (user == null ? other.user == null : user.equals(other.user))
					&& (password == null ? other.password == null : password.equals(other.password));
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (user == null ? 0 : user.hashCode()) ^ (password == null ? 0 : password.hashCode());
	}

	@Override
	public String toString() {
		return "DataSourceRequestInfo[" + user + "]";
	}
}
