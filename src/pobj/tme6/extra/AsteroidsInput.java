package pobj.tme6.extra;

import pobj.tme6.memory.ICopyableMemory;

// Memory-mapped input
public class AsteroidsInput implements ICopyableMemory {

	static private boolean log = false;
	
	private boolean in[] = new boolean[8];
	private String name;
		
	public AsteroidsInput(String name) {
		this.name = name;
	}
	
	@Override
	public int size() {
		return 8;
	}

	@Override
	public int read(int addr) {
		if (log) {
			System.out.println("input " + name + " @" + addr);
		}
		return in[addr & 7] ? 0x80 : 0x7f;
	}

	@Override
	public void write(int addr, int value) {
		// nothing
	}
	
	public void setInput(int line, boolean value) {
		if (log) {
			System.out.println("set " + name + " @" + line + " = " + value);
		}
		in[line] = value;
	}
	
	@Override
	public AsteroidsInput copy() {
		AsteroidsInput r = new AsteroidsInput(name);
		r.in = in.clone();
		return r;
	}
}
