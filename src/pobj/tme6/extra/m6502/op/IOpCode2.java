package pobj.tme6.extra.m6502.op;

import pobj.tme6.cpu.op.IOpCode;

// Une catégorie d'instructions pour notre CPU
public interface IOpCode2<T> extends IOpCode<T> {
	public String disassemble(T cpu);
}
