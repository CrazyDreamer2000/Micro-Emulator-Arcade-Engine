package pobj.tme6.extra.m6502.op;

import pobj.tme6.extra.m6502.M6502;

// Addressing mode, usable in an OpCode to fetch arguments fom / store result to memory
public interface IAddrMode {
	
	// Where to read argument from  / write to in memory
	// undefined for immediate addressing mode (no address, only a value)
	// may cause PC to be incremented
	public int fetchAddr(M6502 cpu);
	
	// Fetched value of the argument
	// may cause PC to be incremented
	default public int fetchValue(M6502 cpu) { return cpu.readMem8(fetchAddr(cpu)); }

	// String representation of the addressing mode, as used in the disassembler
	// may cause PC to be incremented	
	public String disassemble(M6502 cpu);
	
	// Number of extra cycles caused by this addressing mode
	// may not be always accurate (if this is address-dependent)
	public int getCycles();
}
