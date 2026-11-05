package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pobj.tme6.device.Screen;

public class Q4bScreen3 {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream newOut = new PrintStream(buf);

	@Test
    public void testDeviceZero() {
		Screen s = new Screen();
		System.setOut(newOut);
		s.write(0,  66);
		s.tick(10);
		newOut.flush();
		assertEquals("", buf.toString());
	}

}
