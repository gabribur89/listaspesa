package eccezioni;

// estendo la classe generica delle eccezioni per aggiungere il mio messaggio
public class NumeroCampiException extends Exception {
		
	public NumeroCampiException()
		{
		
		super("Numero dei campi errato"); 
		
		} 
	
}