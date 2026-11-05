package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpPLP implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setP(cpu.pop8() | M6502.STATUS_DFL | M6502.STATUS_B);
		return 4;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "PLP";
	}
}
