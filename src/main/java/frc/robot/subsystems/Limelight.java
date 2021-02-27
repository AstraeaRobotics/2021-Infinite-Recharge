package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight extends SubsystemBase {

  private String networkTableName;

  NetworkTable table;

  /**
    Constructs a new Limelight Object with network table name (to connect to the correct limelight). Default is "limelight"
   */

  public Limelight(String networkTableName) {

    this.networkTableName = networkTableName;

    table = NetworkTableInstance.getDefault().getTable(this.networkTableName);

    SmartDashboard.putNumber("Limelight object contructed", table.getEntry("tl").getDouble(0));

  }

  //set pipeline number (0-9 configured on limelight web dashboard)
  public void setPipeline(int pipelinenumber) throws IllegalArgumentException{

    if (pipelinenumber > 9 || pipelinenumber < 0) {
      throw new IllegalArgumentException();
    }

    table.getEntry("pipeline").setNumber(pipelinenumber);

  }

  //set limelight LED to on off or blink
  public void setLED (String status) throws IllegalArgumentException{

    if (status.equalsIgnoreCase("on")) {
      table.getEntry("ledMode").setNumber(3);
    }

    else if (status.equalsIgnoreCase("off")) {
      table.getEntry("ledMode").setNumber(1);
    }

    else if (status.equalsIgnoreCase("blink")) {
      table.getEntry("ledMode").setNumber(2);
    }

    else {
      throw new IllegalArgumentException();
    }

  }

  //set limelight camera to driver only mode (increases exposure turns off vision processing) (true or false)
  public void driverOnlyMode (boolean drivermode) {

    if(drivermode) {
      table.getEntry("camMode").setNumber(1);
    } else {
      table.getEntry("camMode").setNumber(0);
    }
 
  }

  //take a snapshot, can be viewed later from the limelight web dashboard
  public void takeSnapshot() {

    table.getEntry("snapshot").setNumber(1);
    table.getEntry("snapshot").setNumber(0);

  }

  //Whether the limelight has any valid targets 
  public boolean hasTarget() {

    double value = table.getEntry("tv").getDouble(0);
    if (value == 0) {
    return false;
    } else if (value == 1) {
      return true;
    } else {
      System.out.println("Error in getting target data; Limelight may not be connected");
      return false;
    }

  }

  //Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
  public double getHorizontalOffset () {
    return table.getEntry("tx").getDouble(0);
  }

  //Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
  public double getVerticalOffset () {
    return table.getEntry("ty").getDouble(0);
  }

  //Target Area (0% of image to 100% of image)
  public double getTargetArea() {
    return table.getEntry("ta").getDouble(0);
  }

  //Skew or rotation (-90 degrees to 0 degrees)
  public double getSkew() {
    return table.getEntry("ts").getDouble(0);
  }

  //The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
  public double getLatency() {
    return table.getEntry("tl").getDouble(0);
  }

//Gets the number of corners the limelight detects (WIP)
public double getNumOfCorners() {
 // return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tcornx").getDoubleArray().length();
 return 4;
}

//Vertical sidelength of the rough bounding box (0 - 320 pixels)
public double getVerticalSidelength() {
  return table.getEntry("tvert").getDouble(0);
}

//Horizontal sidelength of the rough bounding box (0 - 320 pixels)
public double getHorizontalSideLength() {
  return table.getEntry("thor").getDouble(0);
}

public double getDistance(){
  double h_goal = 13; //Height of the goal in inches
  double h_limeyboy = 5;
  double shooter_Angle = 12.5; //fixed shooter angle. TODO: experimentally determine this
  double distanceLimelight = (h_goal-h_limeyboy)/Math.tan(Math.toRadians(shooter_Angle + getVerticalOffset()));
  return distanceLimelight;
}

}