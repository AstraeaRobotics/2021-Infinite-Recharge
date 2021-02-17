package frc.robot.commands;
 
//import javax.annotation.OverridingMethodsMustInvokeSuper;
 
// import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.RobotContainer;
 
 
 
public class ShooterUpCommand extends CommandBase {
   public ShooterUpCommand() {
       addRequirements(RobotContainer.m_ShooterSubsystem);
   }
   @Override
   public void initialize() {}
   @Override
   public void execute() {RobotContainer.m_ShooterSubsystem.shooterUp();}
   @Override
   public boolean isFinished() {return false;}
   @Override
   public void end(boolean interrupted) {
       if(interrupted) {
           RobotContainer.m_ShooterSubsystem.setNeoSpeed(0);
       }
   }
}
