package OOT_SS14_DC.Spieler;
import java.util.LinkedList;

import OOT_SS14_DC.Spielkarton.Ecke;
/**
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 *
 */

/**
 * 
 * Diese Klasse ermöglicht das generieren eines individuelen Computernamens und
 * die zufällige auswahl einer Ecke und eines Symbols für alle KIs,
 * Hierdurch kann einComputer-Spieler ohne Parameter instanziert werden 
 *
 */
public class KIgenerator {
    static String name = "Computer ";
    static int nummer = 0;
    static LinkedList<String> symbol;
    static LinkedList<Ecke> ecke;
    
    /**Generiert einen KI Namen (Computer X) 
     * 
     * @return Individueller Name
     */
    
    static String getKIName(){
        nummer++;
        return ""+name+nummer; 
    }
    
    /**Liest alle noch verfügbaren Ecken ein
     * 
     * @param verfuegbareecke Alle verfügbaren Ecken
     */
    public static void setEcke(LinkedList<Ecke> verfuegbareecke){
        ecke = verfuegbareecke;
    }
    
    /**gibt eine verfügbare Startecke zurück
     * 
     * @return Zufällige Ecke
     */
    static Ecke getEcke(){
        return ecke.remove(0);
    }
    
    /**Liest alle noch verfügbaren Symbole ein
     * 
     * @param verfuegbaressymbol Alle verfügbaren Symbole
     */
    public static void setSymbol (LinkedList<String> verfuegbaressymbol){
        symbol = verfuegbaressymbol;
    }
    
    /**gibt ein verfügbares Symbol zurück
     * 
     * @return Symbol als String
     */
    static String getSymbol(){
        return symbol.remove(0);
    }
    
    
    
}
