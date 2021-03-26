/*
 ____  ____  __  _  _  ____  ____  _  _  ____  ____  _  _  ____  ____  ____  _  _ 
(    \(  _ \(  )/ )( \(  __)/ ___)/ )( \(  _ \/ ___)( \/ )/ ___)(_  _)(  __)( \/ )
 ) D ( )   / )( \ \/ / ) _) \___ \) \/ ( ) _ (\___ \ )  / \___ \  )(   ) _) / \/ \
(____/(__\_)(__) \__/ (____)(____/\____/(____/(____/(__/  (____/ (__) (____)\_)(_/*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANEncoder;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.Constants.DriveConstants;

import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {

	CANSparkMax left1;
	CANSparkMax left2;
	CANSparkMax right1;
	CANSparkMax right2;

	DifferentialDrive drive;
    DifferentialDriveOdometry m_odometry;

    AHRS m_gyro;

    CANEncoder leftEncoder;
    CANEncoder rightEncoder;
    private final Field2d m_field = new Field2d();

	public DriveSubsystem() {
		right1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
		right2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
		left1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
		left2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

		left2.follow(left1);
		// leftB.follow(leftF);
		right2.follow(right1);
		// rightB.follow(rightF);

		leftEncoder = left1.getEncoder();
        rightEncoder = right1.getEncoder();

		drive = new DifferentialDrive(left1, right1);
		m_odometry = new DifferentialDriveOdometry(getHeading());
    
		resetEncoders();
		zeroHeading();

		try {
			m_gyro = new AHRS(SPI.Port.kMXP);
		} catch (Exception e){
			System.out.println("Error instantiating NavX " + e.toString());
		}
	}

  @Override
  public void periodic() {
    m_odometry.update(getHeading(), leftEncoder.getPosition(), rightEncoder.getPosition());
	logData();
  m_field.setRobotPose(m_odometry.getPoseMeters());
  }

  public void drive(double left, double right) {
	System.out.println("left: " + left + " right: " + right);
	drive.tankDrive(left, right);
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(),
        rightEncoder.getVelocity());
  }

  /**
   * Returns current wheel speed of the left wheel
   * 
   * @return The wheel speed of the left wheel
   */
  public double getLeftWheelSpeed() {
    return leftEncoder.getVelocity();
  }

   /**
   * Returns current wheel speed of the right wheel
   * 
   * @return The wheel speed of the right wheel
   */
  public double getRightWheelSpeed() {
    return rightEncoder.getVelocity();
  }

  /**
   * Returns the current Pose2d of the robot
   * 
   * @return the current Pose2d of the robot
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Resets the odometry to a specified pose and angle to 0deg
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }

  /**
   * Returns current position of the Left Encoder
   * @return The current encoder position of the left encoder
   */
  public double getEncoderValue() {
    return leftEncoder.getPosition();
  }

  /**
   * Returns the current position of the Right Encoder
   * @return The current encoder position of the right encoder
   */
  public double getRightEncoderValue() {
    return rightEncoder.getPosition();
  }

  /**
   * Resets Gyros to 0 degrees
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Resets left and right encoders to a position of 0
   */
  public void resetEncoders() {
    leftEncoder.setPosition(0.0);
    rightEncoder.setPosition(0.0);
  }

  /**
   * Returns the current turn rate of the Robot
   * @return The current turn rate of the Robot
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }

  /**
   * Returns the heading of the robot.
   * @return the robot's heading in a Rotation2D
   */
  public Rotation2d getHeading() {
	return m_gyro.getRotation2d();
  }

  public void logData() {
    SmartDashboard.putData("Field", m_field);
    SmartDashboard.putNumber("heading", getHeading().getDegrees());
    SmartDashboard.putNumber("turn rate", getTurnRate());
    SmartDashboard.putString("wheel speeds", getWheelSpeeds().toString());
    SmartDashboard.putString("robot pose",m_odometry.getPoseMeters().toString());
  }

  /*
  	TRAJECTORY GENERATION AND TRACKING
  */

  /**
   * Generate a Trajectory from a given path to the trajectory
   * @param path the path to the Trajectory json file
   * @return The trajectory object to be used
   */
  public Trajectory generateTrajectory(String path) throws IOException {

    Trajectory trajectory = new Trajectory();

    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(path);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      return trajectory;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {

    SmartDashboard.putNumber("left volts", leftVolts);
    SmartDashboard.putNumber("right volts", rightVolts);

    left1.setVoltage(leftVolts);
    right1.setVoltage(-rightVolts);
    drive.feed();
  }

}
