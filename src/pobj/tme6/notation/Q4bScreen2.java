package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import pobj.tme6.device.Screen;

public class Q4bScreen2 {

    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    PrintStream newOut = new PrintStream(buf);

    @Test
        public void testDeviceEmpty() {
		Screen s = new Screen();
		System.setOut(newOut);
		s.tick(150);
		newOut.flush();
		assertEquals("", buf.toString().replaceAll("\\s",""));
	}
}
