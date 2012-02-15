/**
 * 
 */
package com.asys.model.components.exceptions;

import com.asys.constants.ElementPropertyKey;

/**
 *
 */
public class InvalidPropertyException extends Exception {
	private final ElementPropertyKey key;
	private final Object value;
	public InvalidPropertyException(ElementPropertyKey key, Object value){
		this.key=key;
		this.value=value;
	}
	
	public ElementPropertyKey getKey(){
		return key;
	}
	
	public Object getValue(){
		return value;
	}
}
