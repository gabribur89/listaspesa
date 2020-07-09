import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;
/**
* Progetto Programmazione Oggetti A.A. 2019/2020
* Universita' del Piemonte Orientale - Alessandria
*
* file ArticoloTest.java
*
* @author  Buratto Gabriele - 10025213
*/
public class ArticoloTest {

	@Test
	public void testEquals() {
		Articolo art = new Articolo();
		art.setNome("foo");
		art.setCategoria("bar");
		System.out.println(art);
		
		Articolo copy = new Articolo(art);
		System.out.println(copy);
		
		assertTrue(art.equals(copy));
	}

}
