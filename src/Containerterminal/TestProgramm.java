package Containerterminal;

import java.util.Scanner;

public class TestProgramm {
    public static void main(String[] args) {
        String[][] stapel = new String[][]{
                {"", "","^","",""},
                {"", "","","",""},
                {"","□","","",""},
                {"□", "□","","□","□"},
                {"□","□","□","□","□"},
        };
        test_menu_befehle(stapel);
    }

    public static void test_menu_befehle(String [][] stacks){
        ContainerTerminal terminal = new ContainerTerminal(stacks);

        Scanner scanner = new Scanner(System.in);
        String kommando = "";

        while (!kommando.equalsIgnoreCase("X")) {
            terminal.printZustand();
            System.out.println("Menü");
            System.out.println("W: Aufnehmen");
            System.out.println("A: Links");
            System.out.println("S: Ablegen");
            System.out.println("D: Rechts");
            System.out.println("X: Beenden");
            System.out.print("Befehl: ");
            kommando = scanner.nextLine();

            switch (kommando.toUpperCase()) {
                case "W":
                    terminal.container_aufnehmen();
                    break;
                case "A":
                    terminal.kran_nach_links();
                    break;
                case "S":
                    terminal.Container_ablegen();
                    break;
                case "D":
                    terminal.kran_nach_rechts();
                    break;
            }
        }

        scanner.close();
    }
}
