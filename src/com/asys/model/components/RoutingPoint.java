/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 *
 */
public class RoutingPoint implements Serializable {
	private int x, y;

	public int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}
	
}
