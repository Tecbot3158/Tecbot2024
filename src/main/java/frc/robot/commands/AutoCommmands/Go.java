package frc.robot.commands.AutoCommmands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class Go extends Command{

    private final DriveTrain m_drivetrainSubsystem;

    private final double m_PositionX;
    private final double m_PositionY;
    private final double m_Rotation;

    public double metersSetPointX;
    public double metersSetPointY;
    public double metersSetPoint;
    public double metersPosition;
    public double componentX;
    public double componentY;
    public double metersMagnitude;

    public Go(DriveTrain drivetrainSubsystem, double PositionX,
        double PositionY, double Rotation) {

        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_PositionX = PositionX;
        this.m_PositionY = PositionY;
        this.m_Rotation = Rotation;

        addRequirements(drivetrainSubsystem);
    }

    public void initialize(){

        m_drivetrainSubsystem.resetPosition();

        metersSetPointX = m_drivetrainSubsystem.odometer.getPoseMeters().getX() + m_PositionX;
        metersSetPointY = m_drivetrainSubsystem.odometer.getPoseMeters().getY() + m_PositionY;

        metersSetPoint = Math.sqrt(Math.pow(metersSetPointX, 2)+Math.pow(metersSetPointY, 2));
        
    }
    // comment
    public void execute(){
        
        componentX = (metersSetPointX - m_drivetrainSubsystem.odometer.getPoseMeters().getX()); // comment
        componentY = (metersSetPointY - m_drivetrainSubsystem.odometer.getPoseMeters().getY()); // comment

        metersMagnitude = Math.sqrt(Math.pow(componentX, 2) + Math.pow(componentY, 2)); // comment

        m_drivetrainSubsystem.drive(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                componentX / metersMagnitude,
                componentY / metersMagnitude,
                0,
                m_drivetrainSubsystem.getGyroscopeRotation()));

    }

    // comment
    public void end(boolean interrupted){

        m_drivetrainSubsystem.drive(
            ChassisSpeeds.fromFieldRelativeSpeeds(
                0,
                0,
                0,
                m_drivetrainSubsystem.getGyroscopeRotation()));

    }

    public boolean isFinished(){

    if((metersSetPoint - metersMagnitude) < 0.4)
        return false;
    else 
        return true;

    }
}
