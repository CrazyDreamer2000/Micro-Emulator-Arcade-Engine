package pobj.tme6.extra.dvg;

// DVG instruction: jump to subroutine
public class OpJSR implements IOpDVG {
	
	private int addr; // byte address

	// Takes as argument one 16-bit world opcode, and decodes it
	public OpJSR(int w1) {
		addr = 2*(w1&0xfff);
		if (addr >= 0x800) addr -= 0x800;
	}

	@Override
	public void execute(Drawer context) {
		context.jsr(addr);		
	}
	
	@Override
	public String toString() {
		return String.format("JSR $%04X", addr);
	}
	
	@Override
	public int length() {
		return 2;
	}
}
