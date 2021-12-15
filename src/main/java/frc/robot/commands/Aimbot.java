/*
  __   __  _  _  ____   __  ____ 
 / _\ (  )( \/ )(  _ \ /  \(_  _)
/    \ )( / \/ \ ) _ ((  O ) )(  
\_/\_/(__)\_)(_/(____/ \__/ (__) */

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotContainer;
import frc.robot.Constants;

public class Aimbot extends CommandBase {
	public boolean turnedOn = false;
	public int xi = 0;
	public Aimbot() {
		SmartDashboard.putNumber("constructed aimbot ", 1);
		addRequirements(RobotContainer.m_limelight);
		addRequirements(RobotContainer.m_driveSubsystem);

	}

	@Override
	public void initialize() {
		SmartDashboard.putNumber("Running Limelight - init", 1);
		
		// RobotContainer.m_limelight.driverOnlyMode(false);

	}

	@Override
	public void execute() {
		if (!turnedOn){
			turnedOn = true;
			RobotContainer.m_limelight.setLED("on");
			SmartDashboard.putBoolean("TURNING ON LED", true);
		}
		// PID VALUES (MUST BE TUNED)
		final double kPx = -.02f;
		final double kPy = -.1f;
		// minimum value to actually make the robot turn (at smaller values it may not
		// turn due to friction)
		final double feedforward = .16f;

		// if a target is locked
		if (RobotContainer.m_limelight.hasTarget()) {
			SmartDashboard.putNumber("LIMELIGHT DISTANCE", RobotContainer.m_limelight.getDistance());
			// grab data and add to smart dashboard
			SmartDashboard.putBoolean("Limelight Target", true);
			double tx = RobotContainer.m_limelight.getHorizontalOffset();
			SmartDashboard.putNumber("Horizontal Offset", tx);
			double ty = RobotContainer.m_limelight.getVerticalOffset();
			SmartDashboard.putNumber("Vertical Offset", ty);
			double distance = RobotContainer.m_limelight.getDistance();
			SmartDashboard.putNumber("Limelight Distance", distance);

			double x_adjust;
			// on large angles, ignore the minimum aim value, on smaller angles add it to
			// make sure the robot moves
			if (Math.abs(tx) > Constants.visionConstants.largeAngleThreshold) {
				SmartDashboard.putNumber("applying x adjust", 0);
				x_adjust = kPx * -tx;
			} else {
				SmartDashboard.putNumber("applying x adjust", 1);
				if (kPx * -tx > 0) {
					x_adjust = kPx * -tx + feedforward;
				} else {
					x_adjust = kPx * -tx - feedforward;
				}
			}
			if (Math.abs(tx) < Constants.visionConstants.smallAngleThreshold) {
				x_adjust = 0;
				SmartDashboard.putNumber("on target?", 1);
			}
			double y_adjust = kPy * -ty;
			// double y_adjust = 0;
			SmartDashboard.putNumber("Pivot Adjust Val", x_adjust);
			SmartDashboard.putNumber("Drive Adjust Val", y_adjust);

			// drive the robot based on these motor values
			RobotContainer.m_driveSubsystem.drive(x_adjust + y_adjust, -(x_adjust - y_adjust));
			// RobotContainer.m_driveSubsystem.drive(0,feedforward);

		} else {
			SmartDashboard.putBoolean("Limelight Target", false);
		}

	}
	

	@Override
	public boolean isFinished() {
		SmartDashboard.putNumber("Running isFinished", xi);
		xi++;
		if (turnedOn){
		turnedOn = false;
		//RobotContainer.m_limelight.setLED("off");
		//SmartDashboard.putBoolean("TURNING ON LED", false);
		}
		// RobotContainer.m_limelight.driverOnlyMode(true);
		return true;
	}
}