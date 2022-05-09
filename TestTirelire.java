import java.util.Scanner;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Tirelire {
    private int montant = 0;
    private double total = montant;

    public double getMontant() {
        return total;
    }

    public void afficher() {
        if (getMontant() == 0) {
            System.out.println("Vous etes sans le sou.");
        } else {
            System.out.println("Vous avez : " + getMontant() + " euros dans votre tirelire.");
        }
    }

    public void secouer() {
        if (getMontant() > 0) {
            System.out.println("Bing bing");
        }
    }

    public void remplir(double somme) {
        if (somme > 0) {
            total = total + somme;
        }
    }

    public void vider() {
        total = 0;
    }

    public void puiser(double somme) {
        if (somme > 0) {
            if (somme > total) {
                vider();
            } else if (total > 0) {
                total = total - somme;
            }
        }
    }

    public double calculerSolde(double budget) {
        if (budget > 0) {
            return total - budget;
        } else {
            return total;
        }
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
public class TestTirelire {

    public static void main(String[] args) {
        Tirelire piggy = new Tirelire();

        piggy.vider();
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(20.0);
        piggy.secouer();
        piggy.afficher();

        piggy.remplir(550.0);
        piggy.secouer();
        piggy.afficher();

        piggy.puiser(10.0);
        piggy.puiser(5.0);
        piggy.afficher();

        System.out.println();

        // le budget de vos vacances de rêves.
        double budget;
        Scanner clavier = new Scanner(System.in);

        System.out.println("Donnez le budget de vos vacances : ");
        budget = clavier.nextDouble();

        // ce qui resterait dans la tirelire après les
        // vacances
        double solde = piggy.calculerSolde(budget);

        if (solde >= 0) {
            System.out.println("Vous etes assez riche pour partir en vacances !");
            System.out.print(" il vous restera " + solde + " euros");
            System.out.print(" a la rentree \n");
            piggy.puiser(budget);
        }

        else {
            System.out.print("Il vous manque " + (-solde) + " euros");
            System.out.print(" pour partir en vacances !\n");
        }
        clavier.close();
    }
}
