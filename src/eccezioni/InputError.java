package eccezioni;

public class InputError extends Exception{

	public InputError(String msg, Throwable e){
		super(msg, e);
		//System.out.println(msg);
	}
	
}
