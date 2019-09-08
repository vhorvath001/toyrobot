package org.zonedigital.vikhor.toyrobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

import lombok.ToString;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ToString
public class ReportCommand implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportCommand.class);
	
	
	@Override
	public void execute(final ToyRobotPosition toyRobotPosition) {
		LOGGER.info("Executing a REPORT command on the position {}", toyRobotPosition);
		System.out.println("\n----====************||||||||**********====----");
		System.out.println(String.format("The robot's position is (%d,%d), facing %s.", 
				toyRobotPosition.getX(), toyRobotPosition.getY(), toyRobotPosition.getFacing().toString()));
		System.out.println("**********************************************\n");
	}

}
