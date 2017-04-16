package hu.football;

import hu.football.data.log.DataLogger;
import hu.football.data.log.FileDataLogger;
import hu.football.data.service.Data;
import hu.football.service.Console;
import hu.football.service.Football;

/**
 * @author Peter_Fazekas on 2017.04.16..
 */
class App {

    private static final String INPUT = "meccs.txt";
    private static final String OUTPUT = "stat.txt";

    private final Football football;
    private final Console console;
    private final DataLogger log;

    private App() {
        console = new Console();
        Data data = new Data(INPUT);
        football = data.createFootballInstance();
        log = new FileDataLogger(OUTPUT);
    }

    public static void main(String[] args) {
        App app = new App();
        app.println();
    }

    private void println() {
        System.out.print("2. feladat: Kérem adja meg egy forduló számát [1-20]: ");
        System.out.println(football.getRoundDetails(console.readInt()));
        System.out.println("3. feladat: A következő csapatok tudtak fordítani a mérkőzésen: " + football.invertResult());
        System.out.print("4. feladat: Kérem adjon meg egy csapatnevet: ");
        String team = console.read();
        System.out.println("5. feladat: Gól statisztika: " + football.getGoalStatistic(team));
        System.out.println("6. feladat: " + football.getFirstTimeLose(team));
        log.println(football.getFinalResultStatistic());
    }
}
