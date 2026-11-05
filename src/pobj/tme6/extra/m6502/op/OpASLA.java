package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpASLA implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setC(Utils.getBit(cpu.getA(),7));
		cpu.setA(cpu.getA() << 1);
		cpu.updateNZ(cpu.getA());
		return 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "ASL A";
	}	
}
