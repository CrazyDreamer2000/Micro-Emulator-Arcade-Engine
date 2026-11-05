package pobj.tme6.extra.dvg;

// OpCode for a DVG instruction
public interface IOpDVG {
	public void execute(Drawer context);
	public String toString();
	public int length(); // byte-length of operation 
}
