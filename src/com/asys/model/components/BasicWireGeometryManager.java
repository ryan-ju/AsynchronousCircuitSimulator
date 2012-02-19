/**
 * 
 */
package com.asys.model.components;

import com.asys.model.components.exceptions.ViolationException;

/**
 *
 */
public class BasicWireGeometryManager implements WireGeometryManager {
	private static BasicWireGeometryManager wgm;
	
	public static BasicWireGeometryManager getInstance(){
		if (wgm == null) BasicWireGeometryManager.wgm = new BasicWireGeometryManager();
		return wgm;
	}
	/* (non-Javadoc)
	 * @see com.asys.model.components.WireGeometryManager#addWireGeometry(com.asys.model.components.WireGeometry)
	 */
	@Override
	public void addWireGeometry(WireGeometry geo) throws ViolationException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.WireGeometryManager#removeWireGeometry(com.asys.model.components.WireGeometry)
	 */
	@Override
	public boolean removeWireGeometry(WireGeometry geo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.asys.model.components.WireGeometryManager#overlap(com.asys.model.components.WireGeometry)
	 */
	@Override
	public boolean overlap(WireGeometry geo) {
		// TODO Auto-generated method stub
		return false;
	}

}
