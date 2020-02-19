import java.util.ArrayList;
import java.util.HashMap;

public class GestioneListe {
	
	/*un campo statico listeSpesa con l'associazione fra il 
	nome di una lista e un riferimento a un oggetto di tipo ListaSpesa*/
	private static HashMap<String, ListaSpesa> listeSpesa = new HashMap<String,ListaSpesa>();
	/*un campo static categorie che lista tutte
	le categorie (che sono comuni a tutte le liste della spesa)*/
	private static ArrayList<String> categorie = new ArrayList<String>();
	//campo cancellati che rappresenta la lista 'cestino', dei rimossi
	private static ListaSpesa cancellati = new ListaSpesa();
	
	private static ArrayList<Articolo> art = new ArrayList<Articolo>();
	
	// 1) Creare una lista dandole un nome
	public static void creaLista(String nomeLista){
		if(!listeSpesa.containsKey(nomeLista)) 
		{
			listeSpesa.put(nomeLista, new ListaSpesa());
		}
	}
	
	// 2) Creare una categoria
	// sarebbe un metodo analogo a setCategoria() della classe Articolo ???
	
	/* 5) Inserire un articolo in una lista con una quantità 
	(inizialmente l’articolo non è categorizzato)*/
	public static void aggiungiArticolo(String nomeLista, int q, Articolo a){
		art.add(a);
	}
	
	/* 6) Cercare un articolo fornendo il prefisso della stringa 
	(cercare anche nella lista degli articoli rimossi).
	La ricerca ritorna l’indice dell’articolo nella lista della spesa o in quella dei rimossi.*/
	public static ArrayList<Articolo> cercaArticolo(String nomeRub, String prefisso){
		return art;
	}
	
	// 7) Rimuovere un articolo da una lista (questo lo mette nella lista dei rimossi).
	public static boolean rimuoviArticolo(String Rubrica, String prefisso){
		return true;
	}
	
	// metodo che restituisce la dimensione di una lista
	public static int dimensioneLista(){
		return listeSpesa.size();
	}
	
	// metodo che cancella una lista 
	public static boolean cancellaLista(String nomeLista) {
		if(listeSpesa.containsKey(nomeLista))
		{
			listeSpesa.remove(nomeLista);
			return true;
		}
		return false;
	}
	
}/*
	…………
	}
	public static void aggiungiElRubrica(String nomeRub, String con) throws ??? {
	}
	public static ArrayList<String> cercaElRubrica(String nomeRub,String prefisso) {
	…………
	}
	public static boolean rimuoviElRubrica(String Rubrica,String prefisso) {
	…………
	}
	public static String toStringRubrica(String Rubrica) {
	…………
	}*/

