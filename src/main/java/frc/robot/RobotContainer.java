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


  public static GenericHID operatorGamepad = new Joystick(0);

  /*
   * Subsystems
   */

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

  Button raiseElevator = new JoystickButton(operatorGamepad, 1);
  Button lowerElevator = new JoystickButton(operatorGamepad, 2); 
  Button holdElevator = new JoystickButton(operatorGamepad, 3); 

  public RobotContainer() {
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    raiseElevator.whileHeld(new RaiseElevator());
    lowerElevator.whileHeld(new LowerElevator());
    holdElevator.whileHeld(new HoldElevator());
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
