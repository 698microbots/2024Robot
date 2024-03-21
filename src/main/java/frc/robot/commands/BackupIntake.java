// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.subsystems.IntakeSubsystem;

public class BackUpIntake extends Command {
  /** Creates a new BackUpIntake. */
  private final IntakeSubsystem intakeSubsystem;
  private Supplier<Double> xSpeed;
  private Supplier<Double> ySpeed;
  private Supplier<Double> rotationSpeed; // multiple by 1.5 * pi
  private final SwerveRequest.FieldCentric fieldCentric = new SwerveRequest.FieldCentric();
    private CommandSwerveDrivetrain commandSwerveDrivetrain;

  public BackUpIntake(IntakeSubsystem intakeSubsystem,
      Supplier<Double> xSpeed,
      Supplier<Double> ySpeed,
      Supplier<Double> rotationSpeed,
      CommandSwerveDrivetrain commandSwerveDrivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intakeSubsystem = intakeSubsystem;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.rotationSpeed = rotationSpeed;
    this.commandSwerveDrivetrain = commandSwerveDrivetrain;
    addRequirements(intakeSubsystem);
    addRequirements(commandSwerveDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("this work backup intake");
    intakeSubsystem.backupIntakeMotor(.75);
    commandSwerveDrivetrain.setControl(fieldCentric.withVelocityX(- xSpeed.get()).withVelocityY(- ySpeed.get()).withRotationalRate(rotationSpeed.get() * (1.5 * Math.PI)));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.backupIntakeMotor(0);
    commandSwerveDrivetrain.setControl(fieldCentric.withVelocityX(0).withVelocityY(0).withRotationalRate(0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
