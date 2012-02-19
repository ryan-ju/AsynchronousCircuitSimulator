/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 *
 */
public class RoutingPoint extends Point implements Serializable {
	
	public RoutingPoint(){
		super();
	}
	
	public RoutingPoint(int x, int y) {
		super(x,y);
	}

	public RoutingPoint(Point p) {
		super(p);
	}
	
	public RoutingPoint copy(){
		return new RoutingPoint(this);
	}
}
