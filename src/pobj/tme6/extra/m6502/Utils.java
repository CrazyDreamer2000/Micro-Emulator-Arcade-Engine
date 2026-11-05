package pobj.tme6.extra.m6502;

// Bit-manipulation utilities
public class Utils {

	// get a bit 
	public static boolean getBit(int i, int pos) { 
		return (i & (1 << pos)) != 0; 
	}
	
	// set a bit
	public static int setBit(int i, int pos, boolean v) {
		if (v) return i | (1 << pos);
		else return i & ~ (1 << pos);
	}

}
