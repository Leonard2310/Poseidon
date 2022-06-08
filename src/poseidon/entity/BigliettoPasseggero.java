package poseidon.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class BigliettoPasseggero extends Biglietto {

	public BigliettoPasseggero(int codiceBiglietto, LocalDate data, LocalTime ora, int codiceCorsa,
			int codiceImpiegato) {
		super(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
	}
	
	@Override
	public boolean equals(Object o) {
		BigliettoPasseggero b = (BigliettoPasseggero)o;
		return (super.equals(b));
	}

}
