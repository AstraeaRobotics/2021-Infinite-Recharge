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

	final static public double driveSpeed = .75;
	final static public int shooterMode = 0; // mode 0: low shooter | mode 1: high shooter
  
  public static class elevatorConstants {
    
    public static double kP = 5e-5;
    public static double kI = 0;
    public static double  kD = 0; 
    public static double  kIz = 0.0; 
    //public double  kFF = 0.000015;
    //public double  kFF = 0.000156; 
    public static double  kFF = 0.000156;
    public static double  kMaxOutput = 1; 
    public static double  kMinOutput = -1;
    public static double  maxRPM = 5700;

    public static double maxVel = 4000;
    public static double maxAcc = 100;

    public static double setpoint = 500;
  }
  
  public static class shooterConstants  {
    public static final double kP = 6e-5;
    public static final double kI = 0;
    public static final double kD = 0.00001; 
    public static final double kIz = 0.0;
    public static final double kFF = 0.000092; 
    public static final double kMaxOutput = 1; 
    public static final double kMinOutput = -1;
    public static final double maxRPM = 11000;
    public static final double multiplier = .85;
  }

  public static class visionConstants {
    public static double h_goal = 13; // Height of the goal in inches
		public static double h_limeyboy = 5;
		public static double shooter_Angle = 12.5; // fixed shooter angle. TODO: experimentally determine this
    public static double largeAngleThreshold = 10.0;
    public static double smallAngleThreshold = 1.0;
  }
  
  public static final double intakeSpeed = .8;
  public static final double indexerSpeed = .2;
}
