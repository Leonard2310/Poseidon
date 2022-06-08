package poseidon.entity;

public class Dipendente extends Utente {

	private int codiceImpiegato;
	
	public Dipendente(String cognome, String nome, String password, int codiceImpiegato) {
		super(cognome, nome, password);
		this.codiceImpiegato = codiceImpiegato;
	}

	public int getCodiceImpiegato() {
		return codiceImpiegato;
	}

	public void setCodiceImpiegato(int codiceImpiegato) {
		this.codiceImpiegato = codiceImpiegato;
	}
	
	@Override
	public boolean equals(Object o) {
		Dipendente d = (Dipendente)o;
		return (super.equals(d) && this.codiceImpiegato == d.codiceImpiegato);
	}

}
