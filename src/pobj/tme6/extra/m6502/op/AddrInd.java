package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Indirect addressing mode
public class AddrInd implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return cpu.readMem16(cpu.fetch16());
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "($" + String.format("%04X", cpu.fetch16()) + ")";
	}

	@Override
	public int getCycles() {
		return 6;
	}
}
