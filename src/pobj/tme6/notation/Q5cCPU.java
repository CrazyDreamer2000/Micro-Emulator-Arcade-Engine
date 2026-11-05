package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPU;
import pobj.tme6.cpu.ICPU;
import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.MemorySlot;

public class Q5cCPU {
	
	@Test
        public void testNew() {
		RAMnotation ram = new RAMnotation(10,0);
		CPU cpu = new CPU(ram);
		assertTrue(cpu instanceof ICPU);	
		assertEquals(0, cpu.getState().getPC());
		assertEquals(0, cpu.getState().getA());
		assertTrue(Utils.hasOnlyPrivateAttributes(CPU.class));
	}

	@Test
        public void testReset() {
		RAMnotation ram = new RAMnotation(10,0);
		CPU cpu = new CPU(ram);
		cpu.getState().setA(10);
		cpu.getState().setPC(20);
		cpu.reset();
		assertEquals(0, cpu.getState().getPC());
		assertEquals(0, cpu.getState().getA());
	}

	@Test
        public void testSET() {
		CPU cpu = new CPU(new ROMnotation(new int[] { 0,9 }));
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(9, cpu.getState().getA());
	}

	@Test
        public void testADD() {
		CPU cpu = new CPU(new ROMnotation(new int[]  {1,9 }));
		cpu.getState().setA(90);
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(99, cpu.getState().getA());
	}

	@Test
        public void testLOAD() {
		CPU cpu = new CPU(new ROMnotation(new int[] { 2, 4, 0, 0, 999 }));
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(999, cpu.getState().getA());
	}

	@Test
        public void testSTORE() {
		AddressDecoder b = new AddressDecoder(100);
		b.add(new MemorySlot(0, new ROMnotation(new int[] { 3, 10 })));
		b.add(new MemorySlot(10, new RAMnotation(10, 0)));
		CPU cpu = new CPU(b);
		cpu.getState().setA(90);
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(90, cpu.getState().getA());
		assertEquals(90, cpu.getState().getMemory().read(10));
	}

	@Test
        public void testJUMP1() {
		CPU cpu = new CPU(new ROMnotation(new int[]  {4,99 }));
		cpu.getState().setA(90);
		assertEquals(2, cpu.execute());
		assertEquals(99, cpu.getState().getPC());
		assertEquals(90, cpu.getState().getA());
	}

	@Test
        public void testJUMP2() {
		CPU cpu = new CPU(new ROMnotation(new int[]  {4,99 }));
		cpu.getState().setA(-90);
		assertEquals(2, cpu.execute());
		assertEquals(2, cpu.getState().getPC());
		assertEquals(-90, cpu.getState().getA());
	}

	@Test
        public void testNOP() {
		CPU cpu = new CPU(new ROMnotation(new int[] { -1 }));
		assertEquals(1, cpu.execute());
		assertEquals(1, cpu.getState().getPC());
		assertEquals(0, cpu.getState().getA());
	}
}
