package poseidon.entity;

public class Nave {
	private String nome;
	private int capienzaAutoveicoli;
	private int capienzaPassegeri;
	private String categoria;
	private int codiceCorsa;
	
	public Nave(String nome, int capienzaAutoveicoli, int capienzaPassegeri, String categoria, int codiceCorsa) {
		super();
		this.nome = nome;
		this.capienzaAutoveicoli = capienzaAutoveicoli;
		this.capienzaPassegeri = capienzaPassegeri;
		this.categoria = categoria;
		this.codiceCorsa = codiceCorsa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapienzaAutoveicoli() {
		return capienzaAutoveicoli;
	}

	public void setCapienzaAutoveicoli(int capienzaAutoveicoli) {
		this.capienzaAutoveicoli = capienzaAutoveicoli;
	}

	public int getCapienzaPassegeri() {
		return capienzaPassegeri;
	}

	public void setCapienzaPassegeri(int capienzaPassegeri) {
		this.capienzaPassegeri = capienzaPassegeri;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getCodiceCorsa() {
		return codiceCorsa;
	}

	public void setCodiceCorsa(int codiceCorsa) {
		this.codiceCorsa = codiceCorsa;
	}
	
	@Override
	public boolean equals(Object o) {
		Nave n = (Nave)o;
		return (this.nome.equals(n.nome) &&
				this.capienzaAutoveicoli == n.capienzaAutoveicoli &&
				this.capienzaPassegeri == n.capienzaPassegeri &&
				this.categoria.equals(n.categoria) &&
				this.codiceCorsa == n.codiceCorsa);
	}
	
}
