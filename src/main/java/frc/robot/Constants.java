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
  
  public class shooterConstants  {
    public static final double kP = 6e-5;
    public static final double kI = 0;
    public static final double kD = 0.00001; 
    public static final double kIz = 0.0;
    public static final double kFF = 0.000092; 
    public static final double kMaxOutput = 1; 
    public static final double kMinOutput = -1;
    public static final double maxRPM = 5700;
    public static final double velocity = 5700;
  }
}
