package blablacar.test.mower.model;

import blablacar.test.mower.service.LeftMovement;
import blablacar.test.mower.service.ForwardMovement;
import blablacar.test.mower.service.Movement;
import blablacar.test.mower.service.RightMovement;

/**
 * Created by fatma on 26/11/2018.
 */
public enum Instruction {

    /**
     * FORWARD movement
     */
    F {
        @Override
        public Movement create() {
            return new ForwardMovement();
        }
    },
    /**
     * RIGHT Movement
     **/
    R {
        @Override
        public Movement create() {
            return new RightMovement();
        }
    },
    /**
     * Left movement
     */
    L {
        @Override
        public Movement create() {
            return new LeftMovement();
        }
    };

    public abstract Movement create();
}
