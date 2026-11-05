package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpStore;

public class Q5bOpStore {

	@Test
        public void test() {
		RAMnotation ram = new RAMnotation(10, 5);
		CPUState state = new CPUState(ram);
		OpStore op = new OpStore();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(10);
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(10, state.getA());
		assertEquals(10, ram.read(5));
	}
}
