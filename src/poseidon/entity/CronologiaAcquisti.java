package poseidon.entity;

public class CronologiaAcquisti {
	private int codiceCliente;
	private Corsa corsa;
	private Biglietto biglietto;
	private int ricevuta;
	
	public CronologiaAcquisti(int codiceCliente, Corsa corsa, Biglietto biglietto, int ricevuta) {
		super();
		this.codiceCliente = codiceCliente;
		this.corsa = corsa;
		this.biglietto = biglietto;
		this.ricevuta = ricevuta;
	}

	public int getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(int codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public Corsa getCorsa() {
		return corsa;
	}

	public void setCorsa(Corsa corsa) {
		this.corsa = corsa;
	}

	public Biglietto getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}

	public int getRicevuta() {
		return ricevuta;
	}

	public void setRicevuta(int ricevuta) {
		this.ricevuta = ricevuta;
	}
	
}
