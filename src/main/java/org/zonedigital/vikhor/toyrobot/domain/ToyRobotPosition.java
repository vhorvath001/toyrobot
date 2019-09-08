package org.zonedigital.vikhor.toyrobot.domain;

import org.zonedigital.vikhor.toyrobot.constants.FacingEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Domain object to store the position of the robot.
 * 
 * @author vikhor
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToyRobotPosition {

	private int x;
	private int y;
	private FacingEnum facing;
		
}
