package org.tukka.rengine.connector;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by deepakpol on 15/12/14.
 */
public class RServeConnector implements RConnector, InitializingBean {

    private static RConnectionPool rConnectionPool = new RConnectionPool(new RConnectionFactory());
    private String hostName;
    private int port;
    private boolean testConnection;
    private int maxTotal = 8;
    private int maxIdle = 8;
    private int minIdle = 0;

    public RServeConnector(String hostName, int port, boolean testConnection,
                           int maxTotalConnections, int minIdleConnections, int maxIdleConnections){
        this.hostName = hostName;
        this.port = port;
        this.testConnection = testConnection;
        this.maxTotal = maxTotalConnections;
        this.minIdle = minIdleConnections;
        this.maxIdle = maxIdleConnections;
    }
    @Override
    public RConnection getConnection() throws Exception {
        return rConnectionPool.borrowObject();
    }

    @Override
    public void releaseConnection(RConnection rConnection) {
        rConnectionPool.returnObject(rConnection);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxIdle(maxIdle);

        createPool();
    }

    private void createPool() {
        RConnectionFactory connectionFactory =
                new RConnectionFactory(hostName, port, testConnection);

        rConnectionPool = new RConnectionPool(connectionFactory);
    }
}
