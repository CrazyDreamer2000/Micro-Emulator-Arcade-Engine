package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpBIT implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpBIT(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int val = addrMode.fetchValue(cpu);
		cpu.setZ((val & cpu.getA()) == 0);
		cpu.setN(Utils.getBit(val,6));
		cpu.setV(Utils.getBit(val,7));
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "BIT " + addrMode.disassemble(cpu);
	}	
}
