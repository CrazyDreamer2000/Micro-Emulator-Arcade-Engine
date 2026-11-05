package pobj.tme6.extra.dvg;

import pobj.tme6.memory.RAM;
import pobj.tme6.memory.ROM;

// Digital Vector Generator: vector graphic chip used on some Atari games, such as Asteroids
public class DVG {

	private RAM ram;
	private ROM rom;
	
	public DVG(RAM ram, ROM rom) {
		this.rom = rom;
		this.ram = ram;
		System.out.println("DVG vector ROM");
		disassemble(ram.size(), rom.size());
	}

	public int read(int addr) { 
		if (addr < ram.size()) {
			return ram.read(addr); 
		}
		else {
			return rom.read(addr - ram.size());
		}
	}
	
	int readWord(int addr) {
		return read(addr) | (read(addr+1) << 8);
	}
	
	public IOpDVG readOp(int addr) {
		int w1 = readWord(addr);
		switch (w1 >> 12) {
		case 0xF: return new OpSVEC(w1);
		case 0xE: return new OpJMP(w1);
		case 0xD: return new OpRTS();
		case 0xC: return new OpJSR(w1);
		case 0xB: return new OpHALT();
		case 0xA: return new OpCUR(w1, readWord(addr+2));
		default: return new OpVEC(w1, readWord(addr+2));
		}
	}
	
	public void disassemble(int addr, int size) {
		for (int pc = addr; pc < addr+size;) {
			IOpDVG op = readOp(pc);
			System.out.println(String.format("%04X: %s", pc, op.toString()));
			pc += op.length();
			if (op instanceof OpHALT) break;
		}
	}
	
}
