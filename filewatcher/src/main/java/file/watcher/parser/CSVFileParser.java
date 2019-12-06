package file.watcher.parser;

import au.com.bytecode.opencsv.CSVReader;
import file.watcher.line.Header;
import file.watcher.line.Line;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bradai on 03/12/2019.
 */

/**
 * This is an example of csv file parser.
 * It uses the openCSV api.
 * Reads the file line by line.
 *
 */
@Component
@Scope("prototype")
public abstract class CSVFileParser implements FileParser {

    protected Logger logger = Logger.getLogger(CSVFileParser.class.getSimpleName());

    protected static final Character LINE_ITEM_SEPARATOR = ';';

    public CSVReader parse(File f) {
        try {
            return new CSVReader(new InputStreamReader(new FileInputStream(f), "UTF-8"), getLineItemSeparator());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "In out execption while file " + f.getName() + " parsing : " + e.getMessage());
            return null;
        }
    }

    public Line nextLines(CSVReader csvReader, int lineCount) {
        List lines = new ArrayList();
        int itemToRead = lineCount;
        for (int i = 0; i < itemToRead; i++) {
            String[] strings = null;
            try {
                strings = (csvReader != null) ? csvReader.readNext() : null;
                if (strings == null && i == 0) {
                    //file ends and no more mowers.
                    break;
                } else if (strings == null && i > 0 && i < itemToRead - 1) {
                    throw new IllegalStateException("There is a problem with the file format. Expected "
                            + itemToRead + " but did found only " + (i - 1));
                } else {
                    lines.add(strings);
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "In out exception while file parsing : " + e.getMessage());
            }
        }
        return lines.size() != 0 ? newItem(lines) : null;
    }

    @Override
    public Header readFileHeader(CSVReader csvReader) {
        if (getHeaderLinesCount() != 0) {
            return (Header) nextLines(csvReader, getHeaderLinesCount());
        }
        return null;
    }

    @Override
    public Line readNextItem(CSVReader csvReader) {
        return nextLines(csvReader, getLinePerItem());
    }

    public Character getLineItemSeparator() {
        return LINE_ITEM_SEPARATOR;
    }
}
