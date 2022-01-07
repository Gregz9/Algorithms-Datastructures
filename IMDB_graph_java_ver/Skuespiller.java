
import java.util.ArrayList;

public class Skuespiller {
    private ArrayList<Film> filmer = new ArrayList<>();
    private ArrayList<Kant> tilknyttedeKanter = new ArrayList<>();
    String nmId;
    String navn;

    Skuespiller(String id, String n) {
        nmId = id;
        navn = n;

    }

    public void addMovie(Film f) {
        filmer.add(f);
    }

    public void addEdge(Kant k) {
        tilknyttedeKanter.add(k);
    }

    public ArrayList<Film> getMovies() {
        return filmer;
    }

    public ArrayList<Kant> getKanter() {
        return tilknyttedeKanter;
    }

}
