import java.util.Scanner;

public class Sapin {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Quel symbole voulez-vous pour le triangle ? ");
        String c = input.next();
        System.out.print("Combien de lignes (de 8 a 35) ? ");
        int lignes = input.nextInt();
        System.out.println(
                "Quelles guirlandes voulez-vous mettre (taille de 3 a 25 caractères et elle ne peuvent pas contenir le même caractère que celui utilisé pour les épines) ? ");
        String guirlande;
        do {
            guirlande = input.next();
        } while (guirlande.equals(c));
        dessinTriangle(c, lignes, guirlande);

    }

    public static void dessinTriangle(String c, int lignes, String guirlande) {
        int compteur = 0;
        for (int i = 1; i <= lignes + 1; ++i) {
            if (i < lignes + 1) {
                for (int k = 0; k < 8 - i; ++k) {
                    System.out.print(" ");
                }
                if (i % 2 == 1 && (compteur == 0 || compteur == 3)) {
                    dessinLignes(i, c);
                    compteur = 0;
                } else if (i % 2 == 1 && compteur != 0) {
                    compteur = dessinGuirlande(i, guirlande, c, compteur);
                } else {
                    compteur = dessinGuirlande(i, guirlande, c, compteur);
                }

                System.out.println();
            } else {
                for (int k = 0; k < (i - 3); ++k) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 3; j++) {
                    System.out.print("|");
                }
            }
        }
    }

    public static void dessinLignes(int i, String c) {
        for (int j = 0; j < (i * 2 - 1); ++j) {
            System.out.print(c);
        }
    }

    public static int dessinGuirlande(int i, String guirlande, String s, int reste) {
        int compteur = 0;
        int nbEpines;

        if (reste == 0) {
            for (int j = 0; j < (i * 2 - 1); ++j) {
                if (compteur < guirlande.length()) {
                    System.out.print(decoupeGuirlande(guirlande, compteur));
                    ++compteur;
                } else {
                    nbEpines = calculEpines(j, i);
                    for (int k = 0; k < nbEpines; k++) {
                        System.out.print(s);
                    }
                    compteur = 0;
                    j = j + nbEpines - 1;
                }
            }
            return compteur;
        } else {
            reste = guirlande.length() - 1 - reste;
            for (int j = 0; j < (i * 2 - 1); ++j) {
                if ((i * 2 - 1) - j <= reste + 1) {
                    System.out.print(decoupeGuirlande(guirlande, reste));
                    reste = reste - 1;
                } else {
                    System.out.print(s);
                }
            }
            return 0;
        }

    }

    public static int compteurSave() {
        return 1;
    }

    public static void moitie(int i, String guirlande, String s) {
        int compteur = compteurSave();
        for (int j = 0; j < (i * 2 - 1); ++j) {
            if ((i * 2 - 1) - j > compteur) {
                System.out.println(s);
            } else {
                System.out.println(decoupeGuirlande(guirlande, compteur));
            }
        }
    }

    public static char decoupeGuirlande(String guirlande, int j) {
        char c = guirlande.charAt(j);
        return c;
    }

    public static int calculEpines(int j, int i) {

        double random = Math.random() * 100;
        if (random > 50 && (i * 2) - 1 - j >= 3) {
            return 3;
        } else if ((i * 2) - 1 - j >= 2) {
            return 2;
        } else {
            return 1;
        }

    }

}
