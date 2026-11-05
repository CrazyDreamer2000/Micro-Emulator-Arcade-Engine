package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Absolute addressing mode with index
public class AddrAbsX implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return (cpu.fetch16() + cpu.getX()) % 0xffff;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "$" + String.format("%04X", cpu.fetch16()) + ",X";
	}

	@Override
	public int getCycles() {
		return 4; // 5 in case of carry in address computation
	}
}
