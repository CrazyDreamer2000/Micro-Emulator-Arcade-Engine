package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Immediate addressing mode
public class AddrImmed implements IAddrMode {

	@Override
	public int fetchValue(M6502 cpu) {
		return cpu.fetch();
	}

	@Override
	public int fetchAddr(M6502 cpu) {
		throw new IllegalArgumentException("Immediate mode has no address");
	}

	@Override
	public String disassemble(M6502 cpu) {
		//return "#" + cpu.fetch8();
		return String.format("#$%02X", cpu.fetch());
	}

	@Override
	public int getCycles() {
		return 2;
	}
}
