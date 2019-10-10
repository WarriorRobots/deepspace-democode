package frc.robot.util;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.autonomous.paths.AutoDrive;
import frc.robot.commands.autonomous.paths.AutoReverse;

/**
 * Add your docs here.
 */
public class AutoHandler {

    // object of this class
    private static AutoHandler instance = null;

    // Name of auto to run
    private String autoname = null;
    // The command to run for auto
    private Command autocommand = null;

    /**
     * @return The only instance of AutoHandler
     */
    public static AutoHandler getInstance() {
        if (instance == null) {
            instance = new AutoHandler();
        }
        return instance;
    }

    /**
     * Have AutoHandler select a case based on dashboard values
    */
    public void selectCase() {
        switch (DashboardHandler.getInstance().getAutoAction()) {
            case FORWARDS:
                switch (DashboardHandler.getInstance().getStartingHab()) {
                    case HAB1:
                        autoname = "ForwardsHab1";
                        break;
                    case HAB2:
                        autoname = "ForwardsHab2";
                        break;
                }
                break;
            case ROCKET:
                switch (DashboardHandler.getInstance().getStartingPosition()) {
                    case LEFT:
                        switch (DashboardHandler.getInstance().getStartingHab()) {
                            case HAB1:
                                autoname = "RocketLeftHab1";
                                break;
                            case HAB2:
                                autoname = "RocketLeftHab2";
                                break;
                        }
                        break;
                    case RIGHT:
                        switch (DashboardHandler.getInstance().getStartingHab()) {
                            case HAB1:
                                autoname = "RocketRightHab1";
                                break;
                            case HAB2:
                                autoname = "RocketRightHab2";
                                break;
                        }
                        break;
                    case MIDDLE:
                        // There is currently no middle rocket auto
                        break;
                }
                break;
            case CARGOSHIP:
                // There is currently no cargoship autos
                break;
            case NONE:
                // There is no action, this is purposely left blank
                break;
        }

        switch (DashboardHandler.getInstance().getTestAction()) {
            case BACKSTRAIGHTROCKET:
                autoname = "TESTBackStraightRocket";
                break;
            case FORWARDS:
                autoname = "TESTForwards";
                break;
            case LEFT90:
                autoname = "TESTLeft90";
                break;
            case RIGHT90:
                autoname = "TESTRight90";
                break;
            case ROCKETFRONTMID:
                autoname = "TESTRocketFrontMid";
                break;
            case ROCKETRIGHT:
                autoname = "TESTRocketRight";
                break;
            case TURNAROUND:
                autoname = "TESTTurnAround";
                break;
            case NONE:
                break;
        }

        // Only set autocommand to something if something was chosen for it above
        if (autoname != null) {
            autocommand = new AutoDrive(autoname);
        }
        //autocommand = new AutoReverse(autoname);
    }

    /**
     * Gives back the command selected in {@link #selectCase()} </p>
     * NOTE: this can return {@code null} however the scheduler can handle null and does nothing with it.
     * @return The command the AutoHandler has chosen
     */
    public Command getCase() {
        return autocommand;
    }

    /**
     * Resets the data in the AutoHandler
     */
    public void reset() {
        autoname = null;
        autocommand = null;
    }
}
