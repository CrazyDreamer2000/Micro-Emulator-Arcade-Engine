package pobj.tme6.memory;

public class AddressMask implements IMemory {
	private IMemory mem;
	private int size;
	private int mask;

	public AddressMask(IMemory mem , int size , int mask) {
		this.mem = mem;
		this.size = size;
		this.mask = mask;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int read(int addr) {
		return mem.read(addr & mask);
	}

	@Override
	public void write(int addr, int value) {
		mem.write(addr & mask, value);
	}

}
