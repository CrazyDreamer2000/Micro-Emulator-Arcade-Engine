package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpSBC implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpSBC(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int op = addrMode.fetchValue(cpu);
		if (cpu.getD()) {
			// decimal mode
			System.out.println("SBC: decimal mode not supported");
		}
		else {
			// binary mode
			int a = cpu.getA();
			int tmp = a - op - (cpu.getC() ? 1 : 0);		
			cpu.setC((tmp & 0xff00) == 0);
			cpu.setV((~(a ^ op) & (a ^ tmp) & 0x80) != 0);
			cpu.setA(tmp);
			cpu.updateNZ(tmp & 0xff);
		}
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "SBC " + addrMode.disassemble(cpu);
	}	
}
