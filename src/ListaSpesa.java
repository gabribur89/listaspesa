import java.util.ArrayList;

public class ListaSpesa {

	//creo la lista 'ufficiosa'
	private static ArrayList<Articolo> lista = new ArrayList<Articolo>();

	public static int dimensione(){	 
		return lista.size();
	}
	
	public static int aggiungi(Articolo a) {
		 lista.add(a);
		 return 1;
	}
	
	public Articolo elimina(int i){
		return lista.remove(i);
	}
	
	public void svuota(){
		lista.clear();
	}
}
