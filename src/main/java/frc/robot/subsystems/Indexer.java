// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class Indexer extends SubsystemBase {
  private CANSparkMax bottomMotor;
  private CANSparkMax topMotor;
  
  /** Creates a new Indexer. */
  public Indexer() {
    bottomMotor = new CANSparkMax(RobotMap.INDEXER_MOTOR_BOTTOM, CANSparkMaxLowLevel.MotorType.kBrushless);
    topMotor = new CANSparkMax(RobotMap.INDEXER_MOTOR_TOP, CANSparkMaxLowLevel.MotorType.kBrushless);
  }

  public void feed() {
    bottomMotor.set(Constants.indexerSpeed);
    topMotor.set(-Constants.indexerSpeed);
  }

  public void stop() {
    bottomMotor.set(0);
    topMotor.set(0);
  }
}
