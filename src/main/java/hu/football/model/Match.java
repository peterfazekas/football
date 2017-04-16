package hu.football.model;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
public class Match {

    private static final String MASK = "   %-20s - %-20s: %d-%d (%d-%d)%n";
    private final int round;
    private final TeamResult home;
    private final TeamResult guest;

    public Match(int round, TeamResult home, TeamResult guest) {
        this.round = round;
        this.home = home;
        this.guest = guest;
    }

    public int getRound() {
        return round;
    }

    public TeamResult getHome() {
        return home;
    }

    public TeamResult getGuest() {
        return guest;
    }

    @Override
    public String toString() {
        return String.format(MASK, home.getName(), guest.getName(), home.getFinalResult(),
                guest.getFinalResult(), home.getHalftimeResult(), guest.getHalftimeResult());
    }
}
