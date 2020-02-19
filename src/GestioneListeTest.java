import static org.junit.Assert.*;

import org.junit.Test;

public class GestioneListeTest {

	@Test
	public void testLista() {
		GestioneListe.creaLista("prova");
		assertTrue(GestioneListe.dimensioneLista()==1);
		GestioneListe.cancellaLista("prova");
		assertTrue(GestioneListe.dimensioneLista()==0);
	}
	
	@Test
	public void testCategoria(){
		GestioneListe.aggiungiCategoria("bagno");
		assertTrue(GestioneListe.dimensioneCategoria()==1);
		GestioneListe.eliminaCategoria("bagno");
		assertTrue(GestioneListe.dimensioneCategoria()==0);
	}
	
	@Test
	public void testAggiungiArticolo(){
		Articolo a = new Articolo();
		GestioneListe.creaLista("lista a");
		GestioneListe.aggiungiArticolo("lista a", 10, a);
		// ho una listaspesa 
		assertTrue(GestioneListe.dimensioneLista()==1);
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione()==1);
	}
}
