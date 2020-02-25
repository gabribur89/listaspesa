public class Articolo {
	
	protected String nome;
	protected String categoria;
	protected float qta;
	
	public Articolo(Articolo a){
		nome = a.nome;
		categoria = a.categoria;
		qta = a.qta;
	}
	
	public Articolo() {
		// TODO Auto-generated constructor stub
	}

	public void setNome(String n){
		nome = n;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setCategoria(String c){
		categoria = c;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public void setQta(float q){
		qta = q;
	}
	
	public float getQta(){
		return qta;
	}
	
	public void stampa() {
		System.out.println("-----------------");
		System.out.println("nome articolo: "+getNome());
		System.out.println("categoria: "+getCategoria());
		System.out.println("quantita': "+getQta());
}
}
