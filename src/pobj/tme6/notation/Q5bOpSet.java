package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpSet;

public class Q5bOpSet {

	@Test
        public void test() {
		ROMnotation rom = new ROMnotation(new int[] { 99 } );
		CPUState state = new CPUState(rom);
		OpSet op = new OpSet();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(10);
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(99, state.getA());
	}
}
