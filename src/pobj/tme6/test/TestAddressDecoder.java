package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;

public class TestAddressDecoder {

	@Test
	public void test() {
		IMemory ram = new RAM(10, 0);
		MemorySlot slot = new MemorySlot(100, ram);
		AddressDecoder addressDecoder = new AddressDecoder(200);
		addressDecoder.add(slot);
		ram.write(5, 99);
		assertEquals(99, addressDecoder.read(105));
	}
}
