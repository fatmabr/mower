package file.watcher.handler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by bradai on 03/12/2019.
 */
/**
 * Listener to the configured watched dir.
 */
public class FileImportListener implements Runnable {

    Logger logger = Logger.getLogger(FileImportListener.class.getName());
    private WatchService watcher;
    private FileImportHandler handler;
    private Path dir;

    public FileImportListener(Path dir, WatchService watcher, FileImportHandler handler) {
        this.watcher = watcher;
        this.handler = handler;
        this.dir = dir;
    }

    public void run() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                logger.log(Level.INFO, () -> String.format("%s: %s", kind.name(), fileName.toString()));
                if (kind == ENTRY_CREATE) {
                    logger.info("File imported !!!");
                    handler.handle(new File(String.format("%s\\%s", dir.toString(), fileName.toString())));
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
