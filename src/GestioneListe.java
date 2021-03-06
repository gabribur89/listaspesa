import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import eccezioni.InputError;
import eccezioni.NumeroCampiException;

/**
* Progetto Programmazione Oggetti A.A. 2019/2020
* Universita' del Piemonte Orientale - Alessandria
*
* file GestioneListe.java
*
* @author  Buratto Gabriele - 10025213
*/
public class GestioneListe {
	
	/*un campo statico listeSpesa con l'associazione fra il 
	nome di una lista e un riferimento a un oggetto di tipo ListaSpesa*/
	private static HashMap<String, ListaSpesa> listeSpesa = new HashMap<String,ListaSpesa>();
	
	//un campo static categorie che lista tutte
	//le categorie (che sono comuni a tutte le liste della spesa)
	private static ArrayList<String> categorie = new ArrayList<String>();
	
	//campo cancellati che rappresenta la lista 'cestino', dei rimossi
	//private static ListaSpesa cancellati = new ListaSpesa();
	
	//lista cestino ma come HashMap
	private static ListaSpesa cancellati = new ListaSpesa();
	
	/*file da salvare
	private static File out = new File("salvato.csv");
	*/
	
	// 1) Creare una lista dandole un nome
	public static void creaLista(String nomeLista){
		if(!listeSpesa.containsKey(nomeLista)) 
		//se il mio oggetto listeSpesa non contiene un nome, lo aggiungo
		//e di conseguenza viene creato l'oggetto stesso
		{
			listeSpesa.put(nomeLista, new ListaSpesa());
		}
	}
	
	public static boolean esisteLista(String nomeLista) {
		if(listeSpesa.containsKey(nomeLista))
		{
			return true;
		}
		return false;
	}
	
	public static boolean esisteElementoLista() {
		if(!listeSpesa.isEmpty())
		{
			return true;
		}
		return false;
	}
	
	// 2) Creare una categoria
	public static void aggiungiCategoria(String s){
			categorie.add(s);
	}
	
	public static boolean esisteCategoria(String nomeCat) {
		if(categorie.contains(nomeCat))
		{
			return true;
		}
		return false;
	}
	
	// eliminazione categoria
	public static void eliminaCategoria(String s){
		categorie.remove(s);
	}
	
	// dimensione categoria
	public static int dimensioneCategoria(){
		return categorie.size();
	}
	
	//se vi sono categorie, ok
	public static boolean elemCategoria(){
		if(categorie.size()>=0)
		{
			return true;
		}
		return false;
	}
	
	//modifica categoria
	public static void modificaCategoria(String vecchia, String nuova){
		categorie.remove(vecchia);
		categorie.add(nuova);
	}
	
	public static String listatoCategorie() {
		String out = "";
		for (String cat: categorie) {
			out = out + cat + "\n";
		}
		
		return out;
	}
	
	public static boolean categoriaValida(String nomecat) {
		return categorie.contains(nomecat);
	}
	
	// legge una o piu' liste della spesa da un file CSV con questa struttura 
	// genera una eccezione in caso di errore
	// es:
	// 
	// listascorta,fagioli,5,legumi
	// listascorta,sapone,2,bagno
	public static void leggidafile(String filename) throws FileNotFoundException, IOException, InputError, NumeroCampiException {
		try {
		
			BufferedReader br = new BufferedReader(new FileReader(filename));
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	System.out.println(line);
		    	String nomelista;
		    	// campi nel file
		    	String[] campi = line.split(",");
		    	if(campi.length == 4)
		    	{	
		    	Articolo a = new Articolo();
		    	nomelista = campi[0];
		    	if (!listeSpesa.containsKey(nomelista))
		    		creaLista(nomelista);
		    	
		    	a.setNome(campi[1]);
		    	a.setCategoria(campi[3]);
			    aggiungiArticolo(nomelista, Integer.parseInt(campi[2]), a);
		    	System.out.println(Arrays.toString(campi));
		    	}
		    	else throw new NumeroCampiException();
		        line = br.readLine();
		        
		    }
		    br.close();
		// se il file non viene trovato lancia questa eccezione    
		}catch(FileNotFoundException e)
		{
			throw new InputError("Leggi da file InputError",e);
		}
	}
	
	// Dato il nome della lista ne scrive il contenuto in un file
	public static void scrivisufile(String nomelista){
		
		File out = new File(nomelista);
		
		try {
	           FileWriter fw = new FileWriter(out,true);
	           ListaSpesa l = listeSpesa.get(nomelista);
	           Iterator<Articolo> iter = l.iterator(); //metodo restituente iteratore per uso cicli
	    	   while (iter.hasNext()){
	    		   Articolo a = iter.next();
	    		   fw.write(nomelista +"," + a.getNome() + "," + Integer.toString(a.getQta()) +"," + a.getCategoria() + "\n");
	    	   }
	           fw.close();
	       // eccezione sollevata dal FileWriter in caso di problemi con le operazioni su file
	       } catch (IOException ex) {
	           System.err.println("Non posso salvarlo, mi dispiace");
	       }
		
	}
	
	
	/* 6) Inserire un articolo in una lista con una quantita 
	(inizialmente l'articolo non e' categorizzato)*/
	public static void aggiungiArticolo(String nomeLista, int q, Articolo a){
		a.setQta(q);
		if(listeSpesa.containsKey(nomeLista))
		{
			listeSpesa.get(nomeLista).aggiungi(a);
		}
		
	}
	
	/* 7) Cercare un articolo fornendo il prefisso della stringa 
	(cercare anche nella lista degli articoli rimossi).
	La ricerca ritorna l'indice dell' articolo nella lista della spesa o in quella dei rimossi.*/
	public static ArrayList<String> cercaArticoloInTutteLeListe(String prefisso){
		// qui metto i nomi delle liste dove ho trovato l'articolo
		ArrayList<String> out = new ArrayList<String>();
		// itero tutte le liste
		for(HashMap.Entry<String, ListaSpesa> lista : listeSpesa.entrySet()) 
		//for(int i=0;i<listeSpesa.size();i++)
		{
			// per ogni elemento dell'hashmap prendo la lista ed eseguo il suo metodo cerca()
			if(lista.getValue().cercaPerNome(prefisso) != -1) {
				out.add(lista.getKey());
			}
			
		}
		
		//ricerca in lista rimossi
		int trovato = cancellati.cercaPerNome(prefisso);
		if(trovato != -1){
			out.add("cancellati");
		}
		
		/*for(HashMap.Entry<String, ListaSpesa> lista : cancellati.entrySet()) 
		//for(int i=0;i<listeSpesa.size();i++)
		{
			// per ogni elemento dell'hashmap prendo la lista ed eseguo il suo metodo cerca()
			if(lista.getValue().cercaPerNome(prefisso) != -1) {
				out.add(lista.getKey());
			}
			
		}*/
		
		return out; 
	}
	
	// cerca un articolo in una determinata lista della spesa e nella lista rimossi
	// output: l'indice dell'articolo trovato , altrimenti -1 
	public static int cercaArticolo(String nomelista, String nomeart){
		
		//di default
		int indice = -1;
		
		if(listeSpesa.containsKey(nomelista))
		{
			indice = listeSpesa.get(nomelista).cercaPerNome(nomeart);
			if( indice != -1)
			{
				return indice;
			}
		}
		
		/*indice = cancellati.get(nomelista).cercaPerNome(nomeart);
		if(indice != -1)
		{
			return indice;
		}*/
		
		return indice;
		
	}
	
	// 8) Rimuovere un articolo da una lista (questo lo mette nella lista dei rimossi).
	public static boolean rimuoviArticolo(String nomeLista, String nomearticolo){
		if(listeSpesa.containsKey(nomeLista))
		{
			int indice = listeSpesa.get(nomeLista).cercaPerNome(nomearticolo);
			if (indice == -1)
				return false;
			
			Articolo rimosso = listeSpesa.get(nomeLista).elimina(indice);
			cancellati.aggiungi(rimosso);
			return true;
		}
		return false;
	}
	
	/* 9) Ripristinare un articolo dalla lista dei rimossi (operazione contraria al
	   metodo 8) */
	public static boolean ripristinaCancellato(String nomeLista, String nomearticolo){
		if(cancellati.dimensione()>0)
		{
			// prima ottengo l'indice
			int trovato = cancellati.cercaPerNome(nomearticolo);
			if (trovato == -1)
				return false;
			// l'indice mi serve per ottenere l'oggetto Articolo in questione
			Articolo oggettoArt = cancellati.getArticolo(trovato);
		    // metto l'articolo nella lista
			listeSpesa.get(nomeLista).aggiungi(oggettoArt);
			cancellati.elimina(trovato);
			return true;
		}
		return false;
	}
	
	public static boolean esistonoCancellati() {
		if(cancellati.dimensione()>0)
		{
			return true;
		}
		return false;
	}
	
	// 12) Svuotare la lista degli articoli rimossi
	public static void svuotaCancellati(){
		cancellati.svuota();
	}
	
	// metodo che restituisce la dimensione di una lista
	public static int dimensioneLista(){
		return listeSpesa.size();
	}

	public static int dimensioneCancellati(){
		return cancellati.dimensione();
	}
	
	public static void svuotaliste() {
		listeSpesa.clear();
	}
	
	/*public static void stampaCancellati(){
		cancellati.stampa();
	}*/
	
	// metodo che cancella una lista 
	public static boolean cancellaLista(String nomeLista) {
		if(listeSpesa.containsKey(nomeLista))
		{
			listeSpesa.remove(nomeLista);
			return true;
		}
		return false;
	}
	
	public static ListaSpesa getListaSpesa(String nomeLista){
		return listeSpesa.get(nomeLista);
	}
	
	// metodo toString per stampare le liste della spesa o cancellati 
	@Override
	public String toString() {
		String out = new String(); 
		
		System.out.println("Liste: \n");
		for(HashMap.Entry<String, ListaSpesa> entry : listeSpesa.entrySet()) {
		    out += "Lista: " + entry.getKey().toString() + "\n\n" + entry.getValue().toString() + "\n\n";
		}
		
		/*for(HashMap.Entry<String, ListaSpesa> entry : cancellati.entrySet()) {
		    out += "Lista: " + entry.getKey().toString() + "\n\n" + entry.getValue().toString() + "\n\n";
		}*/
		out += "Lista: Cancellati\n\n " + cancellati.toString();

		return out;
		
	}

}
