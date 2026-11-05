package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.ICopyableMemory;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class TestCopy {

	@Test
	public void test() {
		ICopyableMemory mem = new RAM(2,3);
		IMemory mem2 = mem.copy();
		assertEquals(2, mem2.size());
		assertEquals(3, mem2.read(0));
		mem2.write(0,  10);
		assertEquals(10, mem2.read(0));
		assertEquals(3, mem.read(0));
	}
}
