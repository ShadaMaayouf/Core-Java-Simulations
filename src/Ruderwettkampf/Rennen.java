package Ruderwettkampf;
import java.util.Random;

public class Rennen {
    private Boot[] boote;
    private int distanz;
    private Random rand;


    public Rennen(int anzahlBoote, String bootTyp, int distanz) {
        this.distanz = distanz;
        this.rand = new Random();
        boote = new Boot[anzahlBoote];
        for (int i = 0; i < anzahlBoote; i++) {
            switch (bootTyp) {
                case "Doppelzweier":
                    boote[i] = new Doppelzweier(this.rand);
                    break;
                case "Vierer":
                    boote[i] = new Vierer(this.rand);
                    break;
                default:
                    System.out.println("Boot muss entweder ein Doppelzweier oder ein Vierer sein.");
                    return;
            }
        }
    }

    public void start_race() throws InterruptedException {

        int[] positionen = new int[boote.length];

        while (true) {
            System.out.println(generateTrackLine() + "| Goal");
            for (int i = 0; i < boote.length; i++) {
                //int spaces = Math.min(distanz - positionen[i], distanz);
                for (int j = 0; j < distanz - positionen[i]; j++) {
                    System.out.print(" ");
                }
                System.out.println(boote[i].getAussehen());
            }
            //System.out.println("------------------------------| Goal");
            System.out.println(generateTrackLine() + "| Goal");
            Thread.sleep(2000);

            for (int i = 0; i < boote.length; i++) {
                positionen[i] += boote[i].gesamtleistung();
                if (positionen[i] >= distanz) {
                    System.out.println("Boot " + (i + 1) + " hat das Ziel erreicht!");
                    return;
                }
            }
        }
    }

    private String generateTrackLine() {
        char[] trackLine = new char[distanz];
        for (int i = 0; i < distanz; i++) {
            trackLine[i] = '-';
        }
        return new String(trackLine);
    }

}

class Ruderer {
    //private Random rand = new Random();
    private Random rand;
    public Ruderer(Random rand) {
        this.rand = rand;
    }

    public int leistung() {
        return 400 + rand.nextInt(101);
    }
}

class Boot {
    protected Ruderer[] ruderer;

    public int gesamtleistung() {
        int summe = 0;
        for (Ruderer r : ruderer) {
            summe += r.leistung();
        }
        return summe;
    }
    public String getAussehen() {
        return "<--o-o-o-o-->";
    }
}

class Doppelzweier extends Boot {
    public Doppelzweier(Random rand) {
        this.ruderer = new Ruderer[2];
        for (int i = 0; i < 2; i++) {
            this.ruderer[i] = new Ruderer(rand);
        }
    }

    @Override
    public String getAussehen() {
        return "<--o-o-->";
    }
}

class Vierer extends Boot {
    public Vierer(Random rand) {
        this.ruderer = new Ruderer[4];
        for (int i = 0; i < 4; i++) {
            this.ruderer[i] = new Ruderer(rand);
        }
    }
}


