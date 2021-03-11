/*
 ____   __  ____   __  ____     ___  __   __ _  ____  __   __  __ _  ____  ____ 
(  _ \ /  \(  _ \ /  \(_  _)   / __)/  \ (  ( \(_  _)/ _\ (  )(  ( \(  __)(  _ \
 )   /(  O )) _ ((  O ) )(    ( (__(  O )/    /  )( /    \ )( /    / ) _)  )   /
(__\_) \__/(____/ \__/ (__)    \___)\__/ \_)__) (__)\_/\_/(__)\_)__)(____)(__\_)*/
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;

public class RobotContainer {
	/*
	 * OI
	 */

	public static Joystick rightJoy = new Joystick(0);
	public static Joystick leftJoy = new Joystick(1);

	/*
	 * Subsystems
	 */

	// public final static ShooterSubsystem m_ShooterSubsystem = new
	// ShooterSubsystem();
	public final static Limelight m_limelight = new Limelight("limelight");
	// public final static IntakeSubsystem m_IntakeSubsystem = new
	// IntakeSubsystem();
	public final static DriveSubsystem m_driveSubsystem = new DriveSubsystem();
	public final static DriveSubsystem m_driveSubsystemPID = new DrivePID();

	/*
	 * Commands
	 */

	// private final AutoCommand m_autoCommand = new AutoCommand();

	public RobotContainer() {
		configureButtonBindings();
		m_driveSubsystem.setDefaultCommand(new TankDrivePID());
	}

	public static Joystick getRightJoy() {
		return rightJoy;
	}

	public static Joystick getLeftJoy() {
		return leftJoy;
	}

	private void configureButtonBindings() {
		GenericHID operatorGamepad = new Joystick(RobotMap.operatorGamepad);
		Button shootButton = new JoystickButton(operatorGamepad, RobotMap.shootButton);
		Button shooterUp = new JoystickButton(operatorGamepad, 1);
		Button shooterDown = new JoystickButton(operatorGamepad, 2);
		Button aimBot = new JoystickButton(operatorGamepad, 3);
		Button rotatePanelButton = new JoystickButton(operatorGamepad, 5);
		Button openIntake = new JoystickButton(operatorGamepad, 6);
		Button closeIntake = new JoystickButton(operatorGamepad, 7);

		// public GenericHID getRightJoystick(){ return operatorGamepad; }
		// public double readRightForwardAxis() { return rightJoystick.getY();}

	}

	public Command getAutonomousCommand() {
		return null;
	}
}
