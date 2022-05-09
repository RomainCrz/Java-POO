import java.util.*;

public class Puissance4v2 {

    public static void main(String[] args) {
        new Partie();
    }
}

class Joueur {
    private int numJoueur;

    public Joueur(String nomJoueur) {
        numJoueur = 2;
    }

    public Joueur() {
        numJoueur = 1;
    }

    public int getNumJoueur() {
        return numJoueur;
    }
}

class Humain extends Joueur {
    private Scanner input = new Scanner(System.in);

    public Humain(String nomJoueur) {
        super(nomJoueur);
    }

    public void joue(Jeu jeu) {
        int colonne = 0;
        boolean coupJoue = false;
        do {
            System.out.println("Dans quelle colonne mettre le pion ?");
            colonne = input.nextInt();
            coupJoue = jeu.joueCoup(colonne, getNumJoueur());
        } while (!coupJoue);

    }
}

class Robot extends Joueur {
    public Robot() {
        super();
    }

    public void joue(Jeu jeu) {
        int numColonne = 0;
        boolean coupJoue = false;
        do {
            coupJoue = jeu.joueCoup(numColonne, getNumJoueur());
            ++numColonne;
        } while (!coupJoue);
    }
}

class Jeu {
    private int[][] jeu = new int[6][7];

    public Jeu() {
        for (int i = 0; i < jeu.length; i++) {
            for (int j = 0; j < jeu[0].length; j++) {
                jeu[i][j] = 0;
            }
        }
    }

    public boolean joueCoup(int numColonne, int numjoueur) {
        boolean coupJoue = false;

        for (int i = jeu.length - 1; i >= 0 && coupJoue == false; --i) {
            if (jeu[i][numColonne] != 0) {
                coupJoue = false;
            } else {
                coupJoue = true;
                jeu[i][numColonne] = numjoueur;
            }
        }

        return coupJoue;
    }

    public int[][] getJeu() {
        return jeu;
    }

    public boolean cherche4(int numJoueur) {
        int somme = 0;
        int test;
        if (numJoueur == 1) {
            test = 1;
        } else {
            test = 2;
        }

        for (int i = 0; i < jeu.length; i++) {
            for (int j = 0; j < jeu[0].length; j++) {
                somme = 0;

                int nbPions = 0;

                if (jeu[i][j] == test) {

                    if (j <= 3) {
                        for (int k = j; k < j + 4; ++k) {
                            if (jeu[i][k] == test) {
                                ++somme;
                            }
                        }
                        if (nbPions < somme) {
                            nbPions = somme;
                        }
                        somme = 0;
                    }

                    if (i > 2) {
                        for (int k = i; k > i - 4; --k) {
                            if (jeu[k][j] == test) {
                                ++somme;
                            }
                        }
                        if (nbPions < somme) {
                            nbPions = somme;
                        }
                        somme = 0;
                    }

                    if (i > 2 && j <= 3) {
                        int j2 = j;
                        for (int k = i; k > i - 4; --k) {
                            if (jeu[k][j2] == test) {
                                ++somme;
                            }
                            j2 = j2 + 1;

                        }
                        if (nbPions < somme) {
                            nbPions = somme;
                        }
                        somme = 0;
                    }

                    if (i <= 2 && j <= 3) {
                        int j2 = j;
                        for (int k = i; k < i + 4; ++k) {
                            if (jeu[k][j2] == test) {
                                ++somme;
                            }
                            j2 = j2 + 1;

                        }
                        if (nbPions < somme) {
                            nbPions = somme;
                        }
                        somme = 0;
                    }
                }

                if (nbPions == 4) {
                    return true;
                }
            }
        }

        return false;
    }
}

class Partie {
    private Scanner input = new Scanner(System.in);

    public Partie() {

        Jeu jeu = new Jeu();
        System.out.println("Entrez votre nom : ");
        String nomJoueur = input.nextLine();
        Humain humain = new Humain(nomJoueur);
        Robot robot = new Robot();
        boolean gagne = false;
        int numJoueur = 1;
        while (!gagne) {

            if (numJoueur == 1) {
                System.out.println("C'est au tour du programme");
                robot.joue(jeu);
                gagne = jeu.cherche4(numJoueur);
                ++numJoueur;

            } else {
                System.out.println("C'est au tour de " + nomJoueur);
                humain.joue(jeu);
                gagne = jeu.cherche4(numJoueur);
                --numJoueur;
            }
            for (int i = 0; i < jeu.getJeu().length; i++) {
                for (int j = 0; j < jeu.getJeu()[0].length; j++) {
                    System.out.print("|");
                    if (jeu.getJeu()[i][j] == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print(jeu.getJeu()[i][j]);
                    }
                    System.out.print("|");
                }
                System.out.println();

            }
            System.out.println(" 0  1  2  3  4  5  6");
        }
        if (numJoueur == 1) {
            System.out.println(nomJoueur + " a gagné !");
        } else {
            System.out.println("Le programme a gagné ...");
        }

    }

}
