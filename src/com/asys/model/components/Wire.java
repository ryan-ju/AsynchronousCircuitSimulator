/**
 * 
 */
package com.asys.model.components;

import com.asys.model.components.exceptions.ViolationException;

/**
 *
 */
public class Wire implements Component {
	private final WireGeometry geo;
	private Inport ip;
	private Outport op;
	private Executor ext;
	private GroupElement parent;

	/**
	 * 
	 * @return
	 */
	public static Wire getNewInstance() {
		return new Wire();
	}

	/**
	 * 
	 */
	private Wire() {
		this.geo = BasicWireGeometry.getInstance(this);
		ext = null;
		ip = null;
		op = null;
	}

	/**
	 * 
	 * @param geo
	 */
	private Wire(WireGeometry geo) {
		this.geo = geo;
		ext = null;
		ip = null;
		op = null;
	}

	/**
	 * 
	 * @param geo
	 * @param ext
	 * @param ip
	 * @param op
	 */
	private Wire(WireGeometry geo, Executor ext, Inport ip, Outport op) {
		this.geo = geo;
		this.ext = ext;
		this.ip = ip;
		this.op = op;
	}

	/**
	 * 
	 * @param geo
	 * @param ext
	 * @param parent
	 * @param ip
	 * @param op
	 */
	private Wire(WireGeometry geo, Executor ext, GroupElement parent,
			Inport ip, Outport op) {
		this.geo = geo;
		this.ext = ext;
		this.parent = parent;
		this.ip = ip;
		this.op = op;
	}

	/**
	 * 
	 * @return
	 */
	public WireGeometry getWireGeometry() {
		return geo;
	}

	/**
	 * @return
	 */
	public Inport getInport() {
		return ip;
	}

	/**
	 * 
	 * @param ip
	 */
	protected void setInport(Inport ip) {
		this.ip = ip;
	}

	/**
	 * 
	 * @return
	 */
	public Outport getOutport() {
		return op;
	}

	/**
	 * 
	 * @param op
	 */
	protected void setOutport(Outport op) {
		this.op = op;
	}

	/**
	 * 
	 * @return
	 */
	public Wire copy() {
		return new Wire(geo.copy(), ext, ip.copy(), op.copy());
	}

	// ====================================================
	// Interface Component
	// ====================================================
	/**
 * 
 */
	@Override
	public void move(int dx, int dy) {
		geo.moveWire(dx, dy);
	}
	
	public void cancelMove(){
		geo.cancelMoveWire();
	}

	/**
 * 
 */
	@Override
	public UndoableCommand commitMove() throws ViolationException{
		return geo.commitMoveWire();
	}

	/**
 * 
 */
	@Override
	public Executor getExecutor() {
		return ext;
	}

	/**
 * 
 */
	@Override
	public void setExecutor(Executor executor) {
		this.ext = executor;
	}

	/**
 * 
 */
	@Override
	public GroupElement getParent() {
		return parent;
	}

	/**
 * 
 */
	@Override
	public void setParent(GroupElement ge) {
		this.parent = ge;
	}

	/**
 * 
 */
	@Override
	public boolean isInBound(int x, int y) {
		return geo.isOnWire(x, y);
	}

	/**
 * 
 */
	@Override
	public boolean isInBound(Rectangle bound) {
		return geo.isInBound(bound);
	}

	/**
 * 
 */
	@Override
	public void accept(ComponentVisitor cv) {
		cv.visit(this);
	}
}
