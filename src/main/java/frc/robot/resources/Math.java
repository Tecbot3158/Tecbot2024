package frc.robot.resources;

import java.util.concurrent.ThreadLocalRandom;

public class Math {

    public static final double PI = 3.141592653589793238462643383279502884197;

    /**
     * @param p  input to clamp
     * @param mn minimum value of the output
     * @param mx maximum value of the output
     * @return if the input is within mn and mx, returns p. If the input is out of
     *         bounds, returns either mn or mx.
     */
    public static double clamp(double p, double mn, double mx) {
        return java.lang.Math.max(mn, java.lang.Math.min(p, mx));
    }

    /**
     * @return returns positive a mod b
     */
    public static int module(int a, int b) {
        int mod = a % b;
        if (mod < 0) {
            mod += b;
        }
        return mod;
    }

    public static double max(double a, double b) {
        return java.lang.Math.max(a, b);
    }

    public static double min(double a, double b) {
        return java.lang.Math.min(a, b);
    }

    public static double abs(double a) {
        return java.lang.Math.abs(a);
    }

    public static double sin(double a) {
        return java.lang.Math.sin(a);
    }

    public static double cos(double a) {
        return java.lang.Math.cos(a);
    }

    public static double tan(double a) {
        return java.lang.Math.tan(a);
    }

    public static double atan(double radians) {
        return java.lang.Math.atan(radians);
    }

    public static double hypot(double a, double b) {
        return java.lang.Math.hypot(a, b);
    }

    public static double toRadians(double a) {
        return java.lang.Math.toRadians(a);
    }

    public static double toDegrees(double a) {
        return java.lang.Math.toDegrees(a);
    }

    public static double sqrt(double a) {
        return java.lang.Math.sqrt(a);
    }

    public static double pow(double a, double b) {
        return java.lang.Math.pow(a, b);
    }

    /**
     * @param r Red value in a range from 0-1
     * @param g Green value in a range from 0-1
     * @param b Blue value in a range from 0-1
     * @return Array with three values: Hue, Saturation, and Value
     */

    public static double[] RGBtoHSV(double r, double g, double b) {
        double[] hsv = { 0f, 0f, 0f };
        double minRGB = Math.min(r, Math.min(g, b));
        double maxRGB = Math.max(r, Math.max(g, b));

        double d = (r == minRGB) ? g - b : ((b == minRGB) ? r - g : b - r);
        double h = (r == minRGB) ? 3 : ((b == minRGB) ? 1 : 5);
        hsv[0] = 60 * (h - d / (maxRGB - minRGB));
        hsv[1] = (maxRGB - minRGB) / maxRGB;
        hsv[2] = maxRGB;
        return hsv;
    }

    public static double deadZone(double input, double tolerance) {
        if (abs(input) < tolerance) {
            return 0;
        }
        return input;
    }

    public static double[] convertIntArrayToDoubleArray(int[] source) {
        double[] destinationArray = new double[source.length];
        for (int i = 0; i < source.length; i++) {
            destinationArray[i] = source[i];
        }
        return destinationArray;
    }

    /**
     * Returns the angle with the opposite direction of the given angle.
     * 
     * @param angle The angle in degrees (from -180 to 180) to generate its opposite
     *              angle.
     * @return The opposite angle of the given angle in degrees, from -180 to 180
     */
    public static double getOppositeAngle(double angle) {

        double result = angle - 180;

        return normalizeAngle(result);

    }

    /**
     * Returns an angle which is coterminal with the given one, but in a range of
     * -180 to 180.
     * 
     * @param angle The angle (in degrees) to normalize.
     * @return An angle(in degrees), coterminal with the given one, but in a range
     *         of -180 to 180.
     */
    public static double normalizeAngle(double angle) {

        double result = angle;

        if (Math.abs(result) < 180) {
            return result;
        }

        if (result > 180) {
            result -= 360;
        }
        if (result < -180) {
            result += 360;
        }

        return normalizeAngle(result);

    }

    public static double deltaAngle(double angle, double target) {

        double delta = target - angle;

        if (delta > 180) {
            delta -= 360;
        }

        if (delta < -180) {
            delta = 360 - delta;
        }

        return delta;

    }

    public static double distance(double x1, double x2, double y1, double y2) {

        return sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double atan2(double y, double x) {
        return java.lang.Math.atan2(y, x);
    }

}