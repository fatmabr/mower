package blablacar.test.mower.factory;

import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.service.Movement;

/**
 * Created by fatma on 26/11/2018.
 */
public class MovementFactory {

    private MovementFactory() {
    }

    public static Movement create(Instruction instruction){
       return instruction.create();
    }
}
