/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 *
 *
 */
public interface UnredoableCommand extends Serializable{
	public void run();
}
