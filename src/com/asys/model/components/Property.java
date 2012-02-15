package com.asys.model.components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.asys.constants.ElementPropertyKey;
import com.asys.model.components.exceptions.InvalidPropertyException;
import com.asys.model.components.exceptions.InvalidPropertyKeyException;

/**
 * The class is used to describe the electrical and behavioral properties of a
 * circuit gate.
 */
public class Property implements Serializable {
	private Map<ElementPropertyKey, Object> map = new HashMap<ElementPropertyKey, Object>();

	/**
	 * 
	 * @param key
	 *            The key to search by.
	 * @return Object The value associated with the key.
	 * @throws InvalidPropertyKeyException
	 *             If no the Property does not contain the key.
	 */
	public Object getProperty(ElementPropertyKey key)
			throws InvalidPropertyKeyException {
		if (containsKey(key)) {
			return map.get(key);
		} else {
			throw new InvalidPropertyKeyException(key);
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @throws InvalidPropertyException
	 *             If the <key, value> pair does not match. See
	 *             {@link com.asys.constants.ElementPropertyKey#isValueValid(ElementPropertyKey, Object)}
	 *             .
	 * @throws InvalidPropertyKeyException If the key does not exist in the property.
	 */
	public void setProperty(ElementPropertyKey key, Object value)
			throws InvalidPropertyException, InvalidPropertyKeyException {
		if (containsKey(key)) {
			if (ElementPropertyKey.isValueValid(key, value)) {
				map.put(key, value);
			}else{
				throw new InvalidPropertyException(key, value);
			}
		} else {
			throw new InvalidPropertyKeyException(key);
		}
	}

	/**
	 * This method should be called only during the creation of a property.
	 * @param key
	 * @param value
	 * @throws InvalidPropertyException
	 */
	protected void addKey(ElementPropertyKey key, Object value)
			throws InvalidPropertyException {
		if (ElementPropertyKey.isValueValid(key, value)) {
			map.put(key, value);
		} else {
			throw new InvalidPropertyException(key, value);
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(ElementPropertyKey key) {
		return map.containsKey(key);
	}
}
