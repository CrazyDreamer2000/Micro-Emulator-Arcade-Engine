package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;

public class TestMemorySlot {

	@Test
	public void test() {
		IMemory ram = new RAM(10, 0);
		MemorySlot slot = new MemorySlot(100, ram);
		assertEquals(100, slot.getBaseAddress());
		assertEquals(ram, slot.getMemory());
	}
}
