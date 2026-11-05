package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpLoad;

public class Q5bOpLoad {

	@Test
        public void test() {
		ROMnotation rom = new ROMnotation(new int[] { 5, 0, 0, 0, 0, 199 } );
		CPUState state = new CPUState(rom);
		OpLoad op = new OpLoad();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(10);
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(199, state.getA());
	}
}
