import static org.junit.Assert.*;
import org.junit.Test;

public class ListaSpesaTest {

	@Test
	public void aggiungiArticolo(){
		ListaSpesa l = new ListaSpesa();
		Articolo a = new Articolo();
		a.setNome("riso");
		a.setQta(2);
		a.setCategoria("cibo");
		l.aggiungi(a);
		assertTrue(l.dimensione()==1);
	}
	
	@Test
	public void eliminaArticolo(){
		ListaSpesa l = new ListaSpesa();
		Articolo a = new Articolo();
		a.setNome("riso");
		a.setQta(2);
		a.setCategoria("cibo");
		int indice = l.aggiungi(a);
		assertTrue(l.dimensione()==1);
		l.elimina(indice);
		assertTrue(l.dimensione()==0);
	}
	
	@Test
	public void cercaArticolo(){
		ListaSpesa l = new ListaSpesa();
		Articolo a = new Articolo();
		a.setNome("riso");
		a.setQta(2);
		a.setCategoria("cibo");
		l.aggiungi(a);
		int indice = l.cercaPerNome("riso");
		assertTrue(indice != -1);
	}
	
}
