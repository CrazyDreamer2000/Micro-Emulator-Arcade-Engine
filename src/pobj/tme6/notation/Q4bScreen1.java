package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.IDevice;
import pobj.tme6.device.PeriodicDevice;
import pobj.tme6.device.Screen;
import pobj.tme6.memory.IMemory;

public class Q4bScreen1 {

    @Test
    public void testClass() {
		Screen s = new Screen();
		assertTrue(s instanceof IDevice);
		assertTrue(s instanceof IMemory);
		assertTrue(Screen.class.getSuperclass()==PeriodicDevice.class);
		assertTrue(Utils.hasOnlyPrivateAttributes(Screen.class));
	}
	
	@Test
        public void testRAM() {
		Screen s = new Screen();
		assertEquals(10, s.size());
		assertEquals(32, s.read(0));
		s.write(1, 99);
		assertEquals(99, s.read(1));
	}
}
