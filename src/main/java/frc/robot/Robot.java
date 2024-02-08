// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimeLightHelpersSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;


  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    // m_robotContainer.limeLight.setLight(false); limelight auto turns off this
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
    // System.out.println("running robot periodic");
    SmartDashboard.putNumber("Left Y", m_robotContainer.joystick.getLeftY());
    SmartDashboard.putNumber("Left X", m_robotContainer.joystick.getLeftX());

    SmartDashboard.putNumber("Robot Y", m_robotContainer.drivetrain.getState().Pose.getY()); //says this is null when simulating
    SmartDashboard.putNumber("Robot X", m_robotContainer.drivetrain.getState().Pose.getX());
    SmartDashboard.putNumber("AprilTag ID", LimeLightHelpersSubsystem.getFiducialID("limelight"));
    SmartDashboard.putNumber("H Angle", m_robotContainer.limeLight.getH_angle());
    SmartDashboard.putNumber("Angle", m_robotContainer.gyro.getAngle());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

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
    // System.out.println("running teleop periodic");

    
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

  @Override
  public void simulationPeriodic() {}
}
