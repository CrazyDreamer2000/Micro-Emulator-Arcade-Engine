package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;

public class Q2aSlotGetter {

	@Test
        public void test() {
		IMemory mem = new RAMnotation(10,0);
		MemorySlot m = new MemorySlot(10, mem);
		assertEquals(10, m.getBaseAddress());
		assertSame(mem, m.getMemory());
	}
}
