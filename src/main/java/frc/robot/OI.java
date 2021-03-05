/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class OI {
    protected GenericHID operatorGamepad = new Joystick(RobotMap.operatorGamepad);
    protected Button shootButton = new JoystickButton(operatorGamepad, RobotMap.shootButton);
    Button shooterUp = new JoystickButton(operatorGamepad, 1);
    Button shooterDown = new JoystickButton(operatorGamepad, 2);
    Button aimBot = new JoystickButton(operatorGamepad, 3);

    public Button rotatePanelButton = new JoystickButton(operatorGamepad, 5);
    public Button openIntake = new JoystickButton(operatorGamepad, 6);
    public Button closeIntake = new JoystickButton(operatorGamepad, 7);
    //public GenericHID getRightJoystick(){ return operatorGamepad; }


    //public double readRightForwardAxis() { return rightJoystick.getY();}
    public Joystick rightJoy = new Joystick(0);
    public Joystick leftJoy = new Joystick(1);

    public OI() {
      SmartDashboard.putNumber("constructed OI ", 1);
      // shootButton.whileHeld(new ShootCommand());
      // shooterUp.whileHeld(new ShooterUpCommand());
      // shooterDown.whileHeld(new ShooterDownCommand());
      // rotatePanelButton.whileHeld(new SpinIntake());
      // openIntake.whenPressed(new ActivateIntake());
      // closeIntake.whenPressed(new CloseIntake());
       aimBot.whileHeld(new Aimbot());
      //public Joystick ps4_controller = new Joystick(RobotMap.PS4_CONTROLLER_PORT);
      //public JoystickButton rotatePanelButton = new JoystickButton(ps4_controller, 11);
      //public JoystickButton findColorButton = new JoystickButton(ps4_controller, 12);
    }
    //public JoystickButton findColorButton = new JoystickButton(ps4_controller, 12);

    public Joystick getRightJoy() {
      return rightJoy;
    }

    public Joystick getLeftJoy() {
      return leftJoy;
    }
   
}