package OOT_SS14_DC.Bildschirminteraktion;
import java.util.LinkedList;
import java.util.Scanner;

import OOT_SS14_DC.Spieler.Mensch;
import OOT_SS14_DC.Spieler.Spieler;
import OOT_SS14_DC.Spielkarton.Ecke;
import OOT_SS14_DC.Spielkarton.Feld;
import OOT_SS14_DC.Spielkarton.Spielfeld;


public class Test {

	// zum testen
	private static Feld[] feld=new Feld[13];
	// Diese Klasse ist zum Testen  :P
	public static void main(String[] args) {
		Spielfeld spiel= new Spielfeld(4);
		Spieler a= new Mensch("hallo",Ecke.A,"A");
		a.setZielEcke(spiel.zielEckeSetzen(a.getGewaehlteEcke()));
		Spieler b= new Mensch("hallo",Ecke.B,"B");
		Spieler c= new Mensch("hallo",Ecke.C,"+");
		Spieler d= new Mensch("hallo",Ecke.D,"D");
		spiel.spielerSetzen(Ecke.A, a);
		spiel.spielerSetzen(Ecke.B, b);
		spiel.spielerSetzen(Ecke.C, c);
		spiel.spielerSetzen(Ecke.D, d);
		spiel.printSpielfeld();
	// Ab Hier könnte man es in die GameKlasse reinschreiben, bzw eine Methode entwickeln die so ungefähr aussieht
		// Es werden keine Fehler abgefangen! wollte nur demonstrieren dass es geht :P
		// Der Spieler D wird in dem Fall genommen
		for(int l=0;l<1000;l++){
		System.out.println("Welchen Stein möchten sie bewegen?");
		feld=spiel.getFeldvonSpieler(d);
		for(int i=0;i<feld.length;i++){
			System.out.println("Zeile:"+feld[i].getIndexZeile()+" Spalte:"+ feld[i].getIndexSpalte());
		}
		Scanner scanner= new Scanner(System.in);
		int startZeile = scanner.nextInt();
		int startSpalte= scanner.nextInt();
		LinkedList<Feld> feld=spiel.feldersuchen(startZeile, startSpalte);
		System.out.println("Wohin möchten sie es bewegen?");
			while(!feld.isEmpty()){
				Feld feldtmp= feld.poll();
			System.out.println("Zeile:" +feldtmp.getIndexZeile()+" Spalte:"+ feldtmp.getIndexSpalte());
			}
		int zielZeile= scanner.nextInt();
		int zielSpalte=scanner.nextInt();
		spiel.spielerBewegen(startZeile, startSpalte, null);
		spiel.spielerBewegen(zielZeile,zielSpalte, d);
		spiel.printSpielfeld();
	}
	}

}
