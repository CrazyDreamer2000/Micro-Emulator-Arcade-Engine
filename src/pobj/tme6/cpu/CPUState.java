package pobj.tme6.cpu;

import pobj.tme6.memory.IMemory;

public class CPUState implements ICPUState {
	
	private IMemory memoire;
	private int PC;
	private int A;

	public CPUState(IMemory memoire) {
		this.memoire = memoire;
		this.PC = 0;
		this.A = 0;
	}
	@Override
	public int getPC() {
		return PC;
	}
	@Override
	public void setPC(int pc) {
		this.PC = pc;
	}
	@Override
	public int getA() {
		return A;
	}
	@Override
	public void setA(int a) {
		this.A = a;
	}
	@Override
	public IMemory getMemory() {
		return memoire;
	}

	@Override
	public int fetch() {
		
		int res = memoire.read(getPC());
		this.PC++;
		
		return res;
	}
}
