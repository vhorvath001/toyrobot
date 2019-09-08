package org.zonedigital.vikhor.toyrobot.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.zonedigital.vikhor.toyrobot.commands.Command;
import org.zonedigital.vikhor.toyrobot.commands.CommandFactory;


/**
 * The class is a bridge between the text instructions and the ToyRobotSimulation class.
 * 
 * @author vikhor
 *
 */
@Service
public class PuppetMaster {

	
	@Autowired
	private CommandFactory commandFactory;
	@Autowired
	private ToyRobotSimulation toyRobotSimulation;
	
	
	/**
	 * Loading the input resource (text file) that contains text instruction to control the robot.
	 * It creates commands from the instructions and call the ToyRobotSimulation to execute the commands.
	 * @param resource The resource that contains the text instructions.
	 * @throws IOException If the file cannot be loaded.
	 */
	public void act(Resource resource) throws IOException  {
		// read the text file
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			// convert the text instructions to commands
			List<Command> commands = reader.lines().map(i -> commandFactory.getCommand(i))
                                                   .collect(Collectors.toCollection(ArrayList::new));
			// call ToyRobotSimulation
			toyRobotSimulation.perform(commands);
		}
	}
}
