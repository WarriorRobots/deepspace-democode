package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.cargo.FindArmZero;
import frc.robot.commands.debug.DebugRebootAll;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.CargoPickupSubsystem;
import frc.robot.subsystems.LedControllerSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchPickupSubsystem;
import frc.robot.subsystems.PneumaticLauncherSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.util.AutoHandler;
import frc.robot.util.DashboardHandler;
import frc.robot.subsystems.LineFollowerSubsystem;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {

	public static final ArmSubsystem arm = new ArmSubsystem();
	public static final CameraSubsystem camera = new CameraSubsystem();
	public static final CargoPickupSubsystem cargoPickupWheels = new CargoPickupSubsystem();
	public static final LedControllerSubsystem leds = new LedControllerSubsystem();
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static final HatchPickupSubsystem hatchPickupWheels = new HatchPickupSubsystem();
	public static final LineFollowerSubsystem lineFollowers = new LineFollowerSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final PneumaticLauncherSubsystem launchers = new PneumaticLauncherSubsystem();

	/** Reference this to get input from the joysticks and Xbox controller. */
	public static ControlHandler input;

	@Override
	public void robotInit() {
		input = new ControlHandler();
		SmartDashboard.putData(arm);
		SmartDashboard.putData(camera);
		SmartDashboard.putData(leds);
		SmartDashboard.putData(cargoPickupWheels);
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(hatchPickupWheels);
		SmartDashboard.putData(lineFollowers);
		SmartDashboard.putData(pneumatics);
		SmartDashboard.putData(launchers);
	}

	@Override
	public void robotPeriodic() {
		elevator.resetEncoderWhenFloored();
	}

	@Override
	public void disabledInit() {
		DebugRebootAll.rebootAll();
		Scheduler.getInstance().removeAll();
		AutoHandler.getInstance().reset();
		DashboardHandler.getInstance().init();
	}

	@Override
	public void disabledPeriodic() {
		camera.setPipeline(CameraSubsystem.PIPELINE_DRIVER);
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		AutoHandler.getInstance().selectCase();		
		Scheduler.getInstance().add(AutoHandler.getInstance().getCase());
		Scheduler.getInstance().add(new FindArmZero());
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}

}