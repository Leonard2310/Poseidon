package poseidon.entity;

import java.time.LocalTime;

public class Corsa {
	private int codiceCorsa;
	private LocalTime orarioPartenza;
	private LocalTime orarioArrivo;
	private String portoPartenza;
	private String portoArrivo;
	
	public Corsa(int codiceCorsa, LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza,
			String portoArrivo) {
		super();
		this.codiceCorsa = codiceCorsa;
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.portoPartenza = portoPartenza;
		this.portoArrivo = portoArrivo;
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
	
}
