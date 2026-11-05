package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Relative addressing mode
public class AddrRel implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		int v = cpu.fetch();
		if (v > 0x7f) v -= 0x100;
		return (cpu.getPC() + v) & 0xffff;
	}

	@Override
	public String disassemble(M6502 cpu) {
		int v = cpu.fetch();
		if (v > 0x7f) v -= 0x100;
		//return "#" + v;
		return String.format("$%04X", (cpu.getPC() + v) & 0xffff);
	}

	@Override
	public int getCycles() {
		return 0;
	}
}
