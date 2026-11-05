package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpLoad implements IOpCode<ICPUState> {

	public OpLoad() {
	}

	@Override
	public int execute(ICPUState state) {
		
		int adresse = state.fetch();
		int valeur = state.getMemory().read(adresse);
		state.setA(valeur);
	
		return 2;
	}

}
