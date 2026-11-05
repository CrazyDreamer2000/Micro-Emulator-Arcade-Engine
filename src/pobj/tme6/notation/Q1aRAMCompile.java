package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.RAM;

public class Q1aRAMCompile {

	@Test
        public void testClass() {
		RAM r = new RAM(10,0);
		assertTrue(r instanceof IMemory);
	}

	@Test
        public void testAttributes() {
		assertTrue(Utils.hasOnlyPrivateAttributes(RAM.class));
	}
}
