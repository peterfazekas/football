package hu.football.data.service;

import hu.football.data.parse.DataParser;
import hu.football.data.read.DataReader;
import hu.football.data.read.FileDataReader;
import hu.football.model.Match;
import hu.football.service.Football;

import java.util.List;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class Data {

    private final String fileName;

    public Data(final String fileName) {
        this.fileName = fileName;
    }

    private List<Match> getData() {
        DataReader file = new FileDataReader(fileName);
        DataParser data = new DataParser();
        return data.parse(file.read());
    }

    public Football createFootballInstance() {
        return new Football(getData());
    }
}
