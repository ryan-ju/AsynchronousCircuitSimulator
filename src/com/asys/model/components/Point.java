/**
 * 
 */
package com.asys.model.components;

/**
 *
 */
public class Point {
	private int x, y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}

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

	protected void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	protected void setPosition(Point pt) {
		this.x = pt.getX();
		this.y = pt.getY();
	}
}
