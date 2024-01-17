package Containerterminal;

import java.util.Scanner;

public class TestProgramm {
    public static void main(String[] args) {
        String[][] stacks = new String[][]{
                {"", "","^","",""},
                {"", "","","",""},
                {"","□","","",""},
                {"□", "□","","□","□"},
                {"□","□","□","□","□"},
        };
        //test1(stacks);
        test2(stacks);
    }

    public static void test1(String [][] stacks){
        // Erstellen Sie eine Instanz von ContainerTerminal

        ContainerTerminal2 terminal = new ContainerTerminal2(stacks);

        // Führen Sie einige Aktionen durch
        terminal.printState();
        terminal.moveCraneRight();
        terminal.printState();
        terminal.moveCraneRight();
        terminal.printState();
        terminal.pickUpContainer();
        terminal.printState();
        terminal.moveCraneLeft();
        terminal.printState();
        terminal.dropContainer();

        // Geben Sie den Zustand des Terminals aus
        terminal.printState();


    }

    public static void test2(String [][] stacks){
        ContainerTerminal2 terminal = new ContainerTerminal2(stacks);

        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equalsIgnoreCase("X")) {
            terminal.printState();
            System.out.println("Menü");
            System.out.println("W: Aufnehmen");
            System.out.println("A: Links");
            System.out.println("S: Ablegen");
            System.out.println("D: Rechts");
            System.out.println("X: Beenden");
            System.out.print("Befehl: ");
            command = scanner.nextLine();

            switch (command.toUpperCase()) {
                case "W":
                    terminal.pickUpContainer();
                    break;
                case "A":
                    terminal.moveCraneLeft();
                    break;
                case "S":
                    terminal.dropContainer();
                    break;
                case "D":
                    terminal.moveCraneRight();
                    break;
            }
        }

        scanner.close();
    }
}
