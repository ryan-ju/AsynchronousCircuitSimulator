/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

import com.asys.constants.LogicValue;


/**
 *
 */
public abstract class Port implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4501082920172606943L;
	
	public Element getParent(){
		// TODO
		return null;
		
	}
	
	protected void setParent(Element elt){
		// TODO
	}
	
	public Wire getWire(){
		// TODO
		return null;
		
	}
	
	protected void setWire(Wire wire){
		// TODO
	}
	
	abstract public LogicValue getLogicValue();
}
