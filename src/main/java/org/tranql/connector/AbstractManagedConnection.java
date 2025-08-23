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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.DissociatableManagedConnection;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;

/**
 * @version $Revision: 805 $ $Date: 2010-11-11 15:06:35 -0800 (Thu, 11 Nov 2010) $
 */
public abstract class AbstractManagedConnection<T, U> implements ManagedConnection, ManagedConnectionHandle<T, U>, DissociatableManagedConnection {
    protected final ManagedConnectionFactory mcf;
    protected final T physicalConnection;
    protected final LinkedList<DissociatableConnectionHandle> handles = new LinkedList<DissociatableConnectionHandle>();
    protected final ArrayList<ConnectionEventListener> listeners = new ArrayList<ConnectionEventListener>(2);
    private final ExceptionSorter exceptionSorter;

    protected PrintWriter log;
    protected Subject subject;
    protected ConnectionRequestInfo cri;

    public AbstractManagedConnection(ManagedConnectionFactory mcf, T physicalConnection, ExceptionSorter exceptionSorter) {
        assert exceptionSorter != null;
        this.mcf = mcf;
        this.physicalConnection = physicalConnection;
        this.exceptionSorter = exceptionSorter;
    }

    public T getPhysicalConnection() {
        return physicalConnection;
    }

    /**
     * Default implementation dissociates the connection handles.
     * Sub-classes should override to perform any cleanup needed on the physical connection.
     *
     * @throws ResourceException
     */
    public void cleanup() throws ResourceException {
        dissociateConnections();
    }

    public void destroy() throws ResourceException {
        dissociateConnections();
        listeners.clear();
        closePhysicalConnection();
    }

    protected abstract void closePhysicalConnection() throws ResourceException;

    public void associateConnection(Object o) throws ResourceException {
        assert(o instanceof DissociatableConnectionHandle) : "Attempt to associate incompatible handle: " + o;

        DissociatableConnectionHandle handle = (DissociatableConnectionHandle) o;
        ManagedConnectionHandle mc = handle.getAssociation();
        if (mc instanceof AbstractManagedConnection) {
            // handle is associated to another managed connection - diassociate it
            ((AbstractManagedConnection) mc).handles.remove(handle);
        }
        handle.setAssociation(this);
        handles.add(handle);
    }

    public void dissociateConnections() throws ResourceException {
        while (!handles.isEmpty()) {
            DissociatableConnectionHandle handle = handles.removeFirst();
            handle.setAssociation(null);
        }
    }

    public void connectionClosed(U handle) {
        ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
        event.setConnectionHandle(handle);
        //count down in case sending the event results in a handle getting removed.
        for (int i = listeners.size() - 1; i >= 0; i--) {
            ConnectionEventListener listener = listeners.get(i);
            listener.connectionClosed(event);
        }
    }

    //This needs a hook for driver specific subclasses to determine if the specific exception
    //means the physical connection is dead and no longer usable.  Sending this event will
    //result in destroying this managed connection instance.
    public void connectionError(Exception e) {
        if (exceptionSorter.isExceptionFatal(e)) {
            if (exceptionSorter.rollbackOnFatalException()) {
                attemptRollback();
            }
            unfilteredConnectionError(e);
        }
    }

    protected void attemptRollback() {
    }

    protected void unfilteredConnectionError(Exception e) {
        ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_ERROR_OCCURRED, e);
        for (int i = listeners.size() - 1; i >= 0; i--) {
            ConnectionEventListener listener = listeners.get(i);
            listener.connectionErrorOccurred(event);
        }
    }

    public void addConnectionEventListener(ConnectionEventListener connectionEventListener) {
        listeners.add(connectionEventListener);
    }

    public void removeConnectionEventListener(ConnectionEventListener connectionEventListener) {
        listeners.remove(connectionEventListener);
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return log;
    }

    public void setLogWriter(PrintWriter printWriter) throws ResourceException {
        log = printWriter;
    }

    protected void localTransactionStart(boolean isSPI) throws ResourceException {
        if (!isSPI) {
            ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_STARTED);
            for (int i = listeners.size() - 1; i >= 0; i--) {
                ConnectionEventListener listener = listeners.get(i);
                listener.localTransactionStarted(event);
            }
        }
    }

    protected void localTransactionCommit(boolean isSPI) throws ResourceException {
        if (!isSPI) {
            ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_COMMITTED);
            for (int i = listeners.size() - 1; i >= 0; i--) {
                ConnectionEventListener listener = listeners.get(i);
                listener.localTransactionCommitted(event);
            }
        }
    }

    protected void localTransactionRollback(boolean isSPI) throws ResourceException {
        if (!isSPI) {
            ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.LOCAL_TRANSACTION_ROLLEDBACK);
            for (int i = 0; i < listeners.size(); i++) {
                ConnectionEventListener listener = listeners.get(i);
                listener.localTransactionRolledback(event);
            }
        }
    }

    public Object getConnection(Subject subject, ConnectionRequestInfo connectionRequestInfo) throws ResourceException {
        DissociatableConnectionHandleFactory handleFactory = ((UserPasswordHandleFactoryRequestInfo) connectionRequestInfo).getConnectionHandleFactory();
        DissociatableConnectionHandle handle = handleFactory.newHandle(connectionRequestInfo);
        handle.setAssociation(this);
        handles.add(handle);

        this.subject = subject;
        this.cri = connectionRequestInfo;
        return handle;
    }

    protected class LocalTransactionImpl implements LocalTransaction {
        private final boolean isSPI;

        public LocalTransactionImpl(boolean isSPI) {
            this.isSPI = isSPI;
        }

        public void begin() throws ResourceException {
            localTransactionStart(isSPI);
        }

        public void commit() throws ResourceException {
            localTransactionCommit(isSPI);
        }

        public void rollback() throws ResourceException {
            localTransactionRollback(isSPI);
        }
    }
}
