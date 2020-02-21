import java.util.ArrayList;


public class ListaSpesa {

	//creo la lista 'ufficiosa'
	private static ArrayList<Articolo> lista = new ArrayList<Articolo>();

	public int dimensione(){	 
		return lista.size();
	}
	
	public int aggiungi(Articolo a) {
		 lista.add(a);
		 return lista.indexOf(a);
	}
	
	public Articolo elimina(int i){
		System.out.println(i);
		return lista.remove(i);
	}
	
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
	
	
   public void exchange(ArrayList<Articolo> a, int i, int j) 
     {
     Articolo tmp = a.get(i);
     a.set(i, a.get(j));
     a.set(j, tmp);
     }
   
	public void stampa() {
		for(Articolo a: ListaSpesa.lista) {
		    a.stampa();
	    }
	}
	
	public void svuota(){
		lista.clear();
	}
}
