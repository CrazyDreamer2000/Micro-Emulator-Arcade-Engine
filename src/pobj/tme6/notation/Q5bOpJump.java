package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpJump;

public class Q5bOpJump {

	@Test
        public void testJump() {
		ROMnotation rom = new ROMnotation(new int[] { 99 } );
		CPUState state = new CPUState(rom);
		OpJump op = new OpJump();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(10);
		assertEquals(2, op.execute(state));
		assertEquals(99, state.getPC());
		assertEquals(10, state.getA());
	}

	@Test
        public void testNoJump() {
		ROMnotation rom = new ROMnotation(new int[] { 99 } );
		CPUState state = new CPUState(rom);
		OpJump op = new OpJump();
		assertTrue(op instanceof IOpCode);
		state.setPC(0);
		state.setA(-10);
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(-10, state.getA());
	}
}
