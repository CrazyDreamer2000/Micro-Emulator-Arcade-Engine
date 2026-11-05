package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.Alarm;
import pobj.tme6.device.Clock;
import pobj.tme6.device.IAction;
import pobj.tme6.device.IDevice;

public class Q7Clock {

	class Mock implements IAction {
		int called;
		@Override public void action() { called++; }
	};
	
	@Test
        public void testClass() {
		Clock c = new Clock();
		assertTrue(c instanceof IDevice);
		assertTrue(Utils.hasOnlyPrivateAttributes(Clock.class));
	}

	@Test
        public void testAdd() {
		Mock m = new Mock();
		Clock c = new Clock();
		c.addAlarm(new Alarm(10,  m));
		assertEquals(0, m.called);
		c.tick(8);
		assertEquals(0, m.called);
		c.tick(8);
		assertEquals(1, m.called);
		c.tick(8);
		assertEquals(1, m.called);
	}
	
	@Test
        public void testAdd2() {
		Mock m1 = new Mock();
		Mock m2 = new Mock();
		Clock c = new Clock();
		c.addAlarm(new Alarm(10,  m1));
		c.addAlarm(new Alarm(20,  m2));
		c.tick(15);
		assertEquals(1, m1.called);
		assertEquals(0, m2.called);
		c.tick(15);
		assertEquals(1, m1.called);
		assertEquals(1, m2.called);
	}
	
	@Test
        public void testAdd3() {
		Mock m1 = new Mock();
		Mock m2 = new Mock();
		Clock c = new Clock();
		c.addAlarm(new Alarm(20,  m2));
		c.addAlarm(new Alarm(10,  m1));
		c.tick(15);
		assertEquals(1, m1.called);
		assertEquals(0, m2.called);
		c.tick(15);
		assertEquals(1, m1.called);
		assertEquals(1, m2.called);
	}
	
	@Test
        public void testAdd4() {
		Mock m1 = new Mock();
		Mock m2 = new Mock();
		Clock c = new Clock();
		c.addAlarm(new Alarm(10,  m2));
		c.addAlarm(new Alarm(20,  m1));
		c.tick(30);
		assertEquals(1, m1.called);
		assertEquals(1, m2.called);
	}
	
	@Test
        public void testAdd5() {
		Mock m1 = new Mock();
		Mock m2 = new Mock();
		Mock m3 = new Mock();
		Clock c = new Clock();
		c.addAlarm(new Alarm(10,  m1));
		c.tick(5);
		c.addAlarm(new Alarm(20,  m2));
		c.tick(10);
		assertEquals(1, m1.called);
		assertEquals(0, m2.called);
		c.addAlarm(new Alarm(30,  m3));
		c.tick(10);
		assertEquals(1, m1.called);
		assertEquals(1, m2.called);
		assertEquals(0, m3.called);
		c.tick(10);
		assertEquals(1, m1.called);
		assertEquals(1, m2.called);
		assertEquals(1, m3.called);
	}
}
