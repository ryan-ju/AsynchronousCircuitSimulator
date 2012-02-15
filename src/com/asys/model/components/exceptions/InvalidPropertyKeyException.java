/**
 * 
 */
package com.asys.model.components.exceptions;

import com.asys.constants.ElementPropertyKey;

/**
 *
 */
public class InvalidPropertyKeyException extends Exception {
	private ElementPropertyKey key;
	public InvalidPropertyKeyException(ElementPropertyKey key){
		this.key=key;
	}
	public ElementPropertyKey getKey(){
		return key;
	}
}
