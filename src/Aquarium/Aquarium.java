package Aquarium;
import java.util.Random;


public class Aquarium {
    //instanzvariablen
    public int breite = 0; //Breite des Aquariums
    private int tiefe = 0; //Tiefe des Aquariums
    private Fisch[] fische; //Fische im Aquarium: hier werden Fische zum Aquarium eingefügt.
    private int anzahlFische; //Anzahl Fische im Aquarium: jedes mal ein Fisch zu dem Array fische hinzugefügt wird, wird anzahlFische um 1 erhöht.
    //Konstruktor
    public Aquarium(int breite, int tiefe, int maxFische) {
        this.breite = breite;
        this.tiefe = tiefe;
        this.fische = new Fisch[maxFische]; //Wir legen hier die Größe des Arrays fest. D.h. Das array ist noch leer, ist aber so groß wie das Aquarium Fische enthalten kann.
        this.anzahlFische = 0; //Das array ist noch leer
    }

    // methoden
    public void addFisch(Fisch fisch) {
        /*
        Diese Methode fügt ein neuer Fisch zum Aquarium durch die fische-Array.
        Allerdings müssen wir zuvor sicherstellen, dass dieser Fisch zum Aquarium passt.
         */
        if (fisch.getErreichbareTiefe() > this.tiefe) { // Ist unser Aquarium tief genug für den Fisch?
            System.out.println("Dieser Fisch ist zu tief für dieses Aquarium.");
        } else if (this.anzahlFische == this.fische.length) { // ist unser Aquarium voll?
            System.out.println("Das Aquarium ist voll.");
        } else { //wir dürfen den Fisch hinzufügen!
            fisch.setAquarium(this); // "dieser fisch gehört zu diesem Aquarium"
            fisch.setPosition(this.anzahlFische); // Den horizontalen position des Fishes im Aquarium.
            this.fische[this.anzahlFische] = fisch; //den Fisch zu dem Aquarium hinzufügen mithilfe des Arrays.
            this.anzahlFische++;
        }
    }
    public void printZustand() {
        //wir stellen uns vor der Aquarium ist ein großes, leeres 2D array
        String[][] zustand = new String[this.tiefe][this.breite];

        //Der aquarium ist nur mit Wasser " " befüllt
        for (int i = 0; i < zustand.length; i++) {
            for (int j = 0; j < zustand[i].length; j++) {
                zustand[i][j] = " ";
            }
        }

        //nun geben wir unsere vorhandenen Fische hinzu.
        for (int i = 0; i < this.anzahlFische; i++) {
            Fisch fisch = this.fische[i];
            if (fisch.getPosition() < this.breite) {
                zustand[fisch.getErreichbareTiefe()][fisch.getPosition()] = fisch.getRichtung(); //in dieser Position und Tiefe im Aquarium gibt es Wasser " ". Wir ersetzen das Wasser mit einem Fisch in der richtigen Richtung.
            }
        }
        //wir malen Die Rände des Aquarium (rechts und links).
        for (String[] row : zustand) {
            System.out.println("|" + String.join("", row) + "|");
        }
        //wir malen den unteren Rand des Aquarium.
        System.out.println("+" + "--".repeat(this.breite) + "+");
    }

    public void nextZustand() {
        //wir bringen die Fische zum schwimmen.
        for (int i = 0; i < this.anzahlFische; i++) {
            Fisch fisch = this.fische[i];
            fisch.schwimmen();
        }
    }
}

class Fisch {
    //instanzvariablen
    private int erreichbareTiefe; // Welche Tiefe kann der Fisch erreichen?
    private int position; //position im Aquarium
    private String richtung; //schwimmt es nach rechts oder nach links?
    private Aquarium aquarium; //in welcher Aquarium lebt der Fisch?

    //Konstruktor
    public Fisch(int erreichbareTiefe, String richtung) {
        this.erreichbareTiefe = erreichbareTiefe;
        this.richtung = richtung;
    }

    //Getter
    public int getErreichbareTiefe() {

        return this.erreichbareTiefe;
    }

    public Aquarium getAquarium() {
        return aquarium;
    }

    public int getPosition() {
        return this.position;
    }

    public String getRichtung() {
        return this.richtung;
    }

    //Setter
    public void setErreichbareTiefe(int erreichbareTiefe) {
        this.erreichbareTiefe = erreichbareTiefe;
    }
    public void setAquarium(Aquarium aquarium) {
        this.aquarium = aquarium;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRichtung(String richtung) {
        this.richtung = richtung;
    }

    public void schwimmen() { //der fisch kann hin und her schwimmen basierend auf seiner Position im Aquarium.
        if (this.position == 0) { //Ist er am Anfang des Aquariums gelangt?
            this.richtung = "><>"; //wenn ja, schwimm nach rechts.
        } else if (this.position == this.aquarium.breite - 1) { //Ist er am Ende des Aquariums gelangt?
            this.richtung = "<><"; //wenn ja, schwimm zurück (nach links).
        }
        this.position = (this.richtung.equals("><>")) ? this.position + 1 : this.position - 1; // ist er weder am Anfang noch am Ende des Aquariums, soll er in seiner jetzigen richtung weiterschwimmen.
    }

}

class Hai extends Fisch{ // ein Hai ist eine Art Fisch --> Hai ist Subklasse von Fisch
    private static final Random rand = new Random();

    public Hai(int erreichbareTiefe, String richtung) {
        super(erreichbareTiefe, richtung); // wir leiten die Variablen weiter an den Konstriktors der Subklasse Fisch.
    }

    @Override
    public void schwimmen() {
        //-------------------------Dieser Teil ist identisch zu der Schwimmen-methode in der Superklasse Fisch nur mit dem "Hai-aussehen"
        if (this.getPosition() == 0) {
            this.setRichtung("><====\\\\\\>");
        } else if (this.getPosition() == this.getAquarium().breite - 1) {
            this.setRichtung("<///====><");
        }
        this.setPosition((this.getRichtung().equals("><====\\\\\\>")) ? this.getPosition() + 1 : this.getPosition() - 1);
        //------------------------------------
        ////rand generiert eine Zahl zwischen 0 und 4 (4 ist nicht entahlten.) Nur wenn diese Zahl == 0 ist, wird der Code von if zum Laufen gebracht.
        //D.h. ---> wir haben eine 1/4 Chance, dass der Code im if zum Laufen gebracht wird.
        if (rand.nextInt(4) == 0) {
            this.setErreichbareTiefe(this.getErreichbareTiefe() + rand.nextInt(3) - 1); //Der Hai wechselt seine Schwimmtiefe mit der Wahrscheinlichkeit 1/4 um -1 bzw. +1
        }
    }
}

class Kugelfisch extends Fisch { // ein Kugelfisch ist eine Art Fisch --> Kugelfisch ist Subklasse von Fisch
    private static final Random rand = new Random();

    public Kugelfisch(int erreichbareTiefe, String richtung) {
        super(erreichbareTiefe, richtung);
    }

    @Override
    public void schwimmen() {
        if (this.getPosition() == 0) {
            this.setRichtung("><()>");
        } else if (this.getPosition() == this.getAquarium().breite - 1) {
            this.setRichtung("<()><");
        }
        this.setPosition((this.getRichtung().equals("><()>")) ? this.getPosition() + 1 : this.getPosition() - 1);

        //rand generiert eine Zahl zwischen 0 und 10 (10 ist nicht entahlten.) Nur wenn diese Zahl == 0 ist, wird der Code von if zum Laufen gebracht.
        //D.h. ---> wir haben eine 1/10 Chance, dass der Code im if zum Laufen gebracht wird.
        if (rand.nextInt(10) == 0) {
            this.setErreichbareTiefe(this.getErreichbareTiefe() + rand.nextInt(3) - 1); //Der Kugelfisch wechselt seine Schwimmtiefe mit der Wahrscheinlichkeit 1/10 um -1 bzw. +1
        }
    }
}

class Schwertfisch extends Fisch { // ein Schwertfisch ist eine Art Fisch --> Schwertfisch ist Subklasse von Fisch
    private static final Random rand = new Random();

    public Schwertfisch(int erreichbareTiefe, String richtung) {
        super(erreichbareTiefe, richtung);
    }

    @Override
    public void schwimmen() {
        if (this.getPosition() == 0) {
            this.setRichtung("><>-");
        } else if (this.getPosition() == this.getAquarium().breite - 1) {
            this.setRichtung("-<><");
        }
        this.setPosition((this.getRichtung().equals("><>-")) ? this.getPosition() + 1 : this.getPosition() - 1);

        //rand generiert eine Zahl zwischen 0 und 5 (5 ist nicht entahlten.) Nur wenn diese Zahl == 0 ist, wird der Code von if zum Laufen gebracht.
        //D.h. ---> wir haben eine 1/5 Chance, dass der Code im if zum Laufen gebracht wird.
        if (rand.nextInt(5) == 0) {
            this.setErreichbareTiefe(this.getErreichbareTiefe() + rand.nextInt(3) - 1); //Der SChwertfisch wechselt seine Schwimmtiefe mit der Wahrscheinlichkeit 1/5 um -1 bzw. +1
        }
    }
}

