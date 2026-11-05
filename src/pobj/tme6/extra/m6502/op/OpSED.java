package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpSED implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setD(true);
		return 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "SED";
	}
}
