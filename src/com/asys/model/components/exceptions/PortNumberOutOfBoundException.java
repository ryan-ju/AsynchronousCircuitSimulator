/**
 * 
 */
package com.asys.model.components.exceptions;

/**
 *
 */
public class PortNumberOutOfBoundException extends Exception{
	private int max;
	public PortNumberOutOfBoundException(int max){
		this.max = max;
	}
	public int getMaxPortNumber(){
		return max;
	}
}
