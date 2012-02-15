/**
 * 
 */
package com.asys.model.components;

import java.io.Serializable;

/**
 * 
 *
 */
public interface ComponentVisitor extends Serializable{
	public void visit(GroupElement ge);
	public void visit(Element ge);
	public void visit(Wire wire);
}
