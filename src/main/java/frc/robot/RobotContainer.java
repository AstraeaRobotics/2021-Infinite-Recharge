/*
 ____   __  ____   __  ____     ___  __   __ _  ____  __   __  __ _  ____  ____ 
(  _ \ /  \(  _ \ /  \(_  _)   / __)/  \ (  ( \(_  _)/ _\ (  )(  ( \(  __)(  _ \
 )   /(  O )) _ ((  O ) )(    ( (__(  O )/    /  )( /    \ )( /    / ) _)  )   /
(__\_) \__/(____/ \__/ (__)    \___)\__/ \_)__) (__)\_/\_/(__)\_)__)(____)(__\_)*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.controller.RamseteController;

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
  }

  public Command getAutonomousCommand() {
	  RobotContainer.m_driveSubsystem.zeroHeading();

    // Create a voltage constraint to ensure we don't accelerate too fast
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
            DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics, 10);

    // Create config for trajectory
    TrajectoryConfig config = new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

    Trajectory pathWeaverTrajectory = new Trajectory();

    try {
      pathWeaverTrajectory = m_driveSubsystem.generateTrajectory("paths/Unnamed_1.wpilib.json");
    } catch (Exception e) {
      e.printStackTrace();
    }

    RamseteCommand ramseteCommand = new RamseteCommand(
        pathWeaverTrajectory,
        m_driveSubsystem::getPose,  
        new RamseteController(),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                  DriveConstants.kvVoltSecondsPerMeter,
                                  DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        m_driveSubsystem::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_driveSubsystem::tankDriveVolts,
        m_driveSubsystem
    );

    // Reset odometry to the starting pose of the trajectory.
    m_driveSubsystem.resetOdometry(pathWeaverTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> m_driveSubsystem.tankDriveVolts(0, 0));
  }
}
