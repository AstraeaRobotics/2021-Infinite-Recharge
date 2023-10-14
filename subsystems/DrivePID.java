/*

*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivePID extends SubsystemBase {

	double kP = .5;
	//double kI = 1;
	//double kD = 1;
	double straightTolerance = .1;

	CANSparkMax left1;
	CANSparkMax left2;
	CANSparkMax right1;
	CANSparkMax right2;
	// CANSparkMax rightB;

	DifferentialDrive drive;

	public DrivePID() {
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
	leftencoder = new CANEncoder(left1);
		rightencoder = new CANEncoder(right1);

		left1.getEncoder().setPosition(0.0);
		right1.getEncoder().setPosition(0.0);

		drive = new DifferentialDrive(left1, right1);
	}

	public void drive(double left, double right) {
		double diff = Math.abs(left-right);
		if(diff< straightTolerance){
			//assume driver wants to drive straught
			double error = rightencoder.getVelocity()-leftencoder.getVelocity();
			double pivotAdjust = kP*error;
			//if the joystick falls within this tolerance we'll just take the average value of the two joysticks and apply to both sides
			double averageforward = (left+right)/2;
		System.out.println("left: " + (averageforward+pivotAdjust) + " right: " + (averageforward-pivotAdjust));
		drive.tankDrive(averageforward+pivotAdjust, averageforward-pivotAdjust);
		}else{
			drive.tankDrive(left,right);
		}

	}

	@Override
	public void periodic() {
	}
}
