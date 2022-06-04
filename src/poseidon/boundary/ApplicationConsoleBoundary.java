package poseidon.boundary;

import java.io.IOException;

import poseidon.control.*;

public class ApplicationConsoleBoundary {
	public static int login(int flag) {
		// PRECONDITIONS: l'utente ha premuto il pulsante per l'autenticazione
		// POSTCONDITIONS: se le informazioni inserite dall'utente sono corrette, viene visualizzato un messaggio
		// di successo con il suo codice e viene restituito valore 0; altrimenti, viene visualizzato un messaggio
		// di errore viene restituito valore 1;
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String nome = null;
		String cognome = null;
		String password = null;	
		int codice  = 0;
				
		System.out.println("Inserisci il tuo cognome");
		try {
			cognome = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Inserisci il tuo nome");
		try {
			nome = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		System.out.println("Inserisci la tua password");
		try {
			password = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (flag == 1) {
			codice = gestisciCorsa.loginDipendente(cognome, nome, password);
			if (codice > 0) {
				System.out.println("Dipendente " + codice + " autenticato con successo.");
				return(0);
			}
			else
			{
				System.out.println("Autenticazione fallita.");	
				return(1);
			}
		}
		else {
			codice = gestisciCorsa.loginCliente(cognome, nome, password);
			if (codice > 0) {
				System.out.println("Cliente " + codice + " autenticato con successo.");
				return(0);
			}
			else
			{
				System.out.println("Autenticazione fallita.");		
				return(1);
			}
		}
	}

	public static void logout() {
		// FUNZIONE NON IMPLEMENTATA
	}

	public static void ricercaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}

	protected static java.io.BufferedReader inputReader;

}
