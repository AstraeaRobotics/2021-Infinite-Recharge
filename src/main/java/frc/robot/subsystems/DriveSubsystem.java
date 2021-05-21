// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.util.Units;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {

	CANSparkMax leftMaster = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
	CANSparkMax leftSlave = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
	CANSparkMax rightMaster = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
	CANSparkMax rightSlave = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
	DifferentialDrive drive;
	edu.wpi.first.wpilibj.smartdashboard.Field2d m_field = new edu.wpi.first.wpilibj.smartdashboard.Field2d();

	final double gearRatio = 10.81;

	AHRS gyro = new AHRS(SPI.Port.kMXP);

	DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(21.5));
	DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

	SimpleMotorFeedforward feedForward = new SimpleMotorFeedforward(0.145, 2.8, 0.425);

	PIDController leftPIDController = new PIDController(2.32, 0, 0);
	PIDController rightPIDController = new PIDController(2.32, 0, 0);

	Pose2d pose;

	public DriveSubsystem() {
		leftSlave.follow(leftMaster);
		rightSlave.follow(rightMaster);
		leftMaster.setInverted(false);

		if (DriverStation.getInstance().isAutonomous()) {
			rightMaster.setInverted(true);
			SmartDashboard.putBoolean("inverted right", true);
		} else {
			SmartDashboard.putBoolean("inverted right", false);
			rightMaster.setInverted(false);
		}

		resetEncoders();
		drive = new DifferentialDrive(leftMaster, rightMaster);

		gyro.zeroYaw();
		SmartDashboard.putData("Field", m_field);
	}

	public Pose2d getPose() {
		return pose;
	}

	public void resetEncoders() {
		leftMaster.getEncoder().setPosition(0.0);
		rightMaster.getEncoder().setPosition(0.0);
	}

	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		odometry.resetPosition(pose, gyro.getRotation2d());
	}

	@Override
	public void periodic() {
		pose = odometry.update(getHeading(),
				leftMaster.getEncoder().getPosition() / gearRatio * 2 * Math.PI * Units.inchesToMeters(3.0),
				rightMaster.getEncoder().getPosition() / gearRatio * 2 * Math.PI * Units.inchesToMeters(3.0));
		m_field.setRobotPose(odometry.getPoseMeters());

		SmartDashboard.putNumber("odometry x", odometry.getPoseMeters().getX());
		SmartDashboard.putNumber("odometry y", odometry.getPoseMeters().getY());
		SmartDashboard.putNumber("heading", -gyro.getAngle());
		SmartDashboard.putString("wheel speeds", getSpeeds().toString());
	}

	public DifferentialDriveWheelSpeeds getSpeeds() {
		return new DifferentialDriveWheelSpeeds(
				leftMaster.getEncoder().getVelocity() / gearRatio * 2 * Math.PI * Units.inchesToMeters(3.0) / 60,
				rightMaster.getEncoder().getVelocity() / gearRatio * 2 * Math.PI * Units.inchesToMeters(3.0) / 60);
	}

	public DifferentialDriveKinematics getKinematics() {
		return kinematics;
	}

	public Rotation2d getHeading() {
		return Rotation2d.fromDegrees(-gyro.getYaw());
	}

	public SimpleMotorFeedforward getFeedforward() {
		return feedForward;
	}

	public PIDController getLeftPIDController() {
		return leftPIDController;
	}

	public PIDController getRightPIDController() {
		return rightPIDController;
	}

	public void turnInPlace(double speed) {
		drive.arcadeDrive(0, speed);
	}

	public void arcadeDrive(double speed, double turnRate) {
		drive.arcadeDrive(-speed, .4 * turnRate);
	}

	public void drive(double left, double right) {
		// System.out.println("left: " + left + " right: " + right);
		drive.tankDrive(left, right);
	}

	public void setOutput(double leftVolts, double rightVolts) {
		leftMaster.set(leftVolts / 12);
		rightMaster.set(rightVolts / 12);
	}
	public void setBrakes(){
		leftMaster.setIdleMode(IdleMode.kBrake);
		rightMaster.setIdleMode(IdleMode.kBrake);

	}
	public void setCoast(){
		leftMaster.setIdleMode(IdleMode.kCoast);
		rightMaster.setIdleMode(IdleMode.kCoast);

	}
	public double getDirection(){
		double leftV = leftMaster.getEncoder().getVelocity();
		double rightV = -rightMaster.getEncoder().getVelocity();
		SmartDashboard.putNumber("Left V", leftV);
		SmartDashboard.putNumber("Right V", rightV);

		double average = (leftV + rightV)/2.0;
		SmartDashboard.putNumber("Average V", average);
		SmartDashboard.putBoolean("Direction", average>.1);

		return average;

	}

	public void curve(double speed, double turnRate, boolean quick) {
		// System.out.println(speed);
		// System.out.println(turnRate);

		// SmartDashboard.putNumber("right1", rightMaster.getEncoder().getVelocity());
		// SmartDashboard.putNumber("right2", rightSlave.getEncoder().getVelocity());
		// SmartDashboard.putNumber("left1", leftMaster.getEncoder().getVelocity());
		// SmartDashboard.putNumber("left2", leftSlave.getEncoder().getVelocity());

		drive.curvatureDrive(speed, turnRate, quick);
	}
}
