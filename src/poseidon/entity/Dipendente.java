package poseidon.entity;

public class Dipendente extends Utente {

	private int codiceImpiegato;

	public int getCodiceImpiegato() {
		return codiceImpiegato;
	}

	public void setCodiceImpiegato(int codiceImpiegato) {
		this.codiceImpiegato = codiceImpiegato;
	}

	public Dipendente(String cognome, String nome, String password, int codiceImpiegato) {
		super(cognome, nome, password);
		this.codiceImpiegato = codiceImpiegato;
	}
	
}
