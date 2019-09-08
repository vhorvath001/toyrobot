package org.zonedigital.vikhor.toyrobot.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.zonedigital.vikhor.toyrobot.BorderChecker;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

@RunWith(SpringRunner.class)
public class MoveCommandTest {

	@Autowired
	private MoveCommand instance;
	@MockBean
	private BorderChecker mockedBorderChecker;
	
	
	@Test
	// (2,3) --EAST--> (3,3)
	public void testExecute_successful_toEast() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
				                                                      .y(3)
				                                                      .facing(FacingEnum.EAST).build();
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(3)).thenReturn(3);

		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// checking and verifying
		assertEquals(3, toyRobotPosition.getX());
		assertEquals(3, toyRobotPosition.getY());
		assertEquals(FacingEnum.EAST, toyRobotPosition.getFacing());

		verify(mockedBorderChecker, times(1)).check(3);
		verifyNoMoreInteractions(mockedBorderChecker);
	}


	@Test
	// (2,3) --NORTH--> (2,4)
	public void testExecute_successful_toNorth() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
				                                                      .y(3)
				                                                      .facing(FacingEnum.NORTH).build();
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(4)).thenReturn(4);

		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// checking and verifying
		assertEquals(2, toyRobotPosition.getX());
		assertEquals(4, toyRobotPosition.getY());
		assertEquals(FacingEnum.NORTH, toyRobotPosition.getFacing());

		verify(mockedBorderChecker, times(1)).check(4);
		verifyNoMoreInteractions(mockedBorderChecker);
	}


	@Test
	// (2,4) --NORTH--> (2,4)
	public void testExecute_notMoving_toNorth() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
				                                                      .y(4)
				                                                      .facing(FacingEnum.NORTH).build();
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(5)).thenThrow(new IllegalArgumentException("The value 5 is not in 0..4!"));

		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// checking and verifying
		assertEquals(2, toyRobotPosition.getX());
		assertEquals(4, toyRobotPosition.getY());
		assertEquals(FacingEnum.NORTH, toyRobotPosition.getFacing());

		verify(mockedBorderChecker, times(1)).check(5);
		verifyNoMoreInteractions(mockedBorderChecker);
	}


	@Test
	// (0,4) --WEST--> (0,4)
	public void testExecute_notMoving_toWest() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(0)
				                                                      .y(4)
				                                                      .facing(FacingEnum.WEST).build();
		
		// creating the expectations for mocks
		when(mockedBorderChecker.check(-1)).thenThrow(new IllegalArgumentException("The value -1 is not in 0..4!"));

		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// checking and verifying
		assertEquals(0, toyRobotPosition.getX());
		assertEquals(4, toyRobotPosition.getY());
		assertEquals(FacingEnum.WEST, toyRobotPosition.getFacing());

		verify(mockedBorderChecker, times(1)).check(-1);
		verifyNoMoreInteractions(mockedBorderChecker);
	}


	@Configuration
    @Import(MoveCommand.class)
    static class Config { }
}
