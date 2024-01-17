package Aquarium;

public class TestProgramm {
    public static void main(String[] args) throws InterruptedException{

        System.out.println("Test 1: Das Aquarium mit normalen Fischen");
        test_aquarium_mit_normalen_fischen();

        System.out.println("Test 2: Das Aquarium mit normalen Fischen, Haien, Kugel- und Schwertfischen");
        test_aquarium_mit_Hai_Kugel_und_Schwertfischen();

    }


    private static void test_aquarium_mit_normalen_fischen() throws InterruptedException {

        Aquarium aquarium = new Aquarium(10, 7, 30);

        Fisch fisch1 = new Fisch(0, "><>");
        Fisch fisch2 = new Fisch(1, "<><");
        Fisch fisch3 = new Fisch(2, "><>");

        aquarium.addFisch(fisch1);
        aquarium.addFisch(fisch2);
        aquarium.addFisch(fisch3);

        aquarium.printZustand();
        System.out.println();

        for (int i = 0; i < 10; i++) {
            aquarium.nextZustand();
            aquarium.printZustand();
            System.out.println();
            Thread.sleep(1000); // Pause für 1 Sekunde
        }
    }

    private static void test_aquarium_mit_Hai_Kugel_und_Schwertfischen() throws InterruptedException {

        //Aquarium erstellen
        Aquarium aquarium = new Aquarium(10, 7, 30);

        Fisch fisch1 = new Fisch(0, "><>");
        Fisch fisch2 = new Fisch(1, "<><");
        Fisch fisch3 = new Fisch(2, "><>");
        Fisch fisch4 = new Hai(0, "<///====><");
        Fisch fisch5 = new Kugelfisch(2, "(<()><");
        Fisch fisch6 = new Schwertfisch(3, "-<><");

        aquarium.addFisch(fisch1);
        aquarium.addFisch(fisch2);
        aquarium.addFisch(fisch3);
        aquarium.addFisch(fisch4);
        aquarium.addFisch(fisch5);
        aquarium.addFisch(fisch6);

        aquarium.printZustand();
        System.out.println();

        for (int i = 0; i < 10; i++) {
            aquarium.nextZustand();
            aquarium.printZustand();
            System.out.println();
            Thread.sleep(1000); // Pause für 1 Sekunde
        }
    }



}