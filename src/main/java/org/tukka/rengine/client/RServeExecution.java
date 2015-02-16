package org.tukka.rengine.client;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

public interface RServeExecution<T> {
    T execute(RConnection rConnection) throws REXPMismatchException, REngineException;
}