package Ruderwettkampf;


public class TestProgramm {
    public static void main(String[] args) {
        
        test_rennen_3_doppelzweier_distanz2000();
        test_rennen_4_Vierer_distanz4000();

    }

    private static void test_rennen_3_doppelzweier_distanz2000() {
        Rennen rennen = new Rennen(3, "Doppelzweier", 2000);
        try {
            rennen.start_race();
        } catch (InterruptedException e) {
            System.out.println("Das Rennen wurde unterbrochen.");
        }
    }

    private static void test_rennen_4_Vierer_distanz4000() {
        Rennen rennen = new Rennen(4, "Vierer", 4000);
        try {
            rennen.start_race();
        } catch (InterruptedException e) {
            System.out.println("Das Rennen wurde unterbrochen.");
        }
    }
}
