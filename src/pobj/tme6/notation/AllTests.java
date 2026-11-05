package pobj.tme6.notation;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	Q1aRAMCompile.class,
	Q1aRAMMethods.class,
	Q1bROMCompile.class,
	Q1bROMMethods.class,
	Q1cMaskCompile.class,
	Q1cMaskMethods.class,
	Q2aSlotEquals.class,
	Q2aSlotGetter.class,
	Q2bDecoderCompile.class,
	Q2bDecoderMethods.class,
	Q3DecoderCopy.class,
	Q3RAMCopy.class,
	Q4aPeriodicDevice.class,
	Q4bScreen1.class,
	Q4bScreen2.class,
	Q4bScreen3.class,
	Q4bScreen4.class,
	Q4bScreen5.class,
	Q5aCPUState.class,
	Q5bOpAdd.class,
	Q5bOpJump.class,
	Q5bOpLoad.class,
	Q5bOpSet.class,
	Q5bOpStore.class,
	Q5cCPU.class,
	Q6aEmulatorLoop.class,
	Q6bEmulator1.class,
	Q6bEmulator2.class,
	Q7Alarm.class,
	Q7Clock.class,
})
public class AllTests {
}
