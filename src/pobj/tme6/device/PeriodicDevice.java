package pobj.tme6.device;

public abstract class PeriodicDevice implements IDevice {
	
	private int cptPeriode;
	private int periode;

	public PeriodicDevice(int period) {
		
		this.cptPeriode = period;
		this.periode = period;
	}
	
	public abstract void action();

	@Override
	public void tick(int time) {
		
		cptPeriode -= time;
		
		if(cptPeriode <= 0) {
			action();
			cptPeriode = periode;
		}

	}

}
