/*
 ____  _  _   __    __  ____  ____  ____    ____  _  _  ____  ____  _  _  ____  ____  ____  _  _ 
/ ___)/ )( \ /  \  /  \(_  _)(  __)(  _ \  / ___)/ )( \(  _ \/ ___)( \/ )/ ___)(_  _)(  __)( \/ )
\___ \) __ ((  O )(  O ) )(   ) _)  )   /  \___ \) \/ ( ) _ (\___ \ )  / \___ \  )(   ) _) / \/ \
(____/\_)(_/ \__/  \__/ (__) (____)(__\_)  (____/\____/(____/(____/(__/  (____/ (__) (____)\_)(_/
*/
package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 
public class ShooterSubsystem extends SubsystemBase {
  public static ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  
  private CANSparkMax leftMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANPIDController leftPidController = leftMotor.getPIDController();
  private CANEncoder leftEncoder = leftMotor.getEncoder();

  private CANSparkMax rightMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANPIDController rightPidController = rightMotor.getPIDController();
  private CANEncoder rightEncoder = rightMotor.getEncoder();
  
  public ShooterSubsystem() {
    leftPidController.setP(Constants.shooterConstants.kP);
    leftPidController.setI(Constants.shooterConstants.kI);
    leftPidController.setD(Constants.shooterConstants.kD);
    leftPidController.setIZone(Constants.shooterConstants.kIz);
    leftPidController.setFF(Constants.shooterConstants.kFF);
    leftPidController.setOutputRange(Constants.shooterConstants.kMinOutput, Constants.shooterConstants.kMaxOutput);

    rightPidController.setP(Constants.shooterConstants.kP);
    rightPidController.setI(Constants.shooterConstants.kI);
    rightPidController.setD(Constants.shooterConstants.kD);
    rightPidController.setIZone(Constants.shooterConstants.kIz);
    rightPidController.setFF(Constants.shooterConstants.kFF);
    rightPidController.setOutputRange(Constants.shooterConstants.kMinOutput, Constants.shooterConstants.kMaxOutput);
  }
	public void debug(){
    SmartDashboard.putNumber("P Gain", Constants.shooterConstants.kP);
    SmartDashboard.putNumber("I Gain", Constants.shooterConstants.kI);
    SmartDashboard.putNumber("D Gain", Constants.shooterConstants.kD);
    SmartDashboard.putNumber("I Zone", Constants.shooterConstants.kIz);
    SmartDashboard.putNumber("Feed Forward", Constants.shooterConstants.kFF);
    SmartDashboard.putNumber("Max Output", Constants.shooterConstants.kMinOutput);
    SmartDashboard.putNumber("Min Output", Constants.shooterConstants.kMaxOutput);
    SmartDashboard.putNumber("Setpoint", Constants.shooterConstants.velocity);
	}
  public void shoot() {
    leftPidController.setReference(Constants.shooterConstants.velocity, ControlType.kVelocity);
    rightPidController.setReference(-Constants.shooterConstants.velocity, ControlType.kVelocity); // negative b/c reverse
  }
}

