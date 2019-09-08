package org.zonedigital.vikhor.toyrobot.utils;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

/**
 * The class is for checking if the position is on the table.
 * 
 * @author vikhor
 *
 */
@Component
public class BorderChecker {

	private static final int TABLE_SIZE = 5;
	
	private Predicate<Integer> isInside = i -> i >= 0 && i<= TABLE_SIZE-1;
	
	/**
	 * Checking if the position is on the table. If it is retrieving the position, throwing an exception otherwise.
	 * @param pos The position to be checked.
	 * @return The position that was passed.
	 * @throws IllegalArgumentException If the position is not on the table.
	 */
	public int check(int pos) {
		if (isInside.test(pos)) {
			return pos;
		} else {
			throw new IllegalArgumentException("The value " + pos + " is not in 0.." + (TABLE_SIZE-1) + "!");
		}
	}
}
