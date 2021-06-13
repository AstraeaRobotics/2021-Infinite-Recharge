/*
 ____   __  ____   __  ____ 
(  _ \ /  \(  _ \ /  \(_  _)
 )   /(  O )) _ ((  O ) )(  
(__\_) \__/(____/ \__/ (__) */

package frc.robot;

// import frc.robot.commands.TankDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  // Compressor Definition
  public static Compressor compressor = new Compressor(RobotMap.PCM_CAN_ID);
  

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }


  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }


  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }


  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    SmartDashboard.putBoolean("Drive Mode", true);


    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel(); 
    }
  }

  @Override
  public void teleopPeriodic() {
    if(compressor.getPressureSwitchValue()) {
      compressor.setClosedLoopControl(false);
    }
    else {
      compressor.setClosedLoopControl(true);
    }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }


  @Override
  public void testPeriodic() {
  }
}
