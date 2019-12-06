package blablacar.test.mower.file.watcher;

import file.watcher.line.Line;
import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.model.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatma on 28/11/2018.
 */
public class MowerLine implements Line{

    private List<Instruction> instructions = new ArrayList<>();

    private int mowerX;
    private int mowerY;
    private Orientation orientation;

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public int getMowerX() {
        return mowerX;
    }

    public void setMowerX(int mowerX) {
        this.mowerX = mowerX;
    }

    public int getMowerY() {
        return mowerY;
    }

    public void setMowerY(int mowerY) {
        this.mowerY = mowerY;
    }
}
