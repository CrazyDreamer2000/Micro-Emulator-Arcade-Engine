package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.Emulator;

public class Q6bEmulator1 {

	@Test
        public void test1() {
		Emulator emu = new Emulator(Utils.makeArray(0,9));
		emu.run(1);
		assertEquals(2, emu.getCPU().getState().getPC());
		assertEquals(9, emu.getCPU().getState().getA());
	}

	@Test
        public void test2() {
		Emulator emu = new Emulator(Utils.makeArray(0, 99, 3, 150));
		emu.run(3);
		assertEquals(4, emu.getCPU().getState().getPC());
		assertEquals(99, emu.getCPU().getState().getA());
		assertEquals(99, emu.getCPU().getState().getMemory().read(150));
	}

	@Test
        public void test3() {
		Emulator emu = new Emulator(Utils.makeArray(1, 10, 1, -1, 99, 4, 2));
		emu.run(51);
		assertEquals(7, emu.getCPU().getState().getPC());
		assertEquals(0, emu.getCPU().getState().getA());
	}

}
