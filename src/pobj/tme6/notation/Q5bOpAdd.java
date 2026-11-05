package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpAdd;

public class Q5bOpAdd {

	@Test
        public void test() {
		ROMnotation rom = new ROMnotation(new int[] { 99 } );
		CPUState state = new CPUState(rom);
		OpAdd op = new OpAdd();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(10);
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(109, state.getA());
	}
}
