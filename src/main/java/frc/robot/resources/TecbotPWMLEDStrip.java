// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.resources;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class TecbotPWMLEDStrip {

    AddressableLED led;
    AddressableLEDBuffer ledBuffer;

    // FOR RAINBOW AND SOLID CYCLE
    int firstPixelData;

    // FOR FIRE ANIMATION
    int cooling, sparking, cooldown, heat[];

    /**
     * Creates a new Addressable LED strip connected through PWM.
     * 
     * @param port   PWM port for the strip
     * @param length Amount of LEDs in the strip
     */
    public TecbotPWMLEDStrip(int port, int length) {
        led = new AddressableLED(port);
        led.setLength(length);
        ledBuffer = new AddressableLEDBuffer(length);

        led.setData(ledBuffer);
        led.start();

        // FOR RAINBOW AND SOLID CYCLE
        firstPixelData = 0;

        // FOR FIRE ANIMATION
        heat = new int[length];
        for (int i = 0; i < heat.length; i++) {
            heat[i] = 255;
        }

    }

    /**
     * Turns off all lights in the LED strip. Calls for this method should
     * preferrably be instantaneous.
     */

    public void allLedsOff() {
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setHSV(i, 0, 0, 0);
        }
        led.setData(ledBuffer);
    }

    /**
     * Sets a {@link TecbotLEDStrip} to a single solid color.
     * Please make sure that the parameters are in the following range:
     * 
     * @param hue        The HUE of the color 0-180
     * @param saturation The SATURATION of the color 0-255
     * @param value      The VALUE of the color 0-255
     */
    public void setSolidHSV(int hue, int saturation, int value) {

        hue = (int) Math.clamp(hue, 0, 180);
        saturation = (int) Math.clamp(saturation, 0, 255);
        value = (int) Math.clamp(value, 0, 255);

        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setHSV(i, hue, saturation, value);
        }
        led.setData(ledBuffer);
    }

    /**
     * Sets the LED strip to a rainbow cycle animation.
     */

    public void setRainbowCycle() {
        // For every pixel
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final var hue = (firstPixelData + (i * 180 / ledBuffer.getLength())) % 180;
            // Set the value
            ledBuffer.setHSV(i, hue, 255, 255);
        }
        // Increase by to make the rainbow "move"
        firstPixelData += 3;
        // Check bounds
        firstPixelData %= 180;
        led.setData(ledBuffer);
    }

    /**
     * Starts a fire animation of a color given its hue and saturation.
     * 
     * @param hue        hue of the color
     * @param saturation saturation of the color
     * @param cooling    How much the fire cools down. The lower the value, the more
     *                   intense the fire is. Recommended values: 10 for dim fire, 0
     *                   for intense fire.
     * @param sparking   How many sparks are randomly generated. The larger the
     *                   value, the more sparks are created. Recommended values: 10
     *                   for dim fire, 210 for intense fire.
     */
    public void setBasicFire(int hue, int saturation, int cooling, int sparking) {
        // Step 0. Check for mistakes from 8th layer and ensure parameters are alright
        hue = (int) Math.clamp(hue, 0, 180);
        saturation = (int) Math.clamp(saturation, 0, 255);
        cooling = (int) Math.clamp(cooling, 0, 255);
        sparking = (int) Math.clamp(sparking, 0, 255);

        // Step 1. Cool down every cell a little
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            cooldown = Math.randomInt(0, ((cooling * 10) / ledBuffer.getLength()) + 2);
            if (cooldown > heat[i] || heat[i] > 255) {
                heat[i] %= 255;
            } else {
                heat[i] -= cooldown;
            }
        }

        // Step 2. Heat from each cell drifts 'up' and diffuses a little
        for (int k = ledBuffer.getLength() - 1; k >= 2; k--) {
            heat[k] = (heat[k - 1] + heat[k - 2] + heat[k - 2]) / 3;
        }

        heat[1] = (heat[1] + 2 * heat[0]) / 3;

        // Step 3. Randomly ignite new 'sparks' near the bottom
        if (Math.randomInt(0, 255) < sparking) {
            int y = Math.randomInt(0, 7);

            heat[y] += Math.randomInt(160, 255);
        }

        // Step 4. Convert heat to LED colors
        for (int j = 0; j < ledBuffer.getLength(); j++) {
            ledBuffer.setHSV(j, hue, saturation, heat[j]);
        }

        led.setData(ledBuffer);
    }

    /**
     * Starts a fire animation of a color given its hue, varying the fire's
     * intensity based on the value of an input within in a range. The higher the
     * input value within the range, the more intense the fire is i.e. in a scale
     * from 1 to 5, an input of 5 will produce a very intense fire while an input of
     * 1 will make the fire very dim.
     * 
     * @param hue      Hue of the fire's color.
     * @param input    Input.
     * @param min      The input's range minimum value.
     * @param max      The input's range maximum value.
     * @param length   Length of the fire in natural numbers 1..n. (Should
     *                 preferrably be equal to the length of the LED strip).
     * @param offset   LED offset of the animation in natural numbers 0..n.
     * @param inverted Should the animation be upside down? (i.e. is the LED strip
     *                 oriented upside down?).
     */
    public void setFireWithVariableIntensity(int hue, double input, double min, int max, int length, int offset,
            boolean inverted) {
        // Step 0. Check for 8th layer mistakes and ensures parameters are alright
        hue = (int) Math.clamp(hue, 0, 180);
        double absoluteMagnitude = Math.clamp(input / (max - min), 0, 1);
        cooling = 255 - (int) (absoluteMagnitude * 255);

        // Change the coefficent of absoluteMagnitude to 100 for 100% flame intensity
        // when full joystick power. Turn down for less intensity in full joystick
        // power.
        sparking = (int) (absoluteMagnitude * 80);

        // Step 1. Cool down every cell a little
        // The second parameter in cooldown definition is changeable

        for (int i = 0; i < length; i++) {
            cooldown = Math.randomInt(0, ((cooling * 10) / length));
            if (cooldown > heat[i] || heat[i] > 255) {
                heat[i] %= 255;
            } else {
                heat[i] -= cooldown;
            }
        }

        // Step 2. Heat from each cell drifts 'up' and diffuses a little
        // The diffusing is also somewhat arbitrary and changeable

        for (int k = length - 1; k >= 2; k--) {
            heat[k] = (heat[k - 1] + 2 * heat[k - 2]) / 3;
        }
        heat[1] = (heat[1] + 2 * heat[0]) / 3;

        // Step 3. Randomly ignite new 'sparks' near the bottom
        // randomLed picks one in the bottom to be a new spark. Change the second
        // parameter of its definition to increase or decrease the length of the
        // 'bottom'.
        // Currently, the 'bottom' is a fifth of the strip.

        // sparkIntensity non-surprisingly defines the intensity of the new spark
        // created in the randomLed
        // The clamp prevents it from going over, because if the value of randomLed is
        // something like 258, then the displayed intensity will be 258%255 = 3

        int randomLed = Math.randomInt(0, length / 5);
        int sparkIntensity = Math.randomInt(155 + sparking, 255);
        heat[randomLed] = (int) Math.clamp(heat[randomLed] + sparkIntensity, 0, 255);

        // Step 4. Convert heat to LED colors
        // Write parameters to buffer. The saturation parameter is changeable.

        for (int j = 0; j < length; j++) {
            if (!inverted)
                ledBuffer.setHSV(j + offset, hue, 255 - (heat[j] / 3), heat[j]);
            else {
                // System.out.println(offset + length - 1 - j);
                ledBuffer.setHSV(offset + length - 1 - j, hue, 255 - (heat[j] / 3), heat[j]);
            }
        }

        led.setData(ledBuffer);
    }

    public int getLength() {
        return ledBuffer.getLength();
    }
}
