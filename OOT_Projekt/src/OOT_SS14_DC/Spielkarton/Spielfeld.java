package OOT_SS14_DC.Spielkarton;

import java.util.LinkedList;

import OOT_SS14_DC.Spieler.Spieler;

/**
 * Die Klasse <code>Spielfeld<code> erstellt das Spielfeld auf dem die Spieler 
 * spielen können. Es gibt vier Ecken, die nach Spieleranzahl unterschiedliche 
 * Anzahl an Felder besitzen können. Die Spieler werden auf die gewünschten 
 * Ecken platziert.
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 * 
 */
public class Spielfeld {

	public Feld[][] feld;
	private int spielerAnzahl;
	protected Feld[] eckeA;
	protected Feld[] eckeB;
	protected Feld[] eckeC;
	protected Feld[] eckeD;
	private LinkedList<Feld> zugMoeglichkeiten;

	/**
	 * <pre>
	 * Spielfeld(int spielerAnzahl)
	 * </pre>
	 * 
	 * Das Spielfeld wird erstellt.
	 * 
	 * @param spielerAnzahl
	 *            Anzahl der Spieler
	 */
	public Spielfeld(int spielerAnzahl) {
		feld = new Feld[16][16];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				feld[i][j] = new Feld();
				feld[i][j].setIndexSpalte(j);
				feld[i][j].setIndexZeile(i);
			}
		}
		eckeSetzen(Ecke.A, spielerAnzahl);
		eckeSetzen(Ecke.B, spielerAnzahl);
		eckeSetzen(Ecke.C, spielerAnzahl);
		eckeSetzen(Ecke.D, spielerAnzahl);

		this.spielerAnzahl = spielerAnzahl;
	}

	/**
	 * <pre>
	 * spielerSetzen(Ecke ecke, Spieler spieler)
	 * </pre>
	 * 
	 * Die Spielsteine des Spielers werden in die gewünschte Ecke platziert.
	 * 
	 * @param ecke
	 *            Die ausgewählte Ecke
	 * @param spieler
	 *            Der aktuelle Spieler
	 */
	public void spielerSetzen(Ecke ecke, Spieler spieler) {
		spielerSetzen(ecke, spielerAnzahl, spieler);
	}

	/**
	 * <pre>
	 * spielerSetzen(Ecke ecke,int spielerAnzahl,Spieler spieler)
	 * </pre>
	 * 
	 * Die Spielsteine des Spielers werden in die gewünschte Ecke platziert.
	 * 
	 * @param ecke
	 *            Die ausgewählte Ecke
	 * @param spielerAnzahl
	 *            Anzahl der Spieler
	 * @param spieler
	 *            Der aktuelle Spieler
	 */
	private void spielerSetzen(Ecke ecke, int spielerAnzahl, Spieler spieler) {
		Feld[] eckePosition;
		eckePosition = new Feld[eckeA.length];
		if (ecke == Ecke.A) {
			eckePosition = eckeA.clone();
		} else if (ecke == Ecke.B) {
			eckePosition = eckeB.clone();
		} else if (ecke == Ecke.C) {
			eckePosition = eckeC.clone();
		} else if (ecke == Ecke.D) {
			eckePosition = eckeD.clone();
		}
		// Spieler wird an die Startecke gesetzt.
		for (int i = 0; i < eckeA.length; i++) {
			spielerBewegen(eckePosition[i].getIndexZeile(),
					(eckePosition[i].getIndexSpalte()), spieler);
		}
	}

	/**
	 * <pre>
	 * eckeSetzen(Ecke ecke, int spielerAnzahl)
	 * </pre>
	 * 
	 * Die Felder der jeweiligen Ecken werden erstellt. Je nach Spieleranzahl
	 * varriert die Anzahl der Felder.
	 * 
	 * @param ecke
	 *            Die ausgewählte Ecke
	 * @param spielerAnzahl
	 *            Anzahl der Spieler
	 */
	private void eckeSetzen(Ecke ecke, int spielerAnzahl) {
		Feld[] eckePosition;
		if (spielerAnzahl == 2) {
			eckePosition = new Feld[19];
		} else {
			eckePosition = new Feld[13];
		}
		int maxZeile = 4; // die Maximalausbreitung in Zeilenrichtung vom
							// Eckpunkt aus
		int maxSpalte = 4; // die Maximalausbreitung in Spaltenrichtung vom
							// Eckpunkt aus
		if (spielerAnzahl == 2) {
			maxZeile = 5;
			maxSpalte = 5;
		}
		int eckeCounter = 0;
		for (int i = ecke.getIndexZeile(); i != ecke.getIndexZeile() + maxZeile
				* ecke.getRichtungWaagerecht(); i += ecke
				.getRichtungWaagerecht()) {
			for (int j = ecke.getIndexSpalte(); j != ecke.getIndexSpalte()
					+ maxSpalte * ecke.getRichtungSenkrecht(); j += ecke.getRichtungSenkrecht()) {
				// erst wird eine Zeile in Richtung der Spalte  aufgefüllt dannach wird zur nächsten Zeile übergegangen		
				eckePosition[eckeCounter] = new Feld();
				eckePosition[eckeCounter].setIndexZeile(i);
				eckePosition[eckeCounter++].setIndexSpalte(j);
			}
			if (Math.abs(i - ecke.getIndexZeile()) == 1) // Die  Maximalausbreitung der Spalte wird um eins verringert.
				maxSpalte--;
			else if (Math.abs(i - ecke.getIndexZeile()) == 2) // Die Maximalausbreitung der Spalte wird um eins verringert.
				maxSpalte--;
			else if (Math.abs(i - ecke.getIndexZeile()) == 3) // Die Maximalausbreitung der Spalte wird um eins verringert.
				maxSpalte--;
		}
		if (ecke == Ecke.A) {
			eckeA = new Feld[eckePosition.length];
			eckeA = eckePosition.clone();
		} else if (ecke == Ecke.B) {
			eckeB = new Feld[eckePosition.length];
			eckeB = eckePosition.clone();
		} else if (ecke == Ecke.C) {
			eckeC = new Feld[eckePosition.length];
			eckeC = eckePosition.clone();
		} else if (ecke == Ecke.D) {
			eckeD = new Feld[eckePosition.length];
			eckeD = eckePosition.clone();
		}
	}

	/**
	 * <pre>
	 * Feld[] zielEckeSetzen(Ecke ecke)
	 * </pre>
	 * 
	 * Die Zielecke des aktuellen Spieler wird zurückgegeben.
	 * 
	 * @param ecke
	 *            Die Ecke aufdem der Spieler beginnt
	 * @return Zielecke
	 */
	public Feld[] zielEckeSetzen(Ecke ecke) {
		if (ecke == Ecke.A) {
			return eckeD;
		} else if (ecke == Ecke.B) {
			return eckeC;
		} else if (ecke == Ecke.C) {
			return eckeB;
		}
		return eckeA;
	}

	/**
	 * <pre>
	 * SpielerAufFeldSetzen(int zeile, int spalte, Spieler spieler)
	 * </pre>
	 * 
	 * Der Spieler wird auf das bewegte Feld hinzugefügt.
	 * 
	 * @param zeile
	 *            Zeilenindex des Feldes
	 * @param spalte
	 *            Spaltenindex des Feldes
	 * @param spieler
	 *            der aktuelle Spieler
	 */
	public void spielerBewegen(int zeile, int spalte, Spieler spieler) {
		feld[zeile][spalte].setSpieler(spieler);
	}

	/**
	 * <pre>
	 * feldersuchen(int startZeile, int startSpalte)
	 * <pre>
	 * Es wird überprüft ob neben dem gewählten Spielstein freie Felder zum
	 * spielen sind.
	 * 
	 * @param startZeile
	 *            Der Startparameter der Zeile des Spielsteines
	 * @param startSpalte
	 *            Der Startparameter der Spalte des Spielsteines
	 * @return Die freien spielbaren Felder
	 */
	public LinkedList<Feld> feldersuchen(int startZeile, int startSpalte) {
		zugMoeglichkeiten = new LinkedList<>();
		Ecke ecke = feld[startZeile][startSpalte].getSpieler()
		        .getGewaehlteEcke();
		int richtungZeile = ecke.getRichtungWaagerecht(); // in welche Richtung
		int richtungSpalte = ecke.getRichtungSenkrecht(); // ein Stein gehen
															// darf
															// jede Ecke hat
															// verschiedene
															// erlaubte
															// Richtungen
		felderSuchen(startZeile, startSpalte, -1 * richtungZeile,
				1 * richtungSpalte, false);
		felderSuchen(startZeile, startSpalte, 0, 1 * richtungSpalte, false);
		felderSuchen(startZeile, startSpalte, 1 * richtungZeile,
				1 * richtungSpalte, false);
		felderSuchen(startZeile, startSpalte, 1 * richtungZeile, 0, false);
		felderSuchen(startZeile, startSpalte, 1 * richtungZeile, -1
				* richtungSpalte, false);
		// Ab hier wird geschaut ob es nach einem SprungFeld weitere
		// Sprungfelder gibt.
		// Dafür wird für jedes gefundene Sprungfeld nach weiteren Sprungfelder
		// gesucht.
		// Ein Feld ist ein Sprungfeld, wenn der Abstand zum Startpunkt in
		// ,Zeile- oder Spaltenrichtung, größer als 1 ist.
		int index = 0; // Gibt an welches Feld in der Liste genommen werden soll.
		while (index < zugMoeglichkeiten.size()) { 
			Feld i = zugMoeglichkeiten.get(index); // Bei jedem Feld in der Liste wird nach Sprungfelder durgesucht.
			if (Math.abs(i.getIndexZeile() - startZeile) > 1
					|| Math.abs(i.getIndexSpalte() - startSpalte) > 1) {
				felderSuchen(i.getIndexZeile(), i.getIndexSpalte(), -1
						* richtungZeile, 1 * richtungSpalte, true);
				felderSuchen(i.getIndexZeile(), i.getIndexSpalte(), 0,
						1 * richtungSpalte, true);
				felderSuchen(i.getIndexZeile(), i.getIndexSpalte(),
						1 * richtungZeile, 1 * richtungSpalte, true);
				felderSuchen(i.getIndexZeile(), i.getIndexSpalte(),
						1 * richtungZeile, 0, true);
				felderSuchen(i.getIndexZeile(), i.getIndexSpalte(),
						1 * richtungZeile, -1 * richtungSpalte, true);
			}
			index++;
		}
		return zugMoeglichkeiten;
	}

	/**
	 * <pre>
	 * spielsteinKannBewegtwerden(int startZeile, int startSpalte,
	 * 			int richtungZeile, int richtungSpalte, boolean sprung)
	 * </pre>
	 * 
	 * Es wird nach freien Felder für den Spielzug gesucht.
	 * 
	 * @param startZeile
	 *            Der Startparameter der Zeile des Spielsteines
	 * @param startSpalte
	 *            Der Startparameter der Spalte des Spielsteines
	 * @param richtungZeile
	 *            Die erlaubte Zeilenrichtung des Spielsteines
	 * @param richtungSpalte
	 *            Die erlaubte Spaltenrichtung des Spielsteines
	 * @param sprung
	 *            Falls davor ein Sprung stattgefunden hat
	 */
	private void felderSuchen(int startZeile, int startSpalte,
			int richtungZeile, int richtungSpalte, boolean sprung) {
		int endpunktZeile = 16;
		int endpunktSpalte = 16;
		if (richtungZeile < 0) {
			endpunktZeile = -1;
		}
		if (richtungSpalte < 0) {
			endpunktSpalte = -1;
		}
		boolean gefunden = false;
		int i = startZeile + richtungZeile;
		int j = startSpalte + richtungSpalte;
		while (!gefunden && i != endpunktZeile && j != endpunktSpalte) {
			if (feld[i][j].getSpieler() == null) { // Es wird solange geschaut bis ein freies Feld
													// gefunden oder die Endpunkte erreicht wurden.
				if (!sprung) { // falls davor ein Sprung stattgefunden hat
					zugMoeglichkeiten.add(feld[i][j]);
					gefunden = true;
				} else {
					if (Math.abs(i - startZeile) > 1 // es wird geschaut ob das
						|| Math.abs(j - startSpalte) > 1) {	// neue Feld einen
														// Abstand größer als 1 hat.
														// Denn nach einem Sprung kann man nur 
														// Sprünge machen 
						if (!zugMoeglichkeiten.contains(feld[i][j])) { // ob das Feld schon in der Liste ist
								zugMoeglichkeiten.add(feld[i][j]);											
						}
					}
					return; // Falls das erste gefunde freie Feld nicht ein Feld
							// zum Springen ist kann man mit der Suche abbrechen.			
				}
			}
			i += 1 * richtungZeile;
			j += 1 * richtungSpalte;
		}
	}

	/**
	 * <pre>
	 * getFeldvonSpieler(Spieler spieler)
	 * </pre>
	 * 
	 * Die Positionen der Spielsteine des aktuellen Spielers werden gesucht.
	 * 
	 * @param spieler
	 *            der aktuelle Spieler
	 * @return Positionen der Spielsteine
	 */
	public Feld[] getFeldvonSpieler(Spieler spieler) {
		int index = 0;
		Feld[] feldtmp = new Feld[eckeA.length];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (spieler.equals(feld[i][j].getSpieler())) {
					feldtmp[index] = feld[i][j];
					index++;
				}
			}
		}
		return feldtmp;
	}

	/**
	 * <pre>
	 * printSpielfeld()
	 * </pre>
	 * 
	 * Das Spielfeld wird ausgegeben.
	 */
	public void printSpielfeld() {
		for (int i = 0; i < 16; i++) {
			System.out.printf("%3d", (i + 1));
		}
		System.out.println();
		System.out.println("________________________________________________");
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (feld[i][j].getSpieler() != null) {
					System.out.print("|" + feld[i][j].getSpieler().getSymbol()
							+ "|");
				} else {
					System.out.print("|_|");
				}

			}
			System.out.println(" " + (i + 1));
		}
		System.out.println("------------------------------------------------");
	}
}
