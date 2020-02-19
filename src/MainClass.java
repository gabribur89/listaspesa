import jbook.util.Input;

public class MainClass {

	public static void stampaMenu(boolean giaesistente) {
		if (giaesistente) {
			System.out.println("\n\n Premi un tasto per tornare al menu");
			Input.readString();
		}
		System.out.println("\n\n GESTIONE LISTE DELLA SPESA");
		System.out.println("\n\n");
		System.out.println("1 - Crea una lista per nome");
		System.out.println("2 - Crea una categoria");
		System.out.println("3 - Leggi lista da file");
		System.out.println("4 - Scrivi lista su file");
		System.out.println("5 - Ordina elementi lista");
		System.out.println("6 - Inserisci un articolo in una lista (con quantita')");
		System.out.println("7 - Rimuovi un articolo");
		System.out.println("8 - Ripristina un articolo dalla lista dei rimossi");
		System.out.println("9 - Modifica categoria di un articolo");
		System.out.println("10 - Modifica quantita' di un articolo");
		System.out.println("11 - Svuota lista rimossi");
		System.out.println("0 - Esci ");
		System.out.println("\nInserisci un comando: ");
		System.out.println("\n\n\n");
	}
	
}
