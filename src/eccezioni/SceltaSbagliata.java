package eccezioni;

public class SceltaSbagliata extends Exception {

	public SceltaSbagliata(String msg, Throwable e)
	{
		super(msg,e);
	}
	
	public SceltaSbagliata(String msg)
	{
		super(msg);
	}
	
}
