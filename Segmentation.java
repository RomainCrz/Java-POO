import java.util.Scanner;

public class Segmentation {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String phrase;
        System.out.println("Entrez une chaine :");
        phrase = scanner.nextLine();
        TokenizableString toToken = new TokenizableString(phrase);
        toToken.tokenize();
    }
}

class TokenizableString {
    String contenu;
    private int from;
    private int len = 0;

    public TokenizableString(String phrase) {
        contenu = phrase;

    }

    public boolean nextToken() {

        int compteur = len;
        char c = contenu.charAt(compteur);
        while (Character.isSpaceChar(c) && compteur < contenu.length() - 1) {
            ++compteur;
            c = contenu.charAt(compteur);
        }
        from = compteur;
        // System.out.println("from " + from);

        while (!Character.isSpaceChar(c) && compteur < contenu.length() - 1) {
            ++compteur;
            c = contenu.charAt(compteur);
        }
        len = compteur;
        int longueur = len - from;
        // System.out.println("len " + len);

        if (longueur > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void tokenize() {
        System.out.println("Les mots de \"" + contenu + " \" sont : ");
        while (nextToken()) {
            System.out.println("'" + contenu.substring(from, len) + "'");
        }
    }

}