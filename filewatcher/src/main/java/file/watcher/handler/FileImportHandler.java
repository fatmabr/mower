package file.watcher.handler;

import au.com.bytecode.opencsv.CSVReader;
import file.watcher.line.Header;
import file.watcher.line.Line;
import file.watcher.parser.FileParser;
import file.watcher.processor.AbstractFileProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bradai on 03/12/2019.
 */

/**
 * FileImportHandler handles the injected files in the watched dir.
 */
@Component
public class FileImportHandler implements InitializingBean {

    Logger logger = Logger.getLogger(getClass().getName());
    @Autowired
    private ProcessorRegistry registry;

    @Value("${watched.file}")
    private String watchedDir;

    public void handle(File file) {
        final AbstractFileProcessor fileProcessor = registry.matchProcessor(file.getName());
        if (fileProcessor != null) {
            FileParser fileParser = fileProcessor.getFileParser();
            CSVReader csvReader = fileParser.parse(file);
            Line line;
            Header header = null;
            if (fileParser.getHeaderLinesCount() != 0) {
                header = fileParser.readFileHeader(csvReader);
            }
            while ((line = fileParser.readNextItem(csvReader)) != null) {
                if (fileParser.getHeaderLinesCount() != 0) {
                    fileProcessor.processWithHeader(header, line);
                } else {
                    fileProcessor.process(line);
                }
            }
        }
    }

    public void afterPropertiesSet(){
        Path dir = Paths.get(watchedDir);
        WatchService watcher = null;
        try {
            watcher = FileSystems.getDefault().newWatchService();

        dir.register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Watched dir not configured");
            throw new IllegalStateException("A problem with watched dir configuration : hint : verify the value of the " +
                    "${watched.file} in the file config.properties and assert that it exists in you file system ");
        }
        final FileImportListener importListener = new FileImportListener(dir, watcher, this);
        final Thread thread = new Thread(importListener);
        logger.log(Level.INFO, () -> String.format("WATCHED DIR LOG: %s", watchedDir));
        logger.log(Level.INFO, () -> String.format("WATCHED DIR LOG PRINT: %s", watchedDir));
        thread.start();
    }
}
