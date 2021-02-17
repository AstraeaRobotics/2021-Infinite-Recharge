package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.*;

public class Limelight extends SubsystemBase {
  private String networktablename;
  /**
    Constructs a new Limelight Object with network table name (to connect to the correct limelight). Normally just "limelight"
   */
  public Limelight(String inputname) {
    networktablename = inputname;
  }
  //set pipeline number (0-9 configured on limelight web dashboard)
  public void setPipeline(int pipelinenumber) {
    NetworkTableInstance.getDefault().getTable(networktablename).getEntry("pipeline").setNumber(pipelinenumber);
  }
  //set limelight LED to on off or blink
  public void setLED (String status) {
    if (status.equalsIgnoreCase("on")) {
      NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ledMode").setNumber(3);
    }
    else if (status.equalsIgnoreCase("off")) {
      NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ledMode").setNumber(1);
    }
    else if (status.equalsIgnoreCase("blink")) {
      NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ledMode").setNumber(2);
    }
    else {
      System.out.println("Invalid argument passed to setLED");
    }
  }
  //set limelight camera to driver only mode (increases exposure turns off vision processing) (true or false)
  public void driverOnlyMode (boolean drivermode) {
    if (drivermode) {
      NetworkTableInstance.getDefault().getTable(networktablename).getEntry("camMode").setNumber(1);
    }else {
      NetworkTableInstance.getDefault().getTable(networktablename).getEntry("camMode").setNumber(0);
    }
 
  }
  //take a snapshot, can be viewed later from the limelight web dashboard
  public void takeSnapshot() {
    NetworkTableInstance.getDefault().getTable(networktablename).getEntry("snapshot").setNumber(1);
    NetworkTableInstance.getDefault().getTable(networktablename).getEntry("snapshot").setNumber(0);
  }
  //Whether the limelight has any valid targets 
  public boolean hasTarget() {
    double value = NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tv").getDouble(0);
    if (value == 0) {
    return false;
    } else if (value == 1) {
      return true;
    }else {
      System.out.println("Error in getting target data; Limelight may not be connected");
      return false;
    }
  }
  //Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
  public double getHorizontalOffset () {
    return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tx").getDouble(0);
  }
  //Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
  public double getVerticalOffset () {
    return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ty").getDouble(0);
  }
  //Target Area (0% of image to 100% of image)
  public double getTargetArea() {
    return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ta").getDouble(0);
  }
  //Skew or rotation (-90 degrees to 0 degrees)
  public double getSkew() {
    return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("ts").getDouble(0);
  }
  //The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
  public double getLatency() {
    return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tl").getDouble(0);
  }
//Gets the number of corners the limelight detects (WIP)
public double getNumOfCorners() {
 // return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tcornx").getDoubleArray().length();
 return 4;
}
//Vertical sidelength of the rough bounding box (0 - 320 pixels)
public double getVerticalSidelength() {
  return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("tvert").getDouble(0);
}
//Horizontal sidelength of the rough bounding box (0 - 320 pixels)
public double getHorizontalSideLength() {
  return NetworkTableInstance.getDefault().getTable(networktablename).getEntry("thor").getDouble(0);
}
public double getDistance(){
  double h_goal = 98.5; //Height of the goal in inches
  double shooter_Angle = 60; //fixed shooter angle. TODO: experimentally determine this
  double distanceLimelight = h_goal/Math.tan(shooter_Angle + getVerticalOffset());
  return distanceLimelight;
}
}