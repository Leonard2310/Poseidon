package poseidon.boundary;

import java.io.IOException;

public class DipendenteConsoleBoundary {
	public static void showDipendenteConsoleBoundary() {
		// PRECONDITIONS: il dipendente ha premuto il pulsante per visualizzare le operazioni che può effettuare
		// POSTCONDITIONS: le operazioni che il dipendente può effettuare sono state mostrate a schermo
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int option = 0;
		do {
			System.out.println("Le operazioni disponibili sono: \n" +
					"\t1) Inserimento Corsa\n" +
					"\t2) Modifica Corsa\n" +
					"\t3) Cancellazione Corsa\n" +
					"\t4) Emissione Biglietto\n" +
					"\t5) Verifica Acquisti\n" +
					"\t6) Esci");
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				option = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (option) {
				case 1: { inserimentoCorsa(); break; }
				case 2: { modificaCorsa(); break; }
				case 3: { cancellaCorsa(); break; }
				case 4: { emissioneBiglietto(); break; }
				case 5: { verificaAcquisti(); break; }
				case 6: { ApplicationConsoleBoundary.logout(); break; }
				default: { System.out.println("Carattere inserito non riconosciuto!\n"); }
			}
		} while (option != 6);
	}
	
	public static void inserimentoCorsa() {
		//TODO: Daiana
	}
	
	public static void emissioneBiglietto() {
		//TODO: Francesco
	}
	
	public static void verificaAcquisti() {
		//TODO: Francesco
	}
	
	public static void modificaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	public static void cancellaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	
	protected static java.io.BufferedReader inputReader;
}
