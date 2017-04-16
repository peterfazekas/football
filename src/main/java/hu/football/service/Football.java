package hu.football.service;

import hu.football.model.Match;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class Football {

    private final List<Match> matchList;

    public Football(final List<Match> matchList) {
        this.matchList = matchList;
    }

    /**
     * 2. feladat: Kérje be a felhasználótól egy forduló számát, majd írja a képernyőre a bekért forduló
     * mérkőzéseinek adatait a következő formában: Edes-Savanyu: 2-0 (1-0)!
     * Soronként egy mérkőzést tüntessen fel! A különböző sorokban a csapatnevek ugyanazon a pozíción kezdődjenek!
     *
     * @param round - a forduló száma
     * @return String - pl.: Edes-Savanyu: 2-0 (1-0)
     */
    public String getRoundDetails(final int round) {
        StringBuilder sb = new StringBuilder();
        matchList.stream()
                .filter(i -> i.getRound() == round)
                .forEach(sb::append);
        return sb.toString();
    }

    /**
     * 3. feladat: Határozza meg, hogy a bajnokság során mely csapatoknak sikerült megfordítaniuk az állást
     * a második félidőben! Ez azt jelenti, hogy a csapat az első félidőben vesztésre állt ugyan, de sikerült
     * a mérkőzést megnyernie. A képernyőn soronként tüntesse fel a forduló sorszámát és a győztes csapat nevét!
     *
     * @return String - A forduló sorszáma és a győztes csapat neve
     */
    public String invertResult() {
        StringBuilder sb = new StringBuilder();
        matchList.stream()
                .filter(this::isInvert)
                .map(this::printResult)
                .forEach(sb::append);
        return sb.toString();
    }

    private boolean isInvert(final Match match) {
        return (match.getHome().getFinalResult() - match.getGuest().getFinalResult()) *
                (match.getHome().getHalftimeResult() - match.getGuest().getHalftimeResult()) < 0;
    }

    private String printResult(final Match match) {
        String winner = match.getHome().getFinalResult() > match.getGuest().getFinalResult()
                ? match.getHome().getName()
                : match.getGuest().getName();
        return String.format("%n   %2d %s", match.getRound(), winner);
    }

    /**
     * 5. feladat: Határozza meg, majd írja ki, hogy az adott csapat összesen hány gólt lőtt és hány gólt kapott!
     *
     * @return String - Például: lőtt: 23 kapott: 12
     */

    public String getGoalStatistic(final String teamName) {
        int scored = goalScoredAsHome(teamName) + goalScoredAsGuest(teamName);
        int received = goalReceivedAsHome(teamName) + goalSReceivedAsGuest(teamName);
        return String.format("lőtt: %d kapott: %d", scored, received);
    }

    private int goalScoredAsHome(final String teamName) {
        return matchList.stream()
                .filter(i -> i.getHome().getName().equals(teamName))
                .mapToInt(i -> i.getHome().getFinalResult())
                .sum();
    }

    private int goalScoredAsGuest(final String teamName) {
        return matchList.stream()
                .filter(i -> i.getGuest().getName().equals(teamName))
                .mapToInt(i -> i.getGuest().getFinalResult())
                .sum();
    }

    private int goalReceivedAsHome(final String teamName) {
        return matchList.stream()
                .filter(i -> i.getHome().getName().equals(teamName))
                .mapToInt(i -> i.getGuest().getFinalResult())
                .sum();
    }

    private int goalSReceivedAsGuest(final String teamName) {
        return matchList.stream()
                .filter(i -> i.getGuest().getName().equals(teamName))
                .mapToInt(i -> i.getHome().getFinalResult())
                .sum();
    }

    /**
     * 6. feladat: Határozza meg, hogy az adott csapat otthon melyik fordulóban kapott ki először és melyik csapattól!
     * Ha egyszer sem kapott ki (ilyen csapat például a Bogarak), akkor „A csapat otthon veretlen maradt.” szöveget
     * írja a képernyőre!
     *
     * @param teamName - a Csapat neve
     * @return String - a megfelelő visszatérési érték
     */
    public String getFirstTimeLose(final String teamName) {
        Optional<Match> match = firstTimeLoseMatch(teamName);
        return match.isPresent()
                ? match.get().getRound() + " " + match.get().getGuest().getName()
                : "A csapat otthon veretlen maradt.";
    }

    private Optional<Match> firstTimeLoseMatch(final String teamName) {
        return matchList.stream()
                .filter(i -> i.getHome().getName().equals(teamName))
                .filter(this::teamLoseAtHome)
                .findFirst();
    }

    private boolean teamLoseAtHome(final Match match) {
        return match.getGuest().getFinalResult() > match.getHome().getFinalResult();
    }

    /**
     * 7. feladat: Készítsen statisztikát, amely megadja, hogy az egyes végeredmények hány alkalommal fordultak elő!
     * Tekintse egyezőnek a fordított eredményeket (például 4-2 és 2-4)! A nagyobb számot mindig előre írja!
     * Az elkészült listát a stat.txt állományban helyezze el!
     *
     * @return String - a kívánt érték
     */
    public String getFinalResultStatistic() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> results = collectFinalResults();
        results.entrySet().stream().map(this::printResultStatistic).forEach(sb::append);
        return sb.toString();
    }

    private String printResultStatistic(Map.Entry<String, Integer> result) {
        return String.format("%s: %d darab%n", result.getKey(), result.getValue());
    }

    private Map<String, Integer> collectFinalResults() {
        Map<String, Integer> results = new TreeMap<>();
        matchList.forEach(i -> {
            String key = absFinalResult(i);
            int value = results.getOrDefault(key, 0);
            results.put(key, value + 1);
        });
        return results;
    }

    private String absFinalResult(final Match match) {
        return match.getHome().getFinalResult() > match.getGuest().getFinalResult()
                ? String.format("%d-%d", match.getHome().getFinalResult(), match.getGuest().getFinalResult())
                : String.format("%d-%d", match.getGuest().getFinalResult(), match.getHome().getFinalResult());
    }
}
