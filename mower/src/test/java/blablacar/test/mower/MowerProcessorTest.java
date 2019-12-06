package blablacar.test.mower;

import blablacar.test.mower.file.watcher.SurfaceLine;
import blablacar.test.mower.file.watcher.MowerLine;
import blablacar.test.mower.file.watcher.MowerProcessor;
import blablacar.test.mower.model.Instruction;
import blablacar.test.mower.model.Orientation;
import blablacar.test.mower.service.MovementsExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by fatma on 03/12/2019.
 */
@ContextConfiguration(locations = {"/file-poller-mower.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MowerProcessorTest {

    @Autowired
    private MowerProcessor processor;

    @Autowired
    private MovementsExecutor movementsExecutor;

    @Test(expected = IllegalStateException.class)
    public void testProcessorWithoutHeader() {
        processor.process(new MowerLine());
    }

    @Test
    public void testProcessor() {
        SurfaceLine header = new SurfaceLine(2, 2);
        MowerLine line = new MowerLine();
        line.setOrientation(Orientation.N);
        line.setMowerX(1);
        line.setMowerY(1);
        line.setInstructions(Arrays.asList(new Instruction[]{Instruction.F}));
        processor.processWithHeader(header, line);
    }

}
