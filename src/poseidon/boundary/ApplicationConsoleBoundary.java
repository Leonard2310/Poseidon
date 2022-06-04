package poseidon.boundary;

import java.io.IOException;

import poseidon.control.*;

public class ApplicationConsoleBoundary {
	public static void login(int flag) {
		// PRECONDITIONS: 
		// POSTCONDITIONS:
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String nome = null;
		String cognome = null;
		String password = null;		
				
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
			if (gestisciCorsa.loginDipendente(cognome, nome, password) > 0) {
				DipendenteConsoleBoundary.showDipendenteConsoleBoundary();
			}
		}
		else {
			if (gestisciCorsa.loginCliente(cognome, nome, password) > 0) {
				ClienteConsoleBoundary.showClienteConsoleBoundary();
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
