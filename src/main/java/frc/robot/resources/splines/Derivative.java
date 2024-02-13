package frc.robot.resources.splines;

public class Derivative {

    public double squareCoeff;
    public double linearCoeff;
    public double indep;

    public Derivative(double square, double lineal, double term) {
        squareCoeff = square;
        linearCoeff = lineal;
        indep = term;
    }

    public double fPrime(double x) {

        return (squareCoeff * x * x + linearCoeff * x + indep);

    }
}
