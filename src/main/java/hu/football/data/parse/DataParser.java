package hu.football.data.parse;

import hu.football.model.Match;
import hu.football.model.TeamResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class DataParser {

    private static final String SEPARATOR = " ";

    public List<Match> parse(List<String> lines) {
        trim(lines);
        return lines.stream()
                .map(this::createMatch)
                .collect(Collectors.toList());
    }

    private void trim(List<String> lines) {
        lines.remove(0);
    }

    private Match createMatch(final String line) {
        String[] items = line.split(SEPARATOR);
        int round = parseInt(items[0]);
        TeamResult home = createTeamResult(Arrays.asList(items[1], items[3], items[5]));
        TeamResult guest = createTeamResult(Arrays.asList(items[2], items[4], items[6]));
        return new Match(round, home, guest);
    }

    private TeamResult createTeamResult(List<String> items) {
        int finalResult = parseInt(items.get(0));
        int halftimeResult = parseInt(items.get(1));
        String name = items.get(2);
        return new TeamResult(name, halftimeResult, finalResult);
    }

    private int parseInt(final String item) {
        return Integer.parseInt(item);
    }
}
