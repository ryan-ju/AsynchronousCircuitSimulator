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
public class OrGate extends Element<OrGate>{
	protected OrGate(int x, int y, int w, int h, Direction ort, Property prop) {
		super(x,y,w,h,ort,prop);
	}
	
	@Override
	protected void setWidth(int width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setHeight(int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setOrientation(Direction ort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LogicValue evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(ElementVisitor ev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initializeProperty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrGate copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupElement getParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
