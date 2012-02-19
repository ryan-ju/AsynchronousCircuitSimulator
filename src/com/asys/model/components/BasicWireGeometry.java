package com.asys.model.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.asys.constants.Direction;
import com.asys.model.components.exceptions.ViolationException;

/**
 * 
 */

/**
 *
 */
public class BasicWireGeometry implements WireGeometry {
	private final Wire wire;
	private LinkedList<RoutingPoint> rps, rps_copy;
	/*
	 * Acting as a link between the new and the original position of routing
	 * points during wire moving.
	 */
	private HashMap<RoutingPoint, RoutingPoint> rps_map;
	private int dx, dy;
	private RoutingPoint rpa, rpb; // They denote the last moved edge.
	private boolean moveCommitted, edgeMoveCommitted;

	/**
	 * 
	 * @param wire
	 * @return
	 */
	public static BasicWireGeometry getInstance(Wire wire) {
		return new BasicWireGeometry(wire);
	}

	private BasicWireGeometry(Wire wire) {
		this.wire = wire;
		rps = new LinkedList<RoutingPoint>();
		rps_map = new HashMap<RoutingPoint, RoutingPoint>();
		moveCommitted = true;
		edgeMoveCommitted = true;
		init();
	}

	/*
	 * Initialize the wire geometry. Initially, there are three edges, so four
	 * routing points. The reason is for the flexibility of the wire when its
	 * ports are moved.
	 * 
	 * There are two types of initial configuration:
	 * 
	 * 1. If the outport is point up or down. Then the middle edge is
	 * horizontal.
	 * 
	 * 2. If the outport is point left or right. Then the middle edge is
	 * vertical.
	 * 
	 * Note only the outport is considered, not the inport. This may lead to
	 * some artifacts, if the inport is not parallel with the outport. This can
	 * be improved in the future.
	 */
	private void init() {
		RoutingPoint rp_start = new RoutingPoint(getWire().getOutport()
				.getPosition());
		RoutingPoint rp_end = new RoutingPoint(getWire().getInport()
				.getPosition());
		Direction ort_start = getWire().getInport().getOrientation();
		// If the outport is pointing up or down. Then the middle edge should be
		// horizontal.
		RoutingPoint rp1, rp2;
		if (ort_start == Direction.UP || ort_start == Direction.DOWN) {
			int x1 = rp_start.getX();
			int y1 = (rp_start.getY() + rp_end.getY()) / 2;
			int x2 = rp_end.getX();
			int y2 = y1;
			rp1 = new RoutingPoint(x1, y1);
			rp2 = new RoutingPoint(x2, y2);
		} else { // The middle edge should be vertical.
			assert (ort_start == Direction.LEFT || ort_start == Direction.RIGHT);
			int x1 = (rp_start.getX() + rp_end.getX()) / 2;
			int y1 = rp_start.getY();
			int x2 = x1;
			int y2 = rp_end.getY();
			rp1 = new RoutingPoint(x1, y1);
			rp2 = new RoutingPoint(x2, y2);
		}
		rps.addLast(rp_start);
		rps.addLast(rp1);
		rps.addLast(rp2);
		rps.addLast(rp_end);
	}

	private WireGeometryManager getManager() {
		return BasicWireGeometryManager.getInstance();
	}

	/**
	 * 
	 */
	@Override
	public Wire getWire() {
		return wire;
	}

	/**
	 * 
	 */
	@Override
	public List<RoutingPoint> getRoutingPoints() {
		return rps;
	}

	/**
	 * 
	 */
	@Override
	public void setRoutingPoints(List<RoutingPoint> rps){
		assert rps.getClass() == LinkedList.class;
		this.rps = (LinkedList<RoutingPoint>) rps;
	}
	
	/**
	 * pt must be on an edge, otherwise it cannot be added. In fact, two points
	 * are added, with an edge of length 0;
	 */
	@Override
	public void addRoutingPoint(RoutingPoint pt) {
		int i = findIndex(pt);
		if (i > 0) {
			// Add two routing points.
			rps.add(i, pt);
			rps.add(i, pt);
		}
	}

	/**
	 * Given a point, find its appropriate index in the list of routing points.
	 * 
	 * @param pt
	 * @return An index in the list of routing points, or -1 if the point is not
	 *         on the wire.
	 */
	private int findIndex(Point pt) {
		int x = pt.getX();
		int y = pt.getY();
		for (int i = 0; i < rps.size() - 1; i++) {
			int x1 = rps.get(i).getX();
			int y1 = rps.get(i).getY();
			int x2 = rps.get(i + 1).getX();
			int y2 = rps.get(i + 1).getY();
			int min_x = Math.min(x1, x2);
			int max_x = Math.max(x1, x2);
			int min_y = Math.min(y1, y2);
			int max_y = Math.max(y1, y2);
			// If pt is on an edge.
			if ((x1 == x && x2 == x && min_y < x && x < max_y)
					|| (y1 == y && y2 == y && min_x < x && x < max_x)) {
				return i + 1;
			}
		}
		return -1;
	}

	/**
	 * This method only moves the internal routing points, not the end points.
	 * This method should only be called when both ports are moved.
	 * 
	 * Although this method automatically adjusts the wire in case the there is
	 * a difference between the wire's movement and the ports' movement, it is
	 * advised not to move the wire and the port in different ways.
	 */
	@Override
	public void moveWire(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		if (moveCommitted) { // If the previous move was committed, then a
									// new one should start.
			backupRoutingPoints();
			moveCommitted = false;
		}
		for (RoutingPoint rp : rps) {
			rp.setPosition(rps_map.get(rp).getX() + dx, rps_map.get(rp).getY()
					+ dy);
		}
	}

	/**
	 * 
	 */
	@Override
	public void cancelMoveWire() {
		setRoutingPoints(rps_copy);
//		try {
//			adjustForPort(getWire().getInport());
//		} catch (ViolationException e) {
//			// TODO Auto-generated catch block
//			System.out.println("This should not happen!");
//			e.printStackTrace();
//		}
//		try {
//			adjustForPort(getWire().getOutport());
//		} catch (ViolationException e) {
//			// TODO Auto-generated catch block
//			System.out.println("This should not happen!");
//			e.printStackTrace();
//		}
	}

	/**
	 * 
	 */
	private void backupRoutingPoints(){
		rps_map.clear();
		rps_copy = new LinkedList<RoutingPoint>();
		for (RoutingPoint rp:rps){
			RoutingPoint rp_copy = rp.copy();
			rps_map.put(rp, rp_copy);
			rps_copy.addLast(rp_copy);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private LinkedList<RoutingPoint> copyOldRoutingPoint(){
		return rps_copy;
	}
	
	/**
	 * @throws ViolationException
	 * 
	 */
	@Override
	public UndoableCommand commitMoveWire() throws ViolationException {
		if (getManager().overlap(this))
			throw new ViolationException("Wires overlap!");
		UndoableCommand cmd = new UndoableCommand() {
			private LinkedList<RoutingPoint> rps_backup;

			@Override
			public void run() {
				rps_backup = copyOldRoutingPoint();
				BasicWireGeometry.this.moveCommitted = true;
			}

			@Override
			public void cancel() {
				BasicWireGeometry.this.cancelMoveWire();
			}

			@Override
			public void undo() {
				BasicWireGeometry.this.setRoutingPoints(rps_backup);
			}

		};
		return cmd;
	}

	@Override
	public void movePort(Port port) throws ViolationException {
		if (moveCommitted){
			backupRoutingPoints();
			moveCommitted = false;
		}
		adjustForPort(port);
	}
	
	/**
	 * Adjust the position of the ends of the wire to follow the ports. This
	 * method tries to find an available slot to reroute the wire, but if no
	 * available slot is found, it throws an ViolationException.
	 * 
	 * @param port
	 *            The port should be the inport or the outport of the wire
	 *            associated with this wire geometry.
	 */
	private void adjustForPort(Port port) throws ViolationException {
		// 'port' is either inport or outport
		assert port == getWire().getInport() || port == getWire().getOutport();
		// The wire geometry has at least 4 routing points.
		assert rps.size() >= 4;

		RoutingPoint rp_port = new RoutingPoint(port.getPosition());

		RoutingPoint rpa, rpb, rpc;
		boolean isOutport = port == getWire().getOutport();
		int index_mid_edge = isOutport ? 1 : rps.size() - 3;
		int dir;
		if (isOutport) {
			rpa = rps.get(0);
			rpb = rps.get(1);
			rpc = rps.get(2);
		} else {
			rpa = rps.getLast();
			rpb = rps.get(rps.size() - 2);
			rpc = rps.get(rps.size() - 3);
		}

		// Test if the port is on another wire. If so, then it is obvious that
		// no non-overlapping routing can be found.
		if (getManager().overlap(port.getPosition())) {
			throw new ViolationException("Component's port is on another wire!");
		}

		if (rpa.getX() == rpb.getX()) { // The edge rpa-rpb is vertical
			rpa.setPosition(rp_port);
			rpb.setY(rpa.getY());
			int d = rpa.getX() - rpb.getX();
			dir = d < 0 ? -1 : 1;
			// 'ddx' denotes the relative shift of the edge. The direction is
			// determined by the direction of the rpa-rpb edge.
			// The slot follows the moving port's direction.
			int ddx = getManager().availableSlot(this, index_mid_edge,
					d < 0 ? Direction.LEFT : Direction.RIGHT);
			if (ddx < 0) // When there is no available slot.
				throw new ViolationException("Overlapping cannot be resolved!");
			else {
				rpb.setX(rpb.getX() + dir * ddx);
				rpc.setX(rpc.getX() + dir * ddx);
			}
		} else {
			assert rpa.getY() == rpb.getY(); // The edge rpa-rpb should be
												// horizontal
			rpa.setPosition(rp_port);
			rpb.setX(rpa.getX());
			int d = rpa.getY() - rpb.getY();
			dir = d < 0 ? -1 : 1;
			int ddx = getManager().availableSlot(this, index_mid_edge,
					d < 0 ? Direction.UP : Direction.DOWN);
			if (ddx < 0)
				throw new ViolationException("Overlapping cannot be resolved!");
			else {
				rpb.setY(rpb.getY() + dir * ddx);
				rpc.setY(rpc.getY() + dir * ddx);
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean isOnWire(int x, int y) {
		return (findIndex(new Point(x, y)) > 0);
	}

	/**
	 * Always returns false
	 */
	@Override
	public boolean isInBound(Rectangle bound) {
		return false;
	}

	@Override
	public void moveEdge(int x, int y, int dx, int dy) {
		int i = findIndex(new Point(x, y));
		if (i > 1 && i < rps.size() - 1) { // Do not move the first and the last
											// edge
			RoutingPoint rpa = rps.get(i - 1);
			RoutingPoint rpb = rps.get(i);
			this.rpa = rpa;
			this.rpb = rpb;
			if (rpa.getX() == rpb.getX()) { // Vertical edge
				rpa.setY(rpa.getY() + dy);
				rpb.setY(rpb.getY() + dy);
			} else {
				assert rpa.getY() == rpb.getY();
				rpa.setX(rpa.getX() + dx);
				rpb.setX(rpb.getX() + dx);
			}
		}
	}

	@Override
	public void cancelMoveEdge() {
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

	@Override
	public WireGeometry copy() {
		// TODO Auto-generated method stub
		return null;
	}



}
