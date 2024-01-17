package Containerterminal;

public class ContainerTerminal2 {
    private String[][] stacks;
    private int crane_position;
    private int container_in_crane;

    public ContainerTerminal2(String[][] stacks) {
        this.stacks = stacks;
        this.crane_position = findCranePosition(stacks);
        this.container_in_crane = 0; //kein Container
    }

    private int findCranePosition(String[][] stacks) {
        for (int j = 0; j < stacks[0].length; j++) {
            if (stacks[0][j].equals("^")) {
                return j;
            }
        }
        System.out.println("Kran nicht gefunden");
        return -1; // Kran nicht gefunden
    }

    public void moveCraneLeft() {
        if (crane_position > 0) {
            if(this.container_in_crane == 1){
                stacks[0][crane_position] = "";
                stacks[1][crane_position] = "";
                crane_position--;
                stacks[0][crane_position] = "^";
                stacks[1][crane_position] = "□";
            }else{
                stacks[0][crane_position] = "";
                crane_position--;
                stacks[0][crane_position] = "^";
            }
        }
    }

    public void moveCraneRight() {
        if (crane_position < stacks[0].length - 1) {
            if(this.container_in_crane == 1){
                stacks[0][crane_position] = "";
                stacks[1][crane_position] = "";
                crane_position++;
                stacks[0][crane_position] = "^";
                stacks[1][crane_position] = "□";
            }else {
                stacks[0][crane_position] = "";
                crane_position++;
                stacks[0][crane_position] = "^";
            }
        }
    }

    public void pickUpContainer() {
        int container_index = checkContainer();
        if (container_in_crane == 0 && container_index > 0) {
            container_in_crane = 1; //wir nehmen container auf
            stacks[container_index][crane_position] = "";
            stacks[1][crane_position] = "□";
        }
    }

    public int checkContainer(){
        for (int i=2; i<stacks[0].length; i++){
            if(stacks[i][crane_position].equals("□")){
                System.out.println("container index ist "+i);
                return i;
            }
        }
        return 0;
    }

    public void dropContainer() {
        if (container_in_crane == 1 && !stacks[1][crane_position].equals("□")) {
            int container_index = checkContainer();
            container_index--;
            stacks[1][crane_position] = "";
            stacks[container_index][crane_position] = "x";
            container_in_crane = 0;
        }
    }

    public void printState() {
        for (int i = 0; i < stacks.length; i++) {
            for (int j = 0; j < stacks[i].length; j++) {
                System.out.print(stacks[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
}

