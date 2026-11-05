package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Indirect indexed addressing mode
public class AddrIndY implements IAddrMode {

	@Override
	public int fetchAddr(M6502 cpu) {
		return (cpu.readMem16(cpu.fetch()) + cpu.getY()) % 0xffff;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "($" + String.format("%02X", cpu.fetch()) + "),Y";
	}

	@Override
	public int getCycles() {
		return 5; // 6 in case of carry in address computation
	}
}
