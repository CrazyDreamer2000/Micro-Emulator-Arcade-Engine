package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;
import pobj.tme6.memory.ROM;

public class Q1bROMCompile {

	@Test
        public void testClass() {
		int a[] = { 1,2,3 };
		ROM r = new ROM(a);
		assertTrue(r instanceof IMemory);
		assertTrue(r instanceof RAM);
		assertTrue(ROM.class.getSuperclass() == RAM.class);
	}

	@Test
        public void testAttributes() {
		assertTrue(Utils.hasOnlyPrivateAttributes(ROM.class));
	}
}
