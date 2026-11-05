package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpLDY implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpLDY(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setY(addrMode.fetchValue(cpu));
		cpu.updateNZ(cpu.getY());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "LDY " + addrMode.disassemble(cpu);
	}	
}
