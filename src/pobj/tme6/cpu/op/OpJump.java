package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpJump implements IOpCode<ICPUState> {

	public OpJump() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int execute(ICPUState state) {
		
		if (state.getA() > 0) {
			int arg = state.fetch();
			state.setPC(arg);
		}
		return 2;
	}

}
