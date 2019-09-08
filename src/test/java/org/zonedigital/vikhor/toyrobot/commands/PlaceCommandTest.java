package org.zonedigital.vikhor.toyrobot.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;
import org.zonedigital.vikhor.toyrobot.domain.ToyRobotPosition;

public class PlaceCommandTest {

	private PlaceCommand instance = null;
	
	
	@Before
	public void setUp() {
		instance = new PlaceCommand();
	}
	
	
	@Test
	public void testExecute() {
		ToyRobotPosition toyRobotPosition = ToyRobotPosition.builder().x(2)
				                                                      .y(3)
				                                                      .facing(FacingEnum.EAST).build();
		instance.setX(1);
		instance.setY(4);
		instance.setFacing(FacingEnum.SOUTH);
		
		// calling the method to be tested
		instance.execute(toyRobotPosition);
		
		// check
		assertEquals(1, toyRobotPosition.getX());
		assertEquals(4, toyRobotPosition.getY());
		assertEquals(FacingEnum.SOUTH, toyRobotPosition.getFacing());
	}
}
