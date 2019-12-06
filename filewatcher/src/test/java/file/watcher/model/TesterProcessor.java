package file.watcher.model;

import file.watcher.line.Header;
import file.watcher.line.Line;
import file.watcher.processor.AbstractFileProcessor;

/**
 * Created by fatma on 03/12/2019.
 */
public class TesterProcessor extends AbstractFileProcessor {
    @Override
    public void process(Line line) {

    }

    @Override
    public void processWithHeader(Header header, Line line) {

    }

    @Override
    public boolean validate(Line line) {
        return false;
    }
}
