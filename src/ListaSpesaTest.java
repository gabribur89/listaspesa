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
	

}
