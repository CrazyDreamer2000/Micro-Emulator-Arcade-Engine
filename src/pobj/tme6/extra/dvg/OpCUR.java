package pobj.tme6.extra.dvg;

// DVG instruction: set current position
public class OpCUR implements IOpDVG {

	private int x, y, scale;

	// Takes as argument a two 16-bit world opcode, and decodes it
	public OpCUR(int w1, int w2) {
		scale = (w2 >> 12) & 0xf;
		y = w1 & 0x3ff;
		x = w2 & 0x3ff;
		if ((w1 & 0x400) != 0) y -= 0x800;
		if ((w2 & 0x400) != 0) x -= 0x800;
	}

	@Override
	public void execute(Drawer context) {
		context.setPosition(x, y);
		context.setScale(scale);
	}
	
	@Override
	public String toString() {
		return String.format("CUR x=%d y=%d scale=%d", x, y, scale);
	}
	
	@Override
	public int length() {
		return 4;
	}
}
