package pobj.tme6.notation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.tme6.memory.AddressDecoder;
import pobj.tme6.memory.IMemory;

public class Q2bDecoderCompile {

	@Test
        public void testClass() {
		AddressDecoder r = new AddressDecoder(100);
		assertTrue(r instanceof IMemory);
		assertTrue(AddressDecoder.class.getSuperclass() == Object.class);
		assertTrue(Utils.hasOnlyPrivateAttributes(AddressDecoder.class));
	}
}
