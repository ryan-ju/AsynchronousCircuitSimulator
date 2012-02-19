/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

import com.asys.constants.Direction;
import com.asys.constants.LogicValue;


/**
 *
 */
public abstract class Port implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4501082920172606943L;
	
	/**
	 * 
	 * @return
	 */
	public Element getParent(){
		// TODO
		return null;
		
	}
	
	/**
	 * 
	 * @param elt
	 */
	protected void setParent(Element elt){
		// TODO
	}
	
	/**
	 * 
	 * @return
	 */
	public Wire getWire(){
		// TODO
		return null;
		
	}
	
	/**
	 * 
	 * @param wire
	 */
	protected void setWire(Wire wire){
		// TODO
	}
	
	/**
	 * 
	 * @return
	 */
	public Point getPosition(){
		return getParent().getPortPosition(this);
	}
	
	/**
	 * 
	 * @return
	 */
	abstract public Direction getOrientation();
	
	/**
	 * 
	 * @return
	 */
	abstract public LogicValue getLogicValue();
}
