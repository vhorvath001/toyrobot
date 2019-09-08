package org.zonedigital.vikhor.toyrobot.commands;

import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

import lombok.ToString;

/**
 * Implementation of REPORT command
 * 
 * @author vikhor
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ToString
public class ReportCommand implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportCommand.class);
	
	@ToString.Exclude
	@Autowired
	@Qualifier("reportCommandStream")
	private PrintStream printStream;
	

	/**
	 * Reporting the position of the robot.
	 * @param toyRobotPosition The current position of the robot
	 */
	@Override
	public void execute(final ToyRobotPosition toyRobotPosition) {
		LOGGER.info("Executing a REPORT command on the position {}", toyRobotPosition);
		printStream.println(String.format("The robot's position is (%d,%d), facing %s.", 
				toyRobotPosition.getX(), toyRobotPosition.getY(), toyRobotPosition.getFacing().toString()));
	}

}
