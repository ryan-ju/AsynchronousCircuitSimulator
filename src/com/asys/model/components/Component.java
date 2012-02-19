/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

import com.asys.model.components.exceptions.ViolationException;

/**
 * 
 *
 */
public interface Component extends Serializable {
	/**
	 * Move the component by dx, dy.
	 * 
	 * @param dx
	 * @param dy
	 */
	void move(int dx, int dy);

	/**
	 * Commit the move and inform any listeners of the change. If necessary,
	 * before committing the move, the component should check if the move is
	 * allowed.
	 * 
	 * @return
	 */
	UndoableCommand commitMove() throws ViolationException;

	public Executor getExecutor();

	void setExecutor(Executor executor);

	public GroupElement getParent();

	void setParent(GroupElement ge);

	public boolean isInBound(int x, int y);

	public boolean isInBound(Rectangle bound);

	public void accept(ComponentVisitor cv);
}
