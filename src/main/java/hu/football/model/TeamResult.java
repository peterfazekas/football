package hu.football.model;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class TeamResult {

    private final String name;
    private final int halftimeResult;
    private final int finalResult;

    public TeamResult(String name, int halftimeResult, int finalResult) {
        this.name = name;
        this.halftimeResult = halftimeResult;
        this.finalResult = finalResult;
    }

    public String getName() {
        return name;
    }

    public int getHalftimeResult() {
        return halftimeResult;
    }

    public int getFinalResult() {
        return finalResult;
    }
}
