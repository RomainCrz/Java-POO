import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme à partir d'ici.
 *******************************************/

// " -> %.1f pour cent des électeurs\n"

class Postulant {
    private String nom;
    private int nbElecteurs = 0;

    public Postulant(String s, int n) {
        nom = s;
        nbElecteurs = n;
    }

    public Postulant(String s) {
        nom = s;
    }

    public Postulant(Postulant p) {
        this.nom = p.nom;
        this.nbElecteurs = p.nbElecteurs;
    }

    public void elect() {
        ++nbElecteurs;
    }

    public void init() {
        nbElecteurs = 0;
    }

    public int getVotes() {
        return nbElecteurs;
    }

    public String getNom() {
        return nom;
    }
}

class Scrutin {
    private ArrayList<Postulant> liste = new ArrayList<Postulant>();
    private ArrayList<Vote> votes = new ArrayList<Vote>();
    private double nbVoteMax;
    private int jour;
    private boolean init = true;

    public Scrutin(ArrayList<Postulant> scrutin, int vote, int j, boolean init) {
        for (int i = 0; i < scrutin.size(); i++) {
            liste.add(scrutin.get(i));
        }
        nbVoteMax = vote;
        jour = j;
        if (init) {
            for (int i = 0; i < liste.size(); i++) {
                liste.get(i).init();
            }
        }
    }

    public Scrutin(ArrayList<Postulant> scrutin, int vote, int j) {
        for (int i = 0; i < scrutin.size(); i++) {
            liste.add(scrutin.get(i));
        }
        nbVoteMax = vote;
        jour = j;
        for (int i = 0; i < liste.size(); i++) {
            liste.get(i).init();
        }

    }

    public int calculerVotants() {
        int nbVotants = 0;
        for (int i = 0; i < liste.size(); i++) {
            nbVotants += liste.get(i).getVotes();
        }
        return nbVotants;
    }

    public String gagnant() {
        int superieur = 0;
        String gagnant = "";
        for (int i = 0; i < liste.size(); i++) {
            if (superieur <= liste.get(i).getVotes()) {
                superieur = liste.get(i).getVotes();
                gagnant = liste.get(i).getNom();
            }
        }
        return gagnant;
    }

    private double tauxParticipation() {
        return (calculerVotants() / nbVoteMax) * 100;
    }

    public void resultats() {
        if (calculerVotants() > 0) {
            System.out.printf("Taux de participation ->  %.1f pour cent", tauxParticipation());
            System.out.println();
            System.out.println("Nombre effectif de votants -> " + (int) (calculerVotants()));
            System.out.println("Le chef choisi est -> " + gagnant());
            System.out.println();
            System.out.println("Répartition des electeurs");
            for (int i = 0; i < liste.size(); i++) {
                double nbVote = liste.get(i).getVotes();
                System.out.printf(liste.get(i).getNom() + " -> %.1f" + " pour cent des électeurs",
                        (nbVote / calculerVotants()) * 100);
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("Scrutin annulé, pas de votants");
        }

    }

    public void compterVotes() {
        for (int i = 0; i < votes.size(); i++) {
            String nom = votes.get(i).getPostulant();
            if (!votes.get(i).estInvalide()) {
                for (int j = 0; j < liste.size(); j++) {
                    String nomListe = liste.get(j).getNom();
                    if (nom.equals(nomListe)) {
                        liste.get(j).elect();
                    }
                }
            }
        }
    }

    public void simuler(double tauxParticipation, int jourVote) {
        double nbVotantsInter = nbVoteMax * tauxParticipation;
        int nbVotants = (int) (nbVotantsInter);
        for (int i = 0; i < nbVotants; i++) {
            int candNum = Utils.randomInt(liste.size() - 1);
            if (i % 3 == 0) {
                votes.add(new BulletinElectronique(liste.get(candNum).getNom(), jourVote, jour));
            } else if (i % 3 == 1) {
                boolean signe;
                if (i % 2 == 0) {
                    signe = false;
                } else {
                    signe = true;
                }
                votes.add(new BulletinPapier(liste.get(candNum).getNom(), jourVote, jour, signe));
            } else {
                boolean signe;
                if (i % 2 == 0) {
                    signe = false;
                } else {
                    signe = true;
                }
                votes.add(new BulletinCourrier(liste.get(candNum).getNom(), jourVote, jour, signe));
            }
            System.out.println(votes.get(i).toString());
        }

    }

}

abstract class Vote {
    private String nom;
    private int jourDepot;
    private int jourLimite;

    public Vote(String s, int j, int j2) {
        nom = s;
        jourDepot = j;
        jourLimite = j2;
    }

    abstract boolean estInvalide();

    public String getPostulant() {
        return nom;
    }

    public int getDate() {
        return jourDepot;
    }

    public int getDateLimite() {
        return jourLimite;
    }

    public String toString() {
        if (estInvalide()) {
            return " pour " + nom + " -> invalide";
        } else {
            return " pour " + nom + " -> valide";
        }
    }

}

class BulletinElectronique extends Vote implements CheckBulletin {
    public BulletinElectronique(String s, int j, int j2) {
        super(s, j, j2);
    }

    public boolean estInvalide() {
        if (!checkDate()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDate() {
        return getDate() <= getDateLimite() - 2;
    }

    public String toString() {
        return "vote electronique" + super.toString();
    }
}

class BulletinPapier extends Vote {
    private boolean signe;

    public BulletinPapier(String s, int j, int j2, boolean signe) {
        super(s, j, j2);
        this.signe = signe;
    }

    public boolean estInvalide() {
        if (!signe) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "vote par bulletin papier" + super.toString();
    }
}

class BulletinCourrier extends BulletinPapier implements CheckBulletin {
    public BulletinCourrier(String s, int j, int j2, boolean signe) {
        super(s, j, j2, signe);
    }

    public boolean estInvalide() {
        if (super.estInvalide()) {
            return true;
        } else if (!checkDate()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDate() {
        return getDate() <= getDateLimite();
    }

    public String toString() {
        return "envoie par courrier d'un " + super.toString();
    }
}

interface CheckBulletin {
    boolean checkDate();
}

/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // génère un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        // FIN TEST 2

    }
}
