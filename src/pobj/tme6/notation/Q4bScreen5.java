package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pobj.tme6.device.Screen;

public class Q4bScreen5 {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream newOut = new PrintStream(buf);

    @Test
        public void testDeviceTwo() {
		Screen s = new Screen();
		System.setOut(newOut);
		s.write(0,  65);
		s.tick(150);
		s.write(1,  65);
		s.tick(100);
		newOut.flush();
		assertEquals("AAA", buf.toString().replaceAll("\\s",""));
	}
}
