package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.ICPUState;
import pobj.tme6.memory.IMemory;

public class Q5aCPUState {

	@Test
        public void testNew() {
		CPUState cpu = new CPUState(null);
		assertTrue(cpu instanceof ICPUState);
		assertTrue(Utils.hasOnlyPrivateAttributes(CPUState.class));
	}

	@Test
        public void testGetters() {
		IMemory mem = new RAMnotation(10, 0);
		CPUState state = new CPUState(mem);
		assertEquals(0, state.getA());
		assertEquals(0, state.getPC());
		assertSame(mem, state.getMemory());
	}

	@Test
        public void testSetters() {
		IMemory mem = new RAMnotation(10, 0);
		CPUState state = new CPUState(mem);
		state.setA(99);
		state.setPC(199);
		assertEquals(99, state.getA());
		assertEquals(199, state.getPC());
	}

	@Test
        public void testFetch() {
		IMemory mem = new RAMnotation(100, 99);
		CPUState state = new CPUState(mem);
		state.setPC(10);
		assertEquals(99, state.fetch());
		assertEquals(11, state.getPC());
	}
}
