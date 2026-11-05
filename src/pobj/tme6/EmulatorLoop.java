package pobj.tme6;

import java.util.ArrayList;
import java.util.List;

import pobj.tme6.cpu.ICPU;
import pobj.tme6.device.IDevice;

public class EmulatorLoop implements IEmulatorLoop {
	
	private ICPU cpu;
	private List<IDevice> listD;

	public EmulatorLoop(ICPU cpu) {
		this.listD = new ArrayList<>();
		this.cpu = cpu;
	}

	@Override
	public void addDevice(IDevice t) {
		if (t != null) {
			listD.add(t);
		}
	}

	@Override
	public void run(int time) {
		
		int sommeTime = 0;
		int tempsCpu = 0;

		do {
			tempsCpu = cpu.execute();
			sommeTime += tempsCpu;
			
			for (IDevice d : listD) {
				d.tick(tempsCpu);
			}	
		} while(sommeTime <= time);		
	}		
}

