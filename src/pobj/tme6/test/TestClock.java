package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.Alarm;
import pobj.tme6.device.Clock;
import pobj.tme6.device.IAction;

public class TestClock {

	class Act implements IAction {
		boolean called = false;
		@Override public void action() { called = true; }
	};
	
	@Test public void test() {
		Clock c = new Clock();
		Act a = new Act();
		c.addAlarm(new Alarm(10, a)); 
		c.tick(20);
		assertTrue(a.called);
	}
}
