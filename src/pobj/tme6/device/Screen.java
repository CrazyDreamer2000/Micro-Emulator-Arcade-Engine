package pobj.tme6.device;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class Screen extends PeriodicDevice implements IMemory {
	
	private RAM memoireRam;

	public Screen() {
		super(100);
		this.memoireRam = new RAM(10,32);
	}

	@Override
	public int size() {
		return memoireRam.size();
	}

	@Override
	public int read(int addr) {
		return memoireRam.read(addr);
	}

	@Override
	public void write(int addr, int value) {
		memoireRam.write(addr, value);

	}

	@Override
	public void action() {
		
	int i = 0;
	
	while(i < 10) {
		System.out.print((char)this.read(i));
		i++;
	}
	System.out.println();
	}

}
