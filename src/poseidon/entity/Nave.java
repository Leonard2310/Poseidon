package poseidon.entity;

public class Nave {
	private String nome;
	private int capienzaAutoveicoli;
	private int capienzaPassegeri;
	private double prezzo;
	private String categoria;
	private int codiceCorsa;
	
	public Nave(String nome, int capienzaAutoveicoli, int capienzaPassegeri, double prezzo, String categoria, int codiceCorsa) {
		super();
		this.nome = nome;
		this.capienzaAutoveicoli = capienzaAutoveicoli;
		this.capienzaPassegeri = capienzaPassegeri;
		this.prezzo = prezzo;
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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
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
	
}
