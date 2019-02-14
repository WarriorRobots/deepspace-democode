/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the winch motor used to raise the elevator, and the limit switch
 * used to detect its lowest point.
 */
public class ElevatorSubsystem extends Subsystem {

  private static final int WINCH_PORT = 7;
  private static final int LIMIT_SWITCH_PORT = 4;
  // TODO move this to constants (and in CargoSubsystem)
  private static final int PID_ID = 0; // ID number of the PID profile
  private static final int TIMEOUT_MS = 10; // How long a Talon waits for signals before cancelling commands.

  private WPI_TalonSRX winch;
  private DigitalInput limitSwitch;

  /**
   * Instantiates new subsystem; make ONLY ONE.
   * <p>
   * <code> public static final ElevatorSubsystem elevator = new
   * ElevatorSubsystem();
   */
  public ElevatorSubsystem() {
    winch = new WPI_TalonSRX(WINCH_PORT);
    limitSwitch = new DigitalInput(LIMIT_SWITCH_PORT);

    winch.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    winch.config_kP(PID_ID, 0, TIMEOUT_MS);
    winch.config_kI(PID_ID, 0, TIMEOUT_MS);
    winch.config_kD(PID_ID, 0, TIMEOUT_MS);
  }

  /**
   * Moves the elevator to the position specified
   * 
   * @param position Intended position of the elevator, in (units)TODO
   */
  public void moveElevatorTo(double position) {
    winch.set(ControlMode.Position, position);
  }

  /**
   * Shuts off elevator winch motor.
   */
  public void stopElevator() {
    winch.stopMotor();
  }

  /**
   * Returns the position of the elevator in (ticks)TODO
   */
  public int getElevatorPosition() {
    return winch.getSelectedSensorPosition();
  }

  /**
   * Returns if the limit switch at the bottom of the elevator is being triggered.
   */
  public boolean isElevatorFloored() {
    return limitSwitch.get();
  }

  @Override
  public void initDefaultCommand() {
    // TODO
  }
}
