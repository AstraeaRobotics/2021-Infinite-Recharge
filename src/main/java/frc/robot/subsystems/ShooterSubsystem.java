/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
 
public class ShooterSubsystem extends SubsystemBase {
 public static ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
 
  private OI m_oi;
   //TalonSRX neo = new TalonSRX(1);
   //TalonSRX topMotor = new TalonSRX(7);
   //TalonSRX bottomMotor = new TalonSRX(8);
   CANSparkMax neo = new CANSparkMax(RobotMap.shooterAngleMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
   CANSparkMax topMotor = new CANSparkMax(RobotMap.topMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
   CANSparkMax bottomMotor = new CANSparkMax (RobotMap.bottomMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
 
   public void shooterUp() {
    neo.set(.1);
}
 
  
 
   public void shooterDown() {
       neo.set(-.1);
   }
 
   public void shoot() {
       // top motor counterclockwise, bottom motor clockwise @ full speed
       topMotor.set(1);
       bottomMotor.set(-1);
   }
 
   public void setNeoSpeed(double speed) {
      neo.set(speed);
   }
 
   public void setTopMotorSpeed(double speed) {
       topMotor.set(speed);
   }
 
   public void setBottomMotorSpeed(double speed) {
       bottomMotor.set(speed);
   }
 
 
   // @Override
   // default void setDefaultCommand(Command defaultCommand){
   //     // set the default command
   //     // setDefaultCommand (new mySpecialCommand());
   //     CommandScheduler.getInstance().setDefaultCommand(this, defaultCommand);
   // }
  
}
