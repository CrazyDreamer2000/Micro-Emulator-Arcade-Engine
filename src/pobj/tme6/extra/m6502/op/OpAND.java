package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpAND implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpAND(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		cpu.setA(cpu.getA() & addrMode.fetchValue(cpu));
		cpu.updateNZ(cpu.getA());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "AND " + addrMode.disassemble(cpu);
	}	
}
