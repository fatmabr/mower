package blablacar.test.mower.service;

import blablacar.test.mower.factory.MovementFactory;
import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.model.Surface;
import blablacar.test.mower.model.Position;
import blablacar.test.mower.model.Mower;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Created by fatma on 26/11/2018.
 */
@Component
public class MovementsExecutor {
    Logger logger = Logger.getLogger(MovementsExecutor.class.getName());
    /**
     * mower execution
     *
     * @param surface
     * @param mower
     */
    public void execute(final Surface surface, final Mower mower) {
        for (final Instruction instruction : mower.getInstructions()) {
           // logger.info(Thread.currentThread().getName()+" "+ mower.getPosition());
            final Movement movement = MovementFactory.create(instruction);
            //logger.info(Thread.currentThread().getName()+" "+ instruction.name());
            final Position position = mower.getPosition();
            movement.move(surface, position);
            //logger.info(Thread.currentThread().getName()+" "+ mower.getPosition()+"\n");
        }
    }
}
