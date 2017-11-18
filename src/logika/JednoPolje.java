package logika;

public class JednoPolje {
	public static final int STATUS_NEOTVORENO = 0;
	public static final int STATUS_OZNACENO = 1;
	public static final int STATUS_OTVORENO = 2;
	public static final int AKCIJA_DESNI_KLIK = 1;
	public static final int AKCIJA_LIJEVI_KLIK = 2;
	public static final int MINA_OKOLO_NEMA = 0;
	public static final int MINA_OKOLO_IMA = 1;
	public static final int MINA_JE_NA_OVOM_POLJU_OTVORENA = 2;

	private boolean mina;
	private int brojMinaUKomsiluku;
	private int statusPolja;

	
	public JednoPolje() {
		super();
		mina = false;
		brojMinaUKomsiluku = 0;
		statusPolja = STATUS_NEOTVORENO;
	}

	public boolean isMina() {
		return mina;
	}

	public void postaviMinu() {
		mina = true;
	}

	public void povecajBrojMinaUKomsiluku() {
		brojMinaUKomsiluku++;
	}

	public char prikaziPolje() {
		if (statusPolja == STATUS_NEOTVORENO) {
			return ' ';
		}
		if (statusPolja == STATUS_OZNACENO) {
			return 'P';
		}
		if (statusPolja == STATUS_OTVORENO && mina) {
			return '*';
		}
		return (char)(brojMinaUKomsiluku + (int)'0');
	}

	public int setStatus(int akcija) {
		if (akcija == AKCIJA_DESNI_KLIK) {
			if (statusPolja != STATUS_OTVORENO) {
				if (statusPolja == STATUS_NEOTVORENO) {
					statusPolja = STATUS_OZNACENO;
				} else {
					statusPolja = STATUS_NEOTVORENO;
				}
			}
		}
		if (akcija == AKCIJA_LIJEVI_KLIK) {
			if (statusPolja == STATUS_NEOTVORENO) {
				statusPolja = STATUS_OTVORENO;
				if (this.mina) {
					return MINA_JE_NA_OVOM_POLJU_OTVORENA;
				}
				if (brojMinaUKomsiluku == 0) {
					return MINA_OKOLO_NEMA;
				}
			}
		}
		return MINA_OKOLO_IMA;
	}

	public int getStatus() {
		return statusPolja;
	}

	@Override
	public String toString() {
		return "" + brojMinaUKomsiluku;
	}
}
