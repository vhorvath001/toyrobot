package org.zonedigital.vikhor.toyrobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Setter
@Getter
@ToString
public class PlaceCommand implements Command {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceCommand.class);
	
	private int x;
	private int y;
	private FacingEnum facing;
	
	
	@Override
	public void execute(final ToyRobotPosition toyRobotPosition) {
		LOGGER.info("Executing a PLACE command ({},{},{}) on the position {}", x, y, facing, toyRobotPosition);
		toyRobotPosition.setX(this.x);
		toyRobotPosition.setY(this.y);
		toyRobotPosition.setFacing(this.facing);
	}

}
