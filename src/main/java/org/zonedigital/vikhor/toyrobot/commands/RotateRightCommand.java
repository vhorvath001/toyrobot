package org.zonedigital.vikhor.toyrobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

import lombok.ToString;

/**
 * Implementation of RIGHT command
 * 
 * @author vikhor
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ToString
public class RotateRightCommand implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(RotateRightCommand.class);
	
	
	/**
	 * Rotating the robot right i.e. setting the facing in its current position.
	 * @param toyRobotPosition The current position of the robot
	 */
	@Override
	public void execute(final ToyRobotPosition toyRobotPosition) {
		LOGGER.info("Executing a ROTATE RIGHT command on the position {}", toyRobotPosition);
		toyRobotPosition.setFacing(toyRobotPosition.getFacing().rotateRight());
	}

}
