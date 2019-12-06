package blablacar.test.mower.file.watcher;

import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.model.Mower;
import blablacar.test.mower.model.Position;
import blablacar.test.mower.model.Surface;
import file.watcher.line.Header;
import file.watcher.line.Line;
import file.watcher.processor.AbstractFileProcessor;
import blablacar.test.mower.service.MovementsExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by fatma on 03/12/2019.
 */

public class MowerProcessor extends AbstractFileProcessor {

    Logger log = Logger.getLogger(MowerProcessor.class.getName());

    @Autowired
    private MovementsExecutor movementsExecutor;

    @Override
    public void process(Line line) {
        throw new IllegalStateException("The mower size must be the first line of the file.");
    }

    @Override
    public void processWithHeader(Header header, Line line) {
        if (header != null) {
            Surface surface = createSurface((SurfaceLine) header);
            Mower mower = createMower((MowerLine) line);
            movementsExecutor.execute(surface, mower);
            log.info(mower.getPosition().toString());
        } else {
            throw new IllegalStateException("The mower size must be the first line of the file.");
        }
    }

    private Mower createMower(MowerLine line) {
        List<Instruction> instructions = line.getInstructions();
        Instruction[] instructionsArray = instructions.toArray(new Instruction[instructions.size()]);
        return new Mower(new Position(line.getMowerX(), line.getMowerY(), line.getOrientation()), instructionsArray);
    }

    private Surface createSurface(SurfaceLine surfaceLine) {
        return new Surface(surfaceLine.getSurfaceXSise(), surfaceLine.getSurfaceYSise());
    }

    @Override
    public boolean validate(Line line) {
        return true;
    }
}
