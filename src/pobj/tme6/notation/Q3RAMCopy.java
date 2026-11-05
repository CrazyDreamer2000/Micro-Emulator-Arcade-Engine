package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.ICopyableMemory;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class Q3RAMCopy {

	@Test
        public void testClass() {
		RAM r = new RAM(10,0);
		assertTrue(r instanceof ICopyableMemory);
	}

	@Test
        public void testCopy() {
		RAM r1 = new RAM(10,0);
		IMemory r2 = r1.copy();
		assertTrue(r2 instanceof RAM);
	}

	@Test
        public void testSize() {
		RAM r1 = new RAM(10,0);
		IMemory r2 = r1.copy();
		assertEquals(10, r2.size());
	}

	@Test
        public void testCopyRead() {
		RAM r1 = new RAM(10, 99);
		r1.write(1,  199);
		IMemory r2 = r1.copy();
		assertEquals(99, r1.read(0));
		assertEquals(99, r2.read(0));
		assertEquals(199, r1.read(1));
		assertEquals(199, r2.read(1));
	}

	@Test
        public void testCopyWrite() {
		RAM r1 = new RAM(10,0);
		IMemory r2 = r1.copy();
		r1.write(0, 199);
		r2.write(0, 299);
		assertEquals(199, r1.read(0));
		assertEquals(299, r2.read(0));
	}
}
