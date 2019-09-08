package org.zonedigital.vikhor.toyrobot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zonedigital.vikhor.toyrobot.commands.Command;
import org.zonedigital.vikhor.toyrobot.commands.CommandFactory;

@Service
public class PuppetMaster {

	
	@Autowired
	private CommandFactory commandFactory;
	@Autowired
	private ToyRobot toyRobot;
	
	
	public void act() throws IOException, URISyntaxException {
		try (Stream<String> instructions = Files.lines(Paths.get(ClassLoader.getSystemResource("journey.txt").toURI()))) {
			List<Command> commands = instructions.map(i -> commandFactory.getCommand(i))
                                                 .collect(Collectors.toList());
			toyRobot.perform(commands);
		}
	}
}
