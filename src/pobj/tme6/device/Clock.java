package pobj.tme6.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clock implements IDevice {
	
	private int dateCurr;
	private List<Alarm> listA = new ArrayList<>();

	public Clock() {
		this.dateCurr = 0;
	}

	@Override
	public void tick(int time) {
		
		this.dateCurr += time;
		
		while (!listA.isEmpty()){
			Alarm lastAlarm = this.listA.get(listA.size()-1);
			
			if (lastAlarm.getDate() <= dateCurr){
				lastAlarm.action();
				listA.remove(listA.size()-1);
			}else {
				break;
			}
		}
		
	}
	
	
	
	
	public void addAlarm(Alarm a) {
		this.listA.add(a);
		Collections.sort(this.listA);
	}

}
