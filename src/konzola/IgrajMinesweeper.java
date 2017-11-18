package konzola;

import java.util.Scanner;

import logika.JednoPolje;
import logika.PoljanaZaIgru;

public class IgrajMinesweeper {

	public static void main(String[] args) {
		int x, y, potez;
		PoljanaZaIgru poljanaZaIgru = new PoljanaZaIgru(PoljanaZaIgru.LEVEL_BEGINER);
		System.out.println(prikaz(poljanaZaIgru));
		Scanner in = new Scanner(System.in);
		while (poljanaZaIgru.getPobjeda() == poljanaZaIgru.KRAJ_NIJE) {
			System.out.print("Unesi x: ");
			x = in.nextInt();
			System.out.print("Unesi y: ");
			y = in.nextInt();
			System.out.format("Unesi potez (Lijevi - %d, Desni - %d, Oba - %d): ", PoljanaZaIgru.POTEZ_LIJEVI_KLIK, PoljanaZaIgru.POTEZ_DESNI_KLIK, PoljanaZaIgru.POTEZ_OBA_KLIKA);
			potez = in.nextInt();
			
			poljanaZaIgru.odigraj(x, y, potez);
			System.out.println(prikaz(poljanaZaIgru));
		}
		if (poljanaZaIgru.getPobjeda() == PoljanaZaIgru.KRAJ_POBJEDA) {
			System.out.println("Cestitamo, pobijedili ste :)");
		} else {
			System.out.println("LUUUUUUZERUUUUUUUUU !!!!");
		}
	}

	private static String prikaz(PoljanaZaIgru poljana) {
		String str = "   ", linija = "    +";
		for (int i = 0; i < poljana.getSirina(); i++) {
			str = String.format("%s%4d", str, i);
			linija = String.format("%s%s",  linija, "---+");
		}
		str = String.format("%s\n%s", str, linija);
		for (int i = 0; i < poljana.getVisina(); i++) {
			str = String.format("%s\n%3d %s", str, i, "|");
			for (int j = 0; j < poljana.getSirina(); j++) {
				str = String.format("%s %s |", str, poljana.getPoljana(i, j)); // str = str + " " + (tabela[i][j]) + " |";
			}
			str = String.format("%s\n%s", str, linija); // str += "\n" + linija
		}

//		str += poljana.mozeSeJosIgrati() ? "\njos uvijek igramo" : "\ngotovo" ;
		return str;
	}

}
