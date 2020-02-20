import java.util.ArrayList;

import javax.swing.event.ListDataListener;

public class ListaSpesa {

	//creo la lista 'ufficiosa'
	private static ArrayList<Articolo> lista = new ArrayList<Articolo>();

	public int dimensione(){	 
		return lista.size();
	}
	
	public int aggiungi(Articolo a) {
		 lista.add(a);
		 return 1;
	}
	
	public Articolo elimina(int i){
		return lista.remove(i);
	}
	
	public int cercaPerNome(String n){
		for(int i=0;i<lista.size();i++){
			String nome = lista.get(i).getNome();
						
			if (nome.compareTo(n) == 0){
				return i; //restituisco indice se ho trovato il nome
			}
		}
		return -1; //se non ho trovato un nome, ritorno -1
	}
	
	public void svuota(){
		lista.clear();
	}
}
