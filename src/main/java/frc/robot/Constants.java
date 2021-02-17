/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Constants for robot.
 */
public class Constants {
    public static Joystick leftDriveStick = new Joystick(0);
    public static Joystick rightDriveStick = new Joystick(1);

    final static public int driveSpeed  = 1;
    final static public int shooterMode = 0; // mode 0: low shooter | mode 1: high shooter

    final static public double intakeSpeed = 1;

    final static public double deadzone = 0.3;

}
