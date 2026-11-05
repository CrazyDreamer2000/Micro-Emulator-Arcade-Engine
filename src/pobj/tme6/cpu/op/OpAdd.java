package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpAdd implements IOpCode<ICPUState> {

	public OpAdd() {
	}

	@Override
	public int execute(ICPUState state) {
		
		int arg = state.fetch();
		state.setA(state.getA() + arg);
		return 2;
	}

}
