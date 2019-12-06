package blablacar.test.mower.file.watcher;

import file.watcher.line.Header;

/**
 * Created by fatma on 28/11/2018.
 */
public class SurfaceLine implements Header{

    public SurfaceLine(int mowerXSise, int mowerYSise) {
        this.mowerXSise = mowerXSise;
        this.mowerYSise = mowerYSise;
    }

    private int mowerXSise;
    private int mowerYSise;

    public int getSurfaceXSise() {
        return mowerXSise;
    }

    public void setMowerXSise(int mowerXSise) {
        this.mowerXSise = mowerXSise;
    }

    public int getSurfaceYSise() {
        return mowerYSise;
    }

    public void setMowerYSise(int mowerYSise) {
        this.mowerYSise = mowerYSise;
    }
}
