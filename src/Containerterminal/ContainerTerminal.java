package Containerterminal;

public class ContainerTerminal {

    /*
    * Wir stellen uns vor, der Terminal besteht aus einer Anzahl von Stapeln neben einander --> 2d-Array
     * die oberste Zeile ist die Zeile in der sich der Kran bewegt. Das ist die Zeile 0.
     * Wenn der Kran ein Container aufnimmt, landet dieser Container in Zeile 1, direkt unter dem Kran. Alle anderen Container sind in den Zeilen von 2 zu n gestapelt.
     * Wir "bewegen" den Kran und die Container eingentlich nicht, wir löschen sie nur von einer position und fügen sie in einer anderen position ein.
     *
    * */
    private String[][] stapel;
    private int kran_position; //position des Krans in der obersten Zeile
    private boolean container_im_kran; //Zeigt ob der Kran ein Container aufgenommen hat oder nicht.

    public ContainerTerminal(String[][] stapel) {
        this.stapel = stapel;
        this.kran_position = finde_kran_position(stapel); //wir wissen nicht wo sich der Kran im Terminal befindet
        this.container_im_kran = false; //Der Kran hat noch kein Container aufgenommen
    }

    private int finde_kran_position(String[][] stapel) {
        //Wir suchen den Kran nur in der ersten Zeile unseres Arrays.
        for (int j = 0; j < stapel[0].length; j++) {
            if (stapel[0][j].equals("^")) {
                return j;
            }
        }
        System.out.println("Kran nicht gefunden");
        return -1; // Kran nicht gefunden
    }

    public void kran_nach_links() {
        //Bewegt Kran nach links.
        // Wir können den Kran nur nach links bewegen, wenn er noch nicht am linken Ende des arrays angekommen ist --> kran_position > 0
        //Wir müssen auch sicherstellen, ob der Kran ein Container hat oder nicht. Wenn schon, muss der Container mit dem Kran gleichzeitig bewegt werden.

        if (kran_position > 0) {
            if(this.container_im_kran){ //Kran hat Container weil container_im_kran == true --> wir müssen beide gleichzeitig bewegen
                stapel[0][kran_position] = ""; //löschen den Kran (zeile 0) und den Container (zeile 1).
                stapel[1][kran_position] = "";
                kran_position--;                // Kran position wird um 1 verringert --> Kran bewegt sich nach links
                stapel[0][kran_position] = "^"; //Kran und Container in ihre neuen Positionen einfügen.
                stapel[1][kran_position] = "□";
            }else{//Kran ist leer weil container_im_kran == false --> wir müssen nur den Kran bewegen
                stapel[0][kran_position] = "";
                kran_position--;
                stapel[0][kran_position] = "^";
            }
        }
    }

    public void kran_nach_rechts() {
        //Gleich wie die obere Methode
        //Wir können den Kran nur nach rechts bewegen, wenn er noch nicht am rechten Ende des arrays angekommen ist --> kran_position < Länge der ersten Zeile
        if (kran_position < stapel[0].length - 1) {
            if(this.container_im_kran){
                stapel[0][kran_position] = "";
                stapel[1][kran_position] = "";
                kran_position++;
                stapel[0][kran_position] = "^";
                stapel[1][kran_position] = "□";
            }else {
                stapel[0][kran_position] = "";
                kran_position++;
                stapel[0][kran_position] = "^";
            }
        }
    }

    public void container_aufnehmen() { //Kran nimmt container auf
        int container_index = checkContainer(); //sucht den obersten container im Stapel direkt untern Kran.

        //Kran kann container nur aufnehmen, wenn er keinen Container schon aufgenommen hat und wenn der Stapel direkt darunter nicht leer ist.
        if (!container_im_kran && container_index > 0) {
            container_im_kran = true; //wir nehmen container auf
            stapel[container_index][kran_position] = ""; //löschen den Container von seiner ersten position
            stapel[1][kran_position] = "□";             // und wir fügen den in der position direkt unter dem Container.
        }
    }

    public int checkContainer(){ //sucht den obersten container im Stapel direkt untern Kran.
        //int i = 2; //wenn der Kran ein Container hat, ist dieser in Zeile 1, direkt unter dem Container --> wir suchen in der Zeile danach Zeile 2

        for (int i =2; i< stapel[0].length; i++){
            if(stapel[i][kran_position].equals("□")){
                System.out.println("container index ist "+i);
                return i;
            }
        }
        return -1;
    }

    public void Container_ablegen() {
        //Ein Kran kann nur ein Container ablegen, wenn er überhaupt ein Container aufgenommen hat.
        if (container_im_kran) {
            int container_index = checkContainer(); //wir suchen den obersten Container im stapel direkt unter dem Kran
            container_index--;                      //wir wollen aber nicht diesem Container sondern den leeren platz darüber --> container_index--
            stapel[1][kran_position] = "";          //wir löschen den Container im Kran
            stapel[container_index][kran_position] = "□"; ///und legen den ab in der nächst liegenden leeren Position im Stapel.
            container_im_kran = false;              //Der Kran hat keinen Container mehr.
        }
    }

    public void printZustand() { //Zustand rausgeben.
        for (int i = 0; i < stapel.length; i++) {
            for (int j = 0; j < stapel[i].length; j++) {
                System.out.print(stapel[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }
}

