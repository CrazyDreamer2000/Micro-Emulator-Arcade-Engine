package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpROLA implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		int val = (cpu.getA() << 1) | (cpu.getC() ? 1 : 0);
		cpu.setC(Utils.getBit(val,8));
		cpu.setA(val);
		cpu.updateNZ(cpu.getA());
		return 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "ROL A";
	}	
}
