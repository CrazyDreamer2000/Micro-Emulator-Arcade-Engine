package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpJMP implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpJMP(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setPC(addrMode.fetchAddr(cpu));
		return addrMode.getCycles() - 1;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "JMP " + addrMode.disassemble(cpu);
	}	
}
