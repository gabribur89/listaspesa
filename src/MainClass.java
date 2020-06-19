import eccezioni.InputError;
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
		System.out.println("1 - Crea una Lista");
		System.out.println("2 - Crea una categoria");
		System.out.println("3 - Modifica una categoria");
		System.out.println("4 - Elimina una categoria");
		System.out.println("5 - Leggi lista da file");
		System.out.println("6 - Scrivi lista su file");
		System.out.println("7 - Ordina elementi lista per nome articolo");
		System.out.println("8 - Inserisci un articolo in una lista(con categoria e quantita')");
		System.out.println("9 - Cerca un articolo nelle due liste. Lista e Lista Rimossi");
		System.out.println("10 - Rimuovi un articolo dalla Lista(lo mette nella Lista Rimossi)");
		System.out.println("11 - Ripristina un articolo dalla Lista Rimossi(lo rimette nella Lista)");
		System.out.println("12 - Modifica categoria di un articolo");
		System.out.println("13 - Modifica quantita' di un articolo");
		System.out.println("14 - Stampa Lista Rimossi");
		System.out.println("15 - Svuota Lista Rimossi");
		System.out.println("16 - Stampa Liste");
		System.out.println("0 - Esci ");
		System.out.println("\nInserisci un comando: ");
		System.out.println("\n\n\n");
	}
	
	
	// Inizio del programma
	public static void main(String[] args) throws SceltaSbagliata, NullPointerException{
		
		int scelta = 0;
		String sceltalista;
		
		// determina se e' gia' stato prodotto il menu o no
		boolean esistente = false;
		GestioneListe liste = new GestioneListe();
		ListaSpesa li = new ListaSpesa();
		//Articolo a = new Articolo();
		
		do{
			MainClass.stampaMenu(esistente);
			esistente = true; 
			
			try { 
				scelta=Input.readInt();
				System.out.println(scelta);
				if((scelta<0) || (scelta>16))
				{
					throw new SceltaSbagliata("Devi inserire un numero tra 0 e 9!");
				}
				
			} catch (NumberFormatException e){
				  System.out.print("Devi inserire solo numeri interi!");
			} catch(SceltaSbagliata e){
				  scelta = 99;
				  System.out.println("Devi inserire un numero tra 0 e 16!");
			};
			
			switch(scelta){
			case 1:
				try {
					System.out.print("Inserisci il nome della lista!\n\n");
					sceltalista = Input.readString();
					if (li.cercaPerNome(sceltalista) != -1)
					{
						System.out.println("Lista esistente, inserimento non avvenuto");
						break;
					}
					GestioneListe.creaLista(sceltalista);
					break;
				} catch (NullPointerException e) {
					System.out.println("Input non consentito");
					break;
				}
			case 2:
				try {
				System.out.print("Inserisci il nome della categoria!");
				String cat = Input.readString();
				if (li.cercaPerNome(cat) != -1)
				{
					System.out.println("Categoria gia' esistente, inserimento non avvenuto");
					break;
				}
				GestioneListe.aggiungiCategoria(cat);
				break;
				}catch (NullPointerException e) {
					System.out.println("Input non consentito");
					break;
				}
			case 3:
				System.out.print("Inserisci il nome della categoria da modificare!");
				String modifica = Input.readString();
				System.out.println("Inserisci nuova categoria!");
				String nuova = Input.readString();
				GestioneListe.modificaCategoria(modifica,nuova);
				System.out.println("categoria modificata!");
				break;
			case 4:
				System.out.print("Inserisci il nome della categoria da eliminare!");
				String cat_elim = Input.readString();
				GestioneListe.eliminaCategoria(cat_elim);
				System.out.println("categoria " + cat_elim + " eliminata!");
				break;
			case 5:
				try {
					GestioneListe.leggidafile();
				}catch(InputError e){
					System.out.println("Non ho trovato il file....");
				}
				catch (Exception e) {
					System.out.println("Errore nella lettura dei dati da file");
				}
				System.out.println("Liste caricate correttamente");
				
				break;
			case 6:
				try {
					System.out.println("Nome lista da salvare su file");
					String nomelista = Input.readString();
					GestioneListe.scrivisufile(nomelista);
				}catch (Exception e) {
					System.out.println("Errore nel salvataggio su file");
				}
				System.out.println("File salvato correttamente");
				break;
			case 7://ordinamento
				// chiedi il nome della lista
				System.out.println("Nome lista:\n");
				String nome = Input.readString();
				// ottenere la lista 
				ListaSpesa l = GestioneListe.getListaSpesa(nome);
				l.ordina();
				//GestioneListe.ordina(a);
				System.out.println(l); 
				break;
			case 8:
				try{
				Articolo art = new Articolo();
				System.out.println("Nome della lista");
				String nomelista = Input.readString();
				if(!GestioneListe.esisteLista(nomelista)) {
					GestioneListe.creaLista(nomelista);
				}
				System.out.println("Nome dell'articolo");
				art.setNome(Input.readString());
				System.out.println("Lista delle categorie");
				System.out.println(GestioneListe.listatoCategorie());
				System.out.println("Scrivi il nome di una delle Categorie");
				String nuovacategoria = Input.readString();
				if(!GestioneListe.categoriaValida(nuovacategoria)) {
					System.out.println("errore, categoria non valida. Non esiste o non sono state create");
					break;
				}
				art.setCategoria(nuovacategoria);
				System.out.println("quantita");
				GestioneListe.aggiungiArticolo(nomelista, Input.readInt(), art);
				}catch (NumberFormatException e){
					System.out.println("Devi inserire solo numeri interi!");
			    }
				break;
			case 9:
				System.out.print("Nome della lista:\n");
				String nome_lista = Input.readString();
				System.out.print("Cerca un articolo:\n");
				String scelta_art = Input.readString();
				GestioneListe.cercaArticolo(nome_lista, scelta_art);
				System.out.print("Ho trovato un articolo con nome: " + scelta_art + 
				" nella lista " + nome_lista + "\n");
				//mostro lista ufficiosa o lista cancellati dove ho trovato articoli
				//
				break;
			case 10:
				System.out.print("Nome della lista\n");
				sceltalista = Input.readString();
				System.out.print("Nome dell'articolo\n");
				String nomear = Input.readString();
				GestioneListe.rimuoviArticolo(sceltalista, nomear);
				System.out.print("articolo con nome: " + nomear + " eliminato!\n");
				break;
			case 11:
				System.out.println("Inserisci il nome della lista:\n");
				String nomelista1 = Input.readString();
				System.out.println("Inserisci nome articolo da ripristinare nella lista della spesa:\n");
				String nome_art = Input.readString();
				GestioneListe.ripristinaCancellato(nomelista1, nome_art);
				break;
			case 12:
				try {
					System.out.println(liste.toString());
					System.out.println("Inserisci Nome lista");
					sceltalista = Input.readString();
					System.out.println(GestioneListe.getListaSpesa(sceltalista).toString());
					System.out.println("Inserisci Nome articolo");
					String articoloscelto = Input.readString();
					System.out.println("Lista delle categorie\n======================");
					System.out.println(GestioneListe.listatoCategorie());
					System.out.println("Scrivi il nome della categoria scelta");
					String nuovacategoria1 = Input.readString();
					if(!GestioneListe.categoriaValida(nuovacategoria1)) {
						System.out.println("errore, categoria non valida");
						break;
					}
					ListaSpesa listaobj = GestioneListe.getListaSpesa(sceltalista);
					int indicearticolo = listaobj.cercaPerNome(articoloscelto);
					listaobj.modificaCatArticolo(indicearticolo, nuovacategoria1);
					break;
				} catch (NullPointerException e) {
					System.out.println("Input non consentito");
					break;
				}
			case 13:
				try{
				System.out.println(liste.toString());
				System.out.println("Inserisci Nome lista");
				sceltalista = Input.readString();
				System.out.println(GestioneListe.getListaSpesa(sceltalista).toString());
				System.out.println("Inserisci Nome articolo");
				String art_scelto = Input.readString();
				System.out.println("inserisci nuova quantita");
				int nuovaqta = Input.readInt();
				ListaSpesa lista_o = GestioneListe.getListaSpesa(sceltalista);
				int ind_articolo = lista_o.cercaPerNome(art_scelto);
				lista_o.modificaQtaArticolo(ind_articolo, nuovaqta);
				}catch (NumberFormatException e){
					System.out.println("Devi inserire solo numeri interi!");
			    }
				break;
			case 14:
				System.out.println("Lista articoli cancellati(rimossi):\n");
				GestioneListe.stampaCancellati();
				break;
			case 15:
				GestioneListe.svuotaCancellati();
				System.out.println("Lista rimossi svuotata!");
				break;
			case 16:	
				System.out.print(liste);
				break;
			case 0: //esci
				System.out.println("\n ti saluto! Alla prossima! ");
				break;
			default:
				break;
			}
		}while(scelta!=0);
	}
}

