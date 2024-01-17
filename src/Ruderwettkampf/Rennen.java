package Ruderwettkampf;

import java.util.Random;

class Ruderer {
    private Random rand = new Random();

    public int leistung() {
        return 400 + rand.nextInt(101);
    }
}

class Boot {
    private Ruderer[] ruderer;

    public Boot(int anzahlRuderer) {
        ruderer = new Ruderer[anzahlRuderer];
        for (int i = 0; i < anzahlRuderer; i++) {
            ruderer[i] = new Ruderer();
        }
    }

    public int gesamtleistung() {
        int summe = 0;
        for (Ruderer r : ruderer) {
            summe += r.leistung();
        }
        return summe;
    }
}

public class Rennen {
    private Boot[] boote;
    private int distanz;

    public Rennen(int anzahlBoote, int anzahlRuderer, int distanz) {
        this.distanz = distanz;
        boote = new Boot[anzahlBoote];
        for (int i = 0; i < anzahlBoote; i++) {
            boote[i] = new Boot(anzahlRuderer);
        }
    }

    public void start() throws InterruptedException {
        int[] positionen = new int[boote.length];
        while (true) {
            System.out.println("------------------------------| Goal");
            for (int i = 0; i < boote.length; i++) {
                for (int j = 0; j < distanz - positionen[i]; j++) {
                    System.out.print(" ");
                }
                System.out.println("<--o-o-o-o-->");
            }
            System.out.println("------------------------------| Goal");
            Thread.sleep(1000);

            for (int i = 0; i < boote.length; i++) {
                positionen[i] += boote[i].gesamtleistung();
                if (positionen[i] >= distanz) {
                    System.out.println("Boot " + (i + 1) + " hat das Ziel erreicht!");
                    return;
                }
            }
        }
    }
}


