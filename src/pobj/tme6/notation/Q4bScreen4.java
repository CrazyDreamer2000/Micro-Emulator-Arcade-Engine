package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pobj.tme6.device.Screen;

public class Q4bScreen4 {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream newOut = new PrintStream(buf);

	@Test
        public void testDeviceText() {
		Screen s = new Screen();
		System.setOut(newOut);
		s.write(0,  66);
		s.write(1, 79);
		s.write(2, 78);
		s.write(3, 74);
		s.write(4,  79);
		s.write(5, 85);
		s.write(6, 82);
		s.write(8, 33);
		s.tick(150);
		newOut.flush();
		assertEquals("BONJOUR!", buf.toString().replaceAll("\\s",""));
	}
}
