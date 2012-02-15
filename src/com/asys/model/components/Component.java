/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 * 
 *
 */
public interface Component extends Serializable{
	void move(int x, int y);
	void commitMove();
	public Executor getExecutor();
	void setExecutor(Executor executor);
	public GroupElement getParent();
	void setParent(GroupElement ge);
	public boolean isInBound(int x, int y);
	public boolean isInBound(Rectangle bound);
	public void accept(ComponentVisitor cv);
}
