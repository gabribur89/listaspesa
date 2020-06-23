/**
* Progetto Programmazione Oggetti A.A. 2019/2020
* Universita' del Piemonte Orientale - Alessandria
*
* file Articolo.java
*
* @author  Buratto Gabriele - 10025213
*/
public class Articolo {
	
	protected String nome;
	protected String categoria;
	protected int qta;
	
	//costruttori: mi permettono di inizializzare i valori dei campi degli oggetti
	public Articolo(Articolo a){
		this.nome = a.nome;
		this.categoria = a.categoria;
		this.qta = a.qta;
	}
	
	public Articolo(String nome, String cat, int qta){
		this.nome = nome;
		this.categoria = cat;
		this.qta = qta;
	}
		
	public Articolo() {}

	// equals() ridefinito per i campi di Articolo  
    @Override
    public boolean equals(Object o) { 
  
    	// Stesso object sono uguali di sicuro
    	if (o == this) { 
            return true; 
        } 
  
    	// Devo avere un oggetto compatibile con Articolo
    	if (!(o instanceof Articolo)) { 
            return false; 
        } 
        
    	Articolo c = (Articolo) o;
    	
        // Compare fra dati e ritorno di essi, non considero la quantita'   
        return (this.nome == c.nome) && (this.categoria == c.categoria); 
    } 
	
    /*
     * setter e getter
     */
    
	public void setNome(String n){
		this.nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setCategoria(String c){
		this.categoria = c;
	}
	
	public String getCategoria(){
		return this.categoria;
	}
	
	public void setQta(int q){
		this.qta = q;
	}
	
	public float getQta(){
		return this.qta;
	}
	
	//ereditarieta', mi consente di sovrascrivere(override)
	//un metodo in una sottoclasse
	@Override
	public String toString(){
		return String.format("\nnome articolo: "+ this.nome +"\n"+ 
				             "categoria: "+ this.categoria + "\n"+
				             "quantita': "+ Integer.toString(this.qta));
	}
	
	public void stampa() {
		System.out.println("\nnome articolo: "+getNome());
		System.out.println("categoria: "+getCategoria());
		System.out.println("quantita'\n: "+getQta());
}
}
