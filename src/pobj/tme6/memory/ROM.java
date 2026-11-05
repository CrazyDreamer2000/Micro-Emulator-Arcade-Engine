package pobj.tme6.memory;

public class ROM extends RAM {

	public ROM(int[] data) {
		super(data.length, 0);
		
		for (int i = 0 ; i < data.length ; i++) {
			super.write(i, data[i]);
		}
		
	}
	
	public void write(int addr , int value) {}
}
