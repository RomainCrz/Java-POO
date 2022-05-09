class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private int poids;
    private String couleur;
    private int age;
    private boolean clonee;
    private int esperanceVie = ESPERANCE_VIE_DEFAUT;

    public Souris(int poidsSouris, String couleurSouris, int ageSouris, int ev) {
        poids = poidsSouris;
        couleur = couleurSouris;
        age = ageSouris;
        esperanceVie = ev;
        System.out.println("Une nouvelle souris !");

    }

    public Souris(int poidsSouris, String couleurSouris, int ageSouris) {
        poids = poidsSouris;
        couleur = couleurSouris;
        age = ageSouris;
        System.out.println("Une nouvelle souris !");

    }

    public Souris(int poidsSouris, String couleurSouris) {
        poids = poidsSouris;
        couleur = couleurSouris;
        System.out.println("Une nouvelle souris !");
    }

    public Souris(Souris autreSouris) {
        poids = autreSouris.poids;
        couleur = autreSouris.couleur;
        age = autreSouris.age;
        esperanceVie = autreSouris.esperanceVie * 4 / 5;
        clonee = true;
        System.out.println("Clonage d'une souris !");
    }

    public String toString() {
        if (!clonee) {
            return "Une souris " + couleur + " de " + age + " mois et pesant " + poids + " grammes";
        } else {
            return "Une souris " + couleur + ", clonee, de " + age + " mois et pesant " + poids + " grammes";
        }

    }

    public void vieillir() {
        if (age < esperanceVie) {
            age += 1;
            if (clonee && age > esperanceVie / 2) {
                couleur = "verte";
            }
        } else {
            if (clonee) {
                couleur = "verte";
            }
        }
    }

    public void evolue() {
        age = esperanceVie;
        vieillir();
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
