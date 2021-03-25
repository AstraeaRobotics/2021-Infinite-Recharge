/*
 ____  _  _   __    __  ____  ___  __   _  _  _  _   __   __ _  ____ 
/ ___)/ )( \ /  \  /  \(_  _)/ __)/  \ ( \/ )( \/ ) / _\ (  ( \(    \
\___ \) __ ((  O )(  O ) )( ( (__(  O )/ \/ \/ \/ \/    \/    / ) D (
(____/\_)(_/ \__/  \__/ (__) \___)\__/ \_)(_/\_)(_/\_/\_/\_)__)(____/
*/

package frc.robot.commands;
 
//import javax.annotation.OverridingMethodsMustInvokeSuper;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.RobotContainer;
 
 
public class ShootCommand extends CommandBase {
  public ShootCommand() {
   addRequirements(RobotContainer.m_ShooterSubsystem);
  }
 
  @Override
  public void initialize() {
  }
 
   @Override
  public void execute() {
    RobotContainer.m_ShooterSubsystem.shoot();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted) {
      RobotContainer.m_ShooterSubsystem.setSpeed(0);
    }
  }     
}