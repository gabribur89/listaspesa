public class Articolo {
	
	protected String nome;
	protected String categoria;
	protected int qta;
	
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
		
	public Articolo() {
		// TODO Auto-generated constructor stub
	}

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
    	
        // Compare the data members and return accordingly, non considero la quantita   
        return (this.nome == c.nome) && (this.categoria == c.categoria); 
    } 
	
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
	
	@Override
	public String toString(){
		return String.format("nome articolo: "+ this.nome +"\n"+ 
				             "categoria: "+ this.categoria + "\n"+
				             "quantita': "+ Integer.toString(this.qta));
	}
	
	public void stampa() {
		System.out.println("-----------------");
		System.out.println("nome articolo: "+getNome());
		System.out.println("categoria: "+getCategoria());
		System.out.println("quantita': "+getQta());
}
}
