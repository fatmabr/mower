package file.watcher.processor;

import file.watcher.line.Header;
import file.watcher.line.Line;
import file.watcher.parser.FileParser;

/**
 * Created by bradai on 03/12/2019.
 */

/**
 * Parent class to every file processor.
 */
public abstract class AbstractFileProcessor {
    /**
     * the file name pattern should be handled by the processor.
     */
    private String filePattern;
    /**
     * the corresponding file parser.
     */
    private FileParser fileParser;

    public abstract void process(Line line);

    public abstract void processWithHeader(Header header, Line line);

    public abstract boolean validate(Line line);

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }


}
