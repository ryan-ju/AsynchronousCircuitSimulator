/**
 * 
 */
package com.asys.model.components;

import java.util.ArrayList;

/**
 *
 * 
 */
public class GroupElement implements Component {
	private int x, y, width, height;
	private ArrayList<Element> elements;
	private ArrayList<Wire> wires;

	public static GroupElement getNewInstance() {
		return new GroupElement();
	}

	private GroupElement() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		elements = new ArrayList<Element>();
		wires = new ArrayList<Wire>();
	}

	public int getX() {
		return x;
	}
	
	private void setX(int x){
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	private void setY(int y){
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Add an element to the group. All elements are unique.
	 * 
	 * @param elt
	 */
	protected void addElement(Element elt) {
		if (elements.contains(elt)) {
			// Do nothing
		} else {
			elements.add(elt);
			if (elt.getX()<getX()){
				
			}
		}
	}

	/**
	 * 
	 * @param elt
	 * @return Whether the group contained elt.
	 */
	protected boolean removeElement(Element elt) {
		return elements.remove(elt);
	}

	/**
	 * 
	 * @param elt
	 * @return
	 */
	public boolean containsElement(Element elt) {
		return elements.contains(elt);
	}

	/**
	 * Add a wire to the group. All wires are unique.
	 * 
	 * @param wire
	 */
	protected void addWire(Wire wire) {
		if (wires.contains(wire)) {
			// Do nothing
		} else {
			wires.add(wire);
		}
	}

	/**
	 * 
	 * @param wire
	 * @return Whether the group contained wire.
	 */
	protected boolean removeWire(Wire wire) {
		return wires.remove(wire);
	}

	/**
	 * 
	 * @param wire
	 * @return
	 */
	protected boolean containsWire(Wire wire) {
		return wires.contains(wire);
	}

	public GroupElement clone() {
		// TODO
		return null;
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commitMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public Executor getExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExecutor(Executor executor) {
		// TODO Auto-generated method stub

	}

	@Override
	public GroupElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(GroupElement ge) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInBound(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInBound(Rectangle bound) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void accept(ComponentVisitor cv) {
		// TODO Auto-generated method stub

	}
}
