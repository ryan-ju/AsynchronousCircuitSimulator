/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;
import java.util.List;

import com.asys.model.components.exceptions.ViolationException;

/**
 *
 *
 */
public interface WireGeometry extends Serializable{
	public Wire getWire();
	public List<RoutingPoint> getRoutingPoints();
	void setRoutingPoints(List<RoutingPoint> rps);
	void addRoutingPoint(RoutingPoint pt);
	void moveWire(int dx, int dy);
	void cancelMoveWire();
	UndoableCommand commitMoveWire() throws ViolationException;
	public boolean isOnWire(int x, int y);
	public boolean isInBound(Rectangle bound);
	void movePort(Port port) throws ViolationException;
	void moveEdge(int x, int y, int dx, int dy);
	void cancelMoveEdge();
	void commitMoveEdge();
	boolean removeEdge(int x, int y);
	WireGeometry copy();
}
