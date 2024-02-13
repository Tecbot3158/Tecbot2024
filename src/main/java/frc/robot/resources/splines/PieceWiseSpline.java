package frc.robot.resources.splines;

public class PieceWiseSpline {

    CubicSpline spline0, spline1;

    Derivative derivativeSpline0, derivativeSpline1;

    double initialXPosition, middleWaypointX, finalXPosition;

    boolean vertical;

    /**
     * Generates a new piece wise function with two cubic splines.
     * 
     * @param spline0         The first part of the piecewise function.
     * @param spline1         The second part of the piecewise function.
     * @param middleWaypointX The x value at which the first function finishes and
     *                        the second starts.
     */
    public PieceWiseSpline(CubicSpline spline0, CubicSpline spline1, double initialXPosition, double middleWaypointX,
            double finalXPosition, boolean vertical) {

        this.spline0 = spline0;
        this.spline1 = spline1;
        this.middleWaypointX = middleWaypointX;

        this.derivativeSpline0 = SplineGenerator.DifferentiateSpline(spline0);
        this.derivativeSpline1 = SplineGenerator.DifferentiateSpline(spline1);

        this.vertical = vertical;

        this.initialXPosition = initialXPosition;
        this.finalXPosition = finalXPosition;

    }

    /**
     * Returns the evaluation of the function at the given point.
     * 
     * @param x The point at which the funcion will be evaluated.
     * @return Returns the evaluation of the function at the given point.
     */
    public double f(double x) {

        if (x < middleWaypointX) {
            return spline0.f(x);
        } else {
            return spline1.f(x);
        }

    }

    /**
     * Returns the evaluation of the derivative of the spline at the given point.
     * 
     * @param x The point at which the derivative will be evaluated.
     * @return The evaluation of the derivative of the spline at the given point.
     */
    public double fPrime(double x) {

        if (x < middleWaypointX) {
            return derivativeSpline0.fPrime(x);
        } else {
            return derivativeSpline1.fPrime(x);
        }
    }

    /**
     * Returns the angle at which the robot should be at a given x position.
     * 
     * @param x        The x position of the robot.
     * @param vertical By default, the y-axis is paralell to the driver stations,
     *                 true if this needs to be the other way around.
     * @return The angle at which the robot should be at a given x position.
     */
    public double angle(double x) {

        if (x < middleWaypointX) {
            return SplineGenerator.angleFromDerivate(derivativeSpline0, x, vertical);
        } else {
            return SplineGenerator.angleFromDerivate(derivativeSpline1, x, vertical);
        }

    }

    public double getInitialXPosition() {
        return initialXPosition;
    }

    public double getFinalXPosition() {
        return finalXPosition;
    }

}
