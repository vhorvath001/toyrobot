package org.zonedigital.vikhor.toyrobot;

import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zonedigital.vikhor.toyrobot.commands.Command;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

@Service
public class ToyRobot {

	
	private ToyRobotPosition toyRobotPosition = new ToyRobotPosition();
	
	
	public void perform(List<Command> commands) {
		for(Command command : commands) {
			command.execute(toyRobotPosition);
		}
	}
	
}
