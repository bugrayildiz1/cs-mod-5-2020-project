package com.smarthomelightingsystem.exceptions;

public class IllegalLDRDataScopeException extends SHLSException {

    public IllegalLDRDataScopeException() {
        super("The provided scope of the LDR Data is not defined!");
    }

}
