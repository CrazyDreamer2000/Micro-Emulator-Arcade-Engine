package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpBCS implements IOpCode2<M6502> {

	private IAddrMode iAddrMode = new AddrRel();
	
	@Override
	public int execute(M6502 cpu) {
		int target = iAddrMode.fetchAddr(cpu);
		if (cpu.getC()) {
			cpu.setPC(target);
		}
		return 3; // 4 if crossing page boundary
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "BCS " + iAddrMode.disassemble(cpu);
	}
}
