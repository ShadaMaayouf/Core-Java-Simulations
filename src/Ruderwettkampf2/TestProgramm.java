package Ruderwettkampf2;


public class TestProgramm {
    public static void main(String[] args) throws InterruptedException {
        
        test_rennen_3_doppelzweier_distanz1000();
        //test_rennen_4_Vierer_distanz15000();

    }

    private static void test_rennen_3_doppelzweier_distanz1000() {
        Rennen2 rennen1 = new Rennen2(3, "Doppelzweier", 500);
        try {
            rennen1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test_rennen_4_Vierer_distanz15000() {
        Rennen2 rennen2 = new Rennen2(4, "Vierer", 15000);
        try {
            rennen2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
