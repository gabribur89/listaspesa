import static org.junit.Assert.*;

import org.junit.Test;

public class GestioneListeTest {

	@Test
	public void testCreaLista() {
		GestioneListe.creaLista("prova");
		assertTrue(GestioneListe.dimensioneLista()==1);
		GestioneListe.cancellaLista("prova");
		assertTrue(GestioneListe.dimensioneLista()==0);
	}
	
}
