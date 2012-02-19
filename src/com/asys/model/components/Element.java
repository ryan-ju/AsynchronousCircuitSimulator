package com.asys.model.components;

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
import com.asys.model.components.exceptions.ViolationException;

/**
 *
 *
 */
public abstract class Element<E extends Element> implements Component {

	private static final long serialVersionUID = 8399162215079949487L;
	private int x, y, x0, y0, width, height, maxIP, maxOP;
	private boolean moveCommitted;
	private Direction ort;
	private Property prop;
	private final List<Inport> ips;
	private final List<Outport> ops;
	private Executor ext;
	private GroupElement parent;

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
				this.setMaxNumberOfInports(num);
			} else {
				this.setMaxNumberOfOutports(num);
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

	protected void setPosition(int x, int y) {
		this.x = x;
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

	abstract public E copy();

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
		if (ips.size() < this.getMaxNumberOfInports()) {
			ips.add(ips.size() - 1, ip);
		} else {
			throw new PortNumberOutOfBoundException(
					this.getMaxNumberOfInports());
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
	public int getNumberOfInports() {
		return ips.size();
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxNumberOfInports() {
		return maxIP;
	}

	/**
	 * 
	 * @param n
	 * @throws MaxNumberOfPortsOutOfBoundException
	 *             When n is smaller than the current number of inports.
	 */
	protected void setMaxNumberOfInports(int n)
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
		if (ops.size() < this.getMaxNumberOfOutports()) {
			ops.add(ops.size() - 1, op);
		} else {
			throw new PortNumberOutOfBoundException(
					this.getMaxNumberOfOutports());
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Outport> getOutports() {
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
	protected boolean removeAllOutports() {
		boolean b = ops.isEmpty();
		ops.clear();
		return b;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfOutports() {
		return ops.size();
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxNumberOfOutports() {
		return maxOP;
	}

	/**
	 * 
	 * @param n
	 * @throws MaxNumberOfPortsOutOfBoundException
	 *             When n is smaller than the current number of outports.
	 */
	protected void setMaxNumberOfOutports(int n)
			throws MaxNumberOfPortsOutOfBoundException {
		if (n < ops.size()) {
			throw new MaxNumberOfPortsOutOfBoundException(ops.size());
		}
		maxOP = n;
	}

	// ====================================================
	// Interface Component
	// ====================================================

	private ElementOverlappingDetector getEOD() {
		return ComponentManager.getInstance();
	}

	/**
	 * 
	 */
	@Override
	public void move(int dx, int dy) {
		if (moveCommitted) { // If the previous move has been committed, i.e.,
								// the current move is a new one.
			x0 = x;
			y0 = y;
			moveCommitted = false;
		}
		setPosition(x0 + dx, y0 + dy);
	}

	/**
	 * 
	 */
	public void cancelMove() {
		setPosition(x0, y0);
		moveCommitted = true;
	}

	/**
	 * @throws ViolationException
	 * 
	 */
	@Override
	public UndoableCommand commitMove() throws ViolationException {
		if (getEOD().overlap(this))
			throw new ViolationException("Component overlapping!");

		UndoableCommand cmd = new UndoableCommand() {
			int x0_old, y0_old;

			@Override
			public void run() {
				x0_old = Element.this.x0;
				y0_old = Element.this.y0;
				Element.this.x0 = x;
				Element.this.y0 = y;
				Element.this.moveCommitted = true;
			}

			@Override
			public void cancel() {
				Element.this.cancelMove();
			}
			
			@Override
			public void undo() {
				Element.this.x = x0_old;
				Element.this.y = y0_old;
			}

		};
		return cmd;
	}

	/**
	 * 
	 */
	@Override
	public Executor getExecutor() {
		return ext;
	}

	/**
	 * 
	 */
	@Override
	public void setExecutor(Executor executor) {
		this.ext = executor;
	}

	/**
	 * 
	 */
	@Override
	public GroupElement getParent() {
		return parent;
	}

	/**
	 * 
	 */
	@Override
	public void setParent(GroupElement ge) {
		this.parent = ge;
	}

	/**
	 * 
	 */
	@Override
	public boolean isInBound(int x, int y) {
		boolean b = (x < getX()) || (x > (getX() + getWidth())) || (y < getY())
				|| (y > (getY() + getHeight()));
		return !b;
	}

	/**
	 * @return true - the element lies completely in bound.
	 */
	@Override
	public boolean isInBound(Rectangle bound) {
		if (getWidth() > bound.getWidth() || getHeight() > bound.getHeight()) { // Not
																				// in
																				// bound
			return false;
		} else {
			if (getX() >= bound.getX()
					&& (getX() + getWidth()) <= (bound.getX() + bound
							.getWidth())
					&& getY() >= bound.getY()
					&& (getY() + getHeight()) <= (bound.getY() + bound
							.getHeight())) { // Completely in bound.
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void accept(ComponentVisitor cv) {
		cv.visit(this);
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
	protected void setOutput(LogicValue lv) {
		for (Outport op : ops) {
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
	 * 
	 * @param port
	 * @return
	 */
	public abstract Point getPortPosition(Port port);

	/**
	 * Visitor's pattern.
	 * 
	 * @param ev
	 */
	public abstract void visit(ElementVisitor ev);

}
