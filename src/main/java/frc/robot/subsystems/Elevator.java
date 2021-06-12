/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.ControlType;

public class Elevator extends SubsystemBase {

  CANSparkMax elevatorMaster;
	CANSparkMax elevatorSlave;
  double setpoint = 0;

  CANPIDController elevatorMasterPidController;
  CANEncoder elevatorMasterEncoder = elevatorMaster.getEncoder();

  public Elevator() { 
    elevatorMaster = new CANSparkMax(RobotMap.MASTER_ELEVATOR_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
    elevatorSlave = new CANSparkMax(RobotMap.SLAVE_ELEVATOR_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

    elevatorMaster.setIdleMode(IdleMode.kBrake);
    elevatorSlave.setIdleMode(IdleMode.kBrake);

    elevatorSlave.follow(elevatorMaster);
    
    elevatorMasterPidController = elevatorMaster.getPIDController();
    elevatorMasterEncoder = elevatorMaster.getEncoder();
    
    elevatorMasterEncoder.setPosition(0);
    elevatorMasterPidController.setSmartMotionMaxVelocity(Constants.elevatorConstants.maxVel, 0);
    elevatorMasterPidController.setSmartMotionMinOutputVelocity(0, 0);
    elevatorMasterPidController.setSmartMotionMaxAccel(Constants.elevatorConstants.maxAcc, 0);
    elevatorMasterPidController.setSmartMotionAllowedClosedLoopError(0, 0);
    elevatorMasterPidController.setP(Constants.elevatorConstants.kP);
    elevatorMasterPidController.setI(Constants.elevatorConstants.kI);
    elevatorMasterPidController.setD(Constants.elevatorConstants.kD);
    elevatorMasterPidController.setIZone(Constants.elevatorConstants.kIz);
    elevatorMasterPidController.setFF(Constants.elevatorConstants.kFF);
    elevatorMasterPidController.setOutputRange(Constants.elevatorConstants.kMinOutput, Constants.elevatorConstants.kMaxOutput);
    log();
    
  }

  @Override
  public void periodic() {
    log();
    //elevatorMasterPidController.setReference(setpoint, ControlType.kSmartMotion);

  }
  public void log(){
    SmartDashboard.putNumber("P Gain", Constants.elevatorConstants.kP);
    SmartDashboard.putNumber("I Gain", Constants.elevatorConstants.kI);
    SmartDashboard.putNumber("D Gain", Constants.elevatorConstants.kD);
    SmartDashboard.putNumber("I Zone", Constants.elevatorConstants.kIz);
    SmartDashboard.putNumber("Feed Forward", Constants.elevatorConstants.kFF);
    SmartDashboard.putNumber("Max Output", Constants.elevatorConstants.kMaxOutput);
    SmartDashboard.putNumber("Min Output", Constants.elevatorConstants.kMinOutput);
  }

  // Run only when activating a function in the elevator
  public void updateElevator() {
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double getSP = SmartDashboard.getNumber("Setpoint", 0);
    
    if((p != Constants.elevatorConstants.kP)) { elevatorMasterPidController.setP(p); Constants.elevatorConstants.kP = p; }
    if((i != Constants.elevatorConstants.kI)) { elevatorMasterPidController.setI(i); Constants.elevatorConstants.kI = i; }
    if((d != Constants.elevatorConstants.kD)) { elevatorMasterPidController.setD(d); Constants.elevatorConstants.kD = d; }
    if((iz != Constants.elevatorConstants.kIz)) { elevatorMasterPidController.setIZone(iz); Constants.elevatorConstants.kIz = iz; }
    if((ff != Constants.elevatorConstants.kFF)) { elevatorMasterPidController.setFF(ff); Constants.elevatorConstants.kFF = ff; }
    if((max != Constants.elevatorConstants.kMaxOutput) || (min != Constants.elevatorConstants.kMinOutput)) {
      elevatorMasterPidController.setOutputRange(min, max);
      Constants.elevatorConstants.kMinOutput = min; Constants.elevatorConstants.kMaxOutput = max;
    }

    elevatorMasterPidController.setReference(getSP, ControlType.kSmartMotion);
    SmartDashboard.putNumber("Setpoint", setpoint);
    SmartDashboard.putNumber("ProcessVariable", elevatorMasterEncoder.getPosition());
  }

  public void raiseElevator() {
    setpoint = 500;
    elevatorMaster.set(.01);
  }
  public void holdElevator() {
    elevatorMaster.set(0);
  }
  public void lowerElevator() {
    setpoint = 0;
    elevatorMaster.set(-.01);

  }

}