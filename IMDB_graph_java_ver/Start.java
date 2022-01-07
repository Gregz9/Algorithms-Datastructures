import java.util.*;

import java.io.*;

public class Start {
    HashMap<String, Skuespiller> skuespillers = new HashMap<>();
    ArrayList<Skuespiller> skuespillersAlist = new ArrayList<>();
    HashMap<String, Film> filmMap = new HashMap<>();
    ArrayList<Film> filmArray = new ArrayList<>();
    ArrayList<Kant> edges = new ArrayList<>();
    ArrayList<Skuespiller> kopi;

    public void generateMoviesNActors() throws FileNotFoundException {
        File file = new File("movies.tsv");
        Scanner s = new Scanner(file, "utf-8");

        // -------------------------------------------------------------------//
        while (s.hasNextLine()) {
            // endret første linje på movies så den blir lik i split for alle filmene
            String biter[] = s.nextLine().split("	");
            String id = biter[0];
            String t = biter[1];
            double r = Double.parseDouble(biter[2]);
            Film f = new Film(id, t, r);
            // Legger til filmobjektene og ttidene i hver sin hashset

            filmMap.put(id, f);
            filmArray.add(f);
        }

        // -------------------------------------------------------------------//
        // Genererer skuespillere og kobler til film-objekter
        File file2 = new File("actors.tsv");
        Scanner s2 = new Scanner(file2, "utf-8");

        while (s2.hasNextLine()) {
            String biter[] = s2.nextLine().split("	");
            String id = biter[0];
            String n = biter[1];
            Skuespiller skuespiller = new Skuespiller(id, n);
            skuespillers.put(id, skuespiller);
            skuespillersAlist.add(skuespiller);

            for (int i = 2; i < biter.length; i++) {
                // Sjekke først at filmen er i movies.tsv.
                String ttid = biter[i];
                if (filmMap.containsKey(ttid)) {
                    Film f = filmMap.get(ttid);
                    skuespiller.addMovie(f);
                    f.addActor(skuespiller);
                }
            }
        }
        s.close();
        s2.close();
        kopi = new ArrayList<>(skuespillersAlist);
        System.out.println("Oppgave 1 ");
        System.out.println(" ");
        System.out.println("Noder: " + skuespillers.size());

    }

    public void generateEdges() {
        for (Film f : filmArray) {
            if (f.getActors().size() > 1) {
                int index = 1;
                for (int i = 0; i < f.getActors().size(); i++) {
                    for (int j = index; j < f.getActors().size(); j++) {
                        Kant k = new Kant(f, f.getActors().get(i), f.getActors().get(j));
                        f.getActors().get(i).addEdge(k);
                        f.getActors().get(j).addEdge(k);
                        edges.add(k);
                    }
                    index++;
                }
            }
        }

        System.out.println("Kanter: " + edges.size());
        System.out.println(" ");
    }

    // BFS fra startnode s
    public HashMap<Skuespiller, Skuespiller> shortestPath(Skuespiller s) {
        // Hashmap : s, parent
        HashMap<Skuespiller, Skuespiller> parents = new HashMap<>();
        parents.put(s, null);
        ArrayList<Skuespiller> dQ = new ArrayList<>();
        ArrayList<Skuespiller> result = new ArrayList<>();
        dQ.add(s);

        while (!dQ.isEmpty()) {
            Skuespiller v = dQ.remove(0);
            result.add(v);
            for (Kant k : v.getKanter()) {
                if (k.s1.equals(v)) {
                    if (!parents.containsKey(k.s2)) {
                        parents.put(k.s2, v);
                        dQ.add(k.s2);
                    }
                } else if (k.s2.equals(v)) {
                    if (!parents.containsKey(k.s1)) {
                        parents.put(k.s1, v);
                        dQ.add(k.s1);
                    }
                }
            }
        }
        return parents;

    }

    // Lager en path fra n1 til n2 fra parentslista shortestPath fant
    public ArrayList<Skuespiller> shortestPathBetween(Skuespiller start, Skuespiller slutt) {
        HashMap<Skuespiller, Skuespiller> parents = shortestPath(start);
        ArrayList<Skuespiller> path = new ArrayList<>();

        if (!parents.containsKey(slutt)) {
            return path;
        }

        while (slutt != null) {
            path.add(slutt);
            slutt = parents.get(slutt);
        }
        Collections.reverse(path);
        showPath(path);
        return path;
    }

    // Printer ut pathen vi fant
    public void showPath(ArrayList<Skuespiller> path) {
        System.out.println(path.get(0).navn);
        for (int i = 1; i < path.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                Skuespiller sone = path.get(i - 1);
                Skuespiller stwo = path.get(i);
                if (edges.get(j).s1 == sone && edges.get(j).s2 == stwo
                        || edges.get(j).s2 == sone && edges.get(j).s1 == stwo) {
                    System.out.println(
                            "===[" + edges.get(j).f.tittel + ", (" + edges.get(j).f.rating + ") ] ===>" + stwo.navn);
                    break;
                }
            }
        }
        System.out.println(" ");
    }

    // Tar for seg alle de mulige stiene som er korte, og velger den kanten som er
    // kortest fra f.eks n1 til n2. I og med at det finnes flere kanter til n2 fra
    // n1
    public void showChillestPath(ArrayList<Skuespiller> path) {

        Kant kant = null;
        double rating1 = 0;
        double totalRating = 0;
        Skuespiller stwo = null;
        System.out.println(path.get(0).navn);
        for (int i = 1; i < path.size(); i++) {
            double rate = 10.0;
            for (int j = 0; j < edges.size(); j++) {
                Skuespiller sone = path.get(i - 1);
                stwo = path.get(i);
                if (edges.get(j).s1 == sone && edges.get(j).s2 == stwo
                        || edges.get(j).s2 == sone && edges.get(j).s1 == stwo) {
                    if (edges.get(j).f.rating > rating1) {
                        rating1 = edges.get(j).f.rating;
                        kant = edges.get(j);
                    }
                }
            }
            rating1 = 0;
            System.out.println("===[ " + kant.f.tittel + ", (" + kant.f.rating + ") ] ===> " + stwo.navn);
            rate -= kant.f.rating;
            totalRating += rate;
        }
        System.out.println("Total weight: " + totalRating);
    }

    // Bruker djikstra algoritmen til å finne ut hva den korteste stien er fra
    // startnoden s. Lagde en pqSkuespillerklasse slik at vi kunne lett sammenligne
    // nodene(comparable) og putte dem i en pq.
    public HashMap<Skuespiller, Skuespiller> chillestPath(Skuespiller s) {
        HashMap<Skuespiller, Double> totalCost = new HashMap<>();
        // hashmap<s, parent>
        HashMap<Skuespiller, Skuespiller> parents = new HashMap<>();
        PriorityQueue<pqSkuespiller> pQ = new PriorityQueue<>();
        HashMap<Skuespiller, String> visited = new HashMap<>();
        pqSkuespiller first = new pqSkuespiller(0.0, s);
        totalCost.put(s, 0.0);
        pQ.add(first);

        for (Skuespiller sk : skuespillersAlist) {
            if (!sk.equals(s)) {
                totalCost.put(sk, Double.POSITIVE_INFINITY);
            }
        }

        while (!pQ.isEmpty()) {

            pqSkuespiller newSmallest = pQ.poll();
            Skuespiller skuespillerObject = newSmallest.skuespiller;
            visited.put(skuespillerObject, skuespillerObject.navn);

            for (Kant k : skuespillerObject.getKanter()) {
                Double altPath = 0.0;
                if (k.s1.equals(skuespillerObject)) {

                    if (!visited.containsKey(k.s2)) {
                        altPath = totalCost.get(skuespillerObject) + k.weight;
                        if (altPath < totalCost.get(k.s2)) {
                            totalCost.put(k.s2, altPath);
                            parents.put(k.s2, skuespillerObject);
                            pqSkuespiller pS = new pqSkuespiller(altPath, k.s2);
                            pQ.offer(pS);
                        }
                    }
                } else if (k.s2.equals(skuespillerObject)) {

                    if (!visited.containsKey(k.s1)) {
                        altPath = totalCost.get(skuespillerObject) + k.weight;
                        if (altPath < totalCost.get(k.s1)) {
                            totalCost.put(k.s1, altPath);
                            parents.put(k.s1, skuespillerObject);
                            pqSkuespiller pS = new pqSkuespiller(altPath, k.s1);
                            pQ.offer(pS);
                        }
                    }
                }

            }

        }
        return parents;
    }

    // Lik shortestpathbetween
    public ArrayList<Skuespiller> ChillestPathBetween(Skuespiller start, Skuespiller slutt) {
        HashMap<Skuespiller, Skuespiller> parents = chillestPath(start);
        ArrayList<Skuespiller> path = new ArrayList<>();

        if (!parents.containsKey(slutt)) {
            return path;
        }

        while (slutt != null) {
            path.add(slutt);
            slutt = parents.get(slutt);
        }
        Collections.reverse(path);
        showChillestPath(path);
        return path;
    }

    // Lik som bfs over. Eneste forksjellen ligger i at den fjerner noder besøkt fra
    // en kopi av skuespiller Alist
    public HashMap<Skuespiller, Skuespiller> opg4BFS(Skuespiller s) {
        // Hashmap : s, parent
        HashMap<Skuespiller, Skuespiller> parents = new HashMap<>();
        parents.put(s, null);
        ArrayList<Skuespiller> dQ = new ArrayList<>();
        ArrayList<Skuespiller> result = new ArrayList<>();
        dQ.add(s);

        while (!dQ.isEmpty()) {
            Skuespiller v = dQ.remove(0);
            result.add(v);

            kopi.remove(v);
            for (Kant k : v.getKanter()) {
                if (k.s1.equals(v)) {
                    if (!parents.containsKey(k.s2)) {
                        parents.put(k.s2, v);
                        dQ.add(k.s2);
                    }
                } else if (k.s2.equals(v)) {
                    if (!parents.containsKey(k.s1)) {
                        parents.put(k.s1, v);
                        dQ.add(k.s1);
                    }
                }
            }
        }
        return parents;

    }

    // Kjører bfs på først 1 node. Legger alle dem til i en hashmap. Kjører så på
    // neste node som ikke finnes i hashmapen. Dermed finner vi alle komponentene
    public void opg4() {

        HashMap<Skuespiller, Skuespiller> h1 = opg4BFS(skuespillersAlist.get(0));
        ArrayList<HashMap<Skuespiller, Skuespiller>> A = new ArrayList<>();
        A.add(h1);

        while (!kopi.isEmpty()) {
            HashMap<Skuespiller, Skuespiller> ny = opg4BFS(kopi.get(0));
            A.add(ny);
        }

        HashMap<Integer, Integer> count = new HashMap<>();
        for (int k = 0; k < A.size(); k++) {
            int str = A.get(k).size();
            if (count.containsKey(str)) {
                int counter = count.get(str);
                counter++;
                count.put(str, counter);
            } else {
                count.put(str, 1);
            }
        }
        List<Integer> values = new ArrayList<Integer>(count.keySet());

        Collections.sort(values);
        Collections.reverse(values);

        for (int r = 0; r < values.size(); r++) {
            System.out.println("There are " + count.get(values.get(r)) + " components of size : " + values.get(r));
        }
        kopi = new ArrayList<>(skuespillersAlist);

    }

    // UNDER ER DET METODENE SOM HAR EN ANNEN FREMGANGSMÅTE PÅ OPG4. VI VELGER
    // HELLER Å BRUKE METODENE OVER I VÅR INNLEVERING
    public void BFS_Count() {
        HashMap<Skuespiller, Skuespiller> n1 = new HashMap<>();
        ArrayList<HashMap<Skuespiller, Skuespiller>> komponenter = new ArrayList<>();

        // Kopierer innholdet av skuespillersAList til en HashMap og en ArrayList

        for (int i = 0; i < skuespillersAlist.size(); i++) {
            n1.put(skuespillersAlist.get(i), skuespillersAlist.get(i));
        }
        ArrayList<Skuespiller> nySK = new ArrayList<>(skuespillersAlist);
        HashMap<Skuespiller, Skuespiller> n2 = null;
        while (!nySK.isEmpty()) {
            Skuespiller start = nySK.get(0);
            n2 = count_Comps(start, nySK, n1);
            komponenter.add(n2);

        }
        System.out.println(komponenter.get(0).size());
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int k = 0; k < komponenter.size(); k++) {
            int str = komponenter.get(k).size();
            if (count.containsKey(str)) {
                int counter = count.get(str);
                counter++;
                count.put(str, counter);
            } else {
                count.put(str, 1);
            }
        }
        List<Integer> values = new ArrayList<Integer>(count.keySet());

        Collections.sort(values);
        Collections.reverse(values);

        for (int r = 0; r < values.size(); r++) {
            System.out.println("There are " + count.get(values.get(r)) + " components of size : " + values.get(r));
        }

    }

    // Bruker bredde først søk til å løse problemet med telling av komponenter

    public HashMap<Skuespiller, Skuespiller> count_Comps(Skuespiller start, ArrayList<Skuespiller> n1,
            HashMap<Skuespiller, Skuespiller> n34) {

        HashMap<Skuespiller, Skuespiller> besoekt = new HashMap<>();
        besoekt.put(start, start);
        n1.remove(n1.indexOf(start));
        n34.remove(start);
        ArrayList<Skuespiller> dQ = new ArrayList<>();
        dQ.add(start);

        while (!dQ.isEmpty()) {
            Skuespiller v = dQ.remove(0);
            for (Kant k : v.getKanter()) {
                if (k.s1.equals(v)) {
                    if (!besoekt.containsKey(k.s2)) {
                        if (n34.containsKey(k.s2)) {
                            besoekt.put(k.s2, k.s2);
                            n1.remove(n1.indexOf(k.s2));
                            n34.remove(k.s2);
                            dQ.add(k.s2);
                        } else {
                            break;
                        }
                    }
                } else if (k.s2.equals(v)) {
                    if (!besoekt.containsKey(k.s2)) {

                        if (n34.containsKey(k.s1)) {
                            besoekt.put(k.s1, k.s1);
                            n1.remove(n1.indexOf(k.s1));
                            n34.remove(k.s1);
                            dQ.add(k.s1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return besoekt;
    }

}
