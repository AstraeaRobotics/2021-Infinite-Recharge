/*
  ___  __   __ _  ____  ____  __   __ _  ____  ____ 
 / __)/  \ (  ( \/ ___)(_  _)/ _\ (  ( \(_  _)/ ___)
( (__(  O )/    /\___ \  )( /    \/    /  )(  \___ \
 \___)\__/ \_)__)(____/ (__)\_/\_/\_)__) (__) (____/
 */
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public class Constants {
	public static Joystick leftDriveStick = new Joystick(0);
	public static Joystick rightDriveStick = new Joystick(1);

	final static public int driveSpeed = 1;
	final static public int shooterMode = 0; // mode 0: low shooter | mode 1: high shooter

	final static public double intakeSpeed = 1;

	final static public double deadzone = 0.3;
  
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

  public static final class DriveConstants {
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final int kEncoderCPR = 1;
    public static final double kWheelDiameterMeters = 0.15;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

    public static final double ksVolts = 0.222;
    public static final double kvVoltSecondsPerMeter = 2.8;
    public static final double kaVoltSecondsSquaredPerMeter = 0.437;

    public static final double kPDriveVel = 0; // 2.32
  }

  public static final class AutoConstants {
    public static final double kMaxSpeedMetersPerSecond = 0.03048;
    public static final double kMaxAccelerationMetersPerSecondSquared = 0.01524;

    // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }

}
