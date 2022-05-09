
/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Vehicule {
    private String nom;
    private double vitessMax;
    private int poids;
    private int carburant;

    public Vehicule() {
        nom = "Anonyme";
        vitessMax = 130;
        poids = 1000;
        carburant = 0;
    }

    public Vehicule(String s, double d, int p, int c) {
        nom = s;
        vitessMax = d;
        poids = p;
        carburant = c;
    }

    public String getNom() {
        return nom;
    }

    public double getVitessMax() {
        return vitessMax;
    }

    public int getPoids() {
        return poids;
    }

    public int getCarburant() {
        return carburant;
    }

    private double performance() {
        return vitessMax / poids;
    }

    public boolean meilleur(Vehicule autreVehicule) {
        return performance() >= autreVehicule.performance();
    }

    public String toString() {
        return getNom() + " -> vitesse max = " + getVitessMax() + " km/h, poids = " + getPoids() + " kg";
    }

    public boolean estDeuxRoues() {
        return false;
    }

}

class Voiture extends Vehicule {
    private String type;

    public Voiture(String s, double d, int p, int c, String s2) {
        super(s, d, p, c);
        type = s2;
    }

    public String getCategorie() {
        return type;
    }

    public String toString() {
        return super.toString() + ", Voiture de " + getCategorie();
    }

    public boolean estDeuxRoues() {
        return false;
    }
}

class Moto extends Vehicule {
    private boolean sidecar = false;

    public Moto(String s, double d, int p, int c) {
        super(s, d, p, c);
    }

    public Moto(String s, double d, int p, int c, boolean b) {
        super(s, d, p, c);
        sidecar = b;
    }

    public String toString() {
        if (sidecar) {
            return super.toString() + ", Moto, avec sidecar";
        } else {
            return super.toString() + ", Moto";
        }
    }

    public boolean estDeuxRoues() {
        if (sidecar) {
            return false;
        } else {
            return true;
        }
    }
}

abstract class Rallye {
    abstract public boolean check();
}

class GrandPrix extends Rallye {
    private ArrayList<Vehicule> liste = new ArrayList<Vehicule>();

    public void ajouter(Vehicule vehicule) {
        liste.add(vehicule);
    }

    public boolean check() {
        boolean check = liste.get(0).estDeuxRoues();
        for (int i = 1; i < liste.size(); i++) {
            if (liste.get(i).estDeuxRoues() != check) {
                return false;
            }
        }
        return true;
    }

    public void run(int tours) {
        if (!check()) {
            System.out.println("Pas de Grand Prix");
        } else {
            for (int i = 0; i < liste.size(); i++) {
                int carburant = liste.get(i).getCarburant();
                if (carburant <= tours) {
                    liste.remove(i);
                    --i;
                }
            }
            if (liste.size() > 0) {
                int meilleur = 0;
                for (int i = 0; i < liste.size(); i++) {
                    for (int j = 0; j < liste.size(); j++) {
                        if (liste.get(i).meilleur(liste.get(j))) {
                            meilleur = i;
                        }

                    }
                }
                System.out.println("Le gagant du grand prix est : ");
                System.out.println(liste.get(meilleur));
            } else {
                System.out.println("Elimination de tous les vehicules");
            }
        }
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}
