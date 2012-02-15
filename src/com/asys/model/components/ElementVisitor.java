package com.asys.model.components;

public interface ElementVisitor {
	public void visit(Output op);
	public void visit(Input ip);
	public void visit(Environment env);
	public void visit(NotGate not);
	public void visit(AndGate and);
	public void visit(OrGate or);
	public void visit(NandGate nand);
	public void visit(NorGate nor);
	public void visit(XorGate xor);
	public void visit(MajorityGate maj);
	public void visit(CGate c);
	public void visit(FanOut fo);
}
