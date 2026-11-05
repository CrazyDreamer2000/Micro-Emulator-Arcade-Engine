package pobj.tme6.notation;

public class ROMnotation extends RAMnotation {

	public ROMnotation(int[] data) {
		super(data.length,0);
		for (int i=0; i<data.length; i++)
			super.write(i, data[i]);
	}
	
	@Override public void write(int addr, int value) {		
	}

}
