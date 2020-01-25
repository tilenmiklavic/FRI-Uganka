import java.util.Arrays;
import java.util.Scanner;

class Uganka {

    public static char vrni_mode(Scanner sc) {
        while (true) {
            Sysem.out.println("Želite sporočilo šifrirati ali dešifrirati? [S/D]");
            System.out.println("Vnesti morate eno od črk (S/D)");

            char mode = sc.nextLine().toLowerCase().charAt(0);

            if (mode == ('s') ||
                mode == ('d') ||
                mode == ('š')) {

                return mode;
            }
        }
    }

    public static boolean matches(char st) {
        if (st == ('s') ||
            st == ('d') ||
            st == ('š')) {
                return true;
            }
        return false;
    }

    public static void desifriraj(Scanner sc) {
        System.out.println("Vnesi dolzino kljuca (uporabljaj le števila)");
        int kljuc = Integer.parseInt(sc.nextLine());
        System.out.println("Vnesi zašifriran niz:");
        String niz = sc.nextLine();

        char[][] tabela = new char[kljuc][niz.length()];
        int index = 0;
        boolean down = true;

        for (int i = 0; i < kljuc; i++) {
            int stolpec = i;
            down = true;

            while (stolpec < niz.length()) {
                tabela[i][stolpec] = niz.charAt(index);

                if (i == 0 || i == (kljuc - 1)) {
                    stolpec += (kljuc -1) * 2;
                }
                else if (down) {
                    int test = ((kljuc - 1 - i) * 2);
                    if (test < 0) {
                        test = 0;
                    }

                    stolpec += test;

                    down = false;
                } else {
                    stolpec += (i * 2);
                    down = true;
                }
                index++;
            }
        }
        
        //System.out.println(Arrays.deepToString(tabela));

        System.out.println("Odšifrirano sporočilo je:");
        
        for (int i = 0; i < niz.length(); i++) {
            for (int j = 0; j < kljuc; j++) {
                System.out.print(tabela[j][i]);
            }
        }
    }

    public static void sifriraj(Scanner sc) {
        System.out.println("Vnesite niz ki bi ga radi zašifrirali:");
        String niz = sc.nextLine();
        System.out.println("Vnesite dolžino ključa:");
        int kljuc = Integer.parseInt(sc.nextLine());
        
        char[][] tabela = new char[kljuc][niz.length()];
        int vrstica = 0;
        int index = 0;
        boolean down = true;

        for (int i = 0; i < niz.length(); i++) {
            tabela[vrstica][i] = niz.charAt(index);

            index++;
            
            if (down) {
                vrstica++;
            } else {
                vrstica--;
            }

            if (vrstica == 0) {
                down = true;
            } else if (vrstica == kljuc - 1) {
                down = false;
            }
        }

        for (int i = 0; i < kljuc; i++) {
            for (int j = 0; j < niz.length(); j++) {
                System.out.print(tabela[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Želite sporočilo šifrirati ali dešifrirati? [S/D]");
        char mode = sc.nextLine().toLowerCase().charAt(0);

        if (!matches(mode)) {
            mode = vrni_mode(sc);
        } 

        if (mode == 'd') {
            desifriraj(sc);
        } else if (mode == 's' ||
                   mode == 'š') {
            sifriraj(sc);
        }
    }
}