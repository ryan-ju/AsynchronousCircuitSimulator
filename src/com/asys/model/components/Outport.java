/**
 * 
 */
package com.asys.model.components;

import com.asys.constants.Direction;
import com.asys.constants.LogicValue;

/**
 *
 */
public class Outport extends Port{
	private LogicValue value;
	private static final long serialVersionUID = 336806750365269916L;

	@Override
	public LogicValue getLogicValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void setLogicValue(LogicValue lv){
		this.value = value;
	}

	public Outport copy() {
		// TODO
		return null;
	}

	@Override
	public Direction getOrientation() {
		return getParent().getOrientation();
	}
}
