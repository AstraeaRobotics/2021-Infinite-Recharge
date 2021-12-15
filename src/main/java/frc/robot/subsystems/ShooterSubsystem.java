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
  
  private CANSparkMax topMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR_TOP, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANPIDController topPidController = topMotor.getPIDController();
  private CANEncoder topEncoder = topMotor.getEncoder();

  private CANSparkMax bottomMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR_BOTTOM, CANSparkMaxLowLevel.MotorType.kBrushless);
  private CANPIDController bottomPidController = bottomMotor.getPIDController();
  private CANEncoder bottomEncoder = bottomMotor.getEncoder();
  
  public ShooterSubsystem() {
    System.out.println("Shooter Constructor");
    topPidController.setP(Constants.shooterConstants.kP);
    topPidController.setI(Constants.shooterConstants.kI);
    topPidController.setD(Constants.shooterConstants.kD);
    topPidController.setIZone(Constants.shooterConstants.kIz);
    topPidController.setFF(Constants.shooterConstants.kFF);
    topPidController.setOutputRange(Constants.shooterConstants.kMinOutput, Constants.shooterConstants.kMaxOutput);

    bottomPidController.setP(Constants.shooterConstants.kP);
    bottomPidController.setI(Constants.shooterConstants.kI);
    bottomPidController.setD(Constants.shooterConstants.kD);
    bottomPidController.setIZone(Constants.shooterConstants.kIz);
    bottomPidController.setFF(Constants.shooterConstants.kFF);
    bottomPidController.setOutputRange(Constants.shooterConstants.kMinOutput, Constants.shooterConstants.kMaxOutput);
  }
	public void debug(){
    double sp = Constants.shooterConstants.multiplier * Constants.shooterConstants.maxRPM;

    SmartDashboard.putNumber("P Gain", Constants.shooterConstants.kP);
    SmartDashboard.putNumber("I Gain", Constants.shooterConstants.kI);
    SmartDashboard.putNumber("D Gain", Constants.shooterConstants.kD);
    SmartDashboard.putNumber("I Zone", Constants.shooterConstants.kIz);
    SmartDashboard.putNumber("Feed Forward", Constants.shooterConstants.kFF);
    SmartDashboard.putNumber("Max Output", Constants.shooterConstants.kMinOutput);
    SmartDashboard.putNumber("Min Output", Constants.shooterConstants.kMaxOutput);
    SmartDashboard.putNumber("Setpoint", sp);
    SmartDashboard.putNumber("BottomVelocity", bottomEncoder.getVelocity());
    SmartDashboard.putNumber("TopVelocity", topEncoder.getVelocity());
	}
  public void shoot() {
    System.out.println("shoot :D");
    debug();

    double speed = Constants.shooterConstants.multiplier * Constants.shooterConstants.maxRPM;

    topPidController.setReference(speed, ControlType.kVelocity);
    bottomPidController.setReference(speed, ControlType.kVelocity); // negative b/c reverse
  }

  public void setSpeed(double speed) {
    debug();

    topMotor.set(speed);
    bottomMotor.set(-speed);
  }
}

