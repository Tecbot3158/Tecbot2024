// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.DriveTrain;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = Units.inchesToMeters(24.75); // FIXME Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = Units.inchesToMeters(24); // FIXME Measure and set wheelbase


    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 3; // FIXME Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 4; // FIXME Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 10; // FIXME Set front left steer encoder ID
    //public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(19.73); // FIXME Measure and set front left steer offset
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -0.335941792546954; // FIXME Measure and set front left steer offset

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 2; // FIXME Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 1; // FIXME Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 12; // FIXME Set front right steer encoder ID
    //public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(169.36); // FIXME Measure and set front right steer offset
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -2.949845055104088; // FIXME Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; // FIXME Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8; // FIXME Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 9; // FIXME Set back left steer encoder ID
    //public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(143.61); // FIXME Measure and set back left steer offset
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -5.709476492510355 - Math.PI; // FIXME Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 6; // FIXME Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 5; // FIXME Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 11; // FIXME Set back right steer encoder ID
    //public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.92); // FIXME Measure and set back right steer offset
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -4.950156002506965- Math.PI; // FIXME Measure and set back right steer offset

    // The maximum change in speed (in m/s^2) that will be passed to the swerve drive.
    public static final double SWERVE_MAX_ACCELERATION = 10;
    // The maximum change in angular speed (in rad/s^2) that will be passed to the swerve drive.
    public static final double SWERVE_MAX_ANGULAR_ACCELERATION = 8;

    public static final double SWERVE_PRECISION_MODE_MULTIPLIER = 0.5;

    // The maximum speed at which a swerve module can go (in m/s).
    public static final double MAX_MODULE_SPEED = DriveTrain.MAX_VELOCITY_METERS_PER_SECOND;
    // The distance from the center of the robot to the farthest module (in meters).
    public static final double DRIVE_BASE_RADIUS = 0.52;

    public static final Pose2d SHOOTING_POSITION = new Pose2d(15.1, 5.23, new Rotation2d(Math.PI));
    
    public static final HolonomicPathFollowerConfig pathFollowerConfig = new 
    HolonomicPathFollowerConfig(new PIDConstants(0.125, 0.001), 
    new PIDConstants(9, 0.0001), 
    MAX_MODULE_SPEED, DRIVE_BASE_RADIUS, new ReplanningConfig());



    public static final double DRIVETRAIN_TURN_KP = 0.07;
    public static final double DRIVETRAIN_TURN_KI = 0;
    public static final double DRIVETRAIN_TURN_KD = 0.01;

    public static final double DRIVETRAIN_TURN_ARRIVE_OFFSET = 3;



    ///////////// SHOOTER CONTROL //////////////////////////

    public static final double SHOOTER_TOP_FEEDFORWARD_S = 0;
    public static final double SHOOTER_TOP_FEEDFORWARD_V = 0.000182;

    public static final double SHOOTER_TOP_PID_P = 0.00001;
    public static final double SHOOTER_TOP_PID_I = 0;
    public static final double SHOOTER_TOP_PID_D = 0.000005;

    
    public static final double SHOOTER_BOTTOM_FEEDFORWARD_S = 0;
    public static final double SHOOTER_BOTTOM_FEEDFORWARD_V = 0.00018;

    public static final double SHOOTER_BOTTOM_PID_P = 0.000015;
    public static final double SHOOTER_BOTTOM_PID_I = 0;
    public static final double SHOOTER_BOTTOM_PID_D = 0.000015;

    public static final double SHOOTER_TOP_SPEAKER_SPEED = 3500;
    public static final double SHOOTER_BOTTOM_SPEAKER_SPEED = 3800;

    public static final double SHOOTER_RPM_ARRIVE_OFFSET = 70;


    /////// Joystick

    public static final double PILOT_DRIVING_DEADZONE = 0.03;

}
