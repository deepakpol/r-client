package org.tukka.rengine.connector;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * Created by deepakpol on 15/12/14.
 */
public class RConnectionFactory implements PooledObjectFactory<RConnection>{

    private String hostName = "127.0.0.1";
    private int port = 6311;
    private boolean testConnection = false;

    public RConnectionFactory(){}

    public RConnectionFactory(String hostName, int port, boolean testConnection){
        this.hostName = hostName;
        this.port = port;
        this.testConnection = testConnection;
    }

    @Override
    public PooledObject<RConnection> makeObject() throws Exception {
        //TODO: Config
        return new DefaultPooledObject<>(new RConnection(hostName, port));
    }

    @Override
    public void destroyObject(PooledObject<RConnection> pooledObject) throws Exception {
        pooledObject.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<RConnection> pooledObject) {
        final RConnection connection = pooledObject.getObject();
        boolean valid = connection.isConnected();
        if(valid && testConnection){
            try {
                connection.voidEval("R.version.string");
            } catch (RserveException e) {
                valid = false;
            }
        }
        return valid;
    }

    @Override
    public void activateObject(PooledObject<RConnection> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<RConnection> pooledObject) throws Exception {

    }
}
