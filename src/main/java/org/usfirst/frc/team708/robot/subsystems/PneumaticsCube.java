package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.pneumatics.SqueezeCube;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

	
public class PneumaticsCube extends Subsystem {
	
	private DoubleSolenoid grabberSolenoid;
	private Solenoid intakeSolenoid;
	private boolean intake_on = false;
	private boolean clawClosed = false;
	
	/**
	 * Constructor
	 */
	public PneumaticsCube() {
		grabberSolenoid = new DoubleSolenoid(RobotMap.releaseGrabber, RobotMap.squeezeGrabber); //initializes
		intakeSolenoid = new Solenoid(RobotMap.intake);
		
		intakeSolenoid.set(false);
		intakeSolenoid.setPulseDuration(Constants.INTAKE_PULSE_TIME);		
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void initDefaultCommand() {
//		 setDefaultCommand(new SqueezeCube());
    }
	  public void toggleIntake()
	    {
	    	if(intakeSolenoid.get() == false) {
	    		grabberSolenoid.set(Value.kReverse);  // kReverse
	    		intakeSolenoid.set(true);
	    	}
	    	else {
	    		grabberSolenoid.set(Value.kForward);  // kForward
	    		intakeSolenoid.set(false);
	    	}
	    	switch_intake();
	    	clawClosed = !clawClosed;
	    }
	    
	  public boolean getClawPosition() {
		  return (clawClosed);
	  }
	  public void IntakeOn()
	    {
		  clawClosed = false;
		  intakeSolenoid.set(false);
  		grabberSolenoid.set(Value.kForward);
	    }
	  
	  public void IntakeOff()
	    {
		  clawClosed = true;
  		grabberSolenoid.set(Value.kReverse);
		  intakeSolenoid.set(true);
	    }
	  
	    //Activate Butterfly Solenoid for a set duration
	    public void pulseIntake() {
	    	intakeSolenoid.startPulse();
	    }
	    public void switch_intake()
	    {
	    	intake_on = !intake_on;
	    }
	
//	public void setPiston(Value value) {
//		exampleDoubleSolenoid.set(value);	
//	}	
//	
//	public void reverse(){
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kReverse);
//
//	}
//	
//	public void forward() {
//		exampleDoubleSolenoid.set(DoubleSolenoid.Value.kForward);
//	}
	
	public void sendToDashboard() {

//		SmartDashboard.putBoolean("In Soleniod", true);
		SmartDashboard.putBoolean("intake claw closed", getClawPosition());

		if (Constants.DEBUG) {
		}
	}
}