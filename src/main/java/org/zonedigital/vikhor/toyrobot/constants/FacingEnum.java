package org.zonedigital.vikhor.toyrobot.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * The values of facing
 * 
 * @author vikhor
 *
 */
public enum FacingEnum {

	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3);
	
	private int order;

	private static final Map<Integer, FacingEnum> lookupByOrder = new HashMap<>();
	
	static {
		for(FacingEnum f : FacingEnum.values()) {
			lookupByOrder.put(f.order, f);
		}
	}
	
	
	private FacingEnum(int order) {
		this.order = order;
	}

	
	/**
	 * Getting the facing value if rotating left.
	 * @return The facing value after the rotation.
	 */
	public FacingEnum rotateLeft() {
		return this == NORTH ? WEST : lookupByOrder.get(this.order-1);
	}
	
	/**
	 * Getting the facing value if rotating right.
	 * @return The facing value after the rotation.
	 */
	public FacingEnum rotateRight() {
		return this == WEST ? NORTH : lookupByOrder.get(this.order+1);
	}

}
