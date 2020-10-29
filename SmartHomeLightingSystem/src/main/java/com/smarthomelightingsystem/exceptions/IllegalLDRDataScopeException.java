package com.smarthomelightingsystem.exceptions;

@SuppressWarnings("serial")
public class IllegalLDRDataScopeException extends SHLSException {

    public IllegalLDRDataScopeException() {
        super("The provided scope of the LDR Data is not defined!");
    }
}
