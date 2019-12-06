package blablacar.test.mower.model;

/**
 * Created by fatma on 26/11/2018.
 */

/**
 * It represent the surface when the mower executes
 */
public class Surface {
    /**
     * Position is an X Y max value.
      */
    private Position size;

    public Surface(int sizeX, int sizeY) {
        this.size = new Position(sizeX, sizeY, null);
    }

    public Position getSize() {
        return size;
    }

    public void setSize(Position size) {
        this.size = size;
    }
}
