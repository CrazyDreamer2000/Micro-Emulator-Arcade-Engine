package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.EmulatorLoop;
import pobj.tme6.IEmulatorLoop;
import pobj.tme6.cpu.CPU;
import pobj.tme6.cpu.ICPU;
import pobj.tme6.device.IDevice;
import pobj.tme6.memory.IMemory;

public class Q6aEmulatorLoop {
	
	@Test
        public void testNew() {
		IMemory ram = new RAMnotation(10,0);
		ICPU cpu = new CPU(ram);
		EmulatorLoop e = new EmulatorLoop(cpu);
		assertTrue(e instanceof IEmulatorLoop);	
		assertTrue(Utils.hasOnlyPrivateAttributes(EmulatorLoop.class));
	}

	@Test
        public void testRun() {
		ROMnotation rom = new ROMnotation(Utils.makeArray(0,9));
		CPU cpu = new CPU(rom);
		EmulatorLoop e = new EmulatorLoop(cpu);
		e.run(1);
		assertEquals(2, cpu.getState().getPC());
		assertEquals(9, cpu.getState().getA());
	}

	@Test
        public void testRun2() {
		ROMnotation rom = new ROMnotation(Utils.makeArray(0,9,1,10));
		CPU cpu = new CPU(rom);
		EmulatorLoop e = new EmulatorLoop(cpu);
		e.run(3);
		assertEquals(4, cpu.getState().getPC());
		assertEquals(19, cpu.getState().getA());
	}

	class MockCPU implements ICPU {
		private int executeCalled;
		@Override public void reset() { }
		@Override public int execute() { executeCalled++; return 1; }
	}

	@Test
        public void testDelegate() {
		MockCPU m = new MockCPU();
		EmulatorLoop e = new EmulatorLoop(m);
		e.run(10);
		assertTrue(8 <= m.executeCalled && m.executeCalled <= 12);
	}

	class MockDevice implements IDevice {
		int ticks;
		@Override public void tick(int time) { ticks += time; }
	}

	@Test
        public void testDevice1() {
		ROMnotation rom = new ROMnotation(Utils.makeArray(0, 99, 4, 2));
		CPU cpu = new CPU(rom);
		EmulatorLoop e = new EmulatorLoop(cpu);
		MockDevice d = new MockDevice();
		e.addDevice(d);
		e.run(90);
		assertEquals(2, cpu.getState().getPC());
		assertTrue(88 <= d.ticks && d.ticks <=92);
	}

	@Test
        public void testDevice2() {
		ROMnotation rom = new ROMnotation(Utils.makeArray(0, 99, 4, 2));
		CPU cpu = new CPU(rom);
		EmulatorLoop e = new EmulatorLoop(cpu);
		MockDevice d1 = new MockDevice();
		MockDevice d2 = new MockDevice();
		e.addDevice(d1);
		e.addDevice(d2);
		e.run(90);
		assertTrue(88 <= d1.ticks && d1.ticks <=92);
		assertTrue(88 <= d2.ticks && d2.ticks <=92);
	}
}
