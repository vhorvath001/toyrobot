package org.zonedigital.vikhor.toyrobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.BorderChecker;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

import lombok.ToString;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ToString
public class MoveCommand implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(MoveCommand.class);
	
	@Autowired
	private BorderChecker borderChecker;

	
	@Override
	public void execute(final ToyRobotPosition toyRobotPosition) {
		LOGGER.info("Executing a MOVE command on the position {}", toyRobotPosition);
		try {
			switch(toyRobotPosition.getFacing()) {
				case NORTH: toyRobotPosition.setY(borderChecker.check(toyRobotPosition.getY() + 1));
				            break;
				case SOUTH: toyRobotPosition.setY(borderChecker.check(toyRobotPosition.getY() - 1));
	                        break;
				case EAST: toyRobotPosition.setX(borderChecker.check(toyRobotPosition.getX() + 1));
	                       break;
				case WEST: toyRobotPosition.setX(borderChecker.check(toyRobotPosition.getX() - 1));
	                       break;
			}
		} catch(IllegalArgumentException e) {
			LOGGER.info("Not moving as the robot is at the edge of the table...");
		}
	}

}
