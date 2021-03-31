/*
 ____  ____  __  _  _  ____  ____  _  _  ____  ____  _  _  ____  ____  ____  _  _ 
(    \(  _ \(  )/ )( \(  __)/ ___)/ )( \(  _ \/ ___)( \/ )/ ___)(_  _)(  __)( \/ )
 ) D ( )   / )( \ \/ / ) _) \___ \) \/ ( ) _ (\___ \ )  / \___ \  )(   ) _) / \/ \
(____/(__\_)(__) \__/ (____)(____/\____/(____/(____/(__/  (____/ (__) (____)\_)(_/*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveSubsystem extends SubsystemBase {

	CANSparkMax left1;
	CANSparkMax left2;
	CANSparkMax right1;
	CANSparkMax right2;
	// CANSparkMax rightB;

	DifferentialDrive drive;

	public DriveSubsystem() {
		right1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
		right2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
		left1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
		left2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
		// rightM = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
		// rightB = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

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
		System.out.println("left: " + left + " right: " + right);
		drive.tankDrive(left, right);
	}

	public void turnInPlace(double speed) {
		drive.arcadeDrive(0, speed);
	}

	public void curve(double speed, double turnRate, boolean quick) {
		//System.out.println(speed);
		//System.out.println(turnRate);
		
		SmartDashboard.putNumber("Speed", speed);
		SmartDashboard.putNumber("Turn", turnRate);

		drive.curvatureDrive(speed, turnRate, quick);
	}

	@Override
	public void periodic() {
	}
}
