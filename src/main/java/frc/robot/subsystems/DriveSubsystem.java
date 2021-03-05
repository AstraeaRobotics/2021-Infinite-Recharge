/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  CANSparkMax left1;
  CANSparkMax left2;
  CANSparkMax right1;
  CANSparkMax right2;
  //CANSparkMax rightB;

  DifferentialDrive drive;

  public DriveSubsystem() {
    right1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    right2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    left1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    left2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    // rightM = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    //rightB = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

    left2.follow(left1);
    // leftB.follow(leftF);
    right2.follow(right1);
    //rightB.follow(rightF);

    /* OR:
    
    SpeedControllerGroup(leftF, leftM, leftB)
    SpeedControllerGroup(rightF, rightM, rightB)

    */

    left1.getEncoder().setPosition(0.0);
    right1.getEncoder().setPosition(0.0);

    drive = new DifferentialDrive(left1, right1);
  }

  public void drive(double left, double right) {
    System.out.println("left: "+left+" right: "+right);
    drive.tankDrive(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
