package pobj.tme6.cpu;

public interface ICPU {
	// Réinitialise le CPU
	public void reset();	 

	 // Exécute la prochaine instruction et retourne le temps d'exécution
	public int execute();
}
