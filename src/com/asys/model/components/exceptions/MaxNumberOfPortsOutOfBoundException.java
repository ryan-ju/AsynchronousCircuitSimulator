/**
 * 
 */
package com.asys.model.components.exceptions;

/**
 *
 */
public class MaxNumberOfPortsOutOfBoundException extends Exception {
	private int currentNumberOfPorts;

	public MaxNumberOfPortsOutOfBoundException(int num) {
		this.currentNumberOfPorts = num;
	}

	public int getCurrentNumberOfPorts() {
		return currentNumberOfPorts;
	}
}
