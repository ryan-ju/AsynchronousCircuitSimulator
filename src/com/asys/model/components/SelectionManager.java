package com.asys.model.components;

import java.io.Serializable;
import java.util.List;

public class SelectionManager{
	
	public ComponentManager getParent(){
		// TODO
		return null;
	}
	
	public void select(int x, int y){
		// TODO
	}
	
	public void select(Rectangle bound){
		// TODO
	}
	
	public void select(List<Component> comp){
		// TODO
	}
	
	public void commitSelect(){
		// TODO
	}
	
	public void deselect(){
		// TODO
	}
	
	public void addListener(SelectionManagerListener lst){
		// TODO
	}
	
	public boolean removeListener(SelectionManagerListener lst){
		// TODO
		return false;
	}
	
	public boolean removeAllListeners(){
		// TODO
		return false;
	}
	
	private void fireSelectionChanged(){
		// TODO
	}
}
