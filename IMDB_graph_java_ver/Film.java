
import java.util.ArrayList;

public class Film {
    private ArrayList<Skuespiller> skuespillere = new ArrayList<>();
    // boolean checked = false;
    String ttId;
    String tittel;
    double rating = 0;
    // int AntStemmer = 0; kan ignoreres.

    Film(String id, String t, double r) {
        ttId = id;
        tittel = t;
        rating = r;
    }

    public void addActor(Skuespiller s) {
        skuespillere.add(s);
    }

    public ArrayList<Skuespiller> getActors() {
        return skuespillere;
    }
    /*
     * public void setChecked() { checked = true; }
     */
}
