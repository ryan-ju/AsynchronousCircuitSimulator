/**
 * 
 */
package com.asys.model.components.exceptions;

import com.asys.model.components.Port;

/**
 *
 */
public class DuplicatePortException extends Exception {
	private Port port;
	public DuplicatePortException(Port port){
		this.port = port;
	}
	public Port getPort(){
		return port;
	}
}
