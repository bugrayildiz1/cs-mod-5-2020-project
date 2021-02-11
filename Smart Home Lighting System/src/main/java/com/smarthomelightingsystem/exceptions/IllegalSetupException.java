package com.smarthomelightingsystem.exceptions;

public class IllegalSetupException extends SHLSException {

    public IllegalSetupException() {
        super("The provided setup is not defined or interfering!");
    }

}
