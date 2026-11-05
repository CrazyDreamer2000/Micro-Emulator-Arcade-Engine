package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpPLA implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setA(cpu.pop8());
		cpu.updateNZ(cpu.getA());
		return 4;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "PLA";
	}
}
