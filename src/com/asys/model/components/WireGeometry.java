/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 */
public interface WireGeometry extends Serializable{
	public Wire getWire();
	public List<RoutingPoint> getRoutingPoints();
	void addRoutingPoint(RoutingPoint pt);
	void moveWire(int dx, int dy);
	void commitMoveWire();
	public boolean isOnWire(int x, int y);
	void moveEdge(int x, int y, int dx, int dy);
	void commitMoveEdge();
	boolean removeEdge(int x, int y);
}
