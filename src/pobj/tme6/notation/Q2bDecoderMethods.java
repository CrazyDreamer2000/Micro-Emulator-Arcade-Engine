package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;

public class Q2bDecoderMethods {

	@Test
        public void testSize() {
		AddressDecoder r = new AddressDecoder(100);
		assertEquals(100, r.size());
	}
	
	@Test
        public void testAddRead1() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 1);
		b.add(new MemorySlot(0, r));
		r.write(1, 10);
		assertEquals(10, b.read(1));
		assertEquals(1, b.read(2));
		assertEquals(10, r.read(1));
		assertEquals(1, r.read(2));
	}
		
	@Test
        public void testAddRead2() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 1);
		b.add(new MemorySlot(5, r));
		r.write(5, 10);
		assertEquals(10, b.read(10));
		assertEquals(1, b.read(11));
		assertEquals(10, r.read(5));
		assertEquals(1, r.read(6));
	}
	
	@Test
        public void testAddRead3() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r1 = new RAMnotation(10, 1);
		IMemory r2 = new RAMnotation(10, 2);
		IMemory r3 = new RAMnotation(10, 3);
		b.add(new MemorySlot(5, r1));
		b.add(new MemorySlot(20, r2));
		b.add(new MemorySlot(50, r3));
		assertEquals(1, b.read(5));
		assertEquals(1, b.read(14));
		assertEquals(2, b.read(20));
		assertEquals(2, b.read(29));
		assertEquals(3, b.read(50));
		assertEquals(3, b.read(59));
	}
	
	@Test
        public void testAddWrite1() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 1);
		b.add(new MemorySlot(0, r));
		b.write(1, 10);
		assertEquals(10, b.read(1));
		assertEquals(1, b.read(2));
		assertEquals(10, r.read(1));
		assertEquals(1, r.read(2));
	}
	
	@Test
        public void testAddWrite2() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 1);
		b.add(new MemorySlot(5, r));
		b.write(10, 10);
		assertEquals(10, b.read(10));
		assertEquals(1, b.read(11));
		assertEquals(10, r.read(5));
		assertEquals(1, r.read(6));
	}

	@Test
        public void testAddWrite3() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r1 = new RAMnotation(10, 0);
		IMemory r2 = new RAMnotation(10, 0);
		IMemory r3 = new RAMnotation(10, 99);
		b.add(new MemorySlot(50, r3));
		b.add(new MemorySlot(20, r2));
		b.add(new MemorySlot(5, r1));
		b.write(5, 1);
		b.write(14, 2);
		b.write(20, 3);
		b.write(29, 4);
		b.write(50, 5);
		b.write(59, 6);
		assertEquals(1, r1.read(0));
		assertEquals(0, r1.read(1));
		assertEquals(2, r1.read(9));
		assertEquals(3, r2.read(0));
		assertEquals(0, r2.read(1));
		assertEquals(4, r2.read(9));
		assertEquals(5, r3.read(0));
		assertEquals(99, r3.read(1));
		assertEquals(6, r3.read(9));
	}
	
	@Test
	public void testErrorRead() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 0);
		b.add(new MemorySlot(0, r));
		assertThrows(IllegalArgumentException.class, () -> b.read(15));
	}
	
    @Test
	public void testErrorWrite() {
		AddressDecoder b = new AddressDecoder(100);
		IMemory r = new RAMnotation(10, 0);
		b.add(new MemorySlot(0, r));
		assertThrows(IllegalArgumentException.class, () -> b.write(15, 99));
	}
	
     @Test
        public void testRemove() {
		IMemory r1 = new RAMnotation(10, 1);
		IMemory r2 = new RAMnotation(10, 2);
		AddressDecoder b = new AddressDecoder(100);
		b.add(new MemorySlot(10, r1));
		b.add(new MemorySlot(20, r2));
		b.remove(new MemorySlot(10, r1));
		assertThrows(IllegalArgumentException.class, () -> b.read(10));
	}
	
	@Test
        public void testNoRemove() {
		IMemory r1 = new RAMnotation(10, 1);
		IMemory r2 = new RAMnotation(10, 2);
		AddressDecoder b = new AddressDecoder(100);
		b.add(new MemorySlot(10, r1));
		b.add(new MemorySlot(20, r2));
		b.remove(new MemorySlot(11, r1));
		b.remove(new MemorySlot(10, r2));
		assertEquals(1, b.read(10));
		assertEquals(2, b.read(20));
	}
	
	@Test
        public void testDelegate() {
		class Mock implements IMemory {
			public int called = 0;
			@Override public int size() { return 50; }
			@Override public int read(int addr) { called++; return 0; }
			@Override public void write(int addr, int value) { called++; }
		};
		Mock  m = new Mock();
		AddressDecoder b = new AddressDecoder(100);
		b.add(new MemorySlot(0, m));
		b.read(10);
		assertEquals(1, m.called);
		b.write(10, 99);
		assertEquals(2, m.called);
	}
}
