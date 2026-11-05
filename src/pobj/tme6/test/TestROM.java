package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.ROM;

public class TestROM {

	@Test
	public void test() {
		int data[] = { 1, 2, 3 };
		IMemory mem = new ROM(data);
		assertEquals(3, mem.size());
		assertEquals(1, mem.read(0));
		mem.write(0,  10);
		assertEquals(1, mem.read(0));
	}
}
