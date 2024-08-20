// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ejml.equation.Sequence;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Sequence1;
import frc.robot.commands.autos.AutoDriveAndShoot;
import frc.robot.commands.autos.AutoDriveToPosition;
import frc.robot.resources.TecbotPWMLEDStrip;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private static RobotContainer m_robotContainer;
  private OI m_oi;

  private TecbotPWMLEDStrip ledStrip;

  private int i = 0;


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_oi = new OI(m_robotContainer , m_robotContainer.getPilot(), m_robotContainer.getCopilot());
    m_oi.configureButtonBindings();
    m_robotContainer.configureCommands();
    
  }
  
  public static RobotContainer getRobotContainer(){
    return m_robotContainer;
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    //ledStrip.setSolidHSV(69, 255, 255);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // recuerda que X es para adelante y está al reves ... el adelante es del roborio a la pila
    //m_autonomousCommand = new AutoDriveToPosition(m_robotContainer.getDriveTrain() , 0, 0,-1,0,0,90);
    
    CommandScheduler.getInstance().run();
        m_autonomousCommand = getRobotContainer().getChooser().getSelected();
        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    /*Robot.getRobotContainer().getClimber().onR();
    Robot.getRobotContainer().getClimber().onL();*/
    Robot.getRobotContainer().getRampSensorSubsystem().setNoteSensor(true);
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
