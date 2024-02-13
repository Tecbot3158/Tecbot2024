package frc.robot.resources;

import static frc.robot.resources.Math.abs;

public class PositionStepControl {

    double currentPosition = 0;

    double kTarget = 0;

    double kMinimumAbsoluteOutput = 0.09;

    double startControlMultiplier = 1;

    double range = 0.01;

    public PositionStepControl(double kMinimumAbsoluteOutput, double kTarget,
                               double kStartControl) {
        this.kTarget = kTarget;
        this.kMinimumAbsoluteOutput = kMinimumAbsoluteOutput;

        currentPosition = 0;

        setStartControlMultiplier(kStartControl);

    }

    public double setCurrentPosition(double pos) {
        this.currentPosition = pos;
        return pos;
    }


    public double getOutputPosition(double currentPosition) {
        this.currentPosition = currentPosition;
        double proportion = (kTarget - currentPosition) / startControlMultiplier;
        int sign = proportion >= 0 ? 1 : -1;

        proportion = sign * Math.clamp(abs(proportion), kMinimumAbsoluteOutput, 1);

        return Math.clamp(proportion, -1, 1);

    }


    public double setStartControlMultiplier(double startControlMult) {
        return this.startControlMultiplier = startControlMult;
    }

    public double setTarget(double target) {
        return this.kTarget = target;
    }

    public double getTarget() {
        return this.kTarget;
    }

    public double setMinAbsoluteOutput(double minOutput) {
        return this.kMinimumAbsoluteOutput = minOutput;
    }

    public double getCurrentPosition() {
        return this.currentPosition;
    }

    /**
     * How far off should the current position be in order to be
     * within range.
     * <p>
     * Default: 0.01
     * <p>
     * e.g. if it its within 99% it will be considered as in range.
     *
     * @param range
     * @return
     */
    public double setRange(double range) {
        return this.range = range;
    }

    public boolean isInRange() {
        //double proportion =  Math.abs(currentPosition / kTarget ) ;
        double proportion = Math.abs(kTarget - currentPosition);
        //return proportion >= ( (double) 1 - this.range ) && proportion <= ( (double) 1 + this.range);
        return proportion <= range;
    }


}
