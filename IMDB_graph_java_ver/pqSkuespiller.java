public class pqSkuespiller implements Comparable<pqSkuespiller> {

    double weight = 0;
    Skuespiller skuespiller;

    pqSkuespiller(double weight, Skuespiller skuespiller) {
        this.weight = weight;
        this.skuespiller = skuespiller;
    }

    public int compareTo(pqSkuespiller s) {
        if (weight > s.weight) {
            return 1;
        } else if (weight < s.weight) {
            return -1;
        }
        return 0;
    }
}
