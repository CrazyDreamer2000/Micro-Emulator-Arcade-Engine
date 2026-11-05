package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpPHP implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.push8(cpu.getP());
		return 3;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "PHP";
	}
}
