package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPU;
import pobj.tme6.memory.ROM;

public class TestCPU {

	@Test public void test() {
		ROM rom = new ROM(new int[] { 1,9 } ); // ADD 9
		CPU cpu = new CPU(rom);
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(9, cpu.getState().getA());
	}
}
