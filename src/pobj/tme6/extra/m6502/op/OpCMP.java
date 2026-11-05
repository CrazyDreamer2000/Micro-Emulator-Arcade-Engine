package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpCMP implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpCMP(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int val = cpu.getA() - addrMode.fetchValue(cpu);
		cpu.updateNZ(val & 0xff);
		cpu.setC((val & 0xff00) == 0);
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "CMP " + addrMode.disassemble(cpu);
	}	
}
