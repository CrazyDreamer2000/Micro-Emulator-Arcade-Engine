package pobj.tme6.extra.dvg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Execute a DVG display list to generate a list of vectors to draw
public class Drawer {
	
	static private boolean log = false;
	
	private int x, y, scale;
	private int pc;
	
	private List<Vector> displayList  = new ArrayList<>();
	
	private LinkedList<Integer> stack = new LinkedList<>();

	public int getX() { return x; }
	public int getY() { return y; }
	public int getScale() { return scale; }
	public void setPosition(int x, int y) { this.x = x; this.y = y; }
	public void setScale(int scale) { this.scale = scale; }

	public List<Vector> getDisplayList() { return displayList; }
		
	public void line(int deltaX, int deltaY, int brightness) {
		Vector vec = new Vector(x, y, x+deltaX, y+deltaY, brightness);
		displayList.add(vec);
		if (log) {
			System.out.println(vec);
		}
		x += deltaX;
		y += deltaY;
	}

	public void jmp(int pc) {
		this.pc = pc;
	}

	public void jsr(int pc) {
		stack.addFirst(this.pc);
		this.pc = pc;
	}
	
	public void rts() {
		if (stack.isEmpty()) {
			// halt
			this.pc = -1;
		}
		else {
			this.pc = stack.removeFirst();
		}
	}
	
	public void execute(DVG dvg) {
		int max = 10000;
		if (log) {
			System.out.println("DVG: start executing");
		}
		for (pc = 0; pc >= 0 && max > 0; max--) {
			IOpDVG op = dvg.readOp(pc);
			if (log) {
				System.out.println(String.format("%04X: %s", pc, op.toString()));				
			}
			pc += op.length();
			if (op instanceof OpHALT) break;
			op.execute(this);
		}
		if (log) {
			System.out.println("DVG: finished executing");			
			//printDisplayList();
		}
	}
	
	public void printDisplayList() {
		for (Vector v: displayList) {
			System.out.println(v);
		}
	}
}

