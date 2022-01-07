public class Kant {
    double weight = 0;
    Film f;
    Skuespiller s1;
    Skuespiller s2;

    Kant(Film f, Skuespiller s1, Skuespiller s2) {
        this.f = f;
        this.s1 = s1;
        this.s2 = s2;
        this.weight = 10 - f.rating;
    }
}
