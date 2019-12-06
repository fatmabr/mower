package blablacar.test.mower.model;

/**
 * Created by fatma on 26/11/2018.
 */
public enum Orientation {


    N("NORTH"), E("EST"), W("WEST"), S("SOUTH");
    private final String label;

    Orientation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
