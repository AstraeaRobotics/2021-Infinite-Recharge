/*
 ____   __  ____   __  ____     ___  __   __ _  ____  __   __  __ _  ____  ____ 
(  _ \ /  \(  _ \ /  \(_  _)   / __)/  \ (  ( \(_  _)/ _\ (  )(  ( \(  __)(  _ \
 )   /(  O )) _ ((  O ) )(    ( (__(  O )/    /  )( /    \ )( /    / ) _)  )   /
(__\_) \__/(____/ \__/ (__)    \___)\__/ \_)__) (__)\_/\_/(__)\_)__)(____)(__\_)*/
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import java.util.Arrays;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import java.io.IOException;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.Filesystem;
import java.nio.file.Path;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  /*
   * OI
   */

  public static Joystick rightJoy = new Joystick(RobotMap.rightStick);
  public static Joystick leftJoy = new Joystick(RobotMap.leftStick);
  public static GenericHID operatorGamepad = new Joystick(RobotMap.operatorGamepad);
  public static GenericHID driverGamepad = new Joystick(RobotMap.driverGamepad);

  /*
   * Subsystems
   */

  public final static Limelight m_limelight = new Limelight("limelight");
  public final static DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  public final static ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  public final static Intake m_IntakeSubsystem = new Intake();
  public final static Indexer m_IndexerSubsystem = new Indexer();
  public final static Elevator m_ElevatorSubsysem = new Elevator();

  /*
   * Commands
   */

  // private final AutoCommand m_autoCommand = new AutoCommand();

  /**
   * Buttons
   */

  // Button aimBot = new JoystickButton(operatorGamepad, 3);
  // Button shootButton = new JoystickButton(operatorGamepad, RobotMap.SHOOT_BUTTON);
  // Button autoaimButton = new JoystickButton(operatorGamepad, RobotMap.AUTOAIM_BUTTON);
  // Button intakeButton = new JoystickButton(operatorGamepad, RobotMap.INTAKE_BUTTON);

  Button raiseElevator = new JoystickButton(operatorGamepad, 3);
  Button lowerElevator = new JoystickButton(operatorGamepad, 2); 

  public RobotContainer() {
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(new SimDrive());
  }

  public static Joystick getRightJoy() {
    return rightJoy;
  }

  public static Joystick getLeftJoy() {
    return leftJoy;
  }

  private void configureButtonBindings() {
    raiseElevator.whileHeld(new RaiseElevator());
    lowerElevator.whileHeld(new LowerElevator());
  }

  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(
      Units.feetToMeters(2), 
      Units.feetToMeters(2)
    );

    config.setKinematics(m_driveSubsystem.getKinematics());

 Trajectory pathWeaverTrajectory = new Trajectory();
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve("output/slalom.wpilib.json");
    try {
      pathWeaverTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    RamseteCommand command = new RamseteCommand(
        pathWeaverTrajectory,
        m_driveSubsystem::getPose,
        new RamseteController(2.0, 0.7),
        m_driveSubsystem.getFeedforward(),
        m_driveSubsystem.getKinematics(),
        m_driveSubsystem::getSpeeds,
        m_driveSubsystem.getLeftPIDController(),
        m_driveSubsystem.getRightPIDController(),
        m_driveSubsystem::setOutput,
        m_driveSubsystem 
    );

    // Reset odometry to the starting pose of the trajectory.
    m_driveSubsystem.resetOdometry(pathWeaverTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return command.andThen(() -> m_driveSubsystem.setOutput(0, 0));
  }
}
