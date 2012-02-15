/**
 * 
 */
package com.asys.constants;

import com.asys.model.components.Protocol;

/**
 *
 */
public enum ElementPropertyKey {
	MIN_DELAY, MAX_DELAY, PROTOCOL;

	public static boolean isValueValid(ElementPropertyKey key, Object value) {
		if (key == MIN_DELAY && value instanceof Long) {
			return true;
		} else if (key == MAX_DELAY && value instanceof Long) {
			return true;
		} else if(key == PROTOCOL && value instanceof Protocol){
			return true;
		}
		return false;
	}
}
