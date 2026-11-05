package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Indexed indirect addressing mode
public class AddrIndX implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return cpu.readMem16((cpu.fetch() + cpu.getX()) % 0xffff);
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "($" + String.format("%02X", cpu.fetch()) + ",X)";
	}

	@Override
	public int getCycles() {
		return 6;
	}
}
