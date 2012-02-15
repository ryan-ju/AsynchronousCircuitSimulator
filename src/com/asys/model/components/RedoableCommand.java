/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 *
 *
 */
public interface RedoableCommand extends Serializable {
	public void run();

	public void undoAction();
}
