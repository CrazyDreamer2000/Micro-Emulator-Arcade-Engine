package pobj.tme6.memory;

// Interface d'une mémoire
public interface IMemory {

	// Retourne la taille de la mémoire
	public int size(); 
	
	// Lit la valeur à l'adresse addr (entre 0 et size()-1)
	public int read(int addr); 

	// Écrit la valeur val à l'adresse addr (entre 0 et size()-1)
	public void write (int addr, int value); 
}
