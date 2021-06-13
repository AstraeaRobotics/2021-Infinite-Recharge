/* ____   __  ____   __  ____  _  _   __   ____ 
(  _ \ /  \(  _ \ /  \(_  _)( \/ ) / _\ (  _ \
 )   /(  O )) _ ((  O ) )(  / \/ \/    \ ) __/
(__\_) \__/(____/ \__/ (__) \_)(_/\_/\_/(__)  */

package frc.robot;

public class RobotMap {
  // Joystick Ports
  public static int driverGamepad = 3;
  public static int operatorGamepad = 2;
  public static int leftStick = 1;
  public static int rightStick = 0;
  
  // Joystick Buttons
  public static int SHOOT_BUTTON = 1;
  public static int AUTOAIM_BUTTON = 2;
  public static int INTAKE_BUTTON = 3; // Note -- this activates pnuematics as well! Be careful!
  public static int LT_BTN = 7;
  public static int RT_BTN = 8;

  // Joystick Axis
  public static int LS_HORIZONTAL_AXIS = 0;
  public static int LS_VERTICAL_AXIS = 1;
  public static int RS_HORIZONTAL_AXIS = 2;
  public static int RS_VERTICAL_AXIS = 5;
  public static int LT_AXIS = 3;
  public static int RT_AXIS = 4;

  // Can IDs for Motors on Robot
  public static int DRIVE_RIGHT1 = 1;
  public static int DRIVE_RIGHT2 = 2;

  public static int DRIVE_LEFT1 = 3;
  public static int DRIVE_LEFT2 = 4;

  public static int SHOOTER_MOTOR_TOP = 5;
  public static int SHOOTER_MOTOR_BOTTOM = 6;

  public static int INDEXER_MOTOR_TOP = 7;
  public static int INDEXER_MOTOR_BOTTOM = 8;

  public static int INTAKE_MOTOR_LEFT = 9;
  public static int INTAKE_MOTOR_RIGHT = 10;

  // Ports for pnuematics
  public static final int PCM_CAN_ID = 2;
  public static final int LEFT_SOLENOID_FORWARD_CHANNEL = 0;
  public static final int LEFT_SOLENOID_REVERSE_CHANNEL = 1;
  public static final int RIGHT_SOLENOID_FORWARD_CHANNEL = 2;
  public static final int RIGHT_SOLENOID_REVERSE_CHANNEL = 3;
}