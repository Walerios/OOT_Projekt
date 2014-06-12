package OOT_SS14_DC.Spielkarton;
/**
 * Die Enumeration <code> Ecke <code> beinhaltet die Eckkoordinaten der 
 * Ecken und die Richtungszahl. Dies ist für die Erstellung der Ecken wichtig.
 * 
 * 
 * @author Dominik Meixner 1324227
 * @author Deniz Tas 1320360
 * @author Simon Sauerzapf 1320341
 *
 */
	public enum Ecke {

		A(0, 0, 1, 1), B(0, 15, 1, -1), C(15, 0, -1, 1), D(15, 15, -1, -1);

		private final int indexSpalte;
		private final int indexZeile;
		private final int richtungWaagerecht;
		private final int richtungSenkrecht;

		/**
		 * <pre>
		 * Ecke(int indexZeile, int indexSpalte, int richtungWaagerecht, int richtungSenkrecht)
		 * </pre>
		 * Konstruktor der Enumeration <code> Ecke <code>
		 * @param indexZeile Zeilenindex der Eckkoordinate
		 * @param indexSpalte Spaltenindex der Eckkoordinate
		 * @param richtungWaagerecht Richtungszahl der Zeile
		 * @param richtungSenkrecht Richtungszahl der Spalte
		 */
		private Ecke(int indexZeile, int indexSpalte, int richtungWaagerecht,
				int richtungSenkrecht) {
			this.indexZeile = indexZeile;
			this.indexSpalte = indexSpalte;
			this.richtungWaagerecht = richtungWaagerecht;
			this.richtungSenkrecht = richtungSenkrecht;

		}

		/**
		 * <pre>
		 * getRichtungSenkrecht()
		 * </pre>
		 * @return Richtungszahl der Spalte
		 */
		public int getRichtungSenkrecht() {
			return richtungSenkrecht;
		}

		/**
		 * <pre>
		 * getRichtungWaagerecht()
		 * </pre>
		 * @return Richtungszahl der Zeile
		 */
		public int getRichtungWaagerecht() {
			return richtungWaagerecht;
		}

		/**
		 * <pre>
		 * getIndexSpalte()
		 * </pre>
		 * @return Spaltenindex der Eckkoordinate 
		 */
		public int getIndexSpalte() {
			return indexSpalte;
		}

		/**
		 * <pre>
		 * getIndexZeile()
		 * </pre>
		 * @return Zeilenindex der Eckkoordinate
		 */
		public int getIndexZeile() {
			return indexZeile;
		}

	}
