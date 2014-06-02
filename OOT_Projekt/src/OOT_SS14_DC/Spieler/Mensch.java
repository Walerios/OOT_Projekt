package OOT_SS14_DC.Spieler;

import java.util.*;

import OOT_SS14_DC.Spielkarton.*;

/**
 * 
 * Die Klasse repräsentiert einen menachlichen Spieler
 *
 */
public class Mensch extends Spieler {

    /**<pre>Konstruktor menschlichen Spieler</pre>
     * 
     */
    
    public Mensch(String Name, Ecke ecke, String symbol) {
        super(Name, ecke, symbol);
    }

    
    /**
     * @see Spieler
     */
    @Override
    public Feld spielsteinWaehlen(Feld[] feld) {
        int wahlmöglichkeit = 2;
        System.out.print("\n1 = AUSSETZEN\t");
        for (Feld iterator : feld) {
            System.out.print(wahlmöglichkeit +" = "+  (iterator.getIndexZeile() + 1)
                    + "/"+ (iterator.getIndexSpalte() +1)+"  \t");
            if(wahlmöglichkeit%5 == 0){
                System.out.println("\n");
            }
            wahlmöglichkeit++;
        }
        System.out.println("Bitte Spielstein auswählen der bewegt werden soll");
        @SuppressWarnings("resource")
        Scanner eingabe = new Scanner(System.in);
        int auswahl;
        try{
        auswahl = eingabe.nextInt();
        }catch(InputMismatchException ex){
            System.out.println("Die Eingabe war ungültig versuchen sie es nochmal");
            return spielsteinWaehlen(feld);
        }
        
        
        if(auswahl == 1){
            return null;
        }else{
            return feld[auswahl-2];
        }
    }

    /**
     * @see Spieler
     */
    @Override
    public Feld zielWaehlen(LinkedList<Feld> ziel) {
    	
        
        
        int wahlmöglichkeit = 2;
        System.out.print("\n1 = NEUER SPIELSTEIN\t");
        for (Feld iterator : ziel) {
            System.out.print(wahlmöglichkeit +" = "+  (iterator.getIndexZeile() + 1)
                + "/"+ (iterator.getIndexSpalte()+1)+"  \t");
            wahlmöglichkeit++;
        if(wahlmöglichkeit%5 == 0){
            System.out.println("\n");
            }
           
        }
        if (wahlmöglichkeit%5 !=0){
            System.out.println("\n");
        }
        System.out.println("wohin soll der Stein bewegt werden?");
        @SuppressWarnings("resource")
        Scanner eingabe = new Scanner(System.in);
        
        int auswahl;
        try{
            auswahl = eingabe.nextInt();
        }catch(InputMismatchException ex){
            auswahl = -1;
        }
        while (auswahl <= 0 || auswahl > wahlmöglichkeit) {
            System.out.println("Es wurde kein gültiger Ziel gewählt,"
                    + " versuchen sie es erneut");
            try{
                auswahl = eingabe.nextInt();
            }catch(InputMismatchException ex){
                System.out.println("Die Eingabe war ungültig versuchen sie es nochmal");
                return zielWaehlen(ziel);
            }
        }
        
        
        if(auswahl == 1){
            return null;
        }else{
            return ziel.get(auswahl-2);
        }
        
    }
       
        
}
