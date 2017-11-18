/**
 * 
 */
package logika;

import java.util.Random;

/**
 * @author Mi na casu Na KAPJ
 * datum: 14.03.2017.
 * zadnja modifikacija:
 *
 */
public class PoljanaZaIgru {
	public static final int LEVEL_BEGINER = 10;
	public static final int LEVEL_INTERMEDIATE = 2;
	public static final int LEVEL_EXPERT = 33;
	public static final int POTEZ_DESNI_KLIK = 1;
	public static final int POTEZ_LIJEVI_KLIK = 0;
	public static final int POTEZ_OBA_KLIKA = 2;
	public static final int KRAJ_NIJE = 0;
	public static final int KRAJ_POBJEDA = 1;
	public static final int KRAJ_IZGUBLJENO = 2;
	private int visina;
	private int sirina;
	private int brojMina;
	private JednoPolje[][] tabela;
	private boolean igraJeAktivna;

	public PoljanaZaIgru(int duzina, int sirina, int brojMina) {
		super();
		this.igraJeAktivna = true;
		if (duzina < 5) {
			this.visina = 5;
		} else {
			if (duzina > 40) {
				this.visina = 40;
			} else {
				this.visina = duzina;
			}
		}
		if (sirina > 40) {
			this.sirina = 40;
		} else {
			if (this.visina > sirina) {
				this.sirina = this.visina;
			} else {
				this.sirina = sirina;
			}
		}
		if (brojMina < this.visina) {
			this.brojMina = this.visina;
		} else {
			if (brojMina < ((int) 0.8 * this.visina * this.sirina)) {
				this.brojMina = ((int) 0.8 * this.visina * this.sirina);
			} else {
				this.brojMina = brojMina;
			}
		}

		this.tabela = new JednoPolje[this.visina][this.sirina];
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina; j++) {
				tabela[i][j] = new JednoPolje();
			}
		}

		for (int count = 0; count < this.brojMina;) {
			int d = (int)(Math.random() * this.visina);
			int s = (int)(Math.random() * this.sirina);
			if (!tabela[d][s].isMina()) {
				tabela[d][s].postaviMinu();
				count++;
				for (int i = Math.max(d-1, 0); i <= Math.min(d+1, this.visina-1); i++) {
					for (int j = Math.max(s-1, 0); j <= Math.min(s+1, this.sirina-1); j++) {
						tabela[i][j].povecajBrojMinaUKomsiluku();
					}
				}
			}
		}
	}

	public PoljanaZaIgru(int level) {
		this(duzina(level), sirina(level), brojMina(level));
	}

	private static int brojMina(int level) {
		switch (level) {
		case LEVEL_BEGINER:
			return 10;
		case LEVEL_INTERMEDIATE:
			return 40;
		default:
			return 99;
		}
	}

	private static int sirina(int level) {
		switch (level) {
		case LEVEL_BEGINER:
			return 9;
		case LEVEL_INTERMEDIATE:
			return 16;
		default:
			return 30;
		}
	}

	private static int duzina(int level) {
		switch (level) {
		case LEVEL_BEGINER:
			return 9;
		default:
			return 16;
		}
	}
/*
	public boolean mozeSeJosIgrati() {
		return igraJeAktivna;
	}
*/
	public int getPobjeda() {
		int brojOtvorenihPolja = 0;
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				if (tabela[i][j].getStatus() == JednoPolje.STATUS_OTVORENO) {
					brojOtvorenihPolja++;
				}
			}
		}

		System.out.println("Broj otvorenih polja je: " + brojOtvorenihPolja);
		System.out.println("(visina * sirina - brojMina) = " + (visina * sirina - brojMina));
		System.out.println("igraJeAktivna = " + igraJeAktivna);

		if (brojOtvorenihPolja == (visina * sirina - brojMina)) {
			igraJeAktivna = false;
			return KRAJ_POBJEDA;
		}
		if (igraJeAktivna) {
			return KRAJ_NIJE;
		}
		return KRAJ_IZGUBLJENO;
	}

	public void odigraj(int i, int j, int potez) {
		if (igraJeAktivna && (i >= 0) && (i < visina) && (j >= 0) && (j < sirina) && ((potez == POTEZ_DESNI_KLIK) || (potez == POTEZ_LIJEVI_KLIK) || (potez == POTEZ_OBA_KLIKA))) {
			if (potez == POTEZ_DESNI_KLIK) {
				tabela[i][j].setStatus(JednoPolje.AKCIJA_DESNI_KLIK);
			}
			if (potez == POTEZ_LIJEVI_KLIK) {
				int vrijednost = tabela[i][j].setStatus(JednoPolje.AKCIJA_LIJEVI_KLIK);
				if (vrijednost == JednoPolje.MINA_OKOLO_NEMA) {
					for (int vis = Math.max(i-1, 0); vis <= Math.min(i+1, this.visina-1); vis++) {
						for (int sir = Math.max(j-1, 0); sir <= Math.min(j+1, this.sirina-1); sir++) {
							odigraj(vis, sir, POTEZ_LIJEVI_KLIK);
						}
					}
				} else {
					if (vrijednost == JednoPolje.MINA_JE_NA_OVOM_POLJU_OTVORENA) {
						this.igraJeAktivna = false;
					}
				}
			}
			if (potez == POTEZ_OBA_KLIKA) {
				if (tabela[i][j].getStatus() == JednoPolje.STATUS_OTVORENO) {
					int brOznacenihMinaOkolo = 0;
					for (int vis = Math.max(i-1, 0); vis <= Math.min(i+1, this.visina-1); vis++) {
						for (int sir = Math.max(j-1, 0); sir <= Math.min(j+1, this.sirina-1); sir++) {
							if (tabela[vis][sir].getStatus() == JednoPolje.STATUS_OZNACENO) {
								brOznacenihMinaOkolo++;
							}
						}
					}
					if (tabela[i][j].prikaziPolje() == (char)((int)'0' + brOznacenihMinaOkolo)) {
						for (int vis = Math.max(i-1, 0); vis <= Math.min(i+1, this.visina-1); vis++) {
							for (int sir = Math.max(j-1, 0); sir <= Math.min(j+1, this.sirina-1); sir++) {
								odigraj(vis, sir, POTEZ_LIJEVI_KLIK);
							}
						}
					}
				}
			}
		}
		getPobjeda();
	}

	public int getVisina() {
		return visina;
	}

	public int getSirina() {
		return sirina;
	}

	public char[][] getPoljana() {
		char[][] poljana = new char[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				poljana[i][j] = tabela[i][j].prikaziPolje();
			}
		}
		return poljana;
	}

	public char getPoljana(int i, int j) {
		return tabela[i][j].prikaziPolje();
	}

	@Override
	public String toString() {
//		return super.toString();
		String str, linija = "+";
		for (int i = 0; i < sirina; i++) {
			linija = String.format("%s%s",  linija, "---+");
		}
		str = linija;
		for (int i = 0; i < visina; i++) {
			str = String.format("%s%s", str, "\n|");
			for (int j = 0; j < sirina; j++) {
				str = String.format("%s %s |", str, tabela[i][j].prikaziPolje()); // str = str + " " + (tabela[i][j]) + " |";
			}
			str = String.format("%s\n%s", str, linija); // str += "\n" + linija
		}

		str += igraJeAktivna ? "\njos uvijek igramo" : "\ngotovo" ;
		return str;
	}
}
