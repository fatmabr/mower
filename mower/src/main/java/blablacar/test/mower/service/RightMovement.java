package blablacar.test.mower.service;

import blablacar.test.mower.model.Orientation;
import blablacar.test.mower.model.Position;

/**
 * Created by fatma on 26/11/2018.
 */
public class RightMovement extends Movement {

    @Override
    protected Position getNewPosition(Position position) {
        Position resultedPosition = new Position();
        switch (position.getOrientation()) {
            case N: {
                resultedPosition.setOrientation(Orientation.E);
                break;
            }
            case E: {
                resultedPosition.setOrientation(Orientation.S);
                break;
            }
            case W: {
                resultedPosition.setOrientation(Orientation.N);
                break;
            }
            case S: {
                resultedPosition.setOrientation(Orientation.W);
                break;
            }
            default:
                throw new IllegalStateException("Illegal orientation value !!! ");
        }
        return resultedPosition;
    }
}
