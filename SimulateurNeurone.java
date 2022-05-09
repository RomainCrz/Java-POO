import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Position {
    private double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Neurone {
    private Position position;
    private double signal;
    private double attenuation;
    private ArrayList<Neurone> connexions = new ArrayList<Neurone>();

    public Neurone(Position p, double d) {
        position = p;
        attenuation = d;
        signal = 0;
    }

    public Position getPosition() {
        return position;
    }

    public int getNbConnexions() {
        return connexions.size();
    }

    public Neurone getConnexion(int index) {
        return connexions.get(index);
    }

    public double getAttenuation() {
        return attenuation;
    }

    public double getSignal() {
        return signal;
    }

    public void connexion(Neurone n) {
        connexions.add(n);
    }

    public void recoitStimulus(double stimulus) {
        signal = stimulus * attenuation;
        envoieStimulus();

    }

    public void envoieStimulus() {
        for (int i = 0; i < connexions.size(); i++) {
            connexions.get(i).recoitStimulus(signal);
        }
    }

    public void setSignal(double s) {
        signal = s;
    }

    public String toString() {
        String s = "Le neurone en position " + position.toString() + " avec attenuation " + attenuation;
        if (connexions.size() > 0) {
            s += " en connexion avec \n";
            for (int i = 0; i < connexions.size(); i++) {
                s += "  - un neurone en position " + connexions.get(i).getPosition().toString() + "\n";
            }
        } else {
            s += " sans connexion";
        }
        return s;
    }

}

class NeuroneCumulatif extends Neurone {
    public NeuroneCumulatif(Position p, double d) {
        super(p, d);
    }

    public void recoitStimulus(double stimulus) {
        setSignal(getSignal() + (stimulus * getAttenuation()));
        super.envoieStimulus();
    }
}

class Cerveau {
    private ArrayList<Neurone> cerveau = new ArrayList<Neurone>();

    public int getNbNeurones() {
        return cerveau.size();
    }

    public Neurone getNeurone(int index) {
        return cerveau.get(index);
    }

    public void ajouterNeurone(Position pos, double attenuation) {
        cerveau.add(new Neurone(pos, attenuation));
    }

    public void ajouterNeuroneCumulatif(Position pos, double attenuation) {
        cerveau.add(new NeuroneCumulatif(pos, attenuation));
    }

    public void stimuler(int index, double stimulus) {
        cerveau.get(index).recoitStimulus(stimulus);
    }

    public double sonder(int index) {
        return cerveau.get(index).getSignal();
    }

    public void creerConnexions() {
        for (int i = 0; i < cerveau.size(); i++) {
            if (i < cerveau.size() - 2) {
                if (i % 2 == 0) {
                    cerveau.get(i).connexion(cerveau.get(i + 1));
                    cerveau.get(i).connexion(cerveau.get(i + 2));
                } else {
                    cerveau.get(i).connexion(cerveau.get(i + 1));
                    cerveau.get(i + 1).connexion(cerveau.get(i + 2));
                }
            }
        }
    }

    public String toString() {
        String s = "*----------*\n\n";
        s += "Le cerveau contient " + getNbNeurones() + " neurones(s)\n";
        for (int i = 0; i < cerveau.size(); i++) {
            s += cerveau.get(i).toString() + "\n";
            if (i == cerveau.size() - 1) {
                s += "\n";
            }
        }
        s += "*----------*\n\n";
        return s;
    }
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));

        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0, 0), 0.5);
        cerveau.ajouterNeurone(new Position(0, 1), 0.2);
        cerveau.ajouterNeurone(new Position(1, 0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1, 1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
