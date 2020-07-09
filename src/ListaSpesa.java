import java.util.ArrayList;
import java.util.Iterator;

/*
 * Classe Lista della spesa. implementa l'interfaccia Iterable<>. In questo modo è possile usare gli iteratori direttamente 
 * per questa classe mentre invece di dover accedere all'attributo lista di tipo ArrayList<Articolo>. In mancanza di 
 * questo si sarebbe dovuto accedere all'attributo lista per poter iterare sul suo contenuto , ma questo non è possibile 
 * perché è privato. 
 */
public class ListaSpesa implements Iterable<Articolo>{

	//creo la lista 'ufficiosa'
	private ArrayList<Articolo> lista = new ArrayList<Articolo>();

	// riscrivo la funzione Iterator di Iterable per il mio scopo, cioè iterare sulla lista di articoli
	@Override
    public Iterator<Articolo> iterator() {
        return lista.iterator();
    }

	
	public int dimensione(){	 
		return lista.size();
	}
	
	public Articolo getArticolo(int indice){
		return lista.get(indice);
	}
	
	public int aggiungi(Articolo a) {
		 lista.add(a);
		 return lista.indexOf(a);
	}
	
	public Articolo elimina(int i){
		System.out.println(i);
		return lista.remove(i);
	}
	
	// 10) Modificare la categoria
	public boolean modificaCatArticolo(int i, String cat){
		Articolo a = lista.get(i);
		a.setCategoria(cat);
		lista.set(i, a);
		
		return true;
	}
	
	// 11) modificare la quantita di un articolo
	public boolean modificaQtaArticolo(int i, int qta){
		Articolo a = lista.get(i);
		a.setQta(qta);
		lista.set(i, a);
		
		return true;
	}
	
	//cerco nome di un articolo
	public int cercaPerNome(String n){
		for(Articolo art: lista){
			if (art.getNome().contains(n)){
				return lista.indexOf(art); //restituisco indice se ho trovato il nome
			}
		}
		return -1; //se non ho trovato un nome, ritorno -1
	}
	
	
	/*ordinamento per nome, creo una copia dell'articolo
	 * e lavoro su questa, per poi poterla stampare correttamente*/
	public void ordina(){
	    
		 Articolo first;
	     Articolo next;
	     int i = lista.size();
	     while(i>0) 
	       {
	       for(int j=0; j < i-1; j++) 
	         {
	         first = (Articolo) lista.get(j);
	         next = (Articolo) lista.get(j+1);
	         if(first.getNome().compareTo(next.getNome())>0)
	         {
	        	 //scambiare il '<' con '>' per ottenere un ordinamento decrescente
	           exchange(lista,j,j+1);
	         }
	         }
	       i--;
	     }
	     
	     
	     //return a;
	}
	
   // funzione accessoria usata dalla funzione di ordinamento.
   // Scambia i valori di due elementi nell'Array dati i loro indici
   public void exchange(ArrayList<Articolo> a, int i, int j) 
     {
     Articolo tmp = a.get(i);
     a.set(i, a.get(j));
     a.set(j, tmp);
     }
   
	public void stampa() {
		for(Articolo a: lista) {
		    a.stampa();
	    }
	}
	
	public void svuota(){
		lista.clear();
	}
	
	@Override
	public String toString(){
		String out = new String();
		for (Articolo a: lista) {
			out += "\nArticolo: \n" + a.toString();
		}
	
	return out;
	}
}
