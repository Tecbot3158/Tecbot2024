package frc.robot.resources.splines;

public class CubicSpline {

    public double cubicCoeff;
    public double squareCoeff;
    public double linearCoeff;
    public double independentTerm;

    private Derivative derivative;

    public CubicSpline(double cubic, double square, double m_linear, double term) {
        cubicCoeff = cubic;
        squareCoeff = square;
        linearCoeff = m_linear;
        independentTerm = term;
    }

    public double f(double x) {

        double cubic = cubicCoeff * x * x * x;
        double cuadratic = squareCoeff * x * x;
        double linear = linearCoeff * x;

        return (cubic + cuadratic + linear + independentTerm);
    }

}
