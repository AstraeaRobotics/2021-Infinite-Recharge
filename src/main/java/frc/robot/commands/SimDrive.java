// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SPI;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimDrive extends CommandBase {
  AHRS gyro = new AHRS(SPI.Port.kMXP);

  /** Creates a new CheezyDrive. */
  public SimDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Boolean valetMode = SmartDashboard.getBoolean("valetMode",true);

    double driveSpeed = Constants.driveSpeed;
    Boolean pressedL2 = RobotContainer.operatorGamepad.getRawButton(RobotMap.LT_BTN);
    Boolean pressedR2 = RobotContainer.operatorGamepad.getRawButton(RobotMap.RT_BTN);
    double axisLeftJoystick = RobotContainer.operatorGamepad.getRawAxis(RobotMap.LS_HORIZONTAL_AXIS);
    double axisRightJoystick = RobotContainer.operatorGamepad.getRawAxis(RobotMap.RS_HORIZONTAL_AXIS);
   //remap from [-1,1] to [0,1]
    double axisL2raw = (RobotContainer.operatorGamepad.getRawAxis(RobotMap.LT_AXIS)+1.0)/2.0;
    double axisR2raw = (RobotContainer.operatorGamepad.getRawAxis(RobotMap.RT_AXIS)+1.0)/2.0;
   //pass to function x^2
    double axisL2 = axisL2raw*axisL2raw;
    double axisR2 = axisR2raw*axisR2raw;
    if (valetMode){
      axisL2 = axisL2*.3;
      axisR2 = axisR2*.3;
      axisRightJoystick = axisRightJoystick*.3;
    }
    //move this to constants
   SmartDashboard.putNumber("left throttle", axisL2);
   SmartDashboard.putNumber("right throttle", axisR2);
   RobotContainer.m_driveSubsystem.setCoast();
    if(pressedR2 && pressedL2) {
      RobotContainer.m_driveSubsystem.setBrakes();
     RobotContainer.m_driveSubsystem.curve(0, axisRightJoystick, true);
   }
   else if(pressedR2) {
     if(RobotContainer.m_driveSubsystem.getDirection()>0 ||Math.abs(RobotContainer.m_driveSubsystem.getDirection())<5){
     RobotContainer.m_driveSubsystem.curve(axisR2, axisLeftJoystick, false);
     }else{
      RobotContainer.m_driveSubsystem.setBrakes();
      RobotContainer.m_driveSubsystem.curve(0, axisRightJoystick, true);
     }
   } else if(pressedL2) {
     if(RobotContainer.m_driveSubsystem.getDirection()<0 ||Math.abs(RobotContainer.m_driveSubsystem.getDirection())<5){
      RobotContainer.m_driveSubsystem.curve(-axisL2, axisLeftJoystick, false);
     }else{
      RobotContainer.m_driveSubsystem.setBrakes();
      RobotContainer.m_driveSubsystem.curve(0, axisRightJoystick, true);

     }
   } else {
     //turn in place
     RobotContainer.m_driveSubsystem.curve(0, axisRightJoystick, true);
   
   }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
