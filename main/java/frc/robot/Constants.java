/*
  ___  __   __ _  ____  ____  __   __ _  ____  ____ 
 / __)/  \ (  ( \/ ___)(_  _)/ _\ (  ( \(_  _)/ ___)
( (__(  O )/    /\___ \  )( /    \/    /  )(  \___ \
 \___)\__/ \_)__)(____/ (__)\_/\_/\_)__) (__) (____/
 */
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Constants {
	public static Joystick leftDriveStick = new Joystick(0);
	public static Joystick rightDriveStick = new Joystick(1);

	final static public int driveSpeed = 1;
	final static public int shooterMode = 0; // mode 0: low shooter | mode 1: high shooter

	final static public double intakeSpeed = 1;

	final static public double deadzone = 0.3;

}
