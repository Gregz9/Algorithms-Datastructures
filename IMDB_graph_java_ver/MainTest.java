import java.io.*;

public class MainTest {
    public static void main(String[] args) throws FileNotFoundException {
        Start start = new Start();
        start.generateMoviesNActors();
        start.generateEdges();
        Skuespiller s1 = start.skuespillers.get("nm2255973");
        Skuespiller s2 = start.skuespillers.get("nm0000460");
        Skuespiller s3 = start.skuespillers.get("nm0424060");
        Skuespiller s4 = start.skuespillers.get("nm0000243");
        Skuespiller s5 = start.skuespillers.get("nm4689420");
        Skuespiller s6 = start.skuespillers.get("nm0000365");
        Skuespiller s7 = start.skuespillers.get("nm0000288");
        Skuespiller s8 = start.skuespillers.get("nm0001401");
        Skuespiller s9 = start.skuespillers.get("nm0031483");
        Skuespiller s10 = start.skuespillers.get("nm0931324");

        System.out.println("Oppgave 2");
        System.out.println(" ");
        start.shortestPathBetween(s1, s2);
        System.out.println(" ");
        start.shortestPathBetween(s3, s4);
        System.out.println(" ");
        start.shortestPathBetween(s5, s6);
        System.out.println(" ");
        start.shortestPathBetween(s7, s8);
        System.out.println(" ");
        start.shortestPathBetween(s9, s10);
        System.out.println(" ");

        System.out.println("Oppgave 3");
        System.out.println(" ");
        start.ChillestPathBetween(s1, s2);
        System.out.println(" ");
        start.ChillestPathBetween(s3, s4);
        System.out.println(" ");
        start.ChillestPathBetween(s5, s6);
        System.out.println(" ");
        start.ChillestPathBetween(s7, s8);
        System.out.println(" ");
        start.ChillestPathBetween(s9, s10);
        System.out.println(" ");

        System.out.println("Oppgave 4");
        System.out.println(" ");
        start.opg4();
    }
}