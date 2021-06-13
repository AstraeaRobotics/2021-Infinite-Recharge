// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake extends SubsystemBase {

  private DoubleSolenoid leftIntakeSolenoid;
  private DoubleSolenoid rightIntakeSolenoid;
  
  private TalonSRX leftMotor;
  private CANSparkMax rightMotor;
  
  /** Creates a new Intake. */
  public Intake() {
    leftMotor = new TalonSRX(RobotMap.INTAKE_MOTOR_LEFT);
    rightMotor = new CANSparkMax(RobotMap.INTAKE_MOTOR_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

    leftIntakeSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.LEFT_SOLENOID_FORWARD_CHANNEL, RobotMap.LEFT_SOLENOID_REVERSE_CHANNEL);
    rightIntakeSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN_ID, RobotMap.RIGHT_SOLENOID_FORWARD_CHANNEL, RobotMap.RIGHT_SOLENOID_REVERSE_CHANNEL);
  }

  public void openIntake() {
    leftIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
    rightIntakeSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void closeIntake() {
    leftIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    rightIntakeSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void intakeBall() {
    openIntake();

    rightMotor.set(-.08);
    System.out.println("MOTOR");
    System.out.println(rightMotor.getEncoder().getPosition());
    leftMotor.set(ControlMode.PercentOutput, -Constants.intakeSpeed);
  }

  public void stop() {
    closeIntake();

    leftMotor.set(ControlMode.PercentOutput, 0);
    rightMotor.set(0);
  }
}
