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

public class Intake extends SubsystemBase {
  
  private TalonSRX leftMotor;
  private CANSparkMax rightMotor;
  
  /** Creates a new Intake. */
  public Intake() {
    leftMotor = new TalonSRX(RobotMap.INTAKE_MOTOR_LEFT);
    rightMotor = new CANSparkMax(RobotMap.INTAKE_MOTOR_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
  }

  public void intakeBall() {
    rightMotor.set(-.08);
    System.out.println("MOTOR");
    System.out.println(rightMotor.getEncoder().getPosition());
    leftMotor.set(ControlMode.PercentOutput, -Constants.intakeSpeed);
  }

  public void stop() {
    leftMotor.set(ControlMode.PercentOutput, 0);
    rightMotor.set(0);
  }
}
