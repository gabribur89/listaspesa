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
		GestioneListe.ripulisci();
		Articolo a = new Articolo();
		GestioneListe.creaLista("lista a");
		GestioneListe.aggiungiArticolo("lista a", 10, a);
		assertTrue(GestioneListe.dimensioneLista()==1);
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione()==1);

	}
	
	@Test
	public void testRimuoviArticolo(){
		GestioneListe.ripulisci();
		Articolo a = new Articolo();
		a.setNome("foobar");
		GestioneListe.creaLista("lista a");
		assertTrue(GestioneListe.dimensioneLista()==1);

		GestioneListe.aggiungiArticolo("lista a", 10, a);
		System.out.println(GestioneListe.getListaSpesa("lista a").dimensione());
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione() == 1); 
		
		GestioneListe.rimuoviArticolo("lista a", "foo");
		System.out.println(GestioneListe.getListaSpesa("lista a").dimensione());
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione() == 0);

	}
	
}
