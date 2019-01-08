package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	@author Viet Tran
 */
public class VisionProcessor extends Subsystem {
	
	public static Drivetrain 		drivetrain;
	
	private boolean isCentered 	= false;
	private boolean led 		= false;

//	Required Network Table Data 	
	private boolean hasTarget;	//Get from Network Table
	private double tv;
	private double displacementX;	//Get from Network Table

//	Accessing the Limelight's Network Table	
	NetworkTableInstance 	limeLightInstance 	= NetworkTableInstance.getDefault();
	NetworkTable			limeLightTable		= limeLightInstance.getTable("/limelight");
	
//	Sweep function variables
//	private double gyroAngle = 0.0;
    	
	private double rotate			= 0.0;
//	private double move			= 0.0;
	
//	Method for getting different data from a Network Table	
	public double getNTInfo(String tableInfo) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		return limeLightEntry.getDouble(0);	
		 
	}
	
//	Method for setting different data into a Network Table    
	public void setNTInfo(String tableInfo, Double setValue) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		limeLightEntry.setNumber(setValue);
	}
	
	public VisionProcessor() {
		super("Vision Processor");
	}

	public boolean hasTarget() {
		tv = getNTInfo("tv");
		if (tv != 0.0) {
			hasTarget = true;
		}
		else {
			hasTarget = false;
		}
		return hasTarget;
	}
	public boolean isCentered() {
	
		displacementX = getNTInfo("tx");
	
		if (Math.abs(displacementX) <= AutoConstants.X_THRESHOLD) {
			isCentered = true;
		}
		else {
			isCentered = false;
		}
		return isCentered;
	}
	public double getRotate() {	
		rotate=0.0;
		isCentered();
		if (hasTarget()) {
			if (!isCentered())	
			     if (displacementX > 0)
				     rotate = .8;
			     else 
			 	    rotate = -.8;
			 else // centered
				rotate= 0.0;
		} 
		return rotate;
	}
	public void toggleLEDMode() {
		led = !led;
		if(led) {
			setNTInfo("ledMode", Constants.VISION_LED_ON);
		}
		else {
			setNTInfo("ledMode", Constants.VISION_LED_OFF);
		}
	}
//	Method for moving towards a target	-NOT USED IN 2018
	//Returns how to move to get to target distance (targetAmount = target ratio)
	
/*	public double getMove() {
		double move;
		
		if (hasTarget) 
		{
			double difference = targetY - currentY;			
			move = Math708.getSignClippedPercentError(currentY, targetY, 0.4, 0.6); 
			//Check if target is at correct level within threshold
			if (difference <= thresholdY) {
				move = 0.0;
				isAtY = true;
			} else {
				isAtY = false;
			}
			
		} else {
 			move = 0.0;
		}
		
		return move;
	}
*/

	public void sendToDashboard() {		//Might have to rewrite public variables for the smart dashboard...
										//Future me problem		-Viet
		
		SmartDashboard.putBoolean("Is Centered", isCentered());
		SmartDashboard.putNumber("Displacement X", displacementX);
		SmartDashboard.putBoolean("Has Target", hasTarget());
		SmartDashboard.putNumber("TV", tv);
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
    
}

