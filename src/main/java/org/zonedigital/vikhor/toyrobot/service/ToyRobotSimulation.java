package org.zonedigital.vikhor.toyrobot.service;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.zonedigital.vikhor.toyrobot.commands.Command;
import org.zonedigital.vikhor.toyrobot.commands.PlaceCommand;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

/**
 * Main interface class to receive commands to control the toy robot.
 * 
 * @author vikhor
 *
 */
@Service
public class ToyRobotSimulation {

	
	private ToyRobotPosition toyRobotPosition = new ToyRobotPosition();
	
	
	/**
	 * Executing the input commands in sequential order.
	 * Checking if the 1st command is a PLACE
	 * @param commands The command list that will be executed one by one.
	 * @throws IllegalArgumentException if the 1st command is not a PLACE
	 */
	public void perform(List<Command> _commands) {
		if (_commands != null) {
			// search the first occurrence of PLACE
			// the list is an ArrayList so getting an element is O(1) 
			OptionalInt indexOpt = IntStream.range(0, _commands.size())
				                            .filter(i -> _commands.get(i) instanceof PlaceCommand)
				                            .findFirst();
			if (indexOpt.isPresent()) {
				// process the arrived commands from the first PLACE command
				for(Command command : _commands.subList(indexOpt.getAsInt(), _commands.size())) {
					command.execute(toyRobotPosition);
				}
			}
		}
	}
	
}
