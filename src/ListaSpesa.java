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
	
	public void svuota(){
		lista.clear();
	}
}
