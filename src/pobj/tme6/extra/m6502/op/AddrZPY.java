package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Zero-page addressing mode with index
public class AddrZPY implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return (cpu.fetch() + cpu.getY()) & 0xff;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "$" + String.format("%02X", cpu.fetch()) + ",Y";
	}

	@Override
	public int getCycles() {
		return 4;
	}
}
