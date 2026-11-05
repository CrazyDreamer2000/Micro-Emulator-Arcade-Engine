package pobj.tme6;

import pobj.tme6.cpu.CPU;
import pobj.tme6.device.Screen;
import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;
import pobj.tme6.memory.ROM;

public class Emulator {
	private EmulatorLoop emuLoop;
	private CPU cpu;
	
	public Emulator(int[] rom) {
		
		Screen screen = new Screen();

		MemorySlot mSlot1 = new MemorySlot(100, new RAM(100,0));
		MemorySlot mSlot2 = new MemorySlot(0, new ROM(rom));
		MemorySlot mSlot3 = new MemorySlot(200,screen);
				
		AddressDecoder adrDecoder = new AddressDecoder(210);
		this.cpu = new CPU(adrDecoder);
		
			adrDecoder.add(mSlot1);
			adrDecoder.add(mSlot2);
			adrDecoder.add(mSlot3);
			

		this.emuLoop = new EmulatorLoop(cpu);
		this.emuLoop.addDevice(screen);	
	}
	
	public CPU getCPU() {
		return cpu;
	}
	
	public void run(int time) {
		
		this.cpu.reset();
		this.emuLoop.run(time);
	}
	
	
	

}
