/**
 * 
 */
package com.asys.model.components;

import com.asys.constants.Direction;
import com.asys.model.components.exceptions.ViolationException;

/**
 *
 */
public interface WireGeometryManager {
	/**
	 * 
	 * @param geo
	 * @throws ViolationException
	 *             If the new wire cannot be added, due to , for instance,
	 *             overlapping with other wires.
	 */
	public void addWireGeometry(WireGeometry geo) throws ViolationException;

	/**
	 * 
	 * @param geo
	 * @return true - if a wire is removed.
	 */
	public boolean removeWireGeometry(WireGeometry geo);

	/**
	 * Tests if the wire overlaps other wires. Overlapping means any end point
	 * of an edge is on another edge (including end points).
	 * 
	 * @param geo
	 * @return true - if there is overlapping.
	 */
	public boolean overlap(WireGeometry geo);

	/**
	 * Tests if the point is on any edge (including end points).
	 * 
	 * @param point
	 * @return true - if point is on any edge.
	 */
	public boolean overlap(Point point);

	/**
	 * This method is used to find the available slot for the wire edge. The
	 * neighbouring edges are taken into account as well, so an available slot
	 * means that all three edges do not overlap any other edges.
	 * 
	 * Overlapping is defined to be the situation where an edge's end point is
	 * on another edge (including end points).
	 * 
	 * @param geo
	 *            The WireGeometry to find the available slot for.
	 * @param index
	 *            The edge to find the available slot for, starting from 0.
	 * @param dir
	 *            The direction to look for an available slot. It can be up,
	 *            down, left or right.
	 * @return The relative position of the available slot, from the first
	 *         vertex of the edge (the vertex of lower index) to the absolute
	 *         position of the slot, in the given direction. -1 means no
	 *         available slot is found.
	 * 
	 *         Caution: the wire can be vertical or horizontal, and the slot can
	 *         by in any of the four directions, so there are eight
	 *         combinations. Be sure to take care of all of the cases.
	 */
	public int availableSlot(WireGeometry geo, int index, Direction dir);
}
