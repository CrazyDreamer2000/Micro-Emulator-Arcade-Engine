package pobj.tme6.memory;

public class MemorySlot {
	
	private int addresseBase;
	private IMemory memory;

	public MemorySlot(int addr , IMemory memo) {
		this.addresseBase = addr;
		this.memory = memo;
	}
	
	public Integer getBaseAddress() {
		return addresseBase;
		
	}
	
	public IMemory getMemory() {
		return memory;
	}
	
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if( o instanceof MemorySlot m) {
			return (Integer.compare(this.getBaseAddress(), m.getBaseAddress()) == 0) && (this.getMemory() == m.getMemory());
		}
		return false;
		
	}

}
