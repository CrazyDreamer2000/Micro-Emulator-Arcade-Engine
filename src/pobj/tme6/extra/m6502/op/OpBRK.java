package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpBRK implements IOpCode2<M6502> {
	
	@Override
	public int execute(M6502 cpu) {
		cpu.push16(cpu.getPC() + 1);
		cpu.push8(Utils.setBit(cpu.getP(), M6502.STATUS_B, true)); // push status with BREAK bit set
		cpu.setI(true);
		cpu.setPC(cpu.readMem16(M6502.VEC_BRK));
		return 7;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "BRK";
	}
}
