package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class TestRAM {

	@Test
	public void test() {
		IMemory mem = new RAM(2,3);
		assertEquals(2, mem.size());
		assertEquals(3, mem.read(0));
		mem.write(0,  10);
		assertEquals(10, mem.read(0));
	}
}
