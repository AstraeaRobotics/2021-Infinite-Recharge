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

  CANSparkMax leftF;
  CANSparkMax leftM;
  CANSparkMax leftB;
  CANSparkMax rightF;
  CANSparkMax rightM;
  //CANSparkMax rightB;

  DifferentialDrive drive;

  public DriveSubsystem() {
    leftF = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    leftM = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    leftB = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    rightF = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    rightM = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    //rightB = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

    leftM.follow(leftF);
    leftB.follow(leftF);
    rightM.follow(rightF);
    //rightB.follow(rightF);

    /* OR:
    
    SpeedControllerGroup(leftF, leftM, leftB)
    SpeedControllerGroup(rightF, rightM, rightB)

    */

    leftF.getEncoder().setPosition(0.0);
    rightF.getEncoder().setPosition(0.0);

    drive = new DifferentialDrive(leftF, rightF);
  }

  public void drive(double left, double right) {
    drive.tankDrive(left, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
