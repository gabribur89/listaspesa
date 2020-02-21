import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

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
