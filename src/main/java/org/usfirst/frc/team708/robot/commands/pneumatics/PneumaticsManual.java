package org.usfirst.frc.team708.robot.commands.pneumatics;

//import java.awt.Robot;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsCube;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;

public class PneumaticsManual extends Command {


    public PneumaticsManual() {
    	requires(Robot.pneumaticsCube);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   
    	if (OI.driverGamepad.getButton(Gamepad.button_R_Shoulder)) {
    		Robot.pneumaticsCube.toggleIntake();
     	} else if (OI.driverGamepad.getButton(Gamepad.button_L_Shoulder)) {
     		Robot.pneumaticsCube.toggleIntake();
     	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
