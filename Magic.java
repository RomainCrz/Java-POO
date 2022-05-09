import java.util.ArrayList;

public class Magic {
    public static void main(String[] args) {
        JeuMagic maMain = new JeuMagic(10);
        maMain.piocher(new Terrain('b'));
        maMain.piocher(new Creature(6, "Golem", 4, 6));
        maMain.piocher(
                new Sortilege(1, "Croissance Gigantesque", "La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));
        System.out.println("Là, j'ai en stock :");
        maMain.afficher();
        maMain.joue();
    }

}

class JeuMagic {
    private int carteMax;
    private ArrayList<Carte> mesCartes = new ArrayList<Carte>();

    public JeuMagic(int max) {
        carteMax = max;
        System.out.println("On change de main");
    }

    public void piocher(Carte carte) {
        if (mesCartes.size() < carteMax) {
            mesCartes.add(carte);
        }
    }

    public void afficher() {
        for (int i = 0; i < mesCartes.size(); i++) {
            System.out.println(mesCartes.get(i).afficher());
        }
    }

    public void joue() {
        System.out.println("Je joue une carte");
        System.out.println("La carte jouée est : ");
        System.out.println(mesCartes.get(0).afficher());
        mesCartes.set(0, null);
    }
}

abstract class Carte {
    private int cout = 0;

    public Carte(int cout) {
        this.setCout(cout);
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public Carte() {
    }

    abstract String afficher();
}

class Terrain extends Carte {
    private String couleur;

    public Terrain(char c) {
        switch (c) {
            case 'B':
                couleur = "blanc";
                break;

            case 'b':
                couleur = "bleu";
                break;
            case 'n':
                couleur = "noir";
                break;
            case 'r':
                couleur = "rouge";
                break;
            case 'v':
                couleur = "vert";
                break;
        }

        System.out.println("Un nouveau terrain.");
    }

    public String getCouleur() {
        return couleur;
    }

    public String afficher() {
        return "Un terrain " + couleur;
    }
}

class Creature extends Carte {
    private String nom;
    private int degat;
    private int vie;

    public Creature(int cout, String n, int degat, int vie) {
        super(cout);
        nom = n;
        this.degat = degat;
        this.vie = vie;
        System.out.println("Une nouvelle creature.");
    }

    public String getNom() {
        return nom;
    }

    public int getDegat() {
        return degat;
    }

    public int getVie() {
        return vie;
    }

    public String afficher() {
        return "Une créature " + nom + " " + degat + "/" + vie;
    }

}

class Sortilege extends Carte {
    private String nom;
    private String explication;

    public Sortilege(int cout, String n, String e) {
        super(cout);
        nom = n;
        explication = e;
        System.out.println("Un sortilège de plus.");
    }

    public String getNom() {
        return nom;
    }

    public String getExplication() {
        return explication;
    }

    public String afficher() {
        return "Un sortilège " + nom;
    }
}
