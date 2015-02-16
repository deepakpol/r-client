package org.tukka.rengine.connector;

import org.rosuda.REngine.Rserve.RConnection;

/**
 * Created by deepakpol on 15/12/14.
 */
public interface RConnector {

    RConnection getConnection() throws Exception;

    void releaseConnection(RConnection rConnection);
}
