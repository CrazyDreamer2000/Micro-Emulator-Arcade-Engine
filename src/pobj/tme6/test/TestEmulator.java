package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.Emulator;

public class TestEmulator {
	
	@Test public void test() {
		Emulator emu = new Emulator(new int[] { 0, 9 });
		emu.run(1);
		assertEquals(2, emu.getCPU().getState().getPC());
		assertEquals(9, emu.getCPU().getState().getA());
	}
}
