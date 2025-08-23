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
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.InvalidPropertyException;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.ResourceAllocationException;
import javax.security.auth.Subject;
import org.tranql.connector.CredentialExtractor;
import org.tranql.connector.ExceptionSorter;
import org.tranql.connector.ManagedConnectionHandle;
import org.tranql.connector.UserPasswordManagedConnectionFactory;

/**
 * Implementation of a ManagedConnectionFactory that connects to a JDBC database
 * using a generic JDBC Driver.
 *
 * @version $Revision: 800 $ $Date: 2010-11-02 13:14:25 -0700 (Tue, 02 Nov 2010) $
 */
public class JDBCDriverMCF implements UserPasswordManagedConnectionFactory, AutocommitSpecCompliant {
	private static final long serialVersionUID = 4317141492511322929L;
	private Driver driver;
    private String url;
    private String user;
    private String password;
    private ExceptionSorter exceptionSorter = new KnownSQLStateExceptionSorter();
    private boolean commitBeforeAutocommit = false;

    // Although we store the log supplied by the container, there is no way to pass
    // it to the actual Driver we are using. The value is not pushed down into the
    // DriverManager to avoid conflicts with the static value in that class.
    private PrintWriter log;

    
	/* (non-Javadoc)
	 * @see javax.resource.spi.ManagedConnectionFactory#createConnectionFactory()
	 */
    public Object createConnectionFactory() throws ResourceException {
        throw new NotSupportedException("ConnectionManager is required");
    }

	/* (non-Javadoc)
	 * @see javax.resource.spi.ManagedConnectionFactory#createConnectionFactory(ConnectionManager)
	 */
    public Object createConnectionFactory(ConnectionManager connectionManager) throws ResourceException {
        return new TranqlDataSource(this, connectionManager);
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        CredentialExtractor credentialExtractor = new CredentialExtractor(subject, connectionRequestInfo, this);
        Connection sqlConnection = getPhysicalConnection(subject, credentialExtractor);
        return new ManagedJDBCConnection(this, sqlConnection, credentialExtractor, exceptionSorter, commitBeforeAutocommit);
    }

    protected Connection getPhysicalConnection(Subject subject, CredentialExtractor credentialExtractor) throws ResourceException {
        try {
            if (!driver.acceptsURL(url)) {
                throw new ResourceAdapterInternalException("JDBC Driver cannot handle url: " + url);
            }
        } catch (SQLException e) {
            throw new ResourceAdapterInternalException("JDBC Driver rejected url: " + url);
        }

        Properties info = new Properties();
        String user = credentialExtractor.getUserName();
        if (user != null) {
            info.setProperty("user", user);
        }
        String password = credentialExtractor.getPassword();
        if (password != null) {
            info.setProperty("password", password);
        }
        try {
            return driver.connect(url, info);
        } catch (SQLException e) {
            throw new ResourceAllocationException("Unable to obtain physical connection to " + url, e);
        }
    }

    public ManagedConnection matchManagedConnections(Set set, Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        for (Iterator<Object> i = set.iterator(); i.hasNext();) {
            Object o = i.next();
            if (o instanceof ManagedConnectionHandle) {
                ManagedConnectionHandle mc = (ManagedConnectionHandle) o;
                if (mc.matches(this, subject, connectionRequestInfo)) {
                    return mc;
                }
            }
        }
        return null;
    }

    public PrintWriter getLogWriter() {
        return log;
    }

    public void setLogWriter(PrintWriter log) {
        this.log = log;
    }

    /**
     * Return the name of the Driver class
     *
     * @return the name of the Driver class
     */
    public String getDriver() {
        return driver == null ? null : driver.getClass().getName();
    }

    /**
     * Set the name of the Driver class
     *
     * @param driver the name of the Driver class
     *
     * @throws InvalidPropertyException if the class name is null or empty
     */
    public void setDriver(String driver) throws InvalidPropertyException {
        if (driver == null || driver.length() == 0) {
            throw new InvalidPropertyException("Empty driver class name");
        }
        try {
            Class<Driver> driverClass = (Class<Driver>) loadClass(driver);
            this.driver = driverClass.newInstance();
        } catch (ClassNotFoundException e) {
            throw new InvalidPropertyException("Unable to load driver class: " + driver, e);
        } catch (InstantiationException e) {
            throw new InvalidPropertyException("Unable to instantiate driver class: " + driver, e);
        } catch (IllegalAccessException e) {
            throw new InvalidPropertyException("Unable to instantiate driver class: " + driver, e);
        } catch (ClassCastException e) {
            throw new InvalidPropertyException("Class is not a "+ Driver.class.getName() + ": " + driver, e);
        }
    }

    /**
     * Return the JDBC connection URL
     *
     * @return the JDBC connection URL
     */
    public String getConnectionURL() {
        return url;
    }

    /**
     * Set the JDBC connection URL.
     * This URL is passed directly to the Driver and should contain any properties
     * required to configure the connection.
     *
     * @param url the JDBC connection URL
     * @throws javax.resource.spi.InvalidPropertyException if url missing
     */
    public void setConnectionURL(String url) throws InvalidPropertyException {
        if (url == null || url.length() == 0) {
            throw new InvalidPropertyException("Empty connection URL");
        }
        this.url = url;
    }

    /**
     * Return the user name used to establish the connection.
     *
     * @return the user name used to establish the connection
     */
    public String getUserName() {
        return user;
    }

    /**
     * Set the user name used establish the connection.
     * This value is used if no connection information is supplied by the application
     * when attempting to create a connection.
     *
     * @param user the user name used to establish the connection; may be null
     */
    public void setUserName(String user) {
        this.user = user;
    }

    /**
     * Return the password credential used to establish the connection.
     *
     * @return the password credential used to establish the connection
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password credential establish the connection.
     * This value is used if no connection information is supplied by the application
     * when attempting to create a connection.
     *
     * @param password the password credential used to establish the connection; may be null
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return whether the Driver requires a commit before enabling auto-commit.
     *
     * @return TRUE if the Driver requires a commit before enabling auto-commit.
     */
    public Boolean isCommitBeforeAutocommit() {
        return Boolean.valueOf(commitBeforeAutocommit);
    }

    /**
     * Set whether the Driver requires a commit before enabling auto-commit.
     * Although the JDBC specification requires any pending work to be committed
     * when auto-commit is enabled, not all drivers respect this. Setting this property
     * to true will cause the connector to explicitly commit the transaction before
     * re-enabling auto-commit; for compliant drivers this may result in two commits.
     *
     * @param commitBeforeAutocommit set TRUE if a commit should be performed before enabling auto-commit
     */
    public void setCommitBeforeAutocommit(Boolean commitBeforeAutocommit) {
        this.commitBeforeAutocommit = commitBeforeAutocommit != null && commitBeforeAutocommit.booleanValue();
    }

    /**
     * Return the name of the ExceptionSorter implementation used to classify Exceptions
     * raised by the Driver.
     *
     * @return the class name of the ExceptionSorter being used
     */
    public String getExceptionSorterClass() {
        return exceptionSorter.getClass().getName();
    }

    /**
     * Set the name of the ExceptionSorter implementation so use.
     *
     * @param className the class name of an ExceptionSorter to use
     *
     * @throws InvalidPropertyException if the class name is null or empty
     */
    public void setExceptionSorterClass(String className) throws InvalidPropertyException {
        if (className == null || className.length() == 0) {
            throw new InvalidPropertyException("Empty class name");
        }
        try {
            Class<ExceptionSorter> clazz = (Class<ExceptionSorter>) loadClass(className);
            exceptionSorter = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new InvalidPropertyException("Unable to load class: " + className, e);
        } catch (IllegalAccessException e) {
            throw new InvalidPropertyException("Unable to instantiate class: " + className, e);
        } catch (InstantiationException e) {
            throw new InvalidPropertyException("Unable to instantiate class: " + className, e);
        } catch (ClassCastException e) {
            throw new InvalidPropertyException("Class is not a "+ ExceptionSorter.class.getName() + ": " + driver, e);
        }
    }

    /**
     * Equality is define in terms of the url property.
     *
     */
    @Override
	public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JDBCDriverMCF) {
            JDBCDriverMCF other = (JDBCDriverMCF) obj;
            return this.url == other.url || this.url != null && this.url.equals(other.url);
        }
        return false;
    }

    @Override
	public int hashCode() {
        return url == null ? 0 : url.hashCode();
    }

    @Override
	public String toString() {
        return "JDBCDriverMCF[" + user + "@" + url + "]";
    }

    private Class<?> loadClass(String name) throws ClassNotFoundException {
        // first try the TCL, then the classloader that defined us
        ClassLoader cl = getContextClassLoader();
        if (cl != null) {
            try {
                return cl.loadClass(name);
            } catch (ClassNotFoundException e) {
                // ignore this
            }
        }
        return Class.forName(name);
    }

    private ClassLoader getContextClassLoader() {
        return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
            public ClassLoader run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException e) {
                    return null;
                }
            }
        });
    }
}
