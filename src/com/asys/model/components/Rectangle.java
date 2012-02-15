package com.asys.model.components;

import java.io.Serializable;

public class Rectangle implements Serializable {
	private int x, y, width, height;

	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public static boolean overlap(Rectangle r1, Rectangle r2){
		boolean bh = r1.getX()<(r2.getX()+r2.getHeight()) && r2.getY()<(r1.getY()+r1.getHeight());
		boolean bv = r1.getY()<(r2.getY()+r2.getWidth()) && r2.getY()<(r1.getY()+r1.getWidth());
		return bh && bv;
	}

}
