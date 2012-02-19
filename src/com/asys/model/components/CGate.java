/**
 * 
 */
package com.asys.model.components;

import javax.lang.model.element.ElementVisitor;

import com.asys.constants.Direction;
import com.asys.constants.LogicValue;


/**
 *
 */
public class CGate extends Element<CGate>{

	
	protected CGate(int x, int y, int w, int h, Direction ort, Property prop) {
		super(x,y,w,h,ort,prop);
	}
	
	/* (non-Javadoc)
	 * @see com.asys.model.components.Element#setWidth(int)
	 */
	@Override
	protected void setWidth(int width) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.Element#setHeight(int)
	 */
	@Override
	protected void setHeight(int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.Element#setOrientation(int)
	 */
	@Override
	protected void setOrientation(Direction ort) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.Element#evaluate()
	 */
	@Override
	public LogicValue evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.Element#visit(javax.lang.model.element.ElementVisitor)
	 */
	@Override
	public void visit(ElementVisitor ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeProperty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CGate copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
