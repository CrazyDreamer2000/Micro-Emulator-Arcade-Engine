package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpTAX implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setX(cpu.getA());
		cpu.updateNZ(cpu.getX());
		return 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "TAX";
	}
}
