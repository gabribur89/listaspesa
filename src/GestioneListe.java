import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import eccezioni.InputError;

public class GestioneListe {
	
	/*un campo statico listeSpesa con l'associazione fra il 
	nome di una lista e un riferimento a un oggetto di tipo ListaSpesa*/
	private static HashMap<String, ListaSpesa> listeSpesa = new HashMap<String,ListaSpesa>();
	/*un campo static categorie che lista tutte
	le categorie (che sono comuni a tutte le liste della spesa)*/
	private static ArrayList<String> categorie = new ArrayList<String>();
	//campo cancellati che rappresenta la lista 'cestino', dei rimossi
	private static ListaSpesa cancellati = new ListaSpesa();
	
	private String out;
	
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
	public void leggidafile() throws FileNotFoundException, IOException {
		try {
		
			BufferedReader br = new BufferedReader(new FileReader("leggimi.csv")); 
		    //StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	System.out.println(line);
		    	String[] campi = line.split(",");
		    	if(campi.length == 3)
		    	{
		    	Articolo a = new Articolo();
		    	a.setNome(campi[0]);
		    	a.setCategoria(campi[1]);
		    	a.setQta(Integer.parseInt(campi[2]));
		    	inserisci(a);
		    	System.out.println(Arrays.toString(campi));
		    	}
		    	else throw new NumeroCampiException();
		        //sb.append(line);
		        //sb.append(System.lineSeparator());
		        line = br.readLine();
		        
		        //Agenda.inserisciApp(a);
		    }
		    br.close();
		    //String everything = sb.toString();
		}catch(FileNotFoundException e)
		{
			throw new InputError("Leggi da file InputError",e);
		}
	}
	
	public void scrivisufile(){
		
		try {
	           FileWriter fw = new FileWriter(this.out,true);
	           //fw.write(date+" : "+s);
	           Iterator<Articolo> iter = a.iterator(); //metodo restituente iteratore per uso cicli
	    	   while (iter.hasNext()){
	    		   Articolo a = iter.next();
	    		   //System.out.print(a.getOrario().toString());
	    		   fw.write(a.getNome()+"," + a.getCategoria()+"," + a.getQta()+"," 
	    		   +"\n");
	    	   }
	           fw.close();
	       } catch (IOException ex) {
	           System.err.println("Non posso salvarlo, mi dispiace");
	       }
		
	}
	
	// 5) Iterare sugli elementi di una lista (che devono essere mantenuti ordinati)
	/*ordinamento per nome, creo una copia dell'articolo
	 * e lavoro su questa, per poi poterla stampare correttamente*/
	public void ordina(){
	    
		 ListaSpesa first;
	     ListaSpesa next;
	     int i = listeSpesa.size();
	     while(i>0) 
	       {
	       for(int j=0; j < i-1; j++) 
	         {
	         first = listeSpesa.get(j);
	         next = listeSpesa.get(j+1);
	         if(first.getNome().compareTo(next.getNome())>0)
	         {
	        	 //scambiare il '<' con '>' per ottenere un ordinamento decrescente
	           exchange(listeSpesa,j,j+1);
	         }
	         }
	       i--;
	     }
	     
	     
	     //return a;
	}
	
	
   public void exchange(ArrayList<Articolo> a, int i, int j) 
     {
     Articolo tmp = a.get(i);
     a.set(i, a.get(j));
     a.set(j, tmp);
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
	public int cercaArticolo(String nomeLista, String prefisso){
		for(int i=0;i<listeSpesa.size();i++)
		{
			
		}
		return -1; //se non ho trovato, ritorno -1
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
	
	// 10) Modificare la categoria 
	
	// 11) modificare la quantita di un articolo
	
	// 12) Svuotare la lista degli articoli rimossi
	public static void svuotaCancellati(){
		cancellati.svuota();
	}
	
	// metodo che restituisce la dimensione di una lista
	public static int dimensioneLista(){
		return listeSpesa.size();
	}
	
	public static void ripulisci() {
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
	public String toString() {
		String out = new String(); 
		for (String name: listeSpesa.keySet()){
            out += name.toString()+"\n";
		}

		return out;
		
	}

}
