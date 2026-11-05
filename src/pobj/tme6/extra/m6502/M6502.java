package pobj.tme6.extra.m6502;

import java.util.Arrays;
import java.util.List;

import pobj.tme6.cpu.ICPU;
import pobj.tme6.extra.m6502.op.*;
import pobj.tme6.memory.IMemory;

/*
 * MOS 6502 processor emulation.
 */
public class M6502 implements ICPU {

	static private boolean log = false;
	static private boolean logError = false;

	
	// Registers
	//////////////////
	
	private int A; // 8-bit accumulator
	private int X; // 8-bit index
	private int Y; // 8-bit index
	private int P; // 7-bit status flag bits NV1BDIZC
	private int S; // 8-bit stack pointer
	private int PC; // 18-bit program counter

	public final static int STATUS_DFL = 0x20;
	public final static int STACK_DFL = 0xFD;

	public int getA() { return A; }
	public int getX() { return X; }
	public int getY() { return Y; }
	public int getS() { return S;}
	public int getP() { return P; }
	public int getPC() { return PC; }
	public void setA(int v) { A  = v & 0xff; }
	public void setX(int v) { X = v & 0xff; }
	public void setY(int v) { Y = v & 0xff; }
	public void setS(int v) { S = v & 0xff;}
	public void setP(int v) { P = v & 0xff; }
	public void setPC(int v) { PC = v & 0xffff; }
	
	// status flags (P)
	public final static int STATUS_C = 0;
	public final static int STATUS_Z = 1;
	public final static int STATUS_I = 2;
	public final static int STATUS_D = 3;
	public final static int STATUS_B = 4;
	public final static int STATUS_V = 6;
	public final static int STATUS_N = 7;	
	

	// Access to individual flag bits
	public boolean getC() { return Utils.getBit(P,STATUS_C); }
	public boolean getZ() { return Utils.getBit(P,STATUS_Z); }
	public boolean getI() { return Utils.getBit(P,STATUS_I); }
	public boolean getD() { return Utils.getBit(P,STATUS_D); }
	public boolean getB() { return Utils.getBit(P,STATUS_B); }
	public boolean getV() { return Utils.getBit(P,STATUS_V); }
	public boolean getN() { return Utils.getBit(P,STATUS_N); }
	public void setC(boolean v) { P = Utils.setBit(P,STATUS_C,v); }
	public void setZ(boolean v) { P = Utils.setBit(P,STATUS_Z,v); }
	public void setI(boolean v) { P = Utils.setBit(P,STATUS_I,v); }
	public void setD(boolean v) { P = Utils.setBit(P,STATUS_D,v); }
	public void setB(boolean v) { P = Utils.setBit(P,STATUS_B,v); }
	public void setV(boolean v) { P = Utils.setBit(P,STATUS_V,v); }
	public void setN(boolean v) { P = Utils.setBit(P,STATUS_N,v); }

	public String regsToString() {
		return String.format("PC:%04X A:%02X X:%02X Y:%02X S:%02X %c%c%c%c%c%c%c", 
				PC, A, X, Y, S, getC() ? 'C' : '-', getZ() ? 'Z' : '-', getI() ? 'I' : '-', getD() ? 'D' : '-', getB() ? 'B' : '-', getV() ? 'V' : '-', getN() ? 'N' : '-');
	}
	
	
	// Memory
	///////////////
	
	private IMemory memory;

	public IMemory getMemory() {
		return memory;
	}
	
	// 8-bit access
	public int readMem8(int addr) {
		try {
			return ((int)memory.read(addr & 0xffff)) & 0xff;
		}
		catch (IllegalArgumentException e) {
			if (logError) {
				System.out.println(e);
			}
			return 0;
		}
	}

	public void writeMem8(int addr, int value) {
		try {
			memory.write(addr & 0xffff, value & 0xff);
		}
		catch (IllegalArgumentException e) {
			if (logError) {
				System.out.println(e);
			}
		}
	}
	
	/*
	 * 16-bit accesses are performed as two successive 8-bit accesses.
	 * but a 16-bit access cannot cross a 256-byte "page" boundary, i.e.
	 * carry do not propagate to the 8 top address bits!
	 */
	
	public int readMem16(int addr) {
		int lo = readMem8(addr);
		int hi = readMem8( (addr & 0xff00) | ((addr+1) & 0xff));
		return lo | (hi << 8);
	}

	// Fetch next instruction byte at PC and increment PC
	public int fetch() {
		int val = readMem8(PC);
		PC = (PC+1) & 0xffff;
		return val;
	}

	// fetch two bytes to build a 16-bit word
	public int fetch16() {
		int lo = fetch();
		int hi = fetch();
		return lo | (hi << 8);
	}
	
	
	// Stack
	////////////
	
	public void push8(int val) {
		writeMem8(0x100 + S, val & 0xff);
		S = (S - 1) & 0xff;
	}

	public void push16(int val) {
		push8(val >> 8);
		push8(val);
	}
	
	public int pop8() {
		S = (S + 1) & 0xff;
		return readMem8(0x100 + S);
	}
	
	public int pop16() {
		int lo =pop8();
		int hi = pop8();
		return lo | (hi << 8);
	}
	

	// Interrupts
	///////////////////
	

	public static final int VEC_NMI = 0xFFFA; // external non-maskable
	public static final int VEC_RESET = 0xFFFC;
	public static final int VEC_IRQ = 0xFFFE; // external marskable
	public static final int VEC_BRK = 0xFFFE; // internal, shared with IRQ

	
	
	// Utilities
	///////////////
	
	// update zero and negative flag
	public void updateNZ(int v) {
		setZ((v & 0xff) == 0);
		setN(Utils.getBit(v,7));
	}
	

	// Opcodes
	/////////////////
	
	List<IOpCode2<M6502>> buildOpCodeTable() {
		IAddrMode abs = new AddrAbs();
		IAddrMode  absX = new AddrAbsX();
		IAddrMode  absY = new AddrAbsY();
		IAddrMode  imm = new AddrImmed();
		IAddrMode  ind = new AddrInd();
		IAddrMode  indX = new AddrIndX();
		IAddrMode  indY = new AddrIndY();
		IAddrMode  zp = new AddrZP();
		IAddrMode  zpX = new AddrZPX();
		IAddrMode  zpY = new AddrZPY();

		IOpCode2<M6502> unk = new OpUNK();
		
		// OpCode table
		List<IOpCode2<M6502>> op = 	Arrays.asList(
			/* 00 */
				new OpBRK(), new OpORA(indX), unk, unk, unk, new OpORA(zp), new OpASL(zp), unk, 
				new OpPHP(), new OpORA(imm), new OpASLA(), unk, unk, new OpORA(abs), new OpASL(abs), unk,
			/*	 10 */
				new OpBPL(), new OpORA(indY), unk, unk, unk, new OpORA(zpX), new OpASL(zpX), unk, 
				new OpCLC(), new OpORA(absY), unk, unk, unk, new OpORA(absX), new OpASL(absX), unk,
			/* 20 */
				new OpJSR(), new OpAND(indX), unk, unk, new OpBIT(zp), new OpAND(zp), new OpROL(zp), unk, 
				new OpPLP(), new OpAND(imm), new OpROLA(), unk, new OpBIT(abs), new OpAND(abs), new OpROL(abs), unk,
			/* 30 */
				new OpBMI(), new OpAND(indY), unk, unk, unk, new OpAND(zpX), new OpROL(zpX), unk, 
				new OpSEC(), new OpAND(absY), unk, unk, unk, new OpAND(absX), new OpROL(absX), unk,
			/* 40 */
				new OpRTI(), new OpEOR(indX), unk, unk, unk, new OpEOR(zp), new OpLSR(zp), unk, 
				new OpPHA(), new OpEOR(imm), new OpLSRA(), unk, new OpJMP(abs), new OpEOR(abs), new OpLSR(abs), unk,
			/* 50 */
				new OpBVC(), new OpEOR(indY), unk, unk, unk, new OpEOR(zpX), new OpLSR(zpX), unk, 
				new OpCLI(), new OpEOR(absY), unk, unk, unk, new OpEOR(absX), new OpLSR(absX), unk,
			/* 60 */
				new OpRTS(), new OpADC(indX), unk, unk, unk, new OpADC(zp), new OpROR(zp), unk, 
				new OpPLA(), new OpADC(imm), new OpRORA(), unk, new OpJMP(ind), new OpADC(abs), new OpROR(abs), unk,
			/* 70 */
				new OpBVS(), new OpADC(indY), unk, unk, unk, new OpADC(zpX), new OpROR(zpX), unk, 
				new OpSEI(), new OpADC(absY), unk, unk, unk, new OpADC(absX), new OpROR(absX), unk,
			/* 80 */
				unk, new OpSTA(indX), unk, unk, new OpSTY(zp), new OpSTA(zp), new OpSTX(zp), unk, 
				new OpDEY(), unk, new OpTXA(), unk, new OpSTY(abs), new OpSTA(abs), new OpSTX(abs), unk,
			/* 90 */
				new OpBCC(), new OpSTA(indY), unk, unk, new OpSTY(zpX), new OpSTA(zpX), new OpSTX(zpY), unk, 
				new OpTYA(), new OpSTA(absY), new OpTXS(), unk, unk, new OpSTA(absX), unk, unk,
			/* A0 */
				new OpLDY(imm), new OpLDA(indX), new OpLDX(imm), unk, new OpLDY(zp), new OpLDA(zp), new OpLDX(zp), unk, 
				new OpTAY(), new OpLDA(imm), new OpTAX(), unk, new OpLDY(abs), new OpLDA(abs), new OpLDX(abs), unk,
			/* B0 */
				new OpBCS(), new OpLDA(indY), unk, unk, new OpLDY(zpX), new OpLDA(zpX), new OpLDX(zpY), unk, 
				new OpCLV(), new OpLDA(absY), new OpTSX(), unk, new OpLDY(absX), new OpLDA(absX), new OpLDX(absY), unk,
			/* C0 */
				new OpCPY(imm), new OpCMP(indX), unk, unk, new OpCPY(zp), new OpCMP(zp), new OpDEC(zp), unk, 
				new OpINY(), new OpCMP(imm), new OpDEX(), unk, new OpCPY(abs), new OpCMP(abs), new OpDEC(abs), unk,
			/* D0 */
				new OpBNE(), new OpCMP(indY), unk, unk, unk, new OpCMP(zpX), new OpDEC(zpX), unk, 
				new OpCLD(), new OpCMP(absY), unk, unk, unk, new OpCMP(absX), new OpDEC(absX), unk,
			/* E0 */
				new OpCPX(imm), new OpSBC(indX), unk, unk, new OpCPX(zp), new OpSBC(zp), new OpINC(zp), unk, 
				new OpINX(), new OpSBC(imm), new OpNOP(), unk, new OpCPX(abs), new OpSBC(abs), new OpINC(abs), unk,
			/* F0 */
				new OpBEQ(), new OpSBC(indY), unk, unk, unk, new OpSBC(zpX), new OpINC(zpX), unk, 
				new OpSED(), new OpSBC(absY), unk, unk, unk, new OpSBC(absX), new OpINC(absX), unk
			);
		
		if (op.size() != 256) throw new AssertionError("OpCode table construcion failed");
		
		return op;
	}
	
	
	
	// ICPU interface
	//////////////////////////
	
	private List<IOpCode2<M6502>> opcodes;
	
	public M6502(IMemory memory) {
		this.memory = memory;
		opcodes = buildOpCodeTable();
	}
	
	int time = 0;
	
	@Override
	public void reset() {
		if (log) {
			System.out.println("6502 reset");
		}
		// initial register values
		A = 0;
		X = 0;
		Y = 0;
		S = STACK_DFL;
		P = STATUS_DFL;
		PC = readMem16(VEC_RESET);
		setI(true);
	}

	@Override
	public int execute() {
		
		IOpCode2<M6502> op = opcodes.get(fetch());
		
		if (op instanceof OpUNK) {
			System.out.println(String.format("%d %04X %02X unknown opcode", time, PC-1, op));		
		}
		
		if (log) {
			int oldPC = PC-1;
			String s = op.disassemble(this);
			System.out.print(String.format("%d %04X:", time, oldPC));
			for (int i = 0; i < 4;i++) {
				if (oldPC+i < PC) 	System.out.print(String.format(" %02X", readMem8(oldPC + i)));
				else System.out.print("   ");
			}
			System.out.print(String.format("  %-16s",s));
			PC = oldPC+1;
		}
		
		int cycles = op.execute(this);
		
		if (log) {
			System.out.println("   -> (" + cycles + ") " +regsToString());
		}
		
		time += cycles;
		return cycles;
	}

	// disassembler
	public void disassemble(int start, int end) {
		int oldPC = PC;
		PC = start;
		while (PC < end) {
			int orgPC = PC;
			IOpCode2<M6502> op = opcodes.get(fetch());
			String s = op.disassemble(this);
			System.out.print(String.format("%04X:", orgPC));
			for (int i = 0; i < 4;i++) {
				if (orgPC+i < PC)  System.out.print(String.format(" %02X", readMem8(orgPC + i)));
				else System.out.print("   ");
			}
			System.out.println(String.format("  %-16s",s));
		}
		PC = oldPC;
	}
	
	// non-masquable interrupt
	public void nmi() {
		if (log) {
			System.out.println(time + " 6502 interrupt");
		}
		push16(PC);
		push8(P);
		//setI(true);
		setPC(readMem16(M6502.VEC_NMI));
		// should consume 7 cycles
	}
}
