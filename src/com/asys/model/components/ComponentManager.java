package com.asys.model.components;

import java.io.Serializable;
import java.util.List;

import com.asys.constants.LogicElement;


public class ComponentManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8795335385829917382L;
	
	public Executor getExecutor(){
		// TODO
		return null;
	}
	
	public void setExecutor(Executor ext){
		// TODO
	}
	
	public ComponentFactory getComponentFactory(){
		// TODO
		return null;
	}
	
	public void setComponentFactory(ComponentFactory fac){
		// TODO
	}
	
	public void addComponent(Component comp){
		// TODO
	}
	
	public void addElement(Element elt, int x, int y){
		// TODO
	}
	
	public void addWire(Wire wire, Inport ip, Outport op){
		// TODO
	}
	
	public void commitAdd(){
		// TODO
	}
	
	public List<Component> getComponents(){
		// TODO
		return null;
	}
	
	public List<Element> getElementsByType(LogicElement elt){
		// TODO
		return null;
	}
	
	public List<Element> getElements(){
		// TODO
		return null;
	}
	
	public List<Wire> getWires(){
		// TODO 
		return null;
	}
	
	public Component getComponentAt(int x, int y){
		// TODO
		return null;
	}
	
	public Component getComponentAt(Rectangle bound){
		// TODO 
		return null;
	}
	
	/**
	 * 
	 */
	
	public static ComponentManager getInstance(){
		// TODO
		return null;
	}
	
	public static ComponentManager getNewInstance(){
		// TODO
		return null;
	}
	
	public SelectionManager getSelectionManager(){
		// TODO
		return null;
	}
}
