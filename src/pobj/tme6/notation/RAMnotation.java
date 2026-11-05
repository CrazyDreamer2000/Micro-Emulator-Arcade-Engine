package pobj.tme6.notation;

import pobj.tme6.memory.IMemory;

public class RAMnotation implements IMemory {

	private int[] data;
	
	public RAMnotation(int size, int val) {
		data = new int[size];
		for (int i=0; i<size; i++) {
			data[i] = val;
		}
	}
	
	@Override
	public int size() {
		return data.length;
	}

	@Override
	public int read(int addr) {
		return data[addr];
	}

	@Override
	public void write(int addr, int value) {
		data[addr] = value;
	}

}
