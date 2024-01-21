package Containerterminal;

import java.util.Scanner;
/*
Ich habe versucht die Welt
1. Kran bewegung: Kran immer in stapel[0][j], rechts ist j++ und links ist j--
2. Kran muss container suchen: in der gleichen spalte in der er steht, iterieren bis ich den ersten container finde.
3. Kran nimmt container auf: Container vom original position löschen, dann container stapel[i++][j]
4. Kran bewegtb sich nach rechts stapel[0][j++], gleichzeitig container auch nach rechts stapel[i][j++], beide vom original position löschen
5. Kran legt container: container von original position löschen. in neuen position einfügen.
6. Kran muss leeren platz suchen: in der gleichen spalte in der er steht, iterieren bis ich den letzten leeren Feld finde. (Benutze hier die gleiche methode checkContainer und nimm einfach die position drüber)
*/
public class TestProgramm {
    public static void main(String[] args) {

        String[][] stapel = new String[][]{
                {"", "","^","",""}, //Kran ist immer im stapel[i++][j]
                {"", "","","",""},
                {"","□","","",""}, //stapel[2][1]
                {"□", "□","","□","□"},
                {"□","□","□","□","□"}
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
