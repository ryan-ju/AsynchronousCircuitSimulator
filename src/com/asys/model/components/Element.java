package com.asys.model.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ElementVisitor;

import com.asys.constants.Constant;
import com.asys.constants.Direction;
import com.asys.constants.ElementPropertyKey;
import com.asys.constants.LogicElement;
import com.asys.constants.LogicValue;
import com.asys.model.components.exceptions.DuplicatePortException;
import com.asys.model.components.exceptions.InvalidPropertyException;
import com.asys.model.components.exceptions.InvalidPropertyKeyException;
import com.asys.model.components.exceptions.MaxNumberOfPortsOutOfBoundException;
import com.asys.model.components.exceptions.PortNumberOutOfBoundException;

/**
 *
 *
 */
public abstract class Element<E extends Element> implements Serializable {

	private static final long serialVersionUID = 8399162215079949487L;
	private int x, y, width, height, maxIP, maxOP;
	private Direction ort;
	private Property prop;
	private final List<Inport> ips;
	private final List<Outport> ops;

	// ====================================================
	// Constructors
	// ====================================================

	public static Element newInstance(int x, int y, LogicElement le) {
		Element elt;
		if (le == LogicElement.OUTPUT) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY, 0);
				prop.setProperty(ElementPropertyKey.MAX_DELAY, 0);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			elt = new Output(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 1);
			elt.setMaximumNumberOfPorts(true, 0);
		} else if (le == LogicElement.INPUT) {
			elt = new Input(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.LEFT, null);
			elt.setMaximumNumberOfPorts(true, 0);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.ENVIRONMENT) {
			elt = new Environment(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, null);
			elt.setMaximumNumberOfPorts(true, 1);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.NOT) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new NotGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 1);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.AND) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new AndGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.OR) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new OrGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.NAND) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new NandGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.NOR) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new NorGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.XOR) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new XorGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
			;
		} else if (le == LogicElement.MAJORITY) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new MajorityGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 3);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.C) {
			Property prop = new Property();
			try {
				prop.setProperty(ElementPropertyKey.MIN_DELAY,
						Constant.DEFAULT_MIN_DELAY);
				prop.setProperty(ElementPropertyKey.MAX_DELAY,
						Constant.DEFAULT_MAX_DELAY);
			} catch (InvalidPropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidPropertyKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elt = new XorGate(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, prop);
			elt.setMaximumNumberOfPorts(true, 2);
			elt.setMaximumNumberOfPorts(false, 1);
		} else if (le == LogicElement.FANOUT) {
			elt = new FanOut(x, y, Constant.DEFAULT_GATE_WIDTH,
					Constant.DEFAULT_GATE_HEIGHT, Direction.RIGHT, null);
			elt.setMaximumNumberOfPorts(true, 1);
			elt.setMaximumNumberOfPorts(false, 2);
		} else {
			return null;
		}
		// A subclass should define how itself will be initialized.
		elt.initializeProperty();
		return elt;
	}

	/**
	 * This method is only called in the factory method.
	 * 
	 * @param isInport
	 * @param num
	 */
	private void setMaximumNumberOfPorts(boolean isInport, int num) {
		try {
			if (isInport) {
				this.setMaxNumberOfInport(num);
			} else {
				this.setMaxNumberOfOutport(num);
			}
		} catch (MaxNumberOfPortsOutOfBoundException e) {
			// Should not happen, since the method is only used for
			// initialization.
			e.printStackTrace();
		}
	}

	protected Element(int x, int y, int w, int h, Direction ort, Property prop) {
		this(x, y, w, h, ort, prop, new ArrayList<Inport>(),
				new ArrayList<Outport>());
	}

	private Element(int x, int y, int w, int h, Direction ort, Property prop,
			ArrayList<Inport> ips, ArrayList<Outport> ops) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.ort = ort;
		this.prop = prop;
		this.ips = ips;
		this.ops = ops;
	}

	// ====================================================
	// Accessors
	// ====================================================

	public int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	protected abstract void setWidth(int width);

	public int getHeight() {
		return height;
	}

	protected abstract void setHeight(int height);

	public Direction getOrientation() {
		return ort;
	}

	protected abstract void setOrientation(Direction ort);

	abstract public E clone();

	public Property getProperty() {
		return prop;
	}

	protected void setProperty(ElementPropertyKey key, Object value)
			throws InvalidPropertyException, InvalidPropertyKeyException {
		prop.setProperty(key, value);
	}

	// ====================================================
	// Methods for inports
	// ====================================================

	/**
	 * 
	 * @throws DuplicatePortException
	 *             When ip is already contained as an inport.
	 * @throws PortNumberOutOfBoundException
	 *             When the maximum number of inports is exceeded.
	 */
	protected void addInport(Inport ip) throws DuplicatePortException,
			PortNumberOutOfBoundException {
		if (ips.contains(ip)) {
			throw new DuplicatePortException(ip);
		}
		if (ips.size() < this.getMaxNumberOfInport()) {
			ips.add(ips.size() - 1, ip);
		} else {
			throw new PortNumberOutOfBoundException(this.getMaxNumberOfInport());
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Inport> getInports() {
		return ips;
	}

	/**
	 * 
	 * @param ip
	 *            The inport to be removed.
	 * @return true - if the element contained ip
	 */
	protected boolean removeInport(Inport ip) {
		return ips.remove(ip);
	}

	/**
	 * 
	 * @return true - if there were inports in the element.
	 */
	protected boolean removeAllInports() {
		boolean b = ips.isEmpty();
		ips.clear();
		return b;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxNumberOfInport() {
		return maxIP;
	}

	/**
	 * 
	 * @param n
	 * @throws MaxNumberOfPortsOutOfBoundException
	 *             When n is smaller than the current number of inports.
	 */
	protected void setMaxNumberOfInport(int n)
			throws MaxNumberOfPortsOutOfBoundException {
		if (n < ips.size()) {
			throw new MaxNumberOfPortsOutOfBoundException(ips.size());
		}
		maxIP = n;
	}

	// ====================================================
	// Methods of outports
	// ====================================================

	/**
	 * 
	 * @throws DuplicatePortException
	 *             When op is already contained as an outport.
	 * @throws PortNumberOutOfBoundException
	 *             When the maximum number of outports is exceeded.
	 */
	protected void addOutport(Outport op) throws PortNumberOutOfBoundException,
			DuplicatePortException {
		if (ops.contains(op)) {
			throw new DuplicatePortException(op);
		}
		if (ops.size() < this.getMaxNumberOfOutport()) {
			ops.add(ops.size() - 1, op);
		} else {
			throw new PortNumberOutOfBoundException(
					this.getMaxNumberOfOutport());
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Outport> getOurports() {
		return ops;
	}

	/**
	 * 
	 * @param op
	 *            The outport to be removed.
	 * @return true - if the element contained op
	 */
	protected boolean removeOutport(Outport op) {
		return ops.remove(op);
	}

	/**
	 * 
	 * @return true - if there were outports in the element.
	 */
	protected boolean removeAllOutport() {
		boolean b = ops.isEmpty();
		ops.clear();
		return b;
	}

	/**
	 * 
	 * @return
	 */
	public int getumberOfOutport() {
		return ops.size();
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxNumberOfOutport() {
		return maxOP;
	}

	/**
	 * 
	 * @param n
	 * @throws MaxNumberOfPortsOutOfBoundException
	 *             When n is smaller than the current number of outports.
	 */
	protected void setMaxNumberOfOutport(int n)
			throws MaxNumberOfPortsOutOfBoundException {
		if (n < ops.size()) {
			throw new MaxNumberOfPortsOutOfBoundException(ops.size());
		}
		maxOP = n;
	}

	// ====================================================
	// Methods used by subclasses
	// ====================================================

	/**
	 * 
	 * @return A list of input logic values.
	 */
	protected List<LogicValue> getInputs() {
		ArrayList<LogicValue> inputs = new ArrayList<LogicValue>();
		for (Inport ip : ips) {
			inputs.add(ip.getLogicValue());
		}
		return inputs;
	}

	/**
	 * 
	 * @param lv
	 */
	protected void setOutput(LogicValue lv){
		for (Outport op:ops){
			op.setLogicValue(lv);
		}
	}
	
	
	/**
	 * 
	 * @return The output value evaluated using the current input values.
	 */
	public abstract LogicValue evaluate();

	/**
	 * Called when constructing an element.
	 */
	public abstract void initializeProperty();

	/**
	 * Visitor's pattern.
	 * @param ev
	 */
	public abstract void visit(ElementVisitor ev);

}
