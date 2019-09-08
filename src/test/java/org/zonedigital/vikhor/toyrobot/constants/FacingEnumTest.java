package org.zonedigital.vikhor.toyrobot.constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FacingEnumTest {

	
	@Test
	// rotating left from NORTH -> WEST
	public void testRotateLeft_NORTH() {
		assertEquals(FacingEnum.WEST, FacingEnum.NORTH.rotateLeft());
	}
	
	
	@Test
	// rotating left from SOUTH -> EAST
	public void testRotateLeft_SOUTH() {
		assertEquals(FacingEnum.EAST, FacingEnum.SOUTH.rotateLeft());
	}

	
	@Test
	// rotating right from WEST -> NORTH
	public void testRotateRight_WEST() {
		assertEquals(FacingEnum.NORTH, FacingEnum.WEST.rotateRight());
	}

	
	@Test
	// rotating right from EAST -> SOUTH
	public void testRotateRight_EAST() {
		assertEquals(FacingEnum.SOUTH, FacingEnum.EAST.rotateRight());
	}

}
