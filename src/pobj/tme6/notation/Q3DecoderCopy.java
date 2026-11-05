package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.ICopyableMemory;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;

public class Q3DecoderCopy {

	@Test
        public void testClass() {
		AddressDecoder addressDecoder = new AddressDecoder(100);
		assertTrue(addressDecoder instanceof ICopyableMemory);
	}

	@Test
        public void testCopy() {
		AddressDecoder r1 = new AddressDecoder(100);
		IMemory r2 = r1.copy();
		assertTrue(r2 instanceof AddressDecoder);
	}

	@Test
        public void testSize() {
		AddressDecoder r1 = new AddressDecoder(100);
		IMemory r2 = r1.copy();
		assertEquals(100, r2.size());
	}
	
	@Test
        public void testRead1() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		b1.add(new MemorySlot(0, r1));
		IMemory b2 = b1.copy();
		assertEquals(1, b2.read(1));
		assertEquals(1, b2.read(9));
	}

	@Test
        public void testRead2() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		RAM r2 = new RAM(10, 2);
		b1.add(new MemorySlot(0, r1));
		b1.add(new MemorySlot(50, r2));
		IMemory b2 = b1.copy();
		assertEquals(1, b1.read(1));
		assertEquals(1, b1.read(9));
		assertEquals(2, b1.read(51));
		assertEquals(2, b1.read(59));
		assertEquals(1, b2.read(0));
		assertEquals(1, b2.read(9));
		assertEquals(2, b2.read(50));
		assertEquals(2, b2.read(59));
	}

	@Test
        public void testWrite1a() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		b1.add(new MemorySlot(0, r1));
		IMemory b2 = b1.copy();
		b1.write(0, 99);
		assertEquals(99, b1.read(0));
		assertEquals(1, b1.read(9));
		assertEquals(1, b2.read(0));
		assertEquals(1, b2.read(9));
	}

	@Test
        public void testWrite2a() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		RAM r2 = new RAM(10, 2);
		b1.add(new MemorySlot(0, r1));
		b1.add(new MemorySlot(50, r2));
		IMemory b2 = b1.copy();
		b1.write(0, 199);
		b1.write(50, 299);
		assertEquals(199, b1.read(0));
		assertEquals(299, b1.read(50));
		assertEquals(1, b2.read(0));
		assertEquals(2, b2.read(50));
	}

	@Test
        public void testWrite1b() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		b1.add(new MemorySlot(0, r1));
		IMemory b2 = b1.copy();
		b2.write(0, 99);
		assertEquals(1, b1.read(0));
		assertEquals(1, b1.read(9));
		assertEquals(99, b2.read(0));
		assertEquals(1, b2.read(9));
	}

	@Test
        public void testWrite2b() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r1 = new RAM(10, 1);
		RAM r2 = new RAM(10, 2);
		b1.add(new MemorySlot(0, r1));
		b1.add(new MemorySlot(50, r2));
		IMemory b2 = b1.copy();
		b2.write(0, 199);
		b2.write(50, 299);
		assertEquals(1, b1.read(0));
		assertEquals(2, b1.read(50));
		assertEquals(199, b2.read(0));
		assertEquals(299, b2.read(50));
	}
	
	@Test
	public void testErrorRead() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 0);
		b1.add(new MemorySlot(0, r));
		IMemory b2 = b1.copy();
		assertThrows(IllegalArgumentException.class, () -> b2.read(15));
	}
	
	@Test
	public void testErrorWrite() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 0);
		b1.add(new MemorySlot(0, r));
		IMemory b2 = b1.copy();
		assertThrows(IllegalArgumentException.class, () -> b2.write(15, 99));
	}
	
	@Test
	public void testAddWrite1() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 66);
		b1.copy();
		b1.add(new MemorySlot(20, r));
		b1.write(25, 99);
		assertEquals(99, b1.read(25));
		assertEquals(66, b1.read(26));
	}
	
	@Test
	public void testAddWrite2() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 66);
		AddressDecoder b2 = (AddressDecoder)b1.copy();
		b2.add(new MemorySlot(20, r));
		b2.write(25, 99);
		assertEquals(99, b2.read(25));
		assertEquals(66, b2.read(26));
	}
	
    @Test
 	public void testAddWriteError1() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 0);
		IMemory b2 = b1.copy();
		b1.add(new MemorySlot(20, r));
		assertThrows(IllegalArgumentException.class, () -> b2.write(25, 99));
	}
	
    @Test
	public void testAddWriteError2() {
		AddressDecoder b1 = new AddressDecoder(100);
		RAM r = new RAM(10, 0);
		AddressDecoder b2 = (AddressDecoder)b1.copy();
		b2.add(new MemorySlot(20, r));
		assertThrows(IllegalArgumentException.class, () -> b1.write(25, 99));
	}	
	
	@Test
	public void testRemove1() {
		RAM r1 = new RAM(10, 2);
		AddressDecoder b1 = new AddressDecoder(100);
		b1.add(new MemorySlot(10, r1));
		AddressDecoder b2 = (AddressDecoder)b1.copy();
		b1.remove(new MemorySlot(10, r1));
		assertEquals(2, b2.read(10));
	}
	
	@Test
	public void testRemove2() {
		RAM r1 = new RAM(10, 2);
		AddressDecoder b1 = new AddressDecoder(100);
		b1.add(new MemorySlot(10, r1));
		AddressDecoder b2 = (AddressDecoder)b1.copy();
		b2.remove(new MemorySlot(10, r1)); // removing r1 does not remove its copy in b2!
		assertEquals(2, b1.read(10));
	}

	@Test
	public void testNoRemove() {
		RAM r1 = new RAM(10, 2);
		AddressDecoder b1 = new AddressDecoder(100);
		b1.add(new MemorySlot(10, r1));
		AddressDecoder b2 = (AddressDecoder)b1.copy();
		b2.remove(new MemorySlot(10, r1)); // removing r1 does not remove its copy in b2!
		assertEquals(2, b2.read(10));
	}
	
	@Test
	public void testCopyError() {
		class Mock implements IMemory {
			@Override public int size() { return 10; }
			@Override public int read(int addr) { return 0; }
			@Override public void write(int addr, int value) { }
		};
		Mock m = new Mock();
		AddressDecoder b1 = new AddressDecoder(100);
		b1.add(new MemorySlot(10, m));
		assertThrows(UnsupportedOperationException.class, () -> b1.copy());
	}

}
