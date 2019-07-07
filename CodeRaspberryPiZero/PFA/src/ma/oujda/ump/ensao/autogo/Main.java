package ma.oujda.ump.ensao.autogo;

public class Main {
	public static void main(String[] args) {
//		On lance le serveur
		Serveur ts = new Serveur();
		ts.open();
		System.out.println("Serveur initialisé.");
	}
}
