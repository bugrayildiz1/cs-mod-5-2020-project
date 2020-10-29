package com.smarthomelightingsystem.gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LDRReader {

    public static int readLDR() {

        GpioController controller = GpioFactory.getInstance();
        GpioPinDigitalOutput pin = controller.provisionDigitalOutputPin(RaspiPin.GPIO_07, "GPIO_7");

        int value = 0;
        pin.export(PinMode.DIGITAL_OUTPUT);
        pin.setState(PinState.LOW);
        pin.export(PinMode.DIGITAL_INPUT);

        while (pin.getState() == PinState.LOW) { value++;}

        return value;
    }

}
