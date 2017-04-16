package hu.football.data.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class FileDataLogger implements DataLogger {

    private static final String PATH = "src\\main\\resources\\";

    private final String fileName;

    public FileDataLogger(String fileName) {
        this.fileName = PATH + fileName;
    }

    @Override
    public void println(final String text) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
