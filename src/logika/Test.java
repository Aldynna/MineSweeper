/**
 * ovo se ne treba koristiti,
 * cak ga treba izbrisati.
 */
package logika;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		int x, y, potez;
		PoljanaZaIgru poljanaZaIgru = new PoljanaZaIgru(PoljanaZaIgru.LEVEL_BEGINER);
		System.out.println(poljanaZaIgru);
		Scanner in = new Scanner(System.in);
//		while (poljanaZaIgru.mozeSeJosIgrati()) {
			System.out.print("Unesi x: ");
			x = in.nextInt();
			System.out.print("Unesi y: ");
			y = in.nextInt();
			System.out.format("Unesi potez (Lijevi - %d, Desni - %d, Oba - %d): ", PoljanaZaIgru.POTEZ_LIJEVI_KLIK, PoljanaZaIgru.POTEZ_DESNI_KLIK, PoljanaZaIgru.POTEZ_OBA_KLIKA);
			potez = in.nextInt();
			
			poljanaZaIgru.odigraj(x, y, potez);
			System.out.println(poljanaZaIgru);
//		}
	}

}
