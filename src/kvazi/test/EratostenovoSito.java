package kvazi.test;

public class EratostenovoSito {

	public static void main(String[] args) {
		eratosten(100000);
	}

	private static void eratosten(int n) {
		boolean[] niz = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			niz[i] = true;
		}
		for (int i = 2; i <= (n / 2); i++) {
			if (niz[i]) {
				for (int j = 2; i * j <= n; j++) {
					niz[i * j] = false;
				}
			}
		}
		System.out.println("1 nije ni prost ni slozen.");
		for (int i = 2; i <= n; i++) {
			System.out.println(niz[i] ? ("Broj " + i + " je prost.") : ("Broj " + i + " nije prost."));
		}
	}

}
