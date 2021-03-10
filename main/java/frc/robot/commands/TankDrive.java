/*
__  __   __ _  __ _  ____  ____  __  _  _  ____ 
(_  _)/ _\ (  ( \(  / )(    \(  _ \(  )/ )( \(  __)
  )( /    \/    / )  (  ) D ( )   / )( \ \/ / ) _) 
 (__)\_/\_/\_)__)(__\_)(____/(__\_)(__) \__/ (____)
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TankDrive extends CommandBase {


  public TankDrive() {
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.m_driveSubsystem.drive(RobotContainer.getLeftJoy().getY(), RobotContainer.getRightJoy().getY());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}