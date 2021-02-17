/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */
  // Two slaved motors to manage belt
  private DoubleSolenoid activatorSolenoid1;
  private DoubleSolenoid activatorSolenoid2;
  private TalonSRX intakeMotor;

  public IntakeSubsystem(){
    activatorSolenoid1 = new DoubleSolenoid(RobotMap.SOLENOID1_FORWARD_CHANNEL, RobotMap.SOLENOID1_REVERSE_CHANNEL);
    activatorSolenoid2 = new DoubleSolenoid(RobotMap.SOLENOID2_FORWARD_CHANNEL, RobotMap.SOLENOID2_REVERSE_CHANNEL);
    intakeMotor = new TalonSRX(7);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeBall(){
    System.out.println("Intaking ball");
    intakeMotor.set(ControlMode.PercentOutput, 1);
}
  public void reverseIntake() {
    intakeMotor.set(ControlMode.PercentOutput, Constants.intakeSpeed);
  }
  public void stopSpin() {
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  public void openIntake(){
    System.out.println("Open Intak");
    activatorSolenoid1.set(DoubleSolenoid.Value.kForward);
    activatorSolenoid2.set(DoubleSolenoid.Value.kForward);
  }
  public void closeIntake() {
    System.out.println("Close intake");
    activatorSolenoid1.set(DoubleSolenoid.Value.kReverse);
    activatorSolenoid2.set(DoubleSolenoid.Value.kReverse);
    
  }
  public void disableIntake() {
    activatorSolenoid1.set(DoubleSolenoid.Value.kOff);
    activatorSolenoid2.set(DoubleSolenoid.Value.kOff);
  }
}
