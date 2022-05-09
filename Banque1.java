public class Banque1 {
    public static void main(String[] args) {
        Client un = new Client("Pedro", "Geneve", 1000, 2000, true);
        Client deux = new Client("Alexandra", "Lausanne", 3000, 4000, false);
        Comptes comptes = new Comptes();
        System.out.println("Donnees avant le bouclement des comptes : ");
        un.getInfos();
        deux.getInfos();
        System.out.println("Donnees après le bouclement des comptes : ");
        un.setBouclement(comptes.getTauxP(), comptes.getTauxE());
        deux.setBouclement(comptes.getTauxP(), comptes.getTauxE());
        un.getInfos();
        deux.getInfos();

    }

}

class Client {
    private String nom;
    private String ville;
    private double prive;
    private double epargne;
    private boolean masculin;

    public Client(String nomClient, String villeClient, int soldePrive, int soldeEpargne, boolean sexe) {
        nom = nomClient;
        ville = villeClient;
        prive = soldePrive;
        epargne = soldeEpargne;
        masculin = sexe;
    }

    public void getInfos() {
        if (masculin) {
            System.out.println("    Client " + nom + " de " + ville);
        } else {
            System.out.println("    Cliente " + nom + " de " + ville);
        }
        System.out.println("        Compte prive : " + prive + " euros");
        System.out.println("        Compte d'epargne : " + epargne + " euros");
    }

    public void setBouclement(double tauxPrive, double tauxEpargne) {
        prive += prive * tauxPrive;
        epargne += epargne * tauxEpargne;
    }
}

class Comptes {
    private double tauxPrive = 0.01;
    private double tauxEpargne = 0.02;

    public double getTauxP() {
        return tauxPrive;
    }

    public double getTauxE() {
        return tauxEpargne;
    }

}