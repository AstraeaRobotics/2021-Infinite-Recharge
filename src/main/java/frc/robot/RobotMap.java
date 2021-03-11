/* ____   __  ____   __  ____  _  _   __   ____ 
(  _ \ /  \(  _ \ /  \(_  _)( \/ ) / _\ (  _ \
 )   /(  O )) _ ((  O ) )(  / \/ \/    \ ) __/
(__\_) \__/(____/ \__/ (__) \_)(_/\_/\_/(__)  */

package frc.robot;

public class RobotMap {
	public static int driverGamepad = 0;
	public static int operatorGamepad = 2;
	public static int leftStick = 0;
	public static int rightStick = 0;
	public static int shootButton = 7;
	public static int intakeMotor = 0;
	public static int shooterAngleMotor = 10;
	public static int topMotor = 11;
	public static int bottomMotor = 12;

	public static class activatorSolenoid {
		public static int forwardChannel = 0;
		public static int reverseChannel = 0;
	}

	public static final int BALL_INTAKE_MOTOR = 7;
	public static final int SOLENOID1_FORWARD_CHANNEL = 4;
	public static final int SOLENOID1_REVERSE_CHANNEL = 5;
	public static final int SOLENOID2_FORWARD_CHANNEL = 6;
	public static final int SOLENOID2_REVERSE_CHANNEL = 7;
}