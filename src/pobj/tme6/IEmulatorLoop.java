package pobj.tme6;

import pobj.tme6.device.IDevice;

public interface IEmulatorLoop {	
	// ajoute un périphérique
	public void addDevice(IDevice t);

	// exécute des instructions jusqu'à écoulementn d'un certain temps
	public void run(int time);
}
