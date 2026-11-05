package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpTYA implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setA(cpu.getY());
		cpu.updateNZ(cpu.getA());
		return 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "TYA";
	}
}
