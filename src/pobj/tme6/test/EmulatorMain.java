package pobj.tme6.test;

import pobj.tme6.Emulator;

public class EmulatorMain {
	
	public static void main(String[] args) {
		Emulator emu = new Emulator(new int[] { 0, 65, 3, 200, 0, 66, 3, 201, 0, 67, 3, 202, 4, 0 });		
		emu.run(150);
	}
}
