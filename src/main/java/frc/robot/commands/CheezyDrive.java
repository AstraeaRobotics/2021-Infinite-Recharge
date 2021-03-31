// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.*;
import frc.robot.RobotContainer;

public class CheezyDrive extends CommandBase {
  /** Creates a new CheezyDrive. */
  public CheezyDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.operatorGamepad.getRawButton(8)) {
      RobotContainer.m_driveSubsystem.curve(.5*(RobotContainer.operatorGamepad.getRawAxis(4) + 1) / 2, RobotContainer.operatorGamepad.getRawAxis(0), false);
    } else if(RobotContainer.operatorGamepad.getRawButton(7)) {
      RobotContainer.m_driveSubsystem.curve(-.5*(RobotContainer.operatorGamepad.getRawAxis(3) + 1) / 2, RobotContainer.operatorGamepad.getRawAxis(0), false);
    } else {
      RobotContainer.m_driveSubsystem.curve(0, RobotContainer.operatorGamepad.getRawAxis(2), true);
    
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
