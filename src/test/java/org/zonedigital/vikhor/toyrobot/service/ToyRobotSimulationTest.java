package org.zonedigital.vikhor.toyrobot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.zonedigital.vikhor.toyrobot.commands.Command;
import org.zonedigital.vikhor.toyrobot.commands.MoveCommand;
import org.zonedigital.vikhor.toyrobot.commands.PlaceCommand;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

public class ToyRobotSimulationTest {

	
	private ToyRobotSimulation instance;
	
	
	@Before
	public void setUp() {
		instance = new ToyRobotSimulation();
	}
	
	
	@Test
	// Successful -> PLACE, MOVE
	public void testPerform_successful() {
		// creating expectations for mocks
		PlaceCommand mockedPlaceCommand = Mockito.mock(PlaceCommand.class);
		doNothing().when(mockedPlaceCommand).execute(any(ToyRobotPosition.class));
		
		MoveCommand mockedMoveCommand = Mockito.mock(MoveCommand.class);
		doNothing().when(mockedMoveCommand).execute(any(ToyRobotPosition.class));
		
		List<Command> commands = Arrays.asList(mockedPlaceCommand, mockedMoveCommand);

		// call the method to be tested
		instance.perform(commands);
		
		// verifying
		verify(mockedMoveCommand, times(1)).execute(any(ToyRobotPosition.class));
		verify(mockedPlaceCommand, times(1)).execute(any(ToyRobotPosition.class));
		verifyNoMoreInteractions(mockedMoveCommand, mockedPlaceCommand);
	}


	@Test
	// Successful -> MOVE, PLACE (the 1st command is ignored)
	public void testPerform_successful_ignored() {
		// creating expectations for mocks
		PlaceCommand mockedPlaceCommand = Mockito.mock(PlaceCommand.class);
		doNothing().when(mockedPlaceCommand).execute(any(ToyRobotPosition.class));

		MoveCommand mockedMoveCommand = Mockito.mock(MoveCommand.class);
		
		List<Command> commands = Arrays.asList(mockedMoveCommand, mockedPlaceCommand);

		// call the method to be tested
		instance.perform(commands);
		
		// verifying
		verify(mockedPlaceCommand, times(1)).execute(any(ToyRobotPosition.class));
		verifyNoMoreInteractions(mockedMoveCommand, mockedPlaceCommand);
	}
}
