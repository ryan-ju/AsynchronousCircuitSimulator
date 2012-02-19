/**
 * 
 */
package com.asys.constants;

/**
 *
 */
public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	public static Direction getOpposite(Direction dir){
		if (dir == UP) return DOWN;
		else if (dir == DOWN) return UP;
		else if (dir == LEFT) return RIGHT;
		else{
			assert dir == RIGHT;
			return LEFT;
		}
	}
}
