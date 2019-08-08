package org.rif.lumino.explorer.exceptions;

public class LuminoNodeNotFoundException extends RuntimeException {

    public LuminoNodeNotFoundException(String nodeId) {
        super("Could not find node: " + nodeId);
    }

}
