package blablacar.test.mower.file.watcher;

import blablacar.test.mower.model.Instruction;
import file.watcher.line.Line;
import file.watcher.parser.CSVFileParser;
import blablacar.test.mower.model.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatma on 03/12/2019.
 */

/**
 * This is a class that permits to parse a line in the given file
 * It permits to transform the file line to a useful object
 * Surface or Mower
 */
public class MowerLineParser extends CSVFileParser {
    /**
     * first line in the file represents the "mower" max X size and max Y size.
     * It s represented by just one line in the file.
     */
    public static final int HEADER_LINES_COUNT = 1;
    /**
     * Every "Mower" is given in two consecutive lines of the file.
     * First line is the initial position
     * Second line is the instructions to be fulfilled by the "Mower".
     */
    public static final int LINE_PER_ITEM = 2;

    public static final Character LINE_ITEM_SEPARATOR = ' ';

    /**
     * This function creates an item (Surface or Mower) according to the size of the line list.
     * If the line list corresponds to {@link #headerLinesCount} so it return a Surface Line
     * Otherwise if the line list size corresponds to {@link #linePerItem} so it returns a Mower Line
     * @param lines
     * @return
     */
    public Line newItem(List<String[]> lines) {
        if (lines.size() == HEADER_LINES_COUNT) {
            int mowerXSise = Integer.parseInt(lines.get(0)[0]);
            int mowerYSise = Integer.parseInt(lines.get(0)[1]);
            //new Surface
            return new SurfaceLine(mowerXSise, mowerYSise);
        } else if (lines.size() == LINE_PER_ITEM) {
            MowerLine mower = new MowerLine();
            int mowerInitialX = Integer.parseInt(lines.get(0)[0]);
            mower.setMowerX(mowerInitialX);
            int mowerInitialY = Integer.parseInt(lines.get(0)[1]);
            mower.setMowerY(mowerInitialY);
            mower.setOrientation(Orientation.valueOf(lines.get(0)[2]));
            List<Instruction> instructions = getInstructions(lines);
            mower.setInstructions(instructions);
            return mower;
        }
        throw new IllegalStateException("Problem in the file Format!");
    }

    /**
     * parses the instruction line and transform it from String to #Instruction enum
     * @param lines
     * @return
     */
    private List<Instruction> getInstructions(List<String[]> lines) {
        String[] strings = lines.get(1);
        char[] chars = strings[0].toCharArray();
        List<Instruction> instructions = new ArrayList<>();
        for (Character instructionV : chars) {
            Instruction instruction = Instruction.valueOf(instructionV.toString());
            if (instruction != null) {
                instructions.add(instruction);
            }
        }
        return instructions;
    }

    public int getHeaderLinesCount() {
        return HEADER_LINES_COUNT;
    }

    public int getLinePerItem() {
        return LINE_PER_ITEM;
    }

    @Override
    public Character getLineItemSeparator() {
        return LINE_ITEM_SEPARATOR;
    }
}
