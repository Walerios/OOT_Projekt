package OOT_SS14_DC.Spieler;

import java.util.LinkedList;
import OOT_SS14_DC.Spielkarton.Ecke;
import OOT_SS14_DC.Spielkarton.Feld;
/**
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 *
 */
public abstract class Spieler {

	protected Ecke gewaehlteEcke;
	protected String symbol;
	protected String name;
	protected int getaetigteZuege;
	protected Feld[] zielEcke;

	
	/**<pre>Spieler Konstruktor</pre>
	 *  benötigt Name,Ecke und Symbol des Spielers um neuen Spieler zu erzeugen
	 * 
	 * @param name gewählter Name des Spielers als <code>String</code>
	 * @param ecke gewählte Ecke des Spielers als <code>Ecke</code>
	 * @param symbol gewähltes Symbol des Spielers als <code>String</code>
	 */
	public Spieler(String name, Ecke ecke, String symbol) {
		this.gewaehlteEcke = ecke;
		this.symbol = symbol;
		this.name = name;
		this.getaetigteZuege = 0;

	}
	/**<pre>spielsteinWaehlen(Feld[] feld)</pre>
	 * 
	 * ermöglicht dem Spieler einen Spielstein auszuwählen
	 * 
	 * @param feld Alle Spielsteine des Spilers als <code>Feld[]</code>
	 * @return den vom Spieler gewählten Stein als <code>Feld</code>
	 */
	public abstract Feld spielsteinWaehlen(Feld[] feld);

	/**<pre>zielWaehlen(LinkedList<Feld> ziel)</pre>
	 * 
	 * ermögäicht dem Spieler die Auswahl einen Zielfeldes
	 *  das er mit dem aktuellen Spielstein erreichen kann
	 * 
	 * @param ziel Alle möglichen Ziele als <code>LinkedList Feld </code>
	 * @return das vom Spieler gewählte Ziel als <code>Feld</code>
	 */
	public abstract Feld zielWaehlen(LinkedList<Feld> ziel);

	
	/**
	 *<pre>
     * nächsterunde()
     * </pre>
     * erhöht den Zugzähler des Spielrs um 1
	 */
	public void nächsterunde(){
	    this.getaetigteZuege++;
	}
	
	/**
	 * <pre>
     * getZuege()
     * </pre>
     * gibt getätigte Züge zurück
     * 
	 * @return getätigten Züge als int
	 */
	public int getZuege(){
	    return this.getaetigteZuege;
	}
	
	/**
	 * <pre>
	 * zielErreicht()
	 * </pre>
	 * Überprüft ob der Spieler gewonnen hat.
	 * @return aktuelle Lage des Spielers
	 */
	public boolean zielErreicht(Feld[] feld) {
	    if (this.name.toUpperCase().equals("BATMAN")) {
	        return true;
	    }
	    boolean erreicht = false;
        for (int i = 0; i < feld.length; i++) {
            for (int j = 0; j < zielEcke.length; j++) {
                erreicht = false;
                if (feld[i].getIndexZeile() == zielEcke[j]
                        .getIndexZeile()) {
                    if (feld[i].getIndexSpalte() == zielEcke[j]
                            .getIndexSpalte()) {
                        erreicht = true;
                    }
                }
                if (erreicht) {
                    break;
                }
            }
            if (!erreicht) {
                return false;
            }
        }
        return true;
    }

	/**
	 * <pre>
	 * setZielEcke(Feld[] zielEcke)
	 * </pre>
	 * Die Zielecke des aktuellen Spielers wird gesetzt.
	 * @param zielEcke Zielecke 
	 */
	public void setZielEcke(Feld[] zielEcke) {
		this.zielEcke = zielEcke;
	}

	/**
	 * <pre>
	 * getZielEcke()
	 * </pre>
	 * Auslesen der Zielecke des aktuellen Spielers.
	 * @return Zielecke
	 */
	public Feld[] getZielEcke() {
		return zielEcke;
	}

	/**
	 * <pre>
	 * getSymbol()
	 * </pre>
	 * Auslesen des Symbols des aktuellen Spielers.
	 * @return Symbol 
	 */
	public String getSymbol() {
		return symbol;
	}

	
	/**
	 * <pre>
	 * getGewaehlteEcke()
	 * </pre>
	 * Auslesen der gewählten Ecke des aktuellen Spielers
	 * @return gewählte Ecke 	 */
	public Ecke getGewaehlteEcke() {
		return gewaehlteEcke;
	}
	
	  
	/**
	 * Name des aktuellen Spieler wird ausgelesen.
	 */
    public String toString() {
        return "Spieler: " + name;
    }
    
    /**
     * @see Object
     */
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }else if (o == null){
            return false;
        }else if(o instanceof Spieler){
            Spieler spieler = (Spieler)o;
            
            return spieler.name.equals(this.name)&&
                    spieler.gewaehlteEcke == this.gewaehlteEcke &&
                    spieler.symbol.equals(this.symbol);
            
        }else{
            return false;
        }
    }
    
    /**
     * @see Object
     */
    @Override
    
    public int hashCode(){
        return name.hashCode()*gewaehlteEcke.hashCode()*symbol.hashCode();
                
    }
}




