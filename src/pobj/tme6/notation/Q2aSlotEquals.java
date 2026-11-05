package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;

public class Q2aSlotEquals {

	@Test
        public void test() {
		IMemory r1 = new RAMnotation(10,10);
		IMemory r2 = new RAMnotation(10,10);
		MemorySlot b1 = new MemorySlot(10, r1);
		MemorySlot b2 = new MemorySlot(10, r1);
		MemorySlot b3 = new MemorySlot(20, r1);
		MemorySlot b4 = new MemorySlot(10, r2);
		assertEquals(b1, b1);
		assertEquals(b1, b2);
		assertEquals(b2, b1);
		assertNotEquals(b1, b3);
		assertNotEquals(b3, b1);
		assertNotEquals(b1, b4);
		assertNotEquals(b4, b1);
		assertNotEquals(b1, "Toto");
		assertNotEquals("Toto", b1);
	}
}
