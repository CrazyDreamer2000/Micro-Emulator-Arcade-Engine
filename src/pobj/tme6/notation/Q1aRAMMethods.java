package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.RAM;

public class Q1aRAMMethods {

	@Test
        public void testSize() {
		RAM r1 = new RAM(100, 99);
		RAM r2 = new RAM(200, 99);
		assertEquals(100, r1.size());
		assertEquals(200, r2.size());
	}

	@Test
        public void testRead() {
		RAM r1 = new RAM(100, 99);
		RAM r2 = new RAM(100, 98);
		assertEquals(99, r1.read(0));
		assertEquals(98, r2.read(0));
	}
	
	@Test
        public void testWrite() {
		RAM r1 = new RAM(100, 99);
		r1.write(1, 10);
		r1.write(2, 20);
		assertEquals(99, r1.read(0));
		assertEquals(10, r1.read(1));
		assertEquals(20, r1.read(2));
	}
	
	@Test
        public void testOutOfBounds() {
		RAM r1 = new RAM(100,0);
		try { r1.read(-1); fail(); } catch (Exception e) {}
		try { r1.read(100); fail(); } catch (Exception e) {}
	}
}
