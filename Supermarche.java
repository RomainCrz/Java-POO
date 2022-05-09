import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Supermarche {
    public static void main(String[] args) {
        // Les articles vendus dans le supermarché
        Article choufleur = new Article("Chou-fleur extra", 3.50, false);
        Article roman = new Article("Les malheurs de Sophie", 16.50, true);
        Article camembert = new Article("Cremeux 100%MG", 5.80, false);
        Article cdrom = new Article("C++ en trois jours", 48.50, false);
        Article boisson = new Article("Petit-lait", 2.50, true);
        Article petitspois = new Article("Pois surgeles", 4.35, false);
        Article poisson = new Article("Sardines", 6.50, false);
        Article biscuits = new Article("Cookies de grand-mere", 3.20, false);
        Article poires = new Article("Poires Williams", 4.80, false);
        Article cafe = new Article("100% Arabica", 6.90, true);
        Article pain = new Article("Pain d'epautre", 6.90, false);
        // Les caddies du supermarché
        Caddie caddie1 = new Caddie();
        Caddie caddie2 = new Caddie();
        Caddie caddie3 = new Caddie();
        // Les caisses du supermarché
        // le premier argument est le numero de la caisse
        // le second argument est le montant initial de la caisse.
        Caisse caisse1 = new Caisse(1, 0.0);
        Caisse caisse2 = new Caisse(2, 0.0);
        // les clients font leurs achats
        // le second argument de la méthode remplir
        // correspond à une quantité
        // remplissage du 1er caddie
        caddie1.remplir(choufleur, 2);
        caddie1.remplir(cdrom, 1);
        caddie1.remplir(biscuits, 4);
        caddie1.remplir(boisson, 6);
        caddie1.remplir(poisson, 2);
        // remplissage du 2eme caddie
        caddie2.remplir(roman, 1);
        caddie2.remplir(camembert, 1);
        caddie2.remplir(petitspois, 2);
        caddie2.remplir(poires, 2);
        // remplissage du 3eme caddie
        caddie3.remplir(cafe, 2);
        caddie3.remplir(pain, 1);
        caddie3.remplir(camembert, 2);
        // Les clients passent à la caisse
        caisse1.scanner(caddie1);
        caisse1.scanner(caddie2);
        caisse2.scanner(caddie3);
        caisse1.totalCaisse();
        caisse2.totalCaisse();

    }

}

class Article {
    private String nom;
    private double prix;
    private boolean solde;

    public Article(String nomArticle, double prixArticle, boolean soldeArticle) {
        nom = nomArticle;
        prix = prixArticle;
        solde = soldeArticle;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public boolean getSolde() {
        return solde;
    }

}

class Achat {
    private Article article;
    private int quantite;
    private double prix;

    public Achat(Article articleRayon, int quantiteAchetee) {
        article = articleRayon;
        quantite = quantiteAchetee;

    }

    public void afficheAchat() {
        if (article.getSolde()) {
            prix = article.getPrix() / 2;
            System.out.println(article.getNom() + " : " + article.getPrix() + " x " + quantite + "= " + prix * quantite
                    + " euros" + " (1/2 prix)");
        } else {
            prix = article.getPrix();
            System.out.println(article.getNom() + " : " + article.getPrix() + " x " + quantite + " = " + prix * quantite
                    + " euros");
        }
    }

    public double getPrixAchat() {
        return prix * quantite;
    }

    // public void montrerAchat(){
    // if(article.getSolde()){
    // System.out.print(article.getNom()+" : "+ article.getPrix()+" x "+quantite+"
    // euros"+" (1/2 prix)");
    // } else {
    // System.out.print(article.getNom()+" : "+ article.getPrix()+" x "+quantite+"
    // euros");
    // }
    // }

}

class Caddie {
    private ArrayList<Achat> achats = new ArrayList<Achat>();

    public void remplir(Article articleRayon, int quantité) {
        Achat achat = new Achat(articleRayon, quantité);
        achats.add(achat);
    }

    public ArrayList<Achat> contenuCaddie() {
        return achats;
    }
}

class Caisse {
    private int numCaisse;
    private double montantIni;
    private double montantTotal = montantIni;
    private ArrayList<Achat> sommeCaddie = new ArrayList<Achat>();

    public Caisse(int num, double montant) {
        numCaisse = num;
        montantIni = montant;
    }

    public double scanner(Caddie caddie) {
        montantIni = 0;
        System.out.println("=========================================");
        Date dateCourante = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yy");
        System.out.println(formatDate.format(dateCourante));
        System.out.println("Caisse numero " + numCaisse);
        System.out.println();

        sommeCaddie = caddie.contenuCaddie();
        for (int i = 0; i < sommeCaddie.size(); ++i) {
            sommeCaddie.get(i).afficheAchat();
            montantIni += sommeCaddie.get(i).getPrixAchat();
            // System.out.println("[Montant à l'afficheur] " + montantIni);

        }
        System.out.println("Montant à payer : " + montantIni + " euros");
        System.out.println("=========================================");
        montantTotal += montantIni;
        return montantTotal;
    }

    public void totalCaisse() {
        System.out.println("La caisse numero " + numCaisse + " a encaissé " + montantTotal + " euros aujourd'hui");
        ;

    }
}
