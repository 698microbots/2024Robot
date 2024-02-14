package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HangerSubsystem extends SubsystemBase {

    private final CANSparkMax ArmMotorOne = new CANSparkMax(0, CANSparkMax.MotorType.kBrushed);
    private final CANSparkMax ArmMotorTwo = new CANSparkMax(1, CANSparkMax.MotorType.kBrushed);

    public HangerSubsystem(){

    }

    // sets the motor speed for the Hanger
    public void setFlywheelMotorSpeed(double speed) {
        ArmMotorOne.set(speed * .1);
        ArmMotorTwo.set(speed * .1);
        
     }
}