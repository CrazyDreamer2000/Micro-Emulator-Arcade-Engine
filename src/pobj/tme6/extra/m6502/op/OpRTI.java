package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpRTI implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setP(cpu.pop8() | M6502.STATUS_DFL | M6502.STATUS_B);
		cpu.setPC(cpu.pop16());
		return 6;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "RTI";
	}
}
