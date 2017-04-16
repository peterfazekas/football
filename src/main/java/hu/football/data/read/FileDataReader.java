package hu.football.data.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class FileDataReader implements DataReader {

    private static final String PATH = "src\\main\\resources\\";

    private final String fileName;

    public FileDataReader(final String fileName) {
        this.fileName = PATH + fileName;
    }

    public List<String> read() {
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            return read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
