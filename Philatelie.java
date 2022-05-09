import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code = CODE_DEFAUT;
    private int anneeEmission = ANNEE_COURANTE;
    private String paysOrigine = PAYS_DEFAUT;
    private double valeurFaciale = VALEUR_TIMBRE_DEFAUT;

    public Timbre(String codeTimbre, int anneeTimbre, String paysTimbre, double valeurTimbre) {
        code = codeTimbre;
        anneeEmission = anneeTimbre;
        paysOrigine = paysTimbre;
        valeurFaciale = valeurTimbre;
    }

    public Timbre(String codeTimbre) {
        code = codeTimbre;
    }

    public Timbre(String codeTimbre, int anneeTimbre) {
        code = codeTimbre;
        anneeEmission = anneeTimbre;
    }

    public Timbre(String codeTimbre, int anneeTimbre, String paysTimbre) {
        code = codeTimbre;
        anneeEmission = anneeTimbre;
        paysOrigine = paysTimbre;
    }

    public Timbre() {

    }

    public double vente() {
        if (ANNEE_COURANTE - anneeEmission < 5) {
            return valeurFaciale;
        } else {
            return valeurFaciale * (ANNEE_COURANTE - anneeEmission) * 2.5;
        }
    }

    public String toString() {
        return "Timbre de code " + code + " datant de " + anneeEmission + " (provenance " + paysOrigine
                + ") ayant pour valeur faciale " + valeurFaciale + " francs";
    }

    public int age() {
        return ANNEE_COURANTE - anneeEmission;
    }

    public String getCode() {
        return code;
    }

    public int getAnnee() {
        return anneeEmission;
    }

    public String getPays() {
        return paysOrigine;
    }

    public double getValeurFaciale() {
        return valeurFaciale;
    }
}

class Rare extends Timbre {
    private int exemplaires;

    public Rare(String codeTimbre, int anneeTrimbe, String paysTimbre, double valeurTimbre, int exemplairesTimbre) {
        super(codeTimbre, anneeTrimbe, paysTimbre, valeurTimbre);
        exemplaires = exemplairesTimbre;
    }

    public Rare(String codeTimbre, int exemplairesTimbre) {
        super(codeTimbre);
        exemplaires = exemplairesTimbre;
    }

    public Rare(String codeTimbre, int anneeTrimbe, int exemplairesTimbre) {
        super(codeTimbre, anneeTrimbe);
        exemplaires = exemplairesTimbre;
    }

    public Rare(String codeTimbre, int anneeTrimbe, String paysTimbre, int exemplairesTimbre) {
        super(codeTimbre, anneeTrimbe, paysTimbre);
        exemplaires = exemplairesTimbre;
    }

    public Rare(int exemplairesTimbre) {
        super();
        exemplaires = exemplairesTimbre;
    }

    public int getExemplaires() {
        return exemplaires;
    }

    public String toString() {
        return "Timbre de code " + getCode() + " datant de " + getAnnee() + " (provenance " + getPays()
                + ") ayant pour valeur faciale " + getValeurFaciale() + " francs" + "\n" +
                "Nombre d'exemplaires -> " + exemplaires;
    }

    public double vente() {
        double prixBase;
        if (exemplaires < 100) {
            prixBase = PRIX_BASE_1;
        } else if (exemplaires < 1000) {
            prixBase = PRIX_BASE_2;
        } else {
            prixBase = PRIX_BASE_3;
        }
        double prixTimbre = prixBase * (ANNEE_COURANTE - getAnnee()) / 10;
        return prixTimbre;
    }
}

class Commemoratif extends Timbre {
    public Commemoratif(String codeTimbre, int anneeTrimbe, String paysTimbre, double valeurTimbre) {
        super(codeTimbre, anneeTrimbe, paysTimbre, valeurTimbre);
    }

    public Commemoratif() {
        super();
    }

    public Commemoratif(String codeTimbre) {
        super(codeTimbre);
    }

    public Commemoratif(String codeTimbre, int anneeTrimbe) {
        super(codeTimbre, anneeTrimbe);
    }

    public Commemoratif(String codeTimbre, int anneeTrimbe, String paysTimbre) {
        super(codeTimbre, anneeTrimbe, paysTimbre);
    }

    public String toString() {
        return "Timbre de code " + getCode() + " datant de " + getAnnee() + " (provenance " + getPays()
                + ") ayant pour valeur faciale " + getValeurFaciale() + " francs" + "\n" +
                "Timbre celebrant un evenement";
    }

    public double vente() {
        double prixBase = super.vente();
        double prixTimbre = prixBase * 2;
        return prixTimbre;
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);

        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}
