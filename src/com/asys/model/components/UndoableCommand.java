/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

import com.asys.model.components.exceptions.CancellationException;

/**
 *
 *
 */
public interface UndoableCommand extends Serializable {
	public void run() throws CancellationException;
	public void cancel();
	public void undo();
}
