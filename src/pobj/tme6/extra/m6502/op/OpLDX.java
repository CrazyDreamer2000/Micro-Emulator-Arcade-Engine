package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpLDX implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpLDX(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setX(addrMode.fetchValue(cpu));
		cpu.updateNZ(cpu.getX());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "LDX " + addrMode.disassemble(cpu);
	}	
}
