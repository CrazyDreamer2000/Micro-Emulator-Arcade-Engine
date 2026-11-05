package pobj.tme6.extra.dvg;

// DVG instruction: draw vector
public class OpVEC implements IOpDVG {

	private int deltaX, deltaY, scale, brightness;

	// Takes as argument a two 16-bit world opcode, and decodes it
	public OpVEC(int w1, int w2) {
		scale = (w1 >> 12) & 0xf;
		brightness = (w2 >> 12) & 0xf;
		deltaY = w1 & 0x3ff;
		deltaX = w2 & 0x3ff;
		if ((w1 & 0x0400) != 0) deltaY = -deltaY;
		if ((w2 & 0x0400) != 0) deltaX = -deltaX;
	}

	@Override
	public void execute(Drawer context) {
		int s = (scale + context.getScale()) & 0xf;
		if (s > 9) s = -1;
		int dx = deltaX >> (9-s);
		int dy = deltaY >> (9-s);
		context.line(dx, dy, brightness);
	}
	
	@Override
	public String toString() {
		return String.format("VEC dx=%d dy=%d scale=%d br=%d", deltaX, deltaY, scale, brightness);
	}
	
	@Override
	public int length() {
		return 4;
	}
}
