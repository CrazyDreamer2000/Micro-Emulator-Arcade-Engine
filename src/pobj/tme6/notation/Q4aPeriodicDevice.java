package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import pobj.tme6.device.IDevice;
import pobj.tme6.device.PeriodicDevice;

public class Q4aPeriodicDevice {

	@Test
        public void testClass() {
		assertTrue(IDevice.class.isAssignableFrom(PeriodicDevice.class));
		assertTrue(Modifier.isAbstract(PeriodicDevice.class.getModifiers()));
		assertTrue(Utils.hasOnlyPrivateAttributes(PeriodicDevice.class));
	}
	
	class Mock extends PeriodicDevice {
		int called;
		public Mock() { super(10); }
		public void action() { called++; }
	};

	@Test
        public void testTick1() {
		Mock d = new Mock();
		assertEquals(0, d.called);
		d.tick(15);
		assertEquals(1, d.called);
	}

	@Test
        public void testTick2() {
		Mock d = new Mock();
		for (int i=0; i<10; i++) {
			assertEquals(0, d.called);
			d.tick(1);
		}
		for (int i=0; i<10; i++) {
			assertEquals(1, d.called);
			d.tick(1);
		}
		assertEquals(2, d.called);
		d.tick(10);
		assertEquals(3, d.called);
	}

}
