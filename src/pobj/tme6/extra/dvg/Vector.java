package pobj.tme6.extra.dvg;

public class Vector {

	private final int x0,y0,x1,y1;
	private final int brightness;
	
	public int getX0() {
		return x0;
	}

	public int getY0() {
		return y0;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getBrightness() {
		return brightness;
	}

	public Vector(int x0, int y0, int x1, int y1, int brightness) {
		this.x0 = x0;
		this.x1 = x1;
		this.y0  = y0;
		this.y1 = y1;
		this.brightness = brightness;
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d)-(%d,%d)@%d",x0,y0,x1,y1,brightness);
	}
}
