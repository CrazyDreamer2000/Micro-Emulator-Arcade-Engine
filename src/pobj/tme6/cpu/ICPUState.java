package pobj.tme6.cpu;

import pobj.tme6.memory.IMemory;

public interface ICPUState {
	// getters et setters
	public int getPC();
	public void setPC(int pc);
	public int getA();
	public void setA(int a);
	public IMemory getMemory();
	
	// Retourne le mot à l'adresse PC et incrémente PC
	public int fetch(); 
}
