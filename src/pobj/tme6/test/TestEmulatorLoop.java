package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.EmulatorLoop;
import pobj.tme6.cpu.CPU;
import pobj.tme6.device.Screen;
import pobj.tme6.memory.ROM;

public class TestEmulatorLoop {

	@Test public void test() {
		ROM rom = new ROM(new int[] { 0,9, 1, 10 } ); // SET 9; ADD 10
		CPU cpu = new CPU(rom);
		EmulatorLoop emu = new EmulatorLoop(cpu);
		emu.addDevice(new Screen());
		emu.run(3);
		assertEquals(19, cpu.getState().getA());
		assertEquals(4, cpu.getState().getPC());
	}
}
