import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import eccezioni.InputError;
import eccezioni.NumeroCampiException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
* Progetto Programmazione Oggetti A.A. 2019/2020
* Universita' del Piemonte Orientale - Alessandria
*
* file GestioneListeTest.java
*
* @author  Buratto Gabriele - 10025213
*/
public class GestioneListeTest {

	@AfterClass
	public static void svuotatutto() throws Exception {
		GestioneListe.svuotaliste();
		GestioneListe.svuotaCancellati();
	}
	
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
		GestioneListe.creaLista("lista");
		assertTrue(GestioneListe.dimensioneLista()!=0);
		GestioneListe.aggiungiArticolo("lista", 10, a);
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione()!=0);

	}
	
	@Test
	public void testRimuoviArticolo(){

		GestioneListe.svuotaliste();
		Articolo a = new Articolo();
		a.setNome("foobar");
		GestioneListe.creaLista("lista a");
		assertTrue(GestioneListe.dimensioneCancellati() == 0);
		// controlla quante liste ci sono
		assertTrue(GestioneListe.dimensioneLista()==1);

		GestioneListe.aggiungiArticolo("lista a", 10, a);
		System.out.println(GestioneListe.getListaSpesa("lista a").dimensione());
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione() == 1); 
		
		GestioneListe.rimuoviArticolo("lista a", "foo");
		System.out.println(GestioneListe.getListaSpesa("lista a").dimensione());
		assertTrue(GestioneListe.getListaSpesa("lista a").dimensione() == 0);
		
		assertTrue(GestioneListe.dimensioneCancellati() == 1);

	}
	
	@Test
	public void testCercaArticoloNelleListe() {
		Articolo a = new Articolo();
		a.setNome("foobar");
		Articolo b = new Articolo();
		b.setNome("newbar");

		GestioneListe.svuotaliste();
		GestioneListe.creaLista("lista a");
		GestioneListe.creaLista("lista b");
		GestioneListe.creaLista("lista c");

		GestioneListe.aggiungiArticolo("lista a", 10, a);
		GestioneListe.aggiungiArticolo("lista b", 2, a);
		GestioneListe.aggiungiArticolo("lista c", 2, b);
		
		ArrayList<String> risultato = GestioneListe.cercaArticoloInTutteLeListe("foobar");
		System.out.println(risultato);
		assertTrue(risultato.size() == 2);
	}
	
	@Test
	public void testCercaArticolo(){
		
		Articolo a = new Articolo();
		a.setNome("foobar");
		Articolo b = new Articolo();
		b.setNome("newbar");

		GestioneListe.svuotaliste();
		GestioneListe.creaLista("lista a");
		GestioneListe.creaLista("lista b");
		GestioneListe.creaLista("lista c");

		GestioneListe.aggiungiArticolo("lista a", 10, a);
		GestioneListe.aggiungiArticolo("lista a", 10, b);
		GestioneListe.aggiungiArticolo("lista b", 2, a);
		GestioneListe.aggiungiArticolo("lista c", 2, b);
		
		int risultato = GestioneListe.cercaArticolo("lista a",b.getNome());
		System.out.println(risultato);
		assertTrue(risultato == 1);
		
	}
	
	@Test
	public void testEsisteLista(){
		GestioneListe.creaLista("prova");
		assertTrue(GestioneListe.esisteLista("prova"));
	}
	
	@Test
	public void testEsisteElementoLista(){
		GestioneListe.creaLista("prova");
		assertTrue(GestioneListe.esisteElementoLista());
		GestioneListe.creaLista(null);
		assertTrue(GestioneListe.esisteElementoLista());
	}
	
	@Test
	public void testAggiungiCategoria(){
		GestioneListe.aggiungiCategoria("carne");
		assertTrue(GestioneListe.dimensioneCategoria()==1);
	}
	
	@Test
	public void testEsisteCategoria(){
		GestioneListe.aggiungiCategoria("pesce");
		assertTrue(GestioneListe.esisteCategoria("pesce"));
	}
	
	@Test
	public void testApriFile1() throws FileNotFoundException, IOException, InputError, NumeroCampiException{
		System.out.println("FILE LETTO CORRETTAMENTE:");
		String fileok = "leggimi.csv";
		GestioneListe.leggidafile(fileok);
		System.out.println("FINE FILE LETTO CORRETTAMENTE:\n\n");
	}
	
	@Test
	public void testApriFile2() throws FileNotFoundException, IOException, InputError, NumeroCampiException{
		System.out.println("FILE CHE NON VA A BUON FINE 1:");
		String fileno = "bbb";
		GestioneListe.leggidafile(fileno);
	}
	
}
