package poseidon.entity;

import java.util.List;

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
	
	public List<CronologiaAcquisti> visualizzazioneCronologiaAcquisti(int codiceCliente) {
		// FUNZIONE NON IMPLEMENTATA
		
		List<CronologiaAcquisti> listaCronologia = new List<CronologiaAcquisti>;
		Cliente c = CronologiaDAO.readCronologia(codiceCliente);
		
		listaCronologia.add(c);
		
		return listaCronologia;
	}
	
	public int acquistaBiglietto(int codiceCliente, String nome, String cognome, String categoria, String codiceCorsa) {
		
		int ricevuta = 0;
		
		// TODO: FUNZIONE DA IMPLEMENTARE
		
		return ricevuta;
	}
}
