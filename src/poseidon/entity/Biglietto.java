package poseidon.entity;

import java.time.LocalTime;
import java.time.LocalDate;

public class Biglietto {
	private int codiceBiglietto;
	private LocalDate data;
	private LocalTime ora;
	private int codiceCorsa;
	private int codiceImpiegato;
	
	public Biglietto(int codiceBiglietto, LocalDate data, LocalTime ora, int codiceCorsa, int codiceImpiegato) {
		super();
		this.codiceBiglietto = codiceBiglietto;
		this.data = data;
		this.ora = ora;
		this.codiceCorsa = codiceCorsa;
		this.codiceImpiegato = codiceImpiegato;
	}

	public int getCodiceBiglietto() {
		return codiceBiglietto;
	}

	public void setCodiceBiglietto(int codiceBiglietto) {
		this.codiceBiglietto = codiceBiglietto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public int getCodiceCorsa() {
		return codiceCorsa;
	}

	public void setCodiceCorsa(int codiceCorsa) {
		this.codiceCorsa = codiceCorsa;
	}

	public int getCodiceImpiegato() {
		return codiceImpiegato;
	}

	public void setCodiceImpiegato(int codiceImpiegato) {
		this.codiceImpiegato = codiceImpiegato;
	}
	
	@Override
	public boolean equals(Object o) {
		Biglietto b = (Biglietto)o;
		
		if (this.data == null && this.ora == null) {
			return (this.codiceBiglietto == b.codiceBiglietto &&
					this.data == b.data &&
					this.ora == b.ora &&
					this.codiceCorsa == b.codiceCorsa &&
					this.codiceImpiegato == b.codiceImpiegato);
		}
		else if (this.data == null) {
			return (this.codiceBiglietto == b.codiceBiglietto &&
					this.data == b.data &&
					this.ora.equals(b.ora) &&
					this.codiceCorsa == b.codiceCorsa &&
					this.codiceImpiegato == b.codiceImpiegato);
		}
		else if (this.ora == null) {
			return (this.codiceBiglietto == b.codiceBiglietto &&
					this.data.equals(b.data) &&
					this.ora == b.ora &&
					this.codiceCorsa == b.codiceCorsa &&
					this.codiceImpiegato == b.codiceImpiegato);
		}
		else {
			return (this.codiceBiglietto == b.codiceBiglietto &&
				this.data.equals(b.data) &&
				this.ora.equals(b.ora) &&
				this.codiceCorsa == b.codiceCorsa &&
				this.codiceImpiegato == b.codiceImpiegato);
		}
	}
	
}
