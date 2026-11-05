package pobj.tme6.cpu;

import java.util.ArrayList;
import java.util.List;

import pobj.tme6.cpu.op.*;
import pobj.tme6.memory.IMemory;


public class CPU implements ICPU {
	private ICPUState state;
	private List<IOpCode<ICPUState>> ops; 

	public CPU(IMemory memoire) {
		this.ops = new ArrayList<>();
		
		ops.add(new OpSet());
		ops.add(new OpAdd());
		ops.add(new OpLoad());
		ops.add(new OpStore());
		ops.add(new OpJump());
		this.state = new CPUState(memoire);
	}

	@Override
	public void reset() {
		state.setA(0);
		state.setPC(0);

	}

	@Override
	public int execute() {
		
		int Opcode = state.fetch();
		
		if (Opcode < 0 || Opcode > 4) {
			return 1;
			
		}
		int res = ops.get(Opcode).execute(getState());
		
		
		return res;
	}
	
	public ICPUState getState() {
		return state;
	}

}
