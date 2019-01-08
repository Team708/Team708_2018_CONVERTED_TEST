package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * this runs the automode for:
 * Start On Side of Field from driver station view 	= RIGHT
 * SwitchLocation 									= LEFT
 * ScaleLocation 									= RIGHT
 */
public class autoRight_LR extends CommandGroup {

    public autoRight_LR() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new Send("In autoRight_LR"));
    	addSequential(new WaitCommand(5.0));
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
