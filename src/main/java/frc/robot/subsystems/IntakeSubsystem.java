// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdleConfiguration;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  private final CANSparkMax IntakeMotor = new CANSparkMax(15, CANSparkMax.MotorType.kBrushless);
  // Initializes a DigitalInput on DIO 0
  private final DigitalInput photoSensor = new DigitalInput(2); //TODO - make this a constant
  private final CANdle candle = new CANdle(0);
  private boolean canRun = true;

  
 private final CANdleConfiguration config = new CANdleConfiguration();
 
 public IntakeSubsystem() {
 CANdleConfiguration config = new CANdleConfiguration();
 config.stripType = LEDStripType.RGB; // set the strip type to RGB
 config.brightnessScalar = 0.5; // dim the LEDs to half brightness
 candle.configAllSettings(config);  
}

  public void setIntakeMotor(double speed) {
    if(canRun){
    IntakeMotor.set(-speed);
    } else {
      IntakeMotor.set(0);
    }
  }

  //also use to override normal setIntakeMotor
  public void reverseIntakeMotor(double speed){
    IntakeMotor.set(speed);
  }

  public double getIntakeVolts() {
    return IntakeMotor.getBusVoltage();
  }

  public void setCanRun(boolean run) {
    canRun = run;
  }

  public boolean getCanRun() {
    return canRun;
  }

  // public boolean canRun() {
  //   if (getBlocked()) {
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }

  public boolean getBlocked() {
    return photoSensor.get();
  }

  public void setLights(){
    if (photoSensor.get()){
      candle.setLEDs(Constants.colorRGBIntake[0], Constants.colorRGBIntake[1], Constants.colorRGBIntake[2]);
    } else {
      candle.setLEDs(0, 0, 0);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
