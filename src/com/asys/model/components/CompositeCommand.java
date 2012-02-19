/**
 * 
 */
package com.asys.model.components;

/**
 *
 */
public class CompositeCommand implements UndoableCommand {

	/* (non-Javadoc)
	 * @see com.asys.model.components.RedoableCommand#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see com.asys.model.components.RedoableCommand#undo()
	 */
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	
	public void addCommand(UndoableCommand cmd){
		// TODO
	}

	

}
