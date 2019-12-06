package blablacar.test.mower.service;

import blablacar.test.mower.model.Position;
import blablacar.test.mower.model.Orientation;

/**
 * Created by fatma on 26/11/2018.
 */
public class LeftMovement extends Movement {
    @Override
    protected Position getNewPosition(Position position) {
        Position resultedPosition = new Position();
        resultedPosition.setY(position.getY());
        resultedPosition.setX(position.getX());
        switch (position.getOrientation()){
            case N: {
                resultedPosition.setOrientation(Orientation.W);
                break;
            } case E:{
                resultedPosition.setOrientation(Orientation.N);
                break;
            } case W: {
                resultedPosition.setOrientation(Orientation.S);
                break;
            } case S:{
                resultedPosition.setOrientation(Orientation.E);
                break;
            }
            default: throw new IllegalStateException("Illegal orientation value !!! ");
        }
        return resultedPosition;
    }
}
