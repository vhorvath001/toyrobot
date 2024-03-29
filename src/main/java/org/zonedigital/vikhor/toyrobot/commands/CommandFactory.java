package org.zonedigital.vikhor.toyrobot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.constants.InstructionEnum;
import org.zonedigital.vikhor.toyrobot.utils.BorderChecker;


/**
 * Factory class for creating commands from text instruction
 * 
 * @author vikhor
 *
 */
@Component
public class CommandFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandFactory.class);
	
	@Autowired
	private BorderChecker borderChecker;
	
	
	/**
	 * Creating the command based on the input text instruction
	 * @param instruction Text that specifies the command to be constructed. For example: place 0,0,NORTH
	 * @return The command class
	 */
	public Command getCommand(String instruction) {
		Command command;
		try {
			if (instruction.toUpperCase().startsWith(InstructionEnum.PLACE.name())) {
				// getting the parameter of the PLACE instructions and convert them to their types (int,int,FacingEnum)
				String[] params = instruction.substring(InstructionEnum.PLACE.name().length()).split(",");
				int x = Integer.parseInt(checkStringValue(params[0]).strip());
				int y = Integer.parseInt(checkStringValue(params[1]).strip());
				FacingEnum facing = FacingEnum.valueOf(checkStringValue(params[2]).strip().toUpperCase());
				// creating and setting (checking the border values) the PLACE command
				PlaceCommand placeCommand = getPlaceCommand();
				placeCommand.setX(borderChecker.check(x));
				placeCommand.setY(borderChecker.check(y));
				placeCommand.setFacing(facing);
				command = placeCommand;
			} else if (instruction.toUpperCase().startsWith(InstructionEnum.MOVE.name())) {
				command = getMoveCommand();
			} else if (instruction.toUpperCase().startsWith(InstructionEnum.REPORT.name())) {
				command = getReportCommand();
			} else if (instruction.toUpperCase().startsWith(InstructionEnum.LEFT.name())) {
				command = getRotateLeftCommand();
			} else if (instruction.toUpperCase().startsWith(InstructionEnum.RIGHT.name())) {
				command = getRotateRightCommand();
			} else {
				command = getDummyCommand();
			}
		} catch(IllegalArgumentException|NullPointerException e) {
			LOGGER.warn("Exception occured during the command creation! Dummy command will be used. The text instruction in question is " + instruction, e);
			command = getDummyCommand();
		}
		LOGGER.debug("The instruction '{}' initiated the following command {}", instruction, command);
		return command;
	}
	
	
	private String checkStringValue(String s) {
		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("No string value was found!");
		}
		return s;
	}
	
	
	@Lookup
    public MoveCommand getMoveCommand() { return null; }
	@Lookup
    public ReportCommand getReportCommand() { return null; }
	@Lookup
    public PlaceCommand getPlaceCommand() { return null; }
	@Lookup
    public RotateLeftCommand getRotateLeftCommand() { return null; }
	@Lookup
    public RotateRightCommand getRotateRightCommand() { return null; }
	@Lookup
    public DummyCommand getDummyCommand() { return null; }
	
}
