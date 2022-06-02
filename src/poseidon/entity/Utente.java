package poseidon.entity;

public class Utente {
	private String cognome;
	private String nome;
	private String password;

	public Utente(String cognome, String nome, String password) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.password = password;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
