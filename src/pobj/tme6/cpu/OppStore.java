package pobj.tme6.cpu;

import pobj.tme6.cpu.op.IOpCode;

public class OppStore implements IOpCode<ICPUState> {

	public OppStore() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int execute(ICPUState state) {
		
		int valA = state.getA();
		int adresse = state.fetch();
		state.getMemory().write(adresse, valA);
		
		return 2;
	}

}
