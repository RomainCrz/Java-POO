public class CompObj {

}

class Rectangle {
    private double hauteur;
    private double largeur;

    public Rectangle(double largeur, double hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    public String toString() {
        return "Rectangle : \n" + " largeur = " + largeur + "\n" + " hauteur = " + hauteur;
    }

    public boolean equals(Rectangle rect) {
        if (rect != null) {
            if (getClass().equals(rect.getClass())) {
                if (getLargeur() == rect.getLargeur() && getHauteur() == rect.getHauteur()) {
                    return true;
                }
            }
        }
        return false;
    }

}

class RectangleColore2 extends Rectangle {
    private String couleur;

    public RectangleColore2(double largeur, double hauteur, String couleur) {
        super(largeur, hauteur);
        this.couleur = couleur;
    }

    public String getCouleur() {
        return couleur;
    }

    public String toString() {
        return super.toString() + "\n" + " couleur = " + couleur;
    }

    public boolean equals(RectangleColore2 rect) {
        if (rect != null) {
            if (getClass().equals(rect.getClass())) {
                if (super.equals(rect) && getCouleur().equals(rect.getCouleur())) {
                    return true;
                }
            }
        }
        return false;
    }

}

class ToStringEq {
    public static void main(String[] args) {
        System.out.println("Test 1 :");
        Rectangle rect = new Rectangle(12.5, 4.0);
        System.out.println(rect);
        System.out.println();
        System.out.println("Test 2: ");
        // le type de rect1 est RectangleColore
        // l'objet contenu dans rect1 est de type RectangleColore
        RectangleColore2 rect1 = new RectangleColore2(12.5, 4.0, "rouge");
        System.out.println(rect1);
        System.out.println();
        System.out.println("Test 3 :");
        // le type de rect2 est Rectangle
        // l'objet contenu dans rect2 est de type RectangleColore
        Rectangle rect2 = new RectangleColore2(25.0 / 2, 8.0 / 2, new String("rouge"));
        System.out.println(rect2);
        System.out.println(rect1.equals(rect2)); // 1.
        System.out.println(rect2.equals(rect1)); // 2.
        System.out.println(rect1.equals(null)); // 3.
        System.out.println(rect.equals(rect1)); // 4.
        System.out.println(rect1.equals(rect)); // 5.
    }
}
