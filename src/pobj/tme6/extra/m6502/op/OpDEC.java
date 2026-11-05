package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpDEC implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpDEC(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int addr = addrMode.fetchAddr(cpu);
		int val = cpu.readMem8(addr);
		val = (val - 1) & 0xff;
		cpu.updateNZ(val);
		cpu.writeMem8(addr, val);
		return addrMode.getCycles() + 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "DEC " + addrMode.disassemble(cpu);
	}	
}
