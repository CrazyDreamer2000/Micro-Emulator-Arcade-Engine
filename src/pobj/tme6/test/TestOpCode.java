package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.cpu.CPUState;
import pobj.tme6.cpu.ICPUState;
import pobj.tme6.cpu.op.IOpCode;
import pobj.tme6.cpu.op.OpSet;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.ROM;

public class TestOpCode {

	@Test public void test() {
		IMemory rom = new ROM(new int[] { 10 } );
		ICPUState state = new CPUState(rom);
		IOpCode<ICPUState> op = new OpSet();
		assertEquals(2, op.execute(state));
		assertEquals(1, state.getPC());
		assertEquals(10, state.getA());
	}
}
