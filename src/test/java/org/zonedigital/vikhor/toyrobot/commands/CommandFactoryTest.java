package org.zonedigital.vikhor.toyrobot.commands;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.PrintStream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.utils.BorderChecker;

@RunWith(SpringRunner.class)
public class CommandFactoryTest {

	
	@MockBean
	private BorderChecker mockedBorderChecker;
	@Autowired
	private CommandFactory instance;	
	
	
	@Test
	// creating a PLACE command
	public void testGetCommand_PLACE_successful() {
		String instruction = "PLACE 2 , 3,  NORTH";
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(2)).thenReturn(2);
		when(mockedBorderChecker.check(3)).thenReturn(3);
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);
		
		// checking & verifying
		assertEquals(PlaceCommand.class, command.getClass());
		PlaceCommand placeCommand = (PlaceCommand) command;
		assertEquals(2, placeCommand.getX());
		assertEquals(3, placeCommand.getY());
		assertEquals(FacingEnum.NORTH, placeCommand.getFacing());
		
		verify(mockedBorderChecker, times(1)).check(2);
		verify(mockedBorderChecker, times(1)).check(3);
		verifyNoMoreInteractions(mockedBorderChecker);
	}
	
	
	@Test
	// failed to create a PLACE command -> out of border
	public void testGetCommand_PLACE_unsuccessful_outOfBorder() {
		String instruction = "PLACE 2 , 23,  NORTH";
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(2)).thenReturn(2);
		when(mockedBorderChecker.check(23)).thenThrow(new IllegalArgumentException("The value 23 is not in 0..4!"));
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);

		// checking & verifying
		assertEquals(DummyCommand.class, command.getClass());
		verify(mockedBorderChecker, times(1)).check(2);
		verify(mockedBorderChecker, times(1)).check(23);
		verifyNoMoreInteractions(mockedBorderChecker);
}

	
	@Test
	// failed to create a PLACE command -> not number
	public void testGetCommand_PLACE_unsuccessful_notNumber() {
		String instruction = "PLACE 2 , ,  NORTH";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);

		// checking & verifying
		assertEquals(DummyCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}

	
	@Test
	// failed to create a PLACE command -> not FacingEnum
	public void testGetCommand_PLACE_unsuccessful_notEnum() {
		String instruction = "PLACE 2 , ,  NORRTH";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);

		// checking & verifying
		assertEquals(DummyCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}
	
	
	@Test
	// creating a MOVE command
	public void testGetCommand_MOVE_successful() {
		String instruction = "move";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);
		
		// checking & verifying
		assertEquals(MoveCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}

	
	@Test
	// creating a REPORT command
	public void testGetCommand_REPORT_successful() {
		String instruction = "REPORT";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);
		
		// checking & verifying
		assertEquals(ReportCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}

	
	@Test
	// creating a RIGHT command
	public void testGetCommand_RIGHT_successful() {
		String instruction = "right";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);
		
		// checking & verifying
		assertEquals(RotateRightCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}

	
	@Test
	// creating a LEFT command
	public void testGetCommand_LEFT_successful() {
		String instruction = "LEFT";
		
		// calling the method to be tested
		Command command = instance.getCommand(instruction);
		
		// checking & verifying
		assertEquals(RotateLeftCommand.class, command.getClass());
		verifyNoMoreInteractions(mockedBorderChecker);
	}

	
	@After
	public void reset() {
		Mockito.reset(mockedBorderChecker);
	}
	
	@Configuration
	@ComponentScan
    @Import(CommandFactory.class)
    static class Config {
		@Bean(name = "reportCommandStream")
		public PrintStream reportCommandStream() {
			return System.out;
		}		
	}	
}
