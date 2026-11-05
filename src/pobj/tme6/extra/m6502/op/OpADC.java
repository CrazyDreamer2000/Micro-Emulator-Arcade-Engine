package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

public class OpADC implements IOpCode2<M6502> {

	private IAddrMode addrMode;
	
	public OpADC(IAddrMode iAddrMode) {
		this.addrMode = iAddrMode;
	}
	
	@Override
	public int execute(M6502 cpu) {
		int op = addrMode.fetchValue(cpu);
		int a = cpu.getA();
		int tmp = a + op + (cpu.getC() ? 1 : 0);		
		if (cpu.getD()) {
			// decimal mode
			if (((tmp ^ a ^ op) & 0x10) != 0) {
				tmp += 0x06;
			}
			if ((tmp & 0xf0) > 0x90) {
				tmp += 0x60;
			}
		}
		cpu.setC((tmp & 0x100) == 0x100);
		cpu.setV(((a ^ tmp) & (op ^ tmp) & 0x80) != 0);
		cpu.setA(tmp);
		cpu.updateNZ(cpu.getA());
		return addrMode.getCycles();
	}

	@Override
	public String disassemble(M6502 cpu) {
		return "ADC " + addrMode.disassemble(cpu);
	}	
}
