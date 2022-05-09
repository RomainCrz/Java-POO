
/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class OptionVoyage {
    private String nom;
    private double prixForf;

    public OptionVoyage(String s, double d) {
        nom = s;
        prixForf = d;
    }

    public String getNom() {
        return nom;
    }

    public double prix() {
        return prixForf;
    }

    public String toString() {
        return getNom() + " -> " + prix() + " CHF";
    }
}

class Sejour extends OptionVoyage {
    private int nbNuits;
    private double prixNuit;

    public Sejour(String s, double d, int i, double p) {
        super(s, d);
        nbNuits = i;
        prixNuit = p;
    }

    public double prix() {
        return nbNuits * prixNuit + super.prix();
    }

}

class Transport extends OptionVoyage {
    private boolean isLong = false;
    public static final double TARIF_LONG = 1500.0;
    public static final double TARIF_BASE = 200.0;
    private double prixTransport;

    public Transport(String s, double d) {
        super(s, d);
        prixTransport = TARIF_BASE + d;
    }

    public Transport(String s, double d, boolean b) {
        super(s, d);
        isLong = b;
        if (isLong) {
            prixTransport = TARIF_LONG + d;
        } else {
            prixTransport = TARIF_BASE + d;
        }
    }

    public double prix() {
        return prixTransport;
    }
}

class KitVoyage {
    private ArrayList<OptionVoyage> liste = new ArrayList<OptionVoyage>();
    private String depart;
    private String destination;

    public KitVoyage(String s1, String s2) {
        depart = s1;
        destination = s2;
    }

    public double prix() {
        double prixKit = 0;
        for (int i = 0; i < liste.size(); i++) {
            prixKit += liste.get(i).prix();
        }
        return prixKit;
    }

    public String toString() {
        String description = "Voyage de " + depart + " à " + destination + ", avec pour options : \n";
        for (int i = 0; i < liste.size(); i++) {
            description += "- " + liste.get(i).toString() + "\n";
        }
        description += "Prix total : " + prix() + " CHF \n";
        return description;
    }

    public void ajouterOption(OptionVoyage option) {
        liste.add(option);
    }

    public int getNbOptions() {
        int nbOptions = 0;
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) != null) {
                ++nbOptions;
            }
        }
        return nbOptions;
    }

    public void annuler() {
        liste.clear();
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night", 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2

        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}
