package Containerterminal;

public class ContainerTerminal {
    //private String[][] stapel;

    private String[][] stapel = new String[][]{
            {"", "","^","",""},
            {"", "","","",""},
            {"","□","","",""},
            {"□", "□","","□","□"},
            {"□","□","□","□","□"},
    };
    private int[] stapelHoehe;
    private int max_Hoehe;
    private int Kran_position;
    private String container_im_Kran;

    public ContainerTerminal(int num_stacks, int max_Hoehe) {
        //this.stapel = new String[num_stacks][max_Hoehe];
        this.stapelHoehe = new int[num_stacks];
        this.max_Hoehe = this.stapel.length;//max_Hoehe;
        this.Kran_position = 2;
        this.container_im_Kran = null;
    }

    public void kran_nach_links() {
        if (Kran_position > 0) {
            this.stapel[0][Kran_position] = "";
            Kran_position--;
            this.stapel[0][Kran_position] = "^";
        }

    }

    public void kran_nach_rechts() {
        if (Kran_position < stapel.length - 1) {
            this.stapel[0][Kran_position] = "";
            Kran_position++;
            this.stapel[0][Kran_position] = "^";
        }
    }

    public void Container_aufnehmen() {
        if (container_im_Kran == null && stapelHoehe[Kran_position] > 0) {
            container_im_Kran = stapel[Kran_position][stapelHoehe[Kran_position] - 1];
            stapel[Kran_position][stapelHoehe[Kran_position] - 1] = null;
            stapelHoehe[Kran_position]--;
        }

        if (Kran_position > 0) {
            this.stapel[0][Kran_position] = "";
            Kran_position--;
            this.stapel[0][Kran_position] = "^";
        }
    }

    public void Container_ablegen() {
        if (container_im_Kran != null && stapelHoehe[Kran_position] < max_Hoehe) {
            stapel[Kran_position][stapelHoehe[Kran_position]] = container_im_Kran;
            stapelHoehe[Kran_position]++;
            container_im_Kran = null;
        }
    }

    public void printZustand() {
        for (int i = 0; i < stapel.length; i++) {
            for (int j = 0; j < stapel[i].length; j++) {
                System.out.print(stapel[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = max_Hoehe; i >= 0; i--) {
            for (int j = 0; j < stapel.length; j++) {
                if (i == stapelHoehe[j]) {
                    System.out.print((j == Kran_position && container_im_Kran != null) ? "^ " : "  ");
                } else if (i < stapelHoehe[j]) {
                    System.out.print("□ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
