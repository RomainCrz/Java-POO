public class Poeme {
    public static void main(String[] args) {

        Fleur f1 = new Fleur("Violette", "bleu");
        Fleur f2 = new Fleur(f1);
        System.out.print("dans un cristal ");
        f2.eclore();
        System.out.print("ne laissant plus ");
        System.out.println(f1);
        System.out.println(f2);

    }
}

class Fleur {
    private String type;
    private String couleur;

    public Fleur(String unType, String uneCouleur) {
        type = unType;
        couleur = uneCouleur;
        System.out.println(type + " fraichement cueillie");
    }

    public Fleur(Fleur f) {
        type = f.type;
        couleur = f.couleur;
        System.out.print("Fragile corolle taillée ");
    }

    public void eclore() {
        System.out.println("veiné de " + couleur);
    }

    public String toString() {
        return "qu'un simple souffle";
    }
}