package poseidon.boundary;

import java.io.DataInput;
import java.io.IOException;
import java.util.*;

import poseidon.control.gestisciCorsa;

public class ClienteConsoleBoundary {
	public static void showClienteConsoleBoundary() {
		// PRECONDITIONS: il cliente ha premuto il pulsante per visualizzare le operazioni che pu� effettuare
		// POSTCONDITIONS: le operazioni che il cliente pu� effettuare sono state mostrate a schermo
		
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
				case 1: { acquistaBiglietto(option); break; }
				case 2: { visualizzaCronologiaAcquisti(); break; }
				case 3: { ApplicationConsoleBoundary.logout(); break; }
				default: { System.out.println("Carattere inserito non riconosciuto!\n"); }
			}
		} while (option != 3);
	}
	
	public static void acquistaBiglietto(int codiceCorsa) {
		//TODO: Leonardo
		System.out.println("Inserisci il codice cliente: ");
		try {
			int codiceCliente = Integer.parseInt(inputReader.readLine());
			
			try (Scanner input = new Scanner(System.in)) {
				System.out.println("Inserisci le tue informazioni anagrafiche: ");
				System.out.println("Nome: \t");
				String nome = input.nextLine();
				System.out.println("Cognome: \t");
				String cognome = input.nextLine();
				
				System.out.println("Inserisci il tipo di biglietto scelto [passeggero/veicolo]: \t");
				String tipoBiglietto = input.nextLine();
				
				gestisciCorsa.acquistaBiglietto(codiceCliente, nome, cognome, tipoBiglietto, codiceCorsa);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void visualizzaCronologiaAcquisti() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	
	protected static java.io.BufferedReader inputReader;
}
