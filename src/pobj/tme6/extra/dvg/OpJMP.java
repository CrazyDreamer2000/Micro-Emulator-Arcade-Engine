package pobj.tme6.extra.dvg;

// DVG instruction: jump
public class OpJMP implements IOpDVG {

	private int addr; // byte address

	// Takes as argument one 16-bit world opcode, and decodes it
	public OpJMP(int w1) {
		addr = 2*(w1&0xfff);
		if (addr >= 0x800) addr -= 0x800;
	}

	@Override
	public void execute(Drawer context) {
		context.jmp(addr);		
	}
	
	@Override
	public String toString() {
		return String.format("JMP $%04X", addr);
	}
	
	@Override
	public int length() {
		return 2;
	}
}
