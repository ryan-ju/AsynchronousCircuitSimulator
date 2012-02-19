/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 *
 */
public interface ElementOverlappingDetector extends Serializable{
	/**
	 * Decide if ge overlaps other elements.
	 * @param ge
	 * @return true - overlap
	 */
	public boolean overlap(GroupElement ge);
	/**
	 * Decide if elt overlaps other elements.
	 * @param elt
	 * @return true - overlap
	 */
	public boolean overlap(Element elt);
}
