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
		System.out.println("1 - Crea una lista");
		//System.out.println("2 - Crea una categoria");
		System.out.println("3 - Leggi lista da file");
		System.out.println("4 - Scrivi lista su file");
		System.out.println("5 - Ordina elementi lista");
		System.out.println("6 - Inserisci un articolo in una lista (con categoria e quantita')");
		System.out.println("7 - Cerca un articolo nelle due liste");
		System.out.println("8 - Rimuovi un articolo (lo mette nella lista dei rimossi)");
		System.out.println("9 - Ripristina un articolo dalla lista dei rimossi");
		System.out.println("10 - Modifica categoria di un articolo");
		System.out.println("11 - Modifica quantita' di un articolo");
		System.out.println("12 - Stampa lista cancellati");
		System.out.println("13 - Svuota lista rimossi");
		System.out.println("14 - Stampa liste");
		System.out.println("0 - Esci ");
		System.out.println("\nInserisci un comando: ");
		System.out.println("\n\n\n");
	}
	
	
	// Inizio del programma
	public static void main(String[] args) throws SceltaSbagliata{
		
		int scelta = 0;
		String sceltalista;
		
		// determina se e' gia' stato prodotto il menu o no
		boolean esistente = false;
		GestioneListe liste = new GestioneListe();
		ListaSpesa li = new ListaSpesa();
		Articolo a = new Articolo();
		
		do{
			MainClass.stampaMenu(esistente);
			esistente = true; 
			
			try { 
				scelta=Input.readInt();
				System.out.println(scelta);
				if((scelta<0) || (scelta>14))
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
				System.out.print("Inserisci il nome della lista!\n\n");
				sceltalista = Input.readString();
				if (li.cercaPerNome(sceltalista) != -1)
				{
					System.out.println("Non ho trovato niente");
				}
				GestioneListe.creaLista(sceltalista);
				break;
			case 2:
				/*
				System.out.print("Inserisci il nome della categoria!");
				String cat = Input.readString();
				GestioneListe.aggiungiCategoria(cat);
				*/
				break;
			case 3:
				try {
					GestioneListe.leggidafile();
				}catch(InputError e){
					System.out.println("Non ho trovato il file....");
				}
				catch (Exception e) {
					System.out.println("Errore nella lettura da file");
				}
				System.out.println("File caricato correttamente");
				break;
			case 4:
				try {
					System.out.println("Nome lista da salvare su file");
					String nomelista = Input.readString();
					GestioneListe.scrivisufile(nomelista);
				}catch (Exception e) {
					System.out.println("Errore nel salvataggio su file");
				}
				System.out.println("File salvato correttamente");
				break;
			case 5://ordinamento
				// chiedi il nome della lista
				System.out.println("Nome lista:\n");
				String nome = Input.readString();
				// ottenere la lista 
				ListaSpesa l = GestioneListe.getListaSpesa(nome);
				l.ordina();
				//GestioneListe.ordina(a);
				System.out.println(l); 
				break;
			case 6:
				Articolo art = new Articolo();
				System.out.println("Nome della lista");
				String nomelista = Input.readString();
				System.out.println("Nome dell'articolo");
				art.setNome(Input.readString());
				System.out.println("Nome Categoria");
				art.setCategoria(Input.readString());
				System.out.println("quantita");
				GestioneListe.aggiungiArticolo(nomelista, Input.readInt(), art);
				break;
			case 7:
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
			case 8:
				System.out.print("Nome della lista\n");
				sceltalista = Input.readString();
				System.out.print("Prefisso dell'articolo\n");
				String nomear = Input.readString();
				GestioneListe.rimuoviArticolo(sceltalista, nomear);
				System.out.print("articolo con nome: " + nomear + " eliminato!\n");
				break;
			case 9:
				System.out.println("Inserisci il nome della lista:\n");
				String nomelista1 = Input.readString();
				System.out.println("Inserisci nome articolo da ripristinare nella lista della spesa:\n");
				String nome_art = Input.readString();
				GestioneListe.ripristinaCancellato(nomelista1, nome_art);
				break;
			case 10:
				System.out.println(liste.toString());
				System.out.println("Inserisci Nome lista");
				sceltalista = Input.readString();
				System.out.println(GestioneListe.getListaSpesa(sceltalista).toString());
				System.out.println("Inserisci Nome articolo");
				String articoloscelto = Input.readString();
				System.out.println("inserisci nuova categoria");
				String nuovacategoria = Input.readString();
				ListaSpesa listaobj = GestioneListe.getListaSpesa(sceltalista);
				int indicearticolo = listaobj.cercaPerNome(articoloscelto);
				listaobj.modificaCatArticolo(indicearticolo, nuovacategoria);
				break;
			case 11:
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
				break;
			case 12:
				System.out.println("Lista articoli cancellati:\n");
				GestioneListe.stampaCancellati();
				break;
			case 13:
				GestioneListe.svuotaCancellati();
				System.out.println("Lista rimossi svuotata!");
				break;
			case 14:	
				System.out.print(liste);
			case 0: //esci
				System.out.println("\n ti saluto! Alla prossima! ");
			default:
				break;
			}
		}while(scelta!=0);
	}
}

