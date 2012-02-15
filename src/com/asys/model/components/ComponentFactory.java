package com.asys.model.components;

import java.io.Serializable;

import com.asys.constants.LogicElement;


public class ComponentFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 160535780233540097L;

	public Element getNewElement(LogicElement le, Property p) {
		// TODO
		return null;
	}

	public Wire getNewWire(Inport ip, Outport op) {
		// TODO
		return null;
	}
}
