package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.device.Alarm;
import pobj.tme6.device.IAction;

public class Q7Alarm {

	class Mock implements IAction {
		int called;
		@Override public void action() { called++; }
	};
	
	@Test
        public void testClass() {
		Alarm a = new Alarm(10, new Mock());
		assertTrue(a instanceof Comparable);
		assertTrue(Utils.hasOnlyPrivateAttributes(Alarm.class));
	}

	@Test
        public void testMethods() {
		Mock m = new Mock();
		Alarm a = new Alarm(10, m);
		assertEquals(10, a.getDate());
		a.action();
		assertEquals(1, m.called);
	}
	
	@Test
        public void testCompareTo() {
		Alarm a1 = new Alarm(10, new Mock());
		Alarm a2 = new Alarm(20, new Mock());
		assertEquals(0, a1.compareTo(a1));
		assertEquals(0, a2.compareTo(a2));
		assertEquals(1, a1.compareTo(a2));
		assertEquals(-1, a2.compareTo(a1));
	}
}
