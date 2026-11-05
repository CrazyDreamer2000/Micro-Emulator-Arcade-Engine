package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.PeriodicDevice;

public class TestPeriodicDevice {

	class Device extends PeriodicDevice {
		int called;
		public Device() { super(5); }
		public void action() { called++; }
	};

	@Test public void test() {
		Device d = new Device();
		assertEquals(0, d.called);
		d.tick(6);
		assertEquals(1, d.called);
	}
}
