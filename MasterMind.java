import java.util.Scanner;

public class MasterMind {
    static int n = 4;
    static int m = 6;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[] laCombinaison = new int[n];
        int[] combinaison = new int[n];
        boolean identique = false;
        System.out.println("Le jeu a choisit une combinaison, essayez de la deviner ! (chiffre entre 1 et 6 compris) ");
        tirerCombinaison(laCombinaison, n, m);
        System.out.println("Vous avez 10 tentatives ! (# = bien placé, O = mal placé)");
        int compteur = 0;
        do {
            demanderCoup(combinaison, n);
            System.out.println(affiche(combinaison, laCombinaison));
            identique = compare(combinaison, laCombinaison);
            ++compteur;
        } while (!identique && compteur < 10);

        if (identique) {
            System.out.println("Bravo ! Vous avez trouvé en " + compteur + " coups");
        } else {
            System.out.println("Désolé vous n'avez pas trouvé ..");
            System.out.print("La bonne réponse était ");
            for (int i = 0; i < 4; i++) {
                System.out.print(laCombinaison[i] + " ");
            }
        }

    }

    public static void tirerCombinaison(int[] laCombinaison, int n, int m) {
        for (int i = 0; i < n; i++) {
            laCombinaison[i] = hasard(m);
        }
    }

    static int hasard(int max) {
        return (1 + (int) (Math.random() * max));
    }

    static void demanderCoup(int[] combinaison, int n) {

        System.out.println("Entrez les 4 chiffres de votre proposition : ");
        int proposition = input.nextInt();
        for (int i = 3; i >= 0; --i) {

            combinaison[i] = proposition % 10;
            proposition = proposition / 10;

        }
    }

    static boolean compare(int[] combinaison, int[] LaCombinaison) {
        boolean identique = false;
        int compare = 0;
        for (int i = 0; i < 4; i++) {
            int resultat = LaCombinaison[i];
            int chercher = combinaison[i];
            if (resultat == chercher) {
                ++compare;
            }

        }
        if (compare == 4) {
            identique = true;
        }
        return identique;

    }

    static String affiche(int[] combinaison, int[] LaCombinaison) {
        // ArrayList<String> reponse = new ArrayList<String>();
        String[] reponse = { "", "", "", "" };
        String indice = "";
        if (compare(combinaison, LaCombinaison)) {
            return "####";
        } else {
            boolean[] dejaCompte = { false, false, false, false };
            for (int i = 0; i < 4; i++) {
                int resultat = LaCombinaison[i];
                for (int j = 0; j < 4; j++) {
                    int chercher = combinaison[j];

                    if (resultat == chercher) {
                        if (i == j) {
                            // reponse.add("#");
                            reponse[j] = "#";
                            dejaCompte[j] = true;
                        } else if (i != j && dejaCompte[j] == false) {
                            reponse[j] = "O";
                            dejaCompte[j] = true;
                        }
                    }

                }
            }

            for (int i = 0; i < 4; i++) {
                indice = indice + reponse[i];
            }
            return indice;
        }

    }

}
