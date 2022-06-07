package poseidon.boundary;

import java.io.IOException;
import java.util.*;

import poseidon.control.gestisciCorsa;
import poseidon.entity.CronologiaAcquisti;

public class ClienteConsoleBoundary {
	public static void showClienteConsoleBoundary(int codiceCliente) {
		// PRECONDITIONS: il cliente ha premuto il pulsante per visualizzare le operazioni che può effettuare
		// POSTCONDITIONS: le operazioni che il cliente può effettuare sono state mostrate a schermo

		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int option = 0;
		do {
			System.out.println("Le operazioni disponibili sono: \n" + "\t1) Acquista Biglietto\n"
					+ "\t2) Visualizza Cronologia Acquisti\n" + "\t3) Esci");
			System.out.flush();

			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				option = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (option) {
			case 1: {

				System.out.println("Inserisci il codice della corsa scelta: \t");
				
				try {
					int codiceCorsa = Integer.parseInt(inputReader.readLine());
					acquistaBiglietto(codiceCorsa, codiceCliente);
				} catch (NumberFormatException e) {
					option = 0;
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			case 2: {
				visualizzaCronologiaAcquisti(codiceCliente);
				break;
			}
			case 3: {
				ApplicationConsoleBoundary.logout();
				break;
			}
			default: {
				System.out.println("Carattere inserito non riconosciuto!\n");
			}
			}
		} while (option != 3);
	}

	public static void acquistaBiglietto(int codiceCorsa, int codiceCliente) {
		/*
		 * PRECONDITIONS: chiamata ad acquista biglietto mediante menù.
		 * POSTCONDITIONS: viene effettuata una chiamata alla funzione per l'acquisto del biglietto passando come parametri
		 * 	di ingresso i valori indicati in input dall'utente.
		 */ 
		
		String nome = null;
		String cognome = null;
		String tipoBiglietto = null;
		String targa = null;
		String tipologiaPagamento = null;
		
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Inserisci le informazioni anagrafiche: ");
			System.out.print("Nome: \t");
			nome = input.nextLine();
			System.out.print("Cognome: \t");
			cognome = input.nextLine();

			System.out.println("Inserisci il tipo di biglietto scelto [passeggero/veicolo]: \t");
			tipoBiglietto = input.nextLine();
			boolean answer = false;
			do {
				if (tipoBiglietto.equals("passeggero")) {
					answer = true;
				} else if (tipoBiglietto.equals("veicolo")) {
					System.out.print("Targa: \t");
					targa = input.nextLine();
					answer = true;
				} else {
					answer = false;
				}

			} while (answer == false);
			
			System.out.println("Scegli la tipologia di pagamento: \t");
			tipologiaPagamento = input.nextLine();

			gestisciCorsa.acquistaBiglietto(codiceCliente, nome, cognome, tipoBiglietto, codiceCorsa, targa, tipologiaPagamento);
		}
	}

	public static void visualizzaCronologiaAcquisti(int codiceCliente) {
		// FUNZIONE NON IMPLEMENTATA

		List<CronologiaAcquisti> lista_cronologia = new ArrayList<CronologiaAcquisti>();

		lista_cronologia = gestisciCorsa.visualizzazioneCronologiaAcquisti(codiceCliente);

		for (CronologiaAcquisti c : lista_cronologia) {
			System.out.println("Corsa: " + c.getCorsa());
			System.out.println("Biglietto: " + c.getBiglietto());
			System.out.println("Ricevuta: " + c.getRicevuta());
			System.out.println("\n-----------------\n");
		}

	}

	protected static java.io.BufferedReader inputReader;
}
