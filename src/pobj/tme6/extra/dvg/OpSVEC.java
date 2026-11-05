package pobj.tme6.extra.dvg;

// DVG instruction: draw vector (short version)
public class OpSVEC implements IOpDVG {

	private int deltaX, deltaY, scale, brightness;

	// Takes as argument a 16-bit world opcode, and decodes it
	public OpSVEC(int w1) {
		deltaY = (w1 >> 8) & 3;
		deltaX = w1 & 3;
		if ((w1 & 0x0400) != 0) deltaY = -deltaY;
		if ((w1 & 0x0004) != 0) deltaX = -deltaX;		
		scale = ((w1 & 0x0800) >> 11)  | ((w1 & 0x008) >> 2);
		brightness = (w1 >> 4) & 0xf;
	}

	@Override
	public void execute(Drawer context) {
		int s = (scale + 2 + context.getScale()) & 0xf;
		if (s > 9) s = -1;
		int dx = (deltaX << 8) >> (9-s);
		int dy = (deltaY << 8) >> (9-s);
		context.line(dx, dy, brightness);
	}
	
	@Override
	public String toString() {
		return String.format("SVEC dx=%d dy=%d scale=%d br=%d", deltaX, deltaY, scale, brightness);
	}
	
	@Override
	public int length() {
		return 2;
	}
}
