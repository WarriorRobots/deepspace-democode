/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CargoPickupSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.HatchPickupSubsystem;
import frc.robot.subsystems.HatchPlacerSubsystem;
import frc.robot.subsystems.LineFollowerSubsystem;
import frc.robot.subsystems.PneumaticBaseSubsystem;

/**
 * Main class of the Robot.
 */
public class Robot extends TimedRobot {
	
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final HatchPickupSubsystem hatchPickup = new HatchPickupSubsystem();
	public static final HatchPlacerSubsystem hatchPlacer = new HatchPlacerSubsystem();
	public static final ElevatorSubsystem elevator = new ElevatorSubsystem();
	public static final LineFollowerSubsystem lineFollowers = new LineFollowerSubsystem();
	public static final CargoPickupSubsystem cargoPickup = new CargoPickupSubsystem();
	public static final PneumaticBaseSubsystem pneumaticBase = new PneumaticBaseSubsystem();

	public static ControlHandler input;

	@Override
	public void robotInit() {
		input = new ControlHandler();
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(hatchPickup);
		SmartDashboard.putData(hatchPlacer);
		SmartDashboard.putData(lineFollowers);
		SmartDashboard.putData(cargoPickup);
		SmartDashboard.putData(pneumaticBase);
	}

	@Override
	public void robotPeriodic() {
		elevator.loop();
	}

	@Override
	public void disabledInit() {
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
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

}