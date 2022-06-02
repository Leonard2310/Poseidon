package poseidon.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class BigliettoVeicolo extends Biglietto {
	private String targa;

	public BigliettoVeicolo(int codiceBiglietto, LocalDate data, LocalTime ora, int codiceCorsa, int codiceImpiegato,
			String targa) {
		super(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
		this.targa = targa;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

}
