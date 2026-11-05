package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.ROM;

public class Q1bROMMethods {

	@Test
        public void testSize() {
		ROM r1 = new ROM(new int[] {1,2,3 });
		ROM r2 = new ROM(new int[] {1,2,3,4,5,6 });
		assertEquals(3, r1.size());
		assertEquals(6, r2.size());
	}

	@Test
        public void testRead() {
		ROM r1 = new ROM(new int[] {1,2,3 });
		assertEquals(1, r1.read(0));
		assertEquals(2, r1.read(1));
		assertEquals(3, r1.read(2));
	}
	
	@Test
        public void testWrite() {
		ROM r1 = new ROM(new int[] {1,2,3 });
		r1.write(0, 99);
		r1.write(1, 100);
		assertEquals(1, r1.read(0));
		assertEquals(2, r1.read(1));
	}
	
	@Test
        public void testDataCopied() {
		int a[] = { 1,2,3};
		ROM r1 = new ROM(a);
		a[0] = 99;
		assertEquals(1, r1.read(0));
	}
}
