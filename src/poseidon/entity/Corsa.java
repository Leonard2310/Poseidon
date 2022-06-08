package poseidon.entity;

import java.time.LocalTime;

public class Corsa {
	private int codiceCorsa;
	private LocalTime orarioPartenza;
	private LocalTime orarioArrivo;
	private String portoPartenza;
	private String portoArrivo;
	private double prezzo;
	
	public Corsa(int codiceCorsa, LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza,
			String portoArrivo, double prezzo) {
		super();
		this.codiceCorsa = codiceCorsa;
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.portoPartenza = portoPartenza;
		this.portoArrivo = portoArrivo;
		this.prezzo = prezzo;
	}

	public int getCodiceCorsa() {
		return codiceCorsa;
	}

	public void setCodiceCorsa(int codiceCorsa) {
		this.codiceCorsa = codiceCorsa;
	}

	public LocalTime getOrarioPartenza() {
		return orarioPartenza;
	}

	public void setOrarioPartenza(LocalTime orarioPartenza) {
		this.orarioPartenza = orarioPartenza;
	}

	public LocalTime getOrarioArrivo() {
		return orarioArrivo;
	}

	public void setOrarioArrivo(LocalTime orarioArrivo) {
		this.orarioArrivo = orarioArrivo;
	}

	public String getPortoPartenza() {
		return portoPartenza;
	}

	public void setPortoPartenza(String portoPartenza) {
		this.portoPartenza = portoPartenza;
	}

	public String getPortoArrivo() {
		return portoArrivo;
	}

	public void setPortoArrivo(String portoArrivo) {
		this.portoArrivo = portoArrivo;
	}
	
	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public boolean equals(Object o) {
		Corsa c = (Corsa)o;
		
		if (this.orarioPartenza == null && this.orarioArrivo == null) {
			return (this.codiceCorsa == c.codiceCorsa &&
					this.orarioPartenza == c.orarioPartenza &&
					this.orarioArrivo == c.orarioArrivo &&
					this.portoPartenza.equals(c.portoPartenza) &&
					this.portoArrivo.equals(c.portoArrivo) &&
					this.prezzo == c.prezzo);
		}
		else if (this.orarioPartenza == null) {
			return (this.codiceCorsa == c.codiceCorsa &&
					this.orarioPartenza == c.orarioPartenza &&
					this.orarioArrivo.equals(c.orarioArrivo) &&
					this.portoPartenza.equals(c.portoPartenza) &&
					this.portoArrivo.equals(c.portoArrivo) &&
					this.prezzo == c.prezzo);
		}
		else if (this.orarioArrivo == null) {
			return (this.codiceCorsa == c.codiceCorsa &&
					this.orarioPartenza.equals(c.orarioPartenza) &&
					this.orarioArrivo == c.orarioArrivo &&
					this.portoPartenza.equals(c.portoPartenza) &&
					this.portoArrivo.equals(c.portoArrivo) &&
					this.prezzo == c.prezzo);
		}
		return (this.codiceCorsa == c.codiceCorsa &&
				this.orarioPartenza.equals(c.orarioPartenza) &&
				this.orarioArrivo.equals(c.orarioArrivo) &&
				this.portoPartenza.equals(c.portoPartenza) &&
				this.portoArrivo.equals(c.portoArrivo) &&
				this.prezzo == c.prezzo);
	}
	
}
