package pobj.tme6.cpu.op;

// Une catégorie d'instructions pour notre CPU
public interface IOpCode<T> {
	public int execute(T state);
}
