package org.tukka.rengine.connector;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.rosuda.REngine.Rserve.RConnection;

/**
 * Created by deepakpol on 15/12/14.
 */
public class RConnectionPool extends GenericObjectPool<RConnection> {
    public RConnectionPool(PooledObjectFactory<RConnection> factory) {
        super(factory);
    }

    public RConnectionPool(PooledObjectFactory<RConnection> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }

    public RConnectionPool(PooledObjectFactory<RConnection> factory, GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
