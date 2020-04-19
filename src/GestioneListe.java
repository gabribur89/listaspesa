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
import eccezioni.InputError;
import eccezioni.NumeroCampiException;

public class GestioneListe {
	
	/*un campo statico listeSpesa con l'associazione fra il 
	nome di una lista e un riferimento a un oggetto di tipo ListaSpesa*/
	private static HashMap<String, ListaSpesa> listeSpesa = new HashMap<String,ListaSpesa>();
	/*un campo static categorie che lista tutte
	le categorie (che sono comuni a tutte le liste della spesa)*/
	private static ArrayList<String> categorie = new ArrayList<String>();
	//campo cancellati che rappresenta la lista 'cestino', dei rimossi
	private static ListaSpesa cancellati = new ListaSpesa();
	
	private static File out = new File("salvato.csv");
	
	// 1) Creare una lista dandole un nome
	public static void creaLista(String nomeLista){
		if(!listeSpesa.containsKey(nomeLista)) 
		{
			listeSpesa.put(nomeLista, new ListaSpesa());
		}
	}
	
	// 2) Creare una categoria
	// sarebbe un metodo analogo a setCategoria() della classe Articolo ???
	public static void aggiungiCategoria(String s){
		categorie.add(s);
	}
	
	// eliminazione categoria
	public static void eliminaCategoria(String s){
		categorie.remove(s);
	}
	
	// dimensione categoria
	public static int dimensioneCategoria(){
		return categorie.size();
	}
	
	
	
	/* 3) Leggere e 4) scrivere una lista su un file (potete scegliere se usare un file di testo 
	oppure serializzare la classe)*/
	public static void leggidafile() throws FileNotFoundException, IOException, InputError, NumeroCampiException {
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("leggimi.csv")); 
		    //StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	System.out.println(line);
		    	String nomelista;
		    	// campi nel file
		    	String[] campi = line.split(",");
		    	if(campi.length == 3)
		    	{
		    	Articolo a = new Articolo();
		    	nomelista = campi[0];
		    	a.setNome(campi[1]);
		    	a.setCategoria(campi[3]);
		        GestioneListe.aggiungiArticolo(nomelista, Integer.parseInt(campi[2]), a);
		    	System.out.println(Arrays.toString(campi));
		    	}
		    	else throw new NumeroCampiException();
		        line = br.readLine();
		        
		    }
		    br.close();
		    
		}catch(FileNotFoundException e)
		{
			throw new InputError("Leggi da file InputError",e);
		}
	}
	

	public static void scrivisufile(String nomelista){
		
		try {
	           FileWriter fw = new FileWriter(out,true);
	           //fw.write(date+" : "+s);
	           ListaSpesa l = listeSpesa.get(nomelista);
	           Iterator<Articolo> iter = l.iterator(); //metodo restituente iteratore per uso cicli
	    	   while (iter.hasNext()){
	    		   Articolo a = iter.next();
	    		   fw.write(nomelista+"," + a.getNome()+"," + a.getCategoria()+"," + a.getQta()+"," 
	    		   +"\n");
	    	   }
	           fw.close();
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
		if(cancellati.cercaPerNome(prefisso) != -1){
			out.add("cancellati");
		}
		return out; 
	}
	
	public static int cercaArticolo(String nomelista, Articolo a){
		
		//di default
		int indice = -1;
		
		if(listeSpesa.containsKey(nomelista))
		{
			indice = listeSpesa.get(nomelista).cercaPerNome(a.getNome());
			if( indice != -1)
			{
				return indice;
			}
		}
		
		indice = cancellati.cercaPerNome(a.getNome());
		if(indice != -1)
		{
			return indice;
		}
		
		return indice;
		
	}
	
	// 8) Rimuovere un articolo da una lista (questo lo mette nella lista dei rimossi).
	public static boolean rimuoviArticolo(String nomeLista, String prefisso){
		if(listeSpesa.containsKey(nomeLista))
		{
			int indice = listeSpesa.get(nomeLista).cercaPerNome(prefisso);
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
	public static boolean ripristinaCancellato(String nome){
		if(cancellati.dimensione()>0)
		{
			for()
			cancellati.elimina(i);
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

	// metodo toString per stampare le liste  
	@Override
	public String toString() {
		String out = new String(); 
		
		System.out.println("Liste: \n");
		for(HashMap.Entry<String, ListaSpesa> entry : listeSpesa.entrySet()) {
		    out += "Lista: " + entry.getKey().toString() + "\n\n" + entry.getValue().toString() + "\n\n";
		}

		return out;
		
	}

}
