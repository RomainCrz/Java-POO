
/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Construction {
    private int nbMaxComposants;
    private ArrayList<Composant> CompArray = new ArrayList<Composant>();

    public Construction(int nbMax) {
        nbMaxComposants = nbMax;
    }

    public int taille() {
        return CompArray.size();
    }

    public int tailleMax() {
        return nbMaxComposants;
    }

    public void ajouterComposant(Piece p, int quantite) {

        if (taille() < tailleMax()) {

            CompArray.add(new Composant(p, quantite));

        } else {
            System.out.println("Ajout de composant impossible");
        }
    }

    public String toString() {
        String construction = "";

        for (int i = 0; i < CompArray.size(); i++) {
            Composant c = CompArray.get(i);
            construction += c.getPiece().toString() + " (quantite " + c.getQuantite() + ")\n";
        }

        return construction;
    }

}

class Piece {
    private String nom;

    public Piece(String nomPiece) {
        nom = nomPiece;
    }

    public String getNom() {
        return nom;
    }

    public String toString() {
        return nom;
    }
}

class Composant {
    private int quantité;
    private Piece piece;

    public Composant(Piece nomPiece, int quantitePiece) {
        piece = nomPiece;
        quantité = quantitePiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getQuantite() {
        return quantité;
    }
}

class Simple extends Piece {
    private String orientation = "aucune";

    public Simple(String nomPiece) {
        super(nomPiece);
    }

    public Simple(String nomPiece, String orientationPiece) {
        super(nomPiece);
        orientation = orientationPiece;
    }

    public String getOrientation() {
        return orientation;
    }

    public String toString() {
        if (orientation != "aucune") {
            return getNom() + " " + orientation;
        } else {
            return getNom();
        }
    }

}

class Composee extends Piece {
    private int nbMaxComp;
    private ArrayList<Piece> liste = new ArrayList<Piece>();

    public Composee(String nomPiece, int nbMax) {
        super(nomPiece);
        nbMaxComp = nbMax;
    }

    public int taille() {
        return liste.size();
    }

    public int tailleMax() {
        return nbMaxComp;
    }

    public void construire(Piece p) {
        if (taille() < tailleMax()) {
            liste.add(p);
        } else {
            System.out.println("Construction impossible");
        }
    }

    public String toString() {
        String description = "";
        for (int i = 0; i < taille(); i++) {
            if (i == taille() - 1) {
                description += liste.get(i).toString();
            } else {
                description += liste.get(i).toString() + ",";
            }
        }
        return getNom() + " (" + description + ")";
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

class Lego {

    public static void main(String[] args) {
        // declare un jeu de construction de 10 pieces
        Construction maison = new Construction(10);

        // ce jeu a pour premier composant: 59 briques standard
        // une brique standard a par defaut "aucune" comme orientation
        maison.ajouterComposant(new Simple("brique standard"), 59);

        // declare une piece dont le nom est "porte", composee de 2 autres pieces
        Composee porte = new Composee("porte", 2);

        // cette piece composee est constituee de deux pieces simples:
        // un cadran de porte orient'e a gauche
        // un battant orient'e a gauche
        porte.construire(new Simple("cadran porte", "gauche"));
        porte.construire(new Simple("battant", "gauche"));

        // le jeu a pour second composant: 1 porte
        maison.ajouterComposant(porte, 1);

        // déclare une piece composee de 3 autres pieces dont le nom est "fenetre"
        Composee fenetre = new Composee("fenetre", 3);

        // cette piece composee est constitu'ee des trois pieces simples:
        // un cadran de fenetre (aucune orientation)
        // un volet orient'e a gauche
        // un volet orient'e a droite
        fenetre.construire(new Simple("cadran fenetre"));
        fenetre.construire(new Simple("volet", "gauche"));
        fenetre.construire(new Simple("volet", "droit"));

        // le jeu a pour troisieme composant: 2 fenetres
        maison.ajouterComposant(fenetre, 2);

        // affiche tous les composants composants (nom de la piece et quantit'e)
        // pour les pieces compos'ees : affiche aussi chaque piece les constituant
        System.out.println("Affichage du jeu de construction : ");
        System.out.println(maison);
    }
}
