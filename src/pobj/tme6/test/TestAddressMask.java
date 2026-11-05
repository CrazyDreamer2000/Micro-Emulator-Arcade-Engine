package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressMask;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.ROM;

public class TestAddressMask {
	
	@Test public void test() {
		IMemory r = new ROM(new int [] { 1,2,3,4 });
		IMemory a = new AddressMask(r,  100,  3);
		assertEquals(1, a.read(4));
		assertEquals(2, a.read(5));
		assertEquals(3, a.read(6));
		assertEquals(4, a.read(7));
	}
}
