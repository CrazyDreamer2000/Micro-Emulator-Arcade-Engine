package pobj.tme6.memory;

public class RAM implements ICopyableMemory {
	private int[] tabRam;

	public RAM(int size , int init) {
		
		this.tabRam = new int[size];
		
		for(int i = 0 ; i < tabRam.length ; i++) {
			tabRam[i] = init;		
		}
	}

	@Override
	public int size() {
		return tabRam.length;
	}

	@Override
	public int read(int addr) {
		return tabRam[addr];
	}

	@Override
	public void write(int addr, int value) {
		tabRam[addr] = value;
	}

	@Override
	public ICopyableMemory copy() {
		
		RAM ram = new RAM(this.size(),0);
		
		for (int i = 0 ; i < this.size(); i++) {
			ram.tabRam[i] = this.tabRam[i];
		}
		
		return ram;
	}

}
