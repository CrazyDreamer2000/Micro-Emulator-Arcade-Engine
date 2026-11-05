package pobj.tme6.extra;

import java.io.FileNotFoundException;

import pobj.tme6.EmulatorLoop;
import pobj.tme6.device.PeriodicDevice;
import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.AddressMask;
import pobj.tme6.memory.IMemory;
import pobj.tme6.memory.MemorySlot;
import pobj.tme6.memory.RAM;
import pobj.tme6.memory.ROM;
import pobj.tme6.extra.dvg.DVG;
import pobj.tme6.extra.dvg.Drawer;
import pobj.tme6.extra.m6502.M6502;

public class AsteroidsEmulator {
	
	static public final int clock =1500000; // 1.5 MHz
	static public final int nmi = 250; // 250 Hz
	
	private M6502 cpu;
	private AsteroidsInput in0, in1;
	private BankedRAM bankedRAM ;
	private DVG dvg; 
	private AsteroidsScreen screen; 
	private EmulatorLoop emu;
	
	// NMI @ 250Hz
	public class NMI extends PeriodicDevice {
		
		public NMI() { 
			super(clock / nmi); 
		}
		
		@Override 
		public void action() { 
			cpu.nmi(); 
		}
	}
	
	// RAM with switchable banks
	public class BankedRAM implements IMemory {

		private int bank;
		private RAM ram = new RAM(0x200, 0);
		
		@Override
		public int size() {
			return ram.size();
		}

		@Override
		public int read(int addr) {
			return ram.read(bank == 0 ? addr : addr ^ 0x100);
		}

		@Override
		public void write(int addr, int value) {
			ram.write(bank == 0 ? addr : addr ^ 0x100, value);
		}
		
		public void setBank(int bank) {
			this.bank = bank;
		}
	}
		
	
	// Bank select
	public class BankedRAMSwitch implements IMemory {

		@Override
		public int size() {
			return 1;
		}

		@Override
		public int read(int addr) {
			return 0;
		}

		@Override
		public void write(int addr, int value) {
			bankedRAM.setBank((value >> 2) & 1);
		}		
	}
	
	// Dip switch
	public class DipSwitch implements IMemory {

		@Override
		public int size() {
			return 4;
		}

		@Override
		public int read(int addr) {
			return 0;
		}

		@Override
		public void write(int addr, int value) {
		}
	}
	
	// Trigger a frame draw
	public class DVGTrigger implements IMemory {

		@Override
		public int size() {
			return 1;
		}

		@Override
		public int read(int addr) {
			return 0;
		}

		@Override
		public void write(int addr, int value) {
			Drawer d = new Drawer();
			d.execute(dvg);
			screen.drawFrame(d.getDisplayList());
		}	
	}
	
	
	// plug everything together
	public AsteroidsEmulator(AsteroidsScreen screen, AsteroidsKeyListener keys) throws FileNotFoundException {
		AddressDecoder map = new AddressDecoder(0x8000);
		IMemory memory = new AddressMask(map, 0x10000, 0x7fff);

		RAM ramLo = new RAM(0x200, 0);  
		bankedRAM = new BankedRAM();
		in0 = new AsteroidsInput("IN0");
		in1 = new AsteroidsInput("IN1");		
		IMemory dsw1 = new DipSwitch();
		DVGTrigger dvgTrigger = new DVGTrigger();
		RAM vectorRam = new RAM(0x800, 0);
		ROM vectorRom = new ROM(Utils.loadROMbin("data/035127-02.np3", 0x800));
		ROM rom1 = new ROM(Utils.loadROMbin("data/035145-04e.ef2", 0x800));
		ROM rom2 = new ROM(Utils.loadROMbin("data/035144-04e.h2", 0x800));
		ROM rom3 = new ROM(Utils.loadROMbin("data/035143-02.j2", 0x800));
		keys.setInputs(in0, in1);
		this.screen = screen;
		dvg = new DVG(vectorRam, vectorRom);

		map.add(new MemorySlot(0x0000, ramLo));
		map.add(new MemorySlot(0x0200, bankedRAM));
		map.add(new MemorySlot(0x2000, in0));
		map.add(new MemorySlot(0x2400, in1));
		map.add(new MemorySlot(0x2800, dsw1));
		map.add(new MemorySlot(0x3000, dvgTrigger));
		map.add(new MemorySlot(0x4000, vectorRam));
		map.add(new MemorySlot(0x5000, vectorRom));
		map.add(new MemorySlot(0x6800, rom1));
		map.add(new MemorySlot(0x7000, rom2));
		map.add(new MemorySlot(0x7800, rom3));
		
		map.add(new MemorySlot(0x3200, new BankedRAMSwitch() ));
		
		cpu = new M6502(memory);
		cpu.reset();

		// memory dump
		for (int x = 0x6800; x < 0x8000;) {
			System.out.print(String.format("%04X:", x));
			for(int i = 0; i < 32; i++,x++) {
				System.out.print(String.format(" %02X", memory.read(x)));
			}
			System.out.println();
		}
		
		// disassembly
		cpu.disassemble(0x6800,  0x7ff9);
				
		emu = new EmulatorLoop(cpu);
		emu.addDevice(new NMI());
	}
	
	public void run(double secs) {
		emu.run((int)(clock * secs));
	}
}
