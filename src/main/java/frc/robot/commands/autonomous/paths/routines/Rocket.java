/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous.paths.routines;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Rocket extends CommandGroup {
  public Rocket(String autoname) {
    addSequential(new RocketDrive(autoname)); // drive to the target and turn the correct direction
    addParallel(new RocketElevator()); // at the same time move the elevator up after 2 seconds
    addSequential(new RocketPlace()); // drive with camera, place, and backup
  }
}
