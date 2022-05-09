/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Patient {
    private int p = 0;
    private double poids = p;
    private int t = 0;
    private double taille = t;

    public void init(double entreePoids, double entreeTaille) {
        if (entreePoids >= 0 && entreeTaille >= 0) {
            poids = entreePoids;
            taille = entreeTaille;
        }
    }

    public double poids() {
        return poids;
    }

    public double taille() {
        return taille;
    }

    public double imc() {
        double imc;
        imc = poids / (taille * taille);
        if (imc > 0) {
            return imc;
        } else {
            return 0.0;
        }
    }

    public void afficher() {
        System.out.printf("Patient : %.1f kg pour %.1f m", poids(), taille());
        System.out.println();

    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Imc {
    public static void main(String[] args) {

        Patient quidam = new Patient();
        quidam.init(74.5, 1.75);
        quidam.afficher();
        System.out.println("IMC : " + quidam.imc());
        quidam.init(-2.0, 4.5);
        quidam.afficher();
    }
}
