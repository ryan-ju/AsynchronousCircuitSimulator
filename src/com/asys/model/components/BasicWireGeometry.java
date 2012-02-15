package com.asys.model.components;
import java.util.List;

import com.asys.model.components.WireGeometry;

/**
 * 
 */

/**
 *
 */
public class BasicWireGeometry implements WireGeometry {

	@Override
	public Wire getWire() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoutingPoint> getRoutingPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRoutingPoint(RoutingPoint pt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveWire(int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commitMoveWire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOnWire(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveEdge(int x, int y, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commitMoveEdge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeEdge(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
