package OOT_SS14_DC.Bildschirminteraktion;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import OOT_SS14_DC.Spieler.*;
import OOT_SS14_DC.Spielkarton.*;

/**
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 * 
 */

// Attribut eingabe entfernt. Alle Scanner entfernt. Laeuft alles ueber die
// Methoden

public class Game {

    private Queue<Spieler> teilnehmer = new LinkedList<Spieler>();

    private Spielfeld spielfeld;

    private Spieler aktuellerSpieler;

    private int anzahlSpieler;

    private LinkedList<String> symbol = new LinkedList<String>();

    private LinkedList<Ecke> freieEcken = new LinkedList<Ecke>();

    /**
     * Game-Konstruktor, es sollte jetzt die runGame()-Methode ausgefuert werden
     */
    public Game(int anzahlSpieler) {
        this.anzahlSpieler = anzahlSpieler;

        symbol.add("@");
        symbol.add("®");
        symbol.add("©");
        symbol.add("§");
        symbol.add("♠");
        symbol.add("♣");
        symbol.add("♥");
        symbol.add("♦");
        symbol.add("☺");
        symbol.add("☻");

        if (anzahlSpieler > 2) {
            freieEcken.add(Ecke.B);
            freieEcken.add(Ecke.C);
        }

        freieEcken.add(Ecke.A);
        freieEcken.add(Ecke.D);
    }

    /**
     * neuer Spieler wird erstellt
     * 
     * @param name
     *            gewaehlter Name
     * @param ecke
     *            gewaehlte Ecke
     * @return erstellter Spieler
     */
    private Mensch erstelleMensch(String name, Ecke ecke) {
        String symbol = symbolWaehlen();
        Mensch spieler = new Mensch(name, ecke, symbol);
        spielfeld.spielerSetzen(spieler.getGewaehlteEcke(), spieler);
        spieler.setZielEcke(spielfeld.zielEckeSetzen(spieler.getGewaehlteEcke()));
        return spieler;
    }

    /**
     * neuer Computerspieler wird erstellt
     * 
     * @param schwierigkeitsgrad
     * @return erstellter Spieler
     */
    private Computer erstelleKi(int schwierigkeitsgrad) {
        Computer ki;
        switch (schwierigkeitsgrad) {
        case 1:
            ki = new Ki1();
            break;
        case 2:
            ki = new Ki2();
            break;
        default:
            System.out.println("Muhaha!! Schwieriger Gegner wird erstellt.");
            ki = new Ki3();
        }
        spielfeld.spielerSetzen(ki.getGewaehlteEcke(), ki);
        ki.setZielEcke(spielfeld.zielEckeSetzen(ki.getGewaehlteEcke()));
        return ki;
    }

    /**
     * man kann aus verschiedenen Symbolen waehlen
     * 
     * @return gewaehltes Symbol
     */
    private String symbolWaehlen() {

        System.out.println("Waehlen Sie ihr Symbol (Eingabe einer Zahl)"
                + "\n Beliebige Taste fuer zufaelliges Symbol.");

        System.out.println(symbol);

        int position = 0; // Standartwert 0 - wenn es zu ungueltigen Eingaben
                          // kommt, wird hier ein Zufallswert genommen.

        // Hier Fehler abfangen!

        try {
            position = eingabeInt();
            return symbol.remove(position - 1);
        } catch (InputMismatchException | IndexOutOfBoundsException ex) {
            position = (int) (Math.random() * symbol.size());
            return symbol.remove(position);
        }
    }

    /**
     * hier wird abgefragt, wer alles mitspielt
     * 
     * @return Gewinner des aktuellen Spiels
     */
    private void spielerErstellen() {

        // Spielfeld erstellen
        spielfeld = new Spielfeld(anzahlSpieler);

        System.out.println("Wie viele davon menschlich?"
                + "\n Beliebige Taste fuer 1 Menschen.");
        int mensch;
        try {
            mensch = eingabeInt();
        } catch (InputMismatchException ex) {
            mensch = 1;
        }
        int menschenZaehler = 0;

        if (mensch > anzahlSpieler) {
            mensch = anzahlSpieler;
            System.out.println("Es koennen nicht mehr Menschen als Spieler "
                    + "mitspielen.");
        }
        System.out.println(mensch + " menschliche(r) Spieler gewaehlt.");

        for (int i = 0; i < anzahlSpieler; i++) {

            if (mensch == 0) {
                KIgenerator.setSymbol(this.symbol);
                KIgenerator.setEcke(freieEcken);
            }

            if (menschenZaehler < mensch) {
                System.out.println("Name fuer Spieler " + (i + 1));
                String name = eingabeString();
                Ecke ecke = eckeWaehlen();
                teilnehmer.add(erstelleMensch(name, ecke));
                menschenZaehler++;
                KIgenerator.setSymbol(this.symbol);
                KIgenerator.setEcke(freieEcken);
            } else {
                teilnehmer.add(erstelleKi(schwierigkeitWaehlen()));
            }
        }
    }

    @SuppressWarnings("resource")
    private int schwierigkeitWaehlen() {
        int schwierigkeit;
        System.out.println("Schwierigkeit fuer Computerspieler waehlen."
                + "\n 1 - Einfach, 2 - Mittel, 3 - Schwierig;"
                + " Bei fehlerhafte Eingabe wird Schwierig gewaehlt!");
        try {
            schwierigkeit = eingabeInt();
        } catch (InputMismatchException ex) {
            schwierigkeit = 3;
        }
        return schwierigkeit;
    }

    /**
     * Ecke wird aus Enumeration gewaehlt A B C D
     * 
     * @return gewaehlte Ecke
     */
    private Ecke eckeWaehlen() {
        Ecke gewaehlt;
        int index;

        do {
            System.out.println("Waehlen Sie eine Ecke.");
            System.out.println(freieEcken);
            String auswahl = eingabeString();
            index = indxDerEckeInListeFinden(auswahl);
        } while (index < 0);

        gewaehlt = freieEcken.remove(index);

        return gewaehlt;
    }

    /**
     * gibt den Index der gewaehlten Ecke in der freienEckenListe an --> zum
     * entfernen
     */
    private int indxDerEckeInListeFinden(String auswahl) {
        int index;
        switch (auswahl.toUpperCase()) {
        case "A":
            index = freieEcken.indexOf(Ecke.A);
            break;
        case "B":
            index = freieEcken.indexOf(Ecke.B);
            break;
        case "C":
            index = freieEcken.indexOf(Ecke.C);
            break;
        case "D":
            index = freieEcken.indexOf(Ecke.D);
            break;
        default:
            index = -1;
        }
        return index;
    }

    /**
     * 
     * @return naechster Spieler in der Reihe
     */
    private Spieler naechsterSpieler() {
        Spieler tmp = teilnehmer.poll();
        teilnehmer.add(tmp);
        return tmp;
    }

    /**
     * 
     * @return zufaelliger Spieler aus der Spielerliste
     */
    private Spieler zufaelligerSpieler() {
        int zufall = (int) (Math.random() * anzahlSpieler);

        Spieler tmp = teilnehmer.peek();

        for (int i = 0; i < zufall; i++) {
            tmp = naechsterSpieler();
        }

        return tmp;
    }

    /**
     * hier laeuft das Spiel ab (s. Kommentare)
     * 
     * @return Gewinner des aktuellen Spiels
     */
    private Spieler spielen() {
        aktuellerSpieler = zufaelligerSpieler();
        System.out.println(aktuellerSpieler);
        Feld[] aktuelleSpielsteintuete; // wird sofort ueberschrieben
        Feld gewaehlterSpielstein;
        LinkedList<Feld> alleZiele;
        Feld ziel;

        do {

            spielfeld.printSpielfeld();
            // der Spieler wird zuerst weitergezaehlt, damit die Abfrage
            // im while() funktioniert.
            aktuellerSpieler = naechsterSpieler();
            // zaehlt getaetigte Zuege weiter
            aktuellerSpieler.nächsterunde();
            // Spielfeld gibt zurueck, wo die Steine des aktuellenSpielers sind
            aktuelleSpielsteintuete = spielfeld
                    .getFeldvonSpieler(aktuellerSpieler);
            // aus diesen Steinen waehlt er einen zum Ziehen.
            System.out.println("aktuellerSpieler: " + aktuellerSpieler);
            gewaehlterSpielstein = aktuellerSpieler
                    .spielsteinWaehlen(aktuelleSpielsteintuete);
            while (gewaehlterSpielstein != null) {
                // alle moeglichen Ziele werden ermittelt
                alleZiele = spielfeld.feldersuchen(
                        gewaehlterSpielstein.getIndexZeile(),
                        gewaehlterSpielstein.getIndexSpalte());
                // die neuen Positionen werden uebergeben
                ziel = aktuellerSpieler.zielWaehlen(alleZiele);
                if (ziel == null) {
                    gewaehlterSpielstein = aktuellerSpieler
                            .spielsteinWaehlen(aktuelleSpielsteintuete);
                } else {
                    spielfeld.spielerBewegen(
                            gewaehlterSpielstein.getIndexZeile(),
                            gewaehlterSpielstein.getIndexSpalte(), null);
                    spielfeld.spielerBewegen(ziel.getIndexZeile(),
                            ziel.getIndexSpalte(), aktuellerSpieler);

                    gewaehlterSpielstein = null;

                }
            }

            // neue Spielsteinpositionen werden uebergeben
            aktuelleSpielsteintuete = spielfeld
                    .getFeldvonSpieler(aktuellerSpieler);

        } while (!aktuellerSpieler.zielErreicht(aktuelleSpielsteintuete));

        spielfeld.printSpielfeld();
        System.out.println(aktuellerSpieler + " hat mit "
                + aktuellerSpieler.getZuege() + " Zügen Gewonnen !");
        return aktuellerSpieler;
    }

    /**
     * spiel starten
     * 
     * @return Gewinner am Ende des Spiels
     */
    public Spieler runGame() {
        spielerErstellen();
        System.out.println(teilnehmer);
        Spieler gewinner = spielen();

        return gewinner;
    }

    /**
     * Liest String von der Tastatur
     * 
     * @return Tastatureingabe
     */

    @SuppressWarnings("resource")
    public String eingabeString() {
        Scanner myScanner = new Scanner(System.in);
        return myScanner.nextLine();
    }

    /**
     * Liest int von der Tastatur
     * 
     * @return Tastatureingabe
     */

    @SuppressWarnings("resource")
    public int eingabeInt() {
        Scanner myScanner = new Scanner(System.in);
        return myScanner.nextInt();
    }
}
