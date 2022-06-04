package poseidon.boundary;

import java.io.IOException;

import poseidon.control.*;

public class ApplicationConsoleBoundary {
	public static void login(int flag) {
		// PRECONDITIONS: 
		// POSTCONDITIONS:
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		char answer = 0;
		String nome = null;
		String cognome = null;
		String password = null;
		
		switch(flag) {
			case 0: {		
				System.out.println("Sei un dipendente (y/n)?");
				try {
					answer = inputReader.readLine().charAt(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
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
				
				//gestisciCorsa.login(cognome, nome, password);
					
				if (answer == 'y') {
					DipendenteConsoleBoundary.showDipendenteConsoleBoundary();
				}
				else {
					ClienteConsoleBoundary.showClienteConsoleBoundary();
				}
			}
			case 1: {
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
