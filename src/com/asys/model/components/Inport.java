/**
 * 
 */
package com.asys.model.components;

import com.asys.constants.Direction;
import com.asys.constants.LogicValue;

/**
 *
 */
public class Inport extends Port {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352070127225621960L;

	@Override
	public LogicValue getLogicValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public Inport copy() {
		// TODO
		return null;
	}

	@Override
	public Direction getOrientation() {
		return Direction.getOpposite(getParent().getOrientation());
	}

}
