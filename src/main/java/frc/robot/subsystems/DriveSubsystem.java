/*
 ____  ____  __  _  _  ____  ____  _  _  ____  ____  _  _  ____  ____  ____  _  _ 
(    \(  _ \(  )/ )( \(  __)/ ___)/ )( \(  _ \/ ___)( \/ )/ ___)(_  _)(  __)( \/ )
 ) D ( )   / )( \ \/ / ) _) \___ \) \/ ( ) _ (\___ \ )  / \___ \  )(   ) _) / \/ \
(____/(__\_)(__) \__/ (____)(____/\____/(____/(____/(__/  (____/ (__) (____)\_)(_/*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel;

import frc.robot.subsystems.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;


public class DriveSubsystem extends SubsystemBase {

	public final double kP = 6e-5;
  public  final double kI = 0;
  public  final double kD = 0; 
  public final double kIz = 0.0;
  public final double kFF = 0.00022; 
  private double kMaxOutput = 1; 
  private double kMinOutput = -1;
  private double maxRPM = 5700;

	CANSparkMax left1;
	CANSparkMax left2;
	CANSparkMax right1;
	CANSparkMax right2;
	// CANSparkMax rightB;

	DifferentialDrive drive;

	public DriveSubsystem() {
		right1 = new CANSparkMax(RobotMap.DRIVE_RIGHT1, CANSparkMaxLowLevel.MotorType.kBrushless);
		right2 = new CANSparkMax(RobotMap.DRIVE_RIGHT2, CANSparkMaxLowLevel.MotorType.kBrushless);
		left1 = new CANSparkMax(RobotMap.DRIVE_LEFT1, CANSparkMaxLowLevel.MotorType.kBrushless);
		left2 = new CANSparkMax(RobotMap.DRIVE_LEFT2, CANSparkMaxLowLevel.MotorType.kBrushless);

		CANEncoder leftEnc = left1.getEncoder();
		CANEncoder rightEnc = right1.getEncoder();

		CANPIDController pidLeft = left1.getPIDController();
		CANPIDController pidRight = right1.getPIDController();
		// rightM = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
		// rightB = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

		pidRight.setP(kP);
    pidRight.setI(kI);
    pidRight.setD(kD);
    pidRight.setIZone(kIz);
    pidRight.setFF(kFF);
    pidRight.setOutputRange(kMinOutput, kMaxOutput);

    pidLeft.setP(kP);
    pidLeft.setI(kI);
    pidLeft.setD(kD);
    pidLeft.setIZone(kIz);
    pidLeft.setFF(kFF);
    pidLeft.setOutputRange(kMinOutput, kMaxOutput);

		left2.follow(left1);
		// leftB.follow(leftF);
		right2.follow(right1);
		// rightB.follow(rightF);
	
		/*
		 * OR:
		 * 
		 * SpeedControllerGroup(leftF, leftM, leftB) SpeedControllerGroup(rightF,
		 * rightM, rightB)
		 * 
		 */

		left1.getEncoder().setPosition(0.0);
		right1.getEncoder().setPosition(0.0);

		drive = new DifferentialDrive(left1, right1);
	}

	public void drive(double left, double right) {
		//System.out.println("left: " + left + " right: " + right);
		drive.tankDrive(left, right);
	}

	public void turnInPlace(double speed) {
		drive.arcadeDrive(0, speed);
	}

	public void curve(double speed, double turnRate, boolean quick) {
		//System.out.println(speed);
		//System.out.println(turnRate);
		
    SmartDashboard.putNumber("right1", right1.getEncoder().getVelocity());
    SmartDashboard.putNumber("right2", right2.getEncoder().getVelocity());
    SmartDashboard.putNumber("left1", left1.getEncoder().getVelocity());
    SmartDashboard.putNumber("left2", left2.getEncoder().getVelocity());

		drive.curvatureDrive(speed, turnRate, quick);
	}

	@Override
	public void periodic() {
	}
}
