package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import javax.annotation.OverridingMethodsMustInvokeSuper;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.RobotContainer;
 
 
public class ShootCommand extends CommandBase {
   public ShootCommand() {
       addRequirements(RobotContainer.m_ShooterSubsystem);
       SmartDashboard.putNumber("Constructed Shoot Command", 1);
   }
 
   @Override
   public void initialize() {
    SmartDashboard.putNumber("Initialized Shoot Command", 1);
   }
 
   @Override
   public void execute() {
    SmartDashboard.putNumber("Constructed Shoot Command", 4);
    RobotContainer.m_ShooterSubsystem.shoot();
   }
   @Override
   public boolean isFinished() {
       return false;
   }
   @Override
   public void end(boolean interrupted) {
       if(interrupted) {
           RobotContainer.m_ShooterSubsystem.setTopMotorSpeed(0);   
           RobotContainer.m_ShooterSubsystem.setBottomMotorSpeed(0);
       }
   }
      
}
