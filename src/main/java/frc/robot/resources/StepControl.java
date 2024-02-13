package frc.robot.resources;

import static frc.robot.resources.Math.abs;

public class StepControl {

    double currentPosition = 0;
    double startPosition = 0;
    double output = 0;


    double kTarget = 0;

    double kMinimumAbsoluteOutput = 0.09;

    double incrementMultiplier = 1;

    double currentOutput = 0;

    double range = 0.01;


    public StepControl ( double kMinimumAbsoluteOutput, double kTarget, double kCurrentPosition,
                         double kIncrementMultiplier ){
        this.kTarget = kTarget;
        this.kMinimumAbsoluteOutput = kMinimumAbsoluteOutput;

        this.startPosition = kCurrentPosition;
        currentPosition = kCurrentPosition;

        this.incrementMultiplier = kIncrementMultiplier;

    }

    public double setCurrentPosition( double pos ){
        this.currentPosition = pos;
        return pos;
    }

    public double getOutputVelocity(double currentVelocity ) {
        this.currentPosition = currentVelocity;
        double proportion = ( ( kTarget - currentVelocity) ) * incrementMultiplier;
        int sign = proportion >= 0 ? 1: -1;

        proportion = sign  * Math.clamp(abs(proportion), kMinimumAbsoluteOutput, 1);

        currentOutput += proportion;

        return Math.clamp(currentOutput, -1, 1) ;

    }

    public double getOutputPosition(double currentPosition ) {
        this.currentPosition = currentPosition;
        double proportion = ( kTarget - currentPosition)  * incrementMultiplier;
        int sign = proportion >= 0 ? 1: -1;

        proportion = sign  * Math.clamp(abs(proportion), kMinimumAbsoluteOutput, 1);

        return Math.clamp(proportion, -1, 1) ;

    }


    public double setIncrementMultiplier (double value){
        return this.incrementMultiplier = value;
    }

    public double setTarget (double target){
        return this.kTarget = target;
    }

    public double getTarget(){
        return this.kTarget;
    }

    public double setMinAbsoluteOutput(double minOutput){
        return   this.kMinimumAbsoluteOutput = minOutput;
    }

    public double getCurrentPosition(){
        return this.currentPosition;
    }

    /**
     * How far off should the current position be in order to be
     * within range.
     *
     * Default: 0.01
     *
     * e.g. if it its within 99% it will be considered as in range.
     *
     * @param range
     * @return
     */
    public double setRange(double range){
        return this.range = range;
    }

    public boolean isInRange(){
        //double proportion =  Math.abs(currentPosition / kTarget ) ;
        double proportion =  Math.abs(kTarget - currentPosition);
        //return proportion >= ( (double) 1 - this.range ) && proportion <= ( (double) 1 + this.range);
        return proportion <= range;
    }

    public double getRange() {
        return range;
    }
}
