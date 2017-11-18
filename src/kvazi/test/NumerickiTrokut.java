package kvazi.test;

public class NumerickiTrokut {

	private static String pravougliNaDesnuStranu(int n) {
		String str = "";
		int duzina = len(n);

		for (int i = 1; i <= n; i++) {
			if (i < n) {
				str = String.format("%s%" + ((n - i) * duzina) + "s", str, " ");
			}
			for (int j = 1; j <= i; j++) {
				str = String.format("%s%" + duzina + "d", str, j);
			}
			str += "\n";
		}

		return str;
	}

	private static int len(int n) {
		return (1 + (int)Math.log10(n));
	}

	public static void main(String[] args) {
		System.out.println(pravougliNaDesnuStranu(5));
	}

}
