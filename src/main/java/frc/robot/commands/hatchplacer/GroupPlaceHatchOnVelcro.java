package frc.robot.commands.hatchplacer;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.QuickAccessVars;

public class GroupPlaceHatchOnVelcro extends CommandGroup {

	/**
	 * Command to place a hatch on the velcro strips.
	 * @param safemode If this is set to true, the hatch cannot be placed without a
	 *                 signal from the line followers.
	 */
	public GroupPlaceHatchOnVelcro(boolean safemode) {
		addParallel(new SubgroupExtendLaunchers(safemode));
		addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
		addParallel(new LoosenScissors());
		addSequential(new WaitCommand(QuickAccessVars.PLACE_HATCH_DELAY));
		addSequential(new SubgroupRetractLaunchers());
	}

	@Override
	protected void initialize() {
		System.out.println("Pneumatics: Starting " + this.getClass().getSimpleName());
	}

	@Override
	protected void end() {
		System.out.println("Pneumatics: Finishing " + this.getClass().getSimpleName());
	}

	@Override
	protected void interrupted() {
		System.out.println("Pneumatics: Canceling " + this.getClass().getSimpleName()); // XXX if interrupted, fix launchers?
	}

	@Override
	public boolean isInterruptible() {
		return false; // returns false so the launcher pistons are unable to be stuck out
		// this is because if it cannot be interrupted the command must go to completion
		// making sure the retract launcher command runs
	}

}