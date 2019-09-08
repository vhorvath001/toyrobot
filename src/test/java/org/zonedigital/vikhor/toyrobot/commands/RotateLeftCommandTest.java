package org.zonedigital.vikhor.toyrobot.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

public class RotateLeftCommandTest {

	private RotateLeftCommand instance = null;
	
	
	@Before
	public void setUp() {
		instance = new RotateLeftCommand();
	}
	
	
	@Test
	public void testExecute() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
				                                                      .y(3)
				                                                      .facing(FacingEnum.NORTH).build();
		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// check
		assertEquals(2, toyRobotPosition.getX());
		assertEquals(3, toyRobotPosition.getY());
		assertEquals(FacingEnum.WEST, toyRobotPosition.getFacing());
	}
}
