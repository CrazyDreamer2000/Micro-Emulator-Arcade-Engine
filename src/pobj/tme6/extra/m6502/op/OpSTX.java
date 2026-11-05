package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpSTX implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpSTX(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.writeMem8(addrMode.fetchAddr(cpu),  cpu.getX());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "STX " + addrMode.disassemble(cpu);
	}	
}
