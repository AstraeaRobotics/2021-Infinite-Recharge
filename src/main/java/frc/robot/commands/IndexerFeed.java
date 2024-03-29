// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotContainer;
import frc.robot.Constants;

public class IndexerFeed extends CommandBase {
  /** Creates a new IndexerFeed. */
  public IndexerFeed() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_IndexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(SmartDashboard.getNumber("BottomVelocity", 0)) < .9*Constants.shooterConstants.multiplier * Constants.shooterConstants.maxRPM) {
      System.out.println("waiting");
      
    } else {
      System.out.println("feeding");
      RobotContainer.m_IndexerSubsystem.feed();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_IndexerSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
