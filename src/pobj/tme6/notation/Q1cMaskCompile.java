package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressMask;
import pobj.tme6.memory.IMemory;

public class Q1cMaskCompile {

	@Test
        public void testClass() {
		IMemory r = new RAMnotation(10,0);
		AddressMask a = new AddressMask(r,  100,  0xf);
		assertTrue(a instanceof IMemory);
		assertTrue(AddressMask.class.getSuperclass() == Object.class);
	}

	@Test
        public void testAttributes() {
		assertTrue(Utils.hasOnlyPrivateAttributes(AddressMask.class));
		assertTrue(Utils.hasFieldOfType(AddressMask.class, IMemory.class));
	}
}
