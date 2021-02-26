/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;

public class Aimbot extends CommandBase {
    /**
     * Creates a new Aimbot.
     * 
     * @return
     */
    public Aimbot() {
      SmartDashboard.putNumber("constructed Limelight ", 1);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_limelight);
    // addRequirements(RobotContainer.m_driveSubsystem);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Running Limelight - init", 1);
   RobotContainer.m_limelight.setLED("on");
    //RobotContainer.m_limelight.driverOnlyMode(false);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Running Limelight", 1);
    
    //PID VALUES (MUST BE TUNED)
    final double kPx = -.1f;
    final double kPy = -.1f;
    //minimum value to actually make the robot turn (at smaller values it may not turn due to friction)
    final double min_aim_command = .05f;

    //if a target is locked
    if (RobotContainer.m_limelight.hasTarget()) {
      //grab data and add to smart dashboard
      SmartDashboard.putBoolean("Limelight Target", true);
      double tx = RobotContainer.m_limelight.getHorizontalOffset();
      SmartDashboard.putNumber("Horizontal Offset", tx);
      double ty = RobotContainer.m_limelight.getVerticalOffset();
      SmartDashboard.putNumber("Vertical Offset", ty);
      double distance = RobotContainer.m_limelight.getDistance();
      SmartDashboard.putNumber("Limelight Distance", distance);

      double x_adjust;
      //on large angles, ignore the minimum aim value, on smaller angles add it to make sure the robot moves
      if(tx>1.0){
        x_adjust = kPx*-tx - min_aim_command;
      }else{
        x_adjust = kPx*-tx + min_aim_command;
      }
      double y_adjust = kPy*ty;
      SmartDashboard.putNumber("Pivot Adjust Val", x_adjust);
      SmartDashboard.putNumber("Drive Adjust Val", y_adjust);

      //drive the robot based on these motor valuesRobotContainer.m_driveSubsystem.drive(x_adjust+y_adjust,-(x_adjust+y_adjust));
      
        
    }else{
        SmartDashboard.putBoolean("Limelight Target", false);
    }
    
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
     // RobotContainer.m_limelight.setLED("off");
      //RobotContainer.m_limelight.driverOnlyMode(true);
       return true;
  }
}