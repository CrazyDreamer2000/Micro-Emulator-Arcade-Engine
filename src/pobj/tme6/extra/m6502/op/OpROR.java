package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpROR implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpROR(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int addr = addrMode.fetchAddr(cpu);
		int val = cpu.readMem8(addr);
		val |= cpu.getC() ? 0x100 : 0;
		cpu.setC(Utils.getBit(val,0));
		val = val >> 1;
		cpu.updateNZ(val);
		cpu.writeMem8(addr, val);
		return addrMode.getCycles() + 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "ROR " + addrMode.disassemble(cpu);
	}	
}
