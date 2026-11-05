package pobj.tme6.cpu.op;

import pobj.tme6.cpu.ICPUState;

public class OpStore implements IOpCode<ICPUState> {

    public OpStore() {
        // Constructeur public sans argument requis par l'énoncé
    }

    @Override
    public int execute(ICPUState state) {
        // 1. Lit l'adresse cible en mémoire et incrémente PC
        int targetAddress = state.fetch();
        
        // 2. Lit la valeur actuelle de l'accumulateur A
        int valueToStore = state.getA();
        
        // 3. Stocke cette valeur à l'adresse indiquée dans la mémoire globale
        state.getMemory().write(targetAddress, valueToStore);
        
        // 4. Retourne la durée de l'instruction (2 unités de temps)
        return 2;
    }
}
