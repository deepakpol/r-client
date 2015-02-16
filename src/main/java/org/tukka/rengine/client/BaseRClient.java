package org.tukka.rengine.client;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tukka.rengine.connector.RConnector;

/**
 * Base class with template implementation.
 * Extend from this class to use R
 * Instance of {@code RConnector} needs to be provided to initialize
 * @see org.tukka.rengine.connector.RConnector
 * Created by deepakpol on 16/02/15.
 */
public class BaseRClient {

    private Logger logger = LoggerFactory.getLogger(BaseRClient.class);

    private RConnector rConnector;

    /**
     * Default constructor to allow setter injection after initialization
     */
    public BaseRClient(){}
    /**
     * Provides constructor initialization
     * @param connector
     */
    public BaseRClient(RConnector connector){
        this.rConnector = connector;
    }

    private <T> T handleExecution(RServeExecution<T> execution) {
        RConnection rConnection = null;
        try {
            rConnection = rConnector.getConnection();
            return execution.execute(rConnection);
        } catch (RserveException | REXPMismatchException e) {
            logger.error("Error while executing scripts in R", e);
            try {
                logger.error("Error from R - " + rConnection.parseAndEval("geterrmessage()").asString());
            } catch (REngineException | REXPMismatchException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Error borrowing RConnection", e);
        } finally {
            if (rConnection != null) {
                rConnector.releaseConnection(rConnection);
            }
        }

        return null;
    }

    public void setConnector(RConnector connector) {
        rConnector = connector;
    }

}
