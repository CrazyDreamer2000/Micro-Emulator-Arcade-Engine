package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpBNE implements IOpCode2<M6502> {

	private IAddrMode addrMode = new AddrRel();
	
	@Override
	public int execute(M6502 cpu) {
		int target = addrMode.fetchAddr(cpu);
		if (!cpu.getZ()) {
			cpu.setPC(target);
		}
		return 3; // 4 if crossing page boundary
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "BNE " + addrMode.disassemble(cpu);
	}
}
