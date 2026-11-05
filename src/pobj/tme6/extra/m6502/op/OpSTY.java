package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpSTY implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpSTY(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.writeMem8(addrMode.fetchAddr(cpu),  cpu.getY());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "STY " + addrMode.disassemble(cpu);
	}	
}
