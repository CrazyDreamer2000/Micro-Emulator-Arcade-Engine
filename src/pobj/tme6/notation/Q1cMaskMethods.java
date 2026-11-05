package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressMask;
import pobj.tme6.memory.IMemory;

public class Q1cMaskMethods {
	
	@Test
        public void testSize() {
		IMemory r = new RAMnotation(10, 0);
		AddressMask a = new AddressMask(r,  100,  3);
		assertEquals(100, a.size());
	}

	@Test
        public void testRead() {
		IMemory r = new ROMnotation(new int [] { 1,2,3,4 });
		AddressMask a = new AddressMask(r,  100,  3);
		assertEquals(1, a.read(0));
		assertEquals(2, a.read(1));
		assertEquals(3, a.read(2));
		assertEquals(4, a.read(3));
		assertEquals(1, a.read(4));
		assertEquals(2, a.read(5));
		assertEquals(3, a.read(6));
		assertEquals(4, a.read(7));
		assertEquals(4, a.read(99));
	}
	
	@Test
        public void testWrite() {
		IMemory r = new RAMnotation(4, 0);
		AddressMask a = new AddressMask(r,  100,  3);
		a.write(0,  1);
		a.write(1,  2);
		a.write(2,  3);
		a.write(3,  4);
		a.write(4,  5);
		a.write(99,  100);
		assertEquals(5, a.read(0));
		assertEquals(2, a.read(1));
		assertEquals(3, a.read(2));
		assertEquals(100, a.read(3));
	}
	
	@Test
        public void testNoWrite() {
		IMemory r = new ROMnotation(new int [] { 1,2,3,4 });
		AddressMask a = new AddressMask(r,  100,  3);
		a.write(0,  99);
		assertEquals(1, a.read(0));
	}
	
	@Test
        public void testDelegate() {
		class Mock implements IMemory {
			public int called = 0;
			@Override public int size() { return 0; }
			@Override public int read(int addr) { called++; return 0; }
			@Override public void write(int addr, int value) { called++; }			
		};
		Mock m = new Mock();
		AddressMask a = new AddressMask(m,  100,  3);
		a.read(0);
		assertEquals(1, m.called);
		a.write(0,0);
		assertEquals(2, m.called);		
	}

}
