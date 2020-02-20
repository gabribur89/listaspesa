import eccezioni.SceltaSbagliata;
import jbook.util.Input;

public class MainClass {

	public static void stampaMenu(boolean giaesistente) {
		if (giaesistente) {
			System.out.println("\n\n Premi un tasto per tornare al menu");
			Input.readString();
		}
		System.out.println("\n\n GESTIONE LISTE DELLA SPESA");
		System.out.println("\n\n");
		System.out.println("1 - Crea una lista");
		System.out.println("2 - Crea una categoria");
		System.out.println("3 - Leggi lista da file");
		System.out.println("4 - Scrivi lista su file");
		System.out.println("5 - Ordina elementi lista");
		System.out.println("6 - Inserisci un articolo in una lista (con quantita')");
		System.out.println("7 - Cerca un articolo nelle due liste");
		System.out.println("8 - Rimuovi un articolo (lo mette nella lista dei rimossi)");
		System.out.println("9 - Ripristina un articolo dalla lista dei rimossi");
		System.out.println("10 - Modifica categoria di un articolo");
		System.out.println("11 - Modifica quantita' di un articolo");
		System.out.println("12 - Svuota lista rimossi");
		System.out.println("0 - Esci ");
		System.out.println("\nInserisci un comando: ");
		System.out.println("\n\n\n");
	}
	public static void main(String[] args) throws SceltaSbagliata{
		
		int scelta = 0;
		
		// determina se e' gia' stato prodotto il menu o no
		boolean esistente = false;

		do{
			MainClass.stampaMenu(esistente);
			esistente = true; 
			
			try { 
				scelta=Input.readInt();
				System.out.println(scelta);
				if((scelta<0) || (scelta>9))
				{
					throw new SceltaSbagliata("Devi inserire un numero tra 0 e 9!");
				}
				
			} catch (NumberFormatException e){
				  System.out.print("Devi inserire solo numeri interi!");
			} catch(SceltaSbagliata e){
				  scelta = 99;
				  System.out.println("Devi inserire un numero tra 0 e 9!");
			};
			
			switch(scelta){
			case 1:
					break;
			case 0: //esci
				System.out.println("\n ti saluto! Alla prossima! ");
			default:
				break;
			}
		}while(scelta!=0);
	}
}

