// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class SimDrive extends CommandBase {
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
    double driveSpeed = Constants.driveSpeed;

    double speed1 = driveSpeed*(RobotContainer.driverGamepad.getRawAxis(RobotMap.RT_AXIS) + 1) / 2;
    double speed2 = driveSpeed*(RobotContainer.driverGamepad.getRawAxis(RobotMap.LT_AXIS) + 1) / 2;

    if(RobotContainer.driverGamepad.getRawButton(RobotMap.RT_BTN)) {
      RobotContainer.m_driveSubsystem.curve(-speed1, RobotContainer.driverGamepad.getRawAxis(RobotMap.LS_HORIZONTAL_AXIS), false);
    } else if(RobotContainer.driverGamepad.getRawButton(RobotMap.LT_BTN)) {
      RobotContainer.m_driveSubsystem.curve(speed2, -RobotContainer.driverGamepad.getRawAxis(RobotMap.LS_HORIZONTAL_AXIS), false);
    } else {
      RobotContainer.m_driveSubsystem.curve(0, .25*RobotContainer.driverGamepad.getRawAxis(RobotMap.RS_HORIZONTAL_AXIS), true);
    }
    

    //RobotContainer.m_driveSubsystem.curve(RobotContainer.driverGamepad.getRawAxis(RobotMap.LS_VERTICAL_AXIS), RobotContainer.driverGamepad.getRawAxis(RobotMap.RS_HORIZONTAL_AXIS), RobotContainer.driverGamepad.getRawButton(RobotMap.SHOOT_BUTTON));
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
