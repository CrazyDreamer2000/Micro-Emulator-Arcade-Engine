package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pobj.tme6.Emulator;

public class Q6bEmulator2 {
	
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream newOut = new PrintStream(buf);

	@Test
        public void test4() {
		Emulator emu = new Emulator(Utils.makeArray(0, 65, 3, 200, 0, 66, 3, 201, 0, 67, 3, 202, 4, 12));		
		System.setOut(newOut);
		emu.run(111);
		newOut.flush();
		assertEquals("ABC", buf.toString().replaceAll("\\s",""));
		assertEquals(12, emu.getCPU().getState().getPC());
		assertEquals(67, emu.getCPU().getState().getA());
	}
}
