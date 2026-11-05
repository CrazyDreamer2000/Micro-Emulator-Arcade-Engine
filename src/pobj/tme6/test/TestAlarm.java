package pobj.tme6.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.Alarm;
import pobj.tme6.device.IAction;

public class TestAlarm {

	class NoAction implements IAction {
		@Override public void action() { }
	}
	
	@Test public void test() {
		Alarm a1 = new Alarm(20, new NoAction());
		Alarm a2 = new Alarm(10, new NoAction());
		assertEquals(-1, a1.compareTo(a2));
	}
}
