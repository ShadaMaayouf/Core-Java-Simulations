package Ruderwettkampf2;
import java.util.Random;

public class Rennen2 {
    private Boot[] boote;
    private int distanz;

    public Rennen2(int anzahlBoote, String bootTyp, int distanz) {
        this.distanz = distanz;
        boote = new Boot[anzahlBoote];
        for (int i = 0; i < anzahlBoote; i++) {
            switch (bootTyp) {
                case "Doppelzweier":
                    boote[i] = new Doppelzweier();
                    break;
                case "Vierer":
                    boote[i] = new Vierer();
                    break;
                default:
                    System.out.println("Boot muss entweder ein Doppelzweier oder ein Vierer sein.");
                    return;
            }
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

class Ruderer {
    private Random rand = new Random();

    public int leistung() {
        return 400 + rand.nextInt(101);
    }
}

class Boot {
    protected Ruderer[] ruderer;

    public Boot() {
    }

    public int gesamtleistung() {
        int summe = 0;
        for (Ruderer r : ruderer) {
            summe += r.leistung();
        }
        return summe;
    }
}

class Doppelzweier extends Boot {
    public Doppelzweier() {
        this.ruderer = new Ruderer[2];
        for (int i = 0; i < 2; i++) {
            this.ruderer[i] = new Ruderer();
        }
    }
}

class Vierer extends Boot {
    public Vierer() {
        this.ruderer = new Ruderer[4];
        for (int i = 0; i < 4; i++) {
            this.ruderer[i] = new Ruderer();
        }
    }
}


