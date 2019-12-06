package blablacar.test.mower;

import blablacar.test.mower.service.LeftMovement;
import blablacar.test.mower.factory.MovementFactory;
import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.service.ForwardMovement;
import blablacar.test.mower.service.Movement;
import blablacar.test.mower.service.RightMovement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by fatma on 03/12/2019.
 */
public class MovementFactoryTest {

    @Test
    public void testRightMovementCreationWhenInstructionD(){
        Movement movement = MovementFactory.create(Instruction.R);
        Assert.assertTrue(movement instanceof RightMovement);
    }
    @Test
    public void testLeftMovementCreationWhenInstructionG(){
        Movement movement = MovementFactory.create(Instruction.L);
        Assert.assertTrue(movement instanceof LeftMovement);
    }
    @Test
    public void testRightMovementCreationWhenInstructionF(){
        Movement movement = MovementFactory.create(Instruction.F);
        Assert.assertTrue(movement instanceof ForwardMovement);
    }
}
