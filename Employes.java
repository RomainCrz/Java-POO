
/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Employe {
    private final String nom;
    private double revenu;
    private int occupation = 100;
    private double prime = 0;

    public Employe(String s, double r, int o) {
        nom = s;
        revenu = r;
        occupation = o;
        if (occupation > 100) {
            occupation = 100;
        } else if (occupation < 10) {
            occupation = 10;
        }
        System.out.print("Nous avons un nouvel employé : " + getNom() + ",");
    }

    public Employe(String s, double r) {
        nom = s;
        revenu = r;
        System.out.print("Nous avons un nouvel employé : " + getNom() + ",");
    }

    public String getNom() {
        return nom;
    }

    public double revenuAnnuel() {
        return revenu * 12 * occupation / 100 + prime;
    }

    public String toString() {
        return nom + " : \n" + "  Taux d'occupation : " + occupation + "%. Salaire annuel : "
                + String.format("%.2f", revenuAnnuel()) + " francs" + affichePrime();
    }

    public double getPrime() {
        return prime;
    }

    public String affichePrime() {
        if (getPrime() > 0) {
            return ", Prime : " + String.format("%.2f", getPrime()) + ".\n";
        } else {
            return ".\n";
        }
    }

    public void demandePrime() {
        Scanner scanner = new Scanner(System.in);
        boolean montantOk = false;
        int compteur = 0;
        do {
            System.out.println("Montant de la prime souhaitée par " + nom + " ?");
            try {
                prime = scanner.nextDouble();
                if (prime <= revenuAnnuel() * 0.02) {
                    montantOk = true;
                } else {
                    System.out.println("Trop cher!");
                    prime = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Vous devez introduire un nombre!");
                scanner.nextLine();
            }

            ++compteur;
        } while (compteur < 5 && montantOk == false);
        scanner.close();
        ;
    }
}

class Manager extends Employe {
    private int jour;
    private int clients;
    public final static int FACTEUR_GAIN_CLIENT = 500;
    public final static int FACTEUR_GAIN_VOYAGE = 100;

    public Manager(String s, double r, int j, int c) {
        super(s, r);
        jour = j;
        clients = c;
        System.out.println(" c'est un manager.");
    }

    public Manager(String s, double r, int j, int c, int o) {
        super(s, r, o);
        jour = j;
        clients = c;
        System.out.println(" c'est un manager.");
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + clients * FACTEUR_GAIN_CLIENT + jour * FACTEUR_GAIN_VOYAGE;
    }

    public String toString() {

        return super.toString() + "  A voyagé " + jour + " jours et apporté " + clients
                + " nouveaux clients.\n";
    }

}

class Testeur extends Employe {
    private int erreur;
    public static final int FACTEUR_GAIN_ERREURS = 10;

    public Testeur(String s, double r, int e) {
        super(s, r);
        erreur = e;
        System.out.println(" c'est un testeur.");
    }

    public Testeur(String s, double r, int e, int o) {
        super(s, r, o);
        erreur = e;
        System.out.println(" c'est un testeur.");
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + erreur * FACTEUR_GAIN_ERREURS;
    }

    public String toString() {

        return super.toString() + "  A corrigé " + erreur + " erreurs.\n";
    }

}

class Programmeur extends Employe {
    private int projetAcheves;
    public static final int FACTEUR_GAIN_PROJETS = 200;

    public Programmeur(String s, double r, int p) {
        super(s, r);
        projetAcheves = p;
        System.out.println(" c'est un programmeur.");
    }

    public Programmeur(String s, double r, int p, int o) {
        super(s, r, o);
        projetAcheves = p;
        System.out.println(" c'est un programmeur.");
    }

    public double revenuAnnuel() {
        return super.revenuAnnuel() + projetAcheves * FACTEUR_GAIN_PROJETS;
    }

    public String toString() {

        return super.toString() + "  A mené à bien " + projetAcheves + " projets\n";
    }

}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4));
        staff.add(new Programmeur("Paul Lepetit", 6456, 3, 75));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}
