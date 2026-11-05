package pobj.tme6.extra.dvg;

// DVG instruction: return from subroutine
public class OpRTS implements IOpDVG {

	@Override
	public void execute(Drawer context) {
		context.rts();
	}
	
	@Override
	public String toString() {
		return "RTS";
	}
	
	@Override
	public int length() {
		return 2;
	}
}
