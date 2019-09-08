package org.zonedigital.vikhor.toyrobot.commands;

import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

/**
 * Interface for implementing commands of toy robot
 * 
 * @author vikhor
 *
 */
public interface Command {

	public void execute(ToyRobotPosition toyRobotPosition);
	
}
