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
     Boolean pressedL2 = RobotContainer.operatorGamepad.getRawButton(7);
     Boolean pressedR2 = RobotContainer.operatorGamepad.getRawButton(8);
     double axisJoystick = RobotContainer.operatorGamepad.getRawAxis(0);
  
    //remap from [-1,1] to [0,1]
     double axisL2 = (RobotContainer.operatorGamepad.getRawAxis(3)+1.0)/2.0;
     double axisR2 = (RobotContainer.operatorGamepad.getRawAxis(4)+1.0)/2.0;
    
     //move this to constants
     double throttleSensitivityMultiplier = .5;
    
    //if both are pressed, then reduce speed based on how much the second trigger is pressed (emulates Need for Speed games)
     if(pressedR2 && pressedL2) {
      RobotContainer.m_driveSubsystem.curve((axisR2-axisL2)*throttleSensitivityMultiplier, axisJoystick, false);
    }
    else if(pressedR2) {
      RobotContainer.m_driveSubsystem.curve(axisR2*throttleSensitivityMultiplier, axisJoystick, false);
    } else if(pressedL2) {
      RobotContainer.m_driveSubsystem.curve(-axisL2*throttleSensitivityMultiplier, axisJoystick, false);
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
