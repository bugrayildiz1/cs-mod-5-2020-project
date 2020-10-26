package com.smarthomelightingsystem.gpio;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinMode;

public class LDR {

    public static void main(String[] args){

        GpioController controller = GpioFactory.getInstance();
        GpioPinDigitalOutput pin0 = controller.provisionDigitalOutputPin(
                RaspiPin.GPIO_07, "GPIO_7");

        for (int i = 0; i < 1000; i++)
        {
            int value = 0;
            pin0.export(PinMode.DIGITAL_OUTPUT);
            pin0.setState(PinState.LOW);
            pin0.export(PinMode.DIGITAL_INPUT);

            while (pin0.getState() == PinState.LOW)
            {
                value++;
            }
            System.out.println("Value: " + value);
            System.out.println("Pin state: " + pin0.getState());
        }

    }
}
