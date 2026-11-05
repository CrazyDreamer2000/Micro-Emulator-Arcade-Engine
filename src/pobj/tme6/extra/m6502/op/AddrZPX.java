package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Zero-page addressing mode with index
public class AddrZPX implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return (cpu.fetch() + cpu.getX()) & 0xff;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "$" + String.format("%02X", cpu.fetch()) + ",X";
	}

	@Override
	public int getCycles() {
		return 4;
	}
}
