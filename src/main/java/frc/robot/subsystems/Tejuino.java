// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.resources.TejuinoBoard;

public class Tejuino extends SubsystemBase 
{
  TejuinoBoard tejuino;

  public Tejuino() 
  {
    tejuino = new TejuinoBoard();
    tejuino.init(tejuino.TEJUINO_DEVICE_NUMBER_0);
    tejuino.rainbow_effect(tejuino.LED_STRIP_1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
