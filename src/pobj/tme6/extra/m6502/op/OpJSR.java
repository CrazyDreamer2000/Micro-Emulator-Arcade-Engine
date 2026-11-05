package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpJSR implements IOpCode2<M6502> {

	private IAddrMode addrMode = new AddrAbs();
	
	@Override
	public int execute(M6502 cpu) {
		int target = addrMode.fetchAddr(cpu);
		cpu.push16(cpu.getPC()-1);
		cpu.setPC(target);
		return addrMode.getCycles() + 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "JSR " + addrMode.disassemble(cpu);
	}	
}
