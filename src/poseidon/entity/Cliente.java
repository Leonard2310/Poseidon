package poseidon.entity;

public class Cliente extends Utente {
	
	private int codiceCliente;
	
	public int getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(int codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public Cliente(String cognome, String nome, String password, int codiceCliente) {
		super(cognome, nome, password);
		this.codiceCliente = codiceCliente;
	}
	

}
