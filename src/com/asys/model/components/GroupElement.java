/**
 * 
 */
package com.asys.model.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.asys.model.components.exceptions.EmptyCloneException;
import com.asys.model.components.exceptions.ViolationException;

/**
 *
 * 
 */
public class GroupElement implements Component {
	private int x, y, width, height, dx, dy;
	private ArrayList<Element> elements;
	private ArrayList<Wire> wires;
	private Executor ext;

	public static GroupElement getNewInstance() {
		return new GroupElement();
	}

	private GroupElement() {
		x = Integer.MAX_VALUE;
		y = Integer.MAX_VALUE;
		width = 0;
		height = 0;
		elements = new ArrayList<Element>();
		wires = new ArrayList<Wire>();
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int w) {
		this.width = w;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int h) {
		this.height = h;
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
			int x2 = Math.max(elt.getX() + elt.getWidth(), getX() + getWidth());
			int y2 = Math.max(elt.getY() + elt.getHeight(), getY()
					+ getHeight());
			setX(Math.min(elt.getX(), getX()));
			setY(Math.min(elt.getY(), getY()));
			setWidth(x2 - getX());
			setHeight(y2 - getY());
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

	/**
	 * 
	 * @return A copy of the GroupElement. Wires are included in the copy iff
	 *         the two end elements of the wire are in the group.
	 */

	public GroupElement copy() throws EmptyCloneException {
		if (elements.size() == 0)
			throw new EmptyCloneException();
		GroupElement group_copy = new GroupElement();
		ArrayList<Element> elts_copy = new ArrayList<Element>();
		ArrayList<Wire> wires_copy = new ArrayList<Wire>();
		// eltMap is used as a link between the original elements and their
		// copy.
		HashMap<Element, Element> eltMap = new HashMap<Element, Element>();
		// portMap is used as a link between the original ports and their
		// copies.
		HashMap<Port, Port> portMap = new HashMap<Port, Port>();

		// Establishing the links between the original and copied elements, and
		// the links between the original and copied ports.
		for (Element elt : elements) {
			Element elt_copy = elt.copy();
			elts_copy.add(elt_copy);
			elt_copy.setParent(group_copy); // Change parent.
			eltMap.put(elt, elt_copy);
			for (int i = 0; i < elt.getNumberOfInports(); i++) {
				portMap.put((Inport) elt.getInports().get(i), (Inport) elt_copy
						.getInports().get(i));
			}
			for (int i = 0; i < elt.getNumberOfOutports(); i++) {
				portMap.put((Outport) elt.getOutports().get(i), (Outport) elt
						.getOutports().get(i));
			}
		}

		// The following codes perform a graph search.
		ArrayList<Element> notSeen = (ArrayList<Element>) elements.clone();
		LinkedList<Element> eltTemp = new LinkedList<Element>();

		while (!notSeen.isEmpty()) { // Inv: eltTemp.isEmpty()
			eltTemp.add(notSeen.get(0));
			/*
			 * Inv: x is seen implies any x's neighbour y in the group is seen
			 * or in eltTemp, and wire x-y has been added to the copy. && elt is
			 * seen and is not in eltTemp anymore.
			 */
			while (!eltTemp.isEmpty()) {
				Element elt = eltTemp.removeFirst();
				notSeen.remove(elt);
				LinkedList<Object[]> eltNs = neighbours(elt);
				for (Object[] nb : eltNs) {
					// nb is not seen yet.
					if (notSeen.contains(nb[3])) {
						eltTemp.addLast((Element) nb[3]);
						// add a new wire to group_copy
						Wire wire_copy = ((Wire) nb[1]).copy();
						Inport ip_copy = (Inport) portMap.get((Port) nb[0]);
						Outport op_copy = (Outport) portMap.get((Port) nb[2]);
						wire_copy.setInport(ip_copy);
						wire_copy.setOutport(op_copy);
						wires_copy.add(wire_copy);
						wire_copy.setParent(group_copy); // Change parent.
					}
				}
			}
			/*
			 * Post condition: eltTemp.isEmpty(), so all of the elements
			 * reachable from the initial elt are seen, and wires added.
			 */
		}
		/*
		 * Post condition: All elements are seen, so all wires are properly
		 * copied.
		 */
		for (Element elt : elts_copy) {
			group_copy.addElement(elt);
		}
		for (Wire wire : wires_copy) {
			group_copy.addWire(wire);
		}
		return group_copy;
	}

	/**
	 * 
	 * @param elt
	 * @return ArrayList<Object[]>. Each element in the array list represents a
	 *         neighbour, which is represented as an Object[] instance of type
	 *         {Inport(of the wire), Wire, Outport(of the wire), Element(the
	 *         neighbour)}.
	 */
	private static LinkedList<Object[]> neighbours(Element elt) {
		LinkedList<Element> ns = new LinkedList<Element>();
		LinkedList<Object[]> nss = new LinkedList<Object[]>();
		List<Inport> ips = elt.getInports();
		for (Inport ip : ips) {
			Wire wire = ip.getWire();
			if (wire != null) { // Make sure the port has a wire
								// connected to.
				assert wire.getOutport() != null;
				Outport op_nb = wire.getOutport();
				Element nb = op_nb.getParent();
				if (!ns.contains(nb)) {
					ns.add(nb);
					nss.add(new Object[] { wire.getInport(), wire, op_nb, nb });
				}
			}
		}
		List<Outport> ops = elt.getOutports();
		for (Outport op : ops) {
			Wire wire = op.getWire();
			if (op.getWire() != null) {// Make sure the port has a wire
										// connected to.
				assert wire.getOutport() != null;
				Inport ip_nb = wire.getInport();
				Element nb = ip_nb.getParent();
				if (!ns.contains(nb)) {
					ns.add(nb);
					nss.add(new Object[] { ip_nb, wire, wire.getOutport(), nb });
				}
			}
		}
		return nss;
	}

	// ====================================================
	// Interface Component
	// ====================================================

	/**
	 * Move all elements and wires.
	 */
	@Override
	public void move(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		for (Element elt : elements) {
			elt.move(this.dx, this.dy);
		}
		for (Wire wire : wires) {
			wire.move(this.dx, this.dy);
		}
	}

	/**
	 * 
	 */
	@Override
	public UndoableCommand commitMove() {
		// TODO Not sure yet.
		CompositeCommand cmd = new CompositeCommand();

		try {
			for (Element elt : elements) {
				cmd.addCommand(elt.commitMove());
			}

			for (Wire wire : wires) {
				cmd.addCommand(wire.commitMove());
			}
		} catch (ViolationException e) {
			// Restore all elements' positions.
			for (Element elt : elements) {
				elt.cancelMove();
			}
			// Restore all wires' positions.
			for (Wire wire : wires) {
				wire.cancelMove();
			}
			return null;
		}
		return cmd;
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
	 * This method should not be called.
	 */
	@Override
	public GroupElement getParent() {
		return null;
	}

	/**
	 * This method should not be called.
	 */
	@Override
	public void setParent(GroupElement ge) {
		// Do nothing
	}

	/**
	 * 
	 */
	@Override
	public boolean isInBound(int x, int y) {
		if (x < getX() || x > (getX() + getWidth()) || y < getY()
				|| y > (getY() + getHeight())) { // As a first test
			return false;
		} else {
			for (Element elt : elements) {
				if (elt.isInBound(x, y))
					return true;
			}
			return false;
		}
	}

	/**
	 * This method always returns false.
	 */
	@Override
	public boolean isInBound(Rectangle bound) {
		return false;
	}

	@Override
	public void accept(ComponentVisitor cv) {
		cv.visit(this);
	}
}
