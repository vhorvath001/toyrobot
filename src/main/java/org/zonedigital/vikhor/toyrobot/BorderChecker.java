package org.zonedigital.vikhor.toyrobot;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class BorderChecker {

	private Predicate<Integer> isInside = i -> i >= 0 && i<= 4;
	
	
	public int check(int x) {
		if (isInside.test(x)) {
			return x;
		} else {
			throw new IllegalArgumentException("The value " + x + " is not in 0..4!");
		}
	}
}
