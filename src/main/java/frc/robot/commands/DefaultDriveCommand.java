package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends Command {
    private final DriveTrain m_drivetrainSubsystem;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private final DoubleSupplier m_rotationSupplier;


    public DefaultDriveCommand(DriveTrain drivetrainSubsystem, DoubleSupplier translationXSupplier,
        DoubleSupplier translationYSupplier, DoubleSupplier rotationSupplier) 
        {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;
        this.m_rotationSupplier = rotationSupplier;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {

        double xSpeed = m_translationXSupplier.getAsDouble() * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND;
        double ySpeed = m_translationYSupplier.getAsDouble() * DriveTrain.MAX_VELOCITY_METERS_PER_SECOND;
        double rotation = m_rotationSupplier.getAsDouble() * DriveTrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;

        if(m_drivetrainSubsystem.isOnPrecisionMode())
        {
            xSpeed *= Constants.SWERVE_PRECISION_MODE_MULTIPLIER;
            ySpeed *= Constants.SWERVE_PRECISION_MODE_MULTIPLIER;
            rotation *= Constants.SWERVE_PRECISION_MODE_MULTIPLIER;
        }

        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        xSpeed,
                        ySpeed,
                        rotation,
                        m_drivetrainSubsystem.getGyroscopeRotation()
                )
        );
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}