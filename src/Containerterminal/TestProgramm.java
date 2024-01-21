package Containerterminal;

import java.util.Scanner;
/*
Ich habe versucht mir den ganzen Containerterminal als einen 2D array vorzustellen.
Die 0te Zeile des arrays [0][j] ist die Zeile in der sich der Kran befindet und bewegt.
Die 1te Zeile des arrays [1][j] ist die Zeile in der sich der Container befindet, den der Kran aufnimmt.
in den restlichen Zeile des Arrays stapeln sich die Container.


1. Kran nimmt container auf:
       1.1. Kran muss container zum Aufnehmen finden. Wo? in der gleichen spalte in der er steht suchen: Spalte iterieren bis ich den ersten container finde.
   1.2. Container vom original position löschen, dann container in der Position direkt unter dem Kran einfügen ( stapel[i+1][kran_position])
   1.3. Variable Container_im_kran = true
2. Kran legt container ab:
    2.1. Hat der Kran überhaupt ein container aufgenommen? falls ja, weiter, falls nicht, mach nichts
    2.2. Kran muss einen leeren platz finden um den Container abzulegen. Wo? in der gleichen spalte in der er steht suchen:
        2.2.1. Spalte iterieren bis ich den ersten container finde,
        2.2.2. dann die Position darüber nehmen stapel[container_index-1][kran_position] (die position direkt über den ersten container ist bestimmt leer) gleiche methode benutzen wie im schritt 1.1.
    2.3. container von original position (unter dem Kran) löschen. in neuen position einfügen.
    2.3. Variable Container_im_kran = false

3. Kran nach rechts bewegen: Kran immer in stapel[0][j], nach rechts bewegen ist j+1 möglich bis zur rechten Ende des Arrays j < stapel[0].length -1. Vom original position löschen
4. Kran bewegt sich nach links: nach links bewegen ist j-1 möglich bis zur linken Ende des Arrays j>0 . Vom original position löschen
5. Wenn kran einen Container hat, müssen sich beide gleichzeitig bewegen. d.h. für Kran stapel[0][j+1] unf für container stapel[1][j+1]
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
