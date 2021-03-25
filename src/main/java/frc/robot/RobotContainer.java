/*
 ____   __  ____   __  ____     ___  __   __ _  ____  __   __  __ _  ____  ____ 
(  _ \ /  \(  _ \ /  \(_  _)   / __)/  \ (  ( \(_  _)/ _\ (  )(  ( \(  __)(  _ \
 )   /(  O )) _ ((  O ) )(    ( (__(  O )/    /  )( /    \ )( /    / ) _)  )   /
(__\_) \__/(____/ \__/ (__)    \___)\__/ \_)__) (__)\_/\_/(__)\_)__)(____)(__\_)*/
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  /*
   * OI
   */

  public static Joystick rightJoy = new Joystick(0);
  public static Joystick leftJoy = new Joystick(1);

  /*
   * Subsystems
   */


  public final static Limelight m_limelight = new Limelight("limelight");
  public final static DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  public final static ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  public final static Indexer m_IndexerSubsystem = new Indexer();

  /*
   * Commands
   */

	// private final AutoCommand m_autoCommand = new AutoCommand();

  /**
   * Buttons
   */
  
  GenericHID operatorGamepad = new Joystick(RobotMap.operatorGamepad);
	Button aimBot = new JoystickButton(operatorGamepad, 3);
	Button rotatePanelButton = new JoystickButton(operatorGamepad, 5);
	Button openIntake = new JoystickButton(operatorGamepad, 6);
	Button closeIntake = new JoystickButton(operatorGamepad, 7);
  Button shootButton = new JoystickButton(operatorGamepad, RobotMap.SHOOT_BUTTON);
  Button indexerFeedButton = new JoystickButton(operatorGamepad, RobotMap.INDEXER_FEED_BUTTON);


  public RobotContainer() {
	  configureButtonBindings();
	  m_driveSubsystem.setDefaultCommand(new TankDrive());
  }

  public static Joystick getRightJoy() {
	  return rightJoy;
  }

  public static Joystick getLeftJoy() {
	  return leftJoy;
  }

  private void configureButtonBindings() {
    shootButton.whileHeld(new ShootCommand());
    indexerFeedButton.whileHeld(new IndexerFeed());
  }

  public Command getAutonomousCommand() {
	  return null;
  }
}
