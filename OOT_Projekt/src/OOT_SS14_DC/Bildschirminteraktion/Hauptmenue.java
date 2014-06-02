package OOT_SS14_DC.Bildschirminteraktion;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import OOT_SS14_DC.Spieler.Spieler;
/**
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 *
 */
public class Hauptmenue {

	public static LinkedList<Spieler> highscore = new LinkedList<Spieler>();
	private static Scanner eingabe;

	/**
	 * Gibt die Spielregeln aus.
	 */
	public static void regelnAusgeben() {
        System.out.println("Ziel:\n"
                + "Ziel ist es, die Spielfiguren in den gegenueberliegenden "
                + "Bereich zu befoerdern.\nGewonnen hat derjenige, der dies "
                + "als erstes geschafft hat.\n\n"
                + "Spielregeln:\n"
                + "1. Der startende Spieler wird ausgelost. \n"
                + "2. Jeder Spieler hat pro Runde einen Spielzug, d.h. er darf "
                + "eine Figur bewegen.\n"
                + "3. Die Spieler wechseln sich gegen den Uhrzeigersinn ab. \n"
                + "4. Eine Figur kann auf ein benachbartes freies Feld springen oder \n"
                + "   beliebige benachbarte Figuren in einer Linie überspringen, \n"
                + "   d.h. sowohl eigene als auch gegnerische Figuren. \n"
                + "   Voraussetzung dafür ist ein freies Feld am Ende des Sprunges.\n"
                + "5. Sind ab diesem Feld weitere Spruenge über andere Figuren moeglich, \n"
                + "   ist es erlaubt im aktuellen Spielzug dieselbe Figur erneut springen zu lassen.\n"
                + "6. Es sind nur Zuege in Zielrichtung und nicht in Startrichtung erlaubt. \n"
                + "7. Ist dem Spieler kein Zug moeglich, muss er eine Runde aussetzen.\n");

	}

	/**
	 * Spiel wird gestartet,
	 * der Gewinner im Anschluss in die Highscoreliste hinzugefuegt.
	 */
	public static void spielStarten() {
	    System.out.println("Wie viele Spieler? (2-4)"+ 
                "\n Beliebige Taste fuer 2 Spieler.");
	    int anzahl;
        try {
            anzahl = eingabe.nextInt();
        } catch (InputMismatchException ex) {
            anzahl = 2;
        }
        if(anzahl<2 || anzahl>4) {
            anzahl = 2;
        }
        System.out.println(anzahl + " Spieler gewaehlt.");
		Game spiel = new Game(anzahl);
		highscore.add(spiel.runGame());
	}
	
	/**
	 * Highscore-Liste wird ausgegeben
	 */
	public static void printHighscore() {
		System.out.println(highscore);
	}
	
	/**
	 * Hauptmenue mit Auswahlmoeglichkeiten
	 */
	public static void printMenue() {
	    System.out.println( "~ ~ ~ ~ HAUPTMENU ~ ~ ~ ~ \n" +
	            "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~        \n \n" +

	            "------------------------------    \n" +
	            "Geben Sie ein:                 \n" +
	            "------------------------------    \n" +
	            "exit  |  Spiel beenden              \n" +
	            "------------------------------    \n" +
	            "1     |  Spielregeln ausgeben       \n" +
	            "------------------------------    \n" +
	            "2     |  Highscore ausgeben         \n" +
	            "------------------------------    \n" +   
	            "------------------------------    \n" +
	            "beliebige Taste:               \n" +   
	            "------------------------------    \n" +
	            "Spiel starten                  \n \n" +
	                
	            " ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~     \n" +
	            "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
	}
	
	
	public static void main(String[] args) {
	    eingabe = new Scanner(System.in);

		
		String auswahl = "";
		
		while (!auswahl.equals("exit")) {
		    printMenue();
		    auswahl = eingabe.next();
			
			switch (auswahl){
			case "exit":
			break;
			case "1":
				regelnAusgeben();
			break;
			case "2":
				printHighscore();
			break;
			default:
				spielStarten();
			}
			
		}
		
		eingabe.close();
	}

}