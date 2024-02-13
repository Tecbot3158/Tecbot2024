// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.resources.TecbotSpeedController.TypeOfMotor;

/** Add your docs here. */
public class RobotMap {

public static final double OFFSET = 0.05;

public static final int driveTrainPorts[] = {14,15};//los buenos son 11, 51

public static final int armPorts[] = {1,2};

public static final TypeOfMotor chassisMotor[] = {TypeOfMotor.CAN_SPARK_BRUSHLESS, TypeOfMotor.CAN_SPARK_BRUSHLESS};

public static final int pilotPort = 0;

public static final int copilotPort = 1;

public static final double chassisSpeedL = 0.3;

public static final double chassisSpeedR = 0.3;

public static final double armSpeedB = 0.50;

public static final double armSpeedE = 0.2;

public static final double armspeedF = 0.30;

public static final double teleop_armspeed = 0.3;

public static final int PCM_1_PORT = 7;

public static final int SolenoidPortClaw[] = {PCM_1_PORT, 5, 10};

public static final int SolenoidPortTransmition[] = {PCM_1_PORT, 1, 14};

public static final int SolenoidPortArm[] = {PCM_1_PORT, 2, 13};

public static final int SolenoidPortExtentionArm[] = {PCM_1_PORT, 3, 12};

public static final int kGyroPort = 0;

public static final double wheelDiameter = 4.0;

public static final int shooterPorts[] = {11, 51};

public static final int intakePorts[] = {15};
}

