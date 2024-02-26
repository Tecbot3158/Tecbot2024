package frc.robot.subsystems;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.RobotMap;
import frc.robot.resources.TecbotSpeedController;
import frc.robot.resources.TecbotSpeedController.TypeOfMotor;


public class Climber extends SubsystemBase {
  RobotContainer robotContainer;
  TecbotSpeedController cmL, cmR;

  ShuffleboardTab tab = Shuffleboard.getTab("Climber");
    double leftEncoderZeroValue = 0;
    double rightEncoderZeroValue = 0;

  public Climber(RobotContainer rc){
   robotContainer = rc;

   cmL = new TecbotSpeedController(RobotMap.climberPorts[0],TypeOfMotor.CAN_SPARK_BRUSHLESS);
   cmR = new TecbotSpeedController(RobotMap.climberPorts[1],TypeOfMotor.CAN_SPARK_BRUSHLESS);

   leftEncoderZeroValue = cmL.getEncPosition();
   rightEncoderZeroValue = cmR.getEncPosition();

   SmartDashboard.putNumber("Left Encoder Zero", leftEncoderZeroValue);
   SmartDashboard.putNumber("Right Encoder Zero", rightEncoderZeroValue);


  }
  

    @Override
  public void periodic() {
    double climberSpeedR = robotContainer.getCopilot().getRightY();
    double climberSpeedL = -1*robotContainer.getCopilot().getLeftY();
  
    if( Math.abs(climberSpeedR) <= .05 ){
      climberSpeedR = 0;
    }

    if( Math.abs(climberSpeedL) <= .05){
      climberSpeedL = 0;
    }

     
    if( cmR.getEncPosition() <= (rightEncoderZeroValue - 18) && climberSpeedR < 0){
      climberSpeedR = 0;
    }
    if( cmR.getEncPosition() >= -2 && climberSpeedR > 0){
      climberSpeedR = 0;
    }
    

    if( (cmL.getEncPosition() >= leftEncoderZeroValue + 18) && climberSpeedL > 0){
      climberSpeedL = 0;
    }
    if( (cmL.getEncPosition() <= 1) && climberSpeedL < 0){
      climberSpeedL = 0;
    }
    
    cmR.set( climberSpeedR);
    cmL.set( climberSpeedL);

    
   SmartDashboard.putNumber("Left Encoder", cmL.getCANSparkMax().getEncoder().getPosition());
   SmartDashboard.putNumber("Right Encoder", cmR.getCANSparkMax().getEncoder().getPosition());

    // This method will be called once per scheduler run
    /*if(xbox != null){
      double axisLeft = xbox.getLeftTriggerAxis();
      double axisRight = xbox.getRightTriggerAxis();
      cmL.set(axisLeft * .1);
      cmR.set(-axisRight * .1);

      boolean bumperLeft = xbox.getLeftBumper();
      boolean bumperRight = xbox.getRightBumper();

      if(bumperLeft){
        cmL.set(axisLeft * -.1);
      }

      if(bumperRight){
        cmR.set(axisRight * .1);
      }
    }

    SmartDashboard.putNumber("Climber/LeftEncoder", cmL.getCANSparkMax().getEncoder().getPosition());
    SmartDashboard.putNumber("Climber/RightEncoder", cmR.getCANSparkMax().getEncoder().getPosition());

    SmartDashboard.putNumber("Climber/LeftEncoder Velocity", cmL.getCANSparkMax().getEncoder().getVelocity());
    SmartDashboard.putNumber("Climber/RightEncoder Velocity", cmR.getCANSparkMax().getEncoder().getVelocity());
    */
  }

  /*public void onL(){
    if(xbox.getRightTriggerAxis()<=0.3){
      cm1.set(-.5);
    }
    else{
      cm1.set(0);
    }
 
  }

  public void onR(){
    if(xbox.getRightTriggerAxis()<=0.3){
      cm2.set(.5);
    }
    else{
      cm2.set(0);
    }

  }

  public void offL(){
    cm1.set(0);
    cm2.set(0);
  }

  public void offR(){
    cm2.set(0);
  }

  public void triggerClimber(){
    cm1.set(0.5);
    cm2.set(0.5);
  }*/
}


