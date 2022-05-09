import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    // Completer la classe Auteur ici
    private String nom;
    private boolean prime;

    public Auteur(String nomAuteur, boolean primeAuteur) {
        nom = nomAuteur;
        prime = primeAuteur;
    }

    public String getNom() {
        return nom;
    }

    public boolean getPrix() {
        return prime;
    }
}

class Oeuvre {
    // Completer la classe Oeuvre ici
    private String titre;
    private Auteur a;
    private String langue;

    public Oeuvre(String titreOeuvre, Auteur aNum, String langueOeuvre) {
        titre = titreOeuvre;
        a = aNum;
        langue = langueOeuvre;
    }

    public Oeuvre(String titreOeuvre, Auteur aNum) {
        titre = titreOeuvre;
        a = aNum;
        langue = "francais";
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return a;
    }

    public String getLangue() {
        return langue;
    }

    public void afficher() {
        System.out.println(titre + ", " + a.getNom() + ", en " + langue);
    }

}

// completer les autres classes ici
class Exemplaire {
    private Oeuvre reference;

    public Exemplaire(Oeuvre oeuvre) {
        reference = oeuvre;
        System.out.println("Nouvel exemplaire -> " + reference.getTitre() + ", " + reference.getAuteur().getNom()
                + ", en " + reference.getLangue());
    }

    public Exemplaire(Exemplaire copie) {
        reference = copie.reference;
        System.out.println("Copie d'un exemplaire de -> " + reference.getTitre() + ", "
                + reference.getAuteur().getNom() + ", en " + reference.getLangue());
    }

    public Oeuvre getOeuvre() {
        return reference;
    }

    public void afficher() {
        System.out.print("Un exemplaire de ");
        reference.afficher();
    }

}

class Bibliotheque {
    private String nom;
    private ArrayList<Exemplaire> exemplaires = new ArrayList<Exemplaire>();

    public Bibliotheque(String nomBiblio) {
        nom = nomBiblio;
        System.out.println("La bibliothèque " + nom + " est ouverte !");
    }

    public String getNom() {
        return nom;
    }

    public int getNbExemplaires() {
        return exemplaires.size();
    }

    public void stocker(Oeuvre oeuvre, int quantite) {
        for (int i = 0; i < quantite; ++i) {
            Exemplaire reference = new Exemplaire(oeuvre);
            exemplaires.add(reference);
        }
    }

    public void stocker(Oeuvre oeuvre) {
        Exemplaire reference = new Exemplaire(oeuvre);
        exemplaires.add(reference);
    }

    public ArrayList<Exemplaire> listerExemplaires(String langue) {
        ArrayList<Exemplaire> exemplairesLangue = new ArrayList<Exemplaire>();
        for (int i = 0; i < exemplaires.size(); ++i) {
            Exemplaire reference = exemplaires.get(i);
            if (reference.getOeuvre().getLangue().equals(langue)) {
                exemplairesLangue.add(reference);
            }
        }
        return exemplairesLangue;
    }

    public ArrayList<Exemplaire> listerExemplaires() {
        ArrayList<Exemplaire> exemplairesLangue = new ArrayList<Exemplaire>();
        for (int i = 0; i < exemplaires.size(); ++i) {
            Exemplaire reference = exemplaires.get(i);
            exemplairesLangue.add(reference);

        }
        return exemplairesLangue;
    }

    public int compterExemplaires(Oeuvre oeuvre) {
        int compteur = 0;
        for (int i = 0; i < exemplaires.size(); ++i) {
            Exemplaire reference = exemplaires.get(i);
            if (reference.getOeuvre().getTitre().equals(oeuvre.getTitre())) {
                ++compteur;
            }
        }
        return compteur;
    }

    public void afficherAuteur(boolean prix) {
        for (int i = 0; i < exemplaires.size(); ++i) {
            Exemplaire reference = exemplaires.get(i);
            if (reference.getOeuvre().getAuteur().getPrix() == prix) {
                System.out.println(reference.getOeuvre().getAuteur().getNom());
            }
        }
    }

    public void afficherAuteur() {
        for (int i = 0; i < exemplaires.size(); ++i) {
            Exemplaire reference = exemplaires.get(i);
            if (reference.getOeuvre().getAuteur().getPrix() == true) {
                System.out.println(reference.getOeuvre().getAuteur().getNom());
            }
        }
    }

}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}
