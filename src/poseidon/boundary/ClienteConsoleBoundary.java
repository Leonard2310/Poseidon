package poseidon.boundary;

import java.io.IOException;

public class ClienteConsoleBoundary {
	public static void showClienteConsoleBoundary() {
		// PRECONDITIONS: il cliente ha premuto il pulsante per visualizzare le operazioni che può effettuare
		// POSTCONDITIONS: le operazioni che il cliente può effettuare sono state mostrate a schermo
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int option = 0;
		do {
			System.out.println("Le operazioni disponibili sono: \n" +
					"\t1) Acquista Biglietto\n" +
					"\t2) Visualizza Cronologia Acquisti\n" +
					"\t3) Esci");
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				option = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (option) {
				case 1: { acquistaBiglietto(); break; }
				case 2: { visualizzaCronologiaAcquisti(); break; }
				case 3: { ApplicationConsoleBoundary.logout(); break; }
				default: { System.out.println("Carattere inserito non riconosciuto!\n"); }
			}
		} while (option != 3);
	}
	
	public static void acquistaBiglietto() {
		//TODO: Leonardo
	}
	
	public static void visualizzaCronologiaAcquisti() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	
	protected static java.io.BufferedReader inputReader;
}
