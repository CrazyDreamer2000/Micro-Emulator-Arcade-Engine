package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;
import pobj.tme6.extra.m6502.Utils;

public class OpROL implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpROL(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int addr = addrMode.fetchAddr(cpu);
		int val = cpu.readMem8(addr);
		val = (val << 1) | (cpu.getC() ? 1 : 0);
		cpu.setC(Utils.getBit(val,8));
		val &= 0xff;
		cpu.updateNZ(val);
		cpu.writeMem8(addr, val);
		return addrMode.getCycles() + 2;
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "ROL " + addrMode.disassemble(cpu);
	}	
}
