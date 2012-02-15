package com.asys.model.components;

import java.io.Serializable;

/**
 * 
 *
 */
public interface Executor extends Serializable{
	public Executor getInstance();
	public void execute(RedoableCommand cmd);
	public void execute(UnredoableCommand cmd);
	public boolean canUndo();
	public boolean canRedo();
	public void undo();
	public void redo();
}
