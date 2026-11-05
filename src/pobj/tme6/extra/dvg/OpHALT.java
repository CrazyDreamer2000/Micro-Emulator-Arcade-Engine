package pobj.tme6.extra.dvg;

// DVG instruction: end of list
public class OpHALT implements IOpDVG {

	@Override
	public void execute(Drawer context) {
		// nothing
	}
	
	@Override
	public String toString() {
		return "HALT";
	}
	
	@Override
	public int length() {
		return 2;
	}
}
