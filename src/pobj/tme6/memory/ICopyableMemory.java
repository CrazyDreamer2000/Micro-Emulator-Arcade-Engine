package pobj.tme6.memory;

public interface ICopyableMemory extends IMemory {
	// duplique la mémoire
	public ICopyableMemory copy();
}