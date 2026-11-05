package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.ICPUState;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class TestCPUState {

	@Test
	public void test() {
		IMemory mem = new RAM(10, 0);
		ICPUState cpu = new CPUState(mem);
		cpu.setPC(99);
		assertEquals(0, cpu.getA());
		assertEquals(99, cpu.getPC());
	}
}
