package org.zonedigital.vikhor.toyrobot.commands;

import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

public interface Command {

	public void execute(ToyRobotPosition toyRobotPosition);
	
}
