package pobj.tme6.device;

public class Alarm implements Comparable<Alarm> {
	private int date;
	private IAction action;

	public Alarm(int date , IAction action) {
		this.date = date;
		this.action = action;
	}
	
	public int getDate() {
		return date;
	}
	
	public void action() {
		action.action();
	}
	
	@Override
	public int compareTo(Alarm a) {
	    if (a.getDate() > this.date) {
	        return 1;
	    }
	    if (a.getDate() < this.date) {
	        return -1;
	    }
	    return 0;
	}


	

}

