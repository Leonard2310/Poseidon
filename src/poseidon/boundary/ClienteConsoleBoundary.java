package poseidon.boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import poseidon.control.gestisciCorsa;
import poseidon.entity.Biglietto;
import poseidon.entity.Corsa;
import poseidon.entity.CronologiaAcquisti;

public class ClienteConsoleBoundary {
	public static void showClienteConsoleBoundary(int codiceCliente) {
		// PRECONDITIONS: il cliente ha premuto il pulsante per visualizzare le operazioni che può effettuare
		// POSTCONDITIONS: le operazioni che il cliente può effettuare sono state mostrate a schermo

		inputReader = new BufferedReader(new InputStreamReader(System.in));
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

				System.out.print("Inserisci il codice della corsa scelta:  ");

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
		// PRECONDITIONS: chiamata ad acquista biglietto mediante menù. 
		// POSTCONDITIONS: viene effettuata una chiamata alla funzione per l'acquisto del biglietto passando come parametri 
		//		di ingresso i valori indicati in input dall'utente.

		String nome = null;
		String cognome = null;
		String tipoBiglietto = null;
		String targa = null;
		String tipologiaPagamento = null;
		
		inputReader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Inserisci le informazioni anagrafiche: ");
		System.out.print("\tNome:  ");
		try {
			nome = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("\tCognome:  ");
		try {
			cognome = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean answer = false;
		do {
			System.out.print("Inserisci il tipo di biglietto scelto [passeggero/veicolo]:   ");
			try {
				tipoBiglietto = inputReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (tipoBiglietto.equals("passeggero")) {
				answer = true;
			} else if (tipoBiglietto.equals("veicolo")) {
				System.out.print("\tTarga: \t");
				try {
					targa = inputReader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				answer = true;
			} else {
				System.out.println("Errore: tipo non valido");
				answer = false;
			}

		} while (answer == false);

		System.out.print("Scegli la tipologia di pagamento:  ");
		try {
			tipologiaPagamento = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		gestisciCorsa.acquistaBiglietto(codiceCliente, nome, cognome, tipoBiglietto, codiceCorsa, targa, tipologiaPagamento);
	}

	public static void visualizzaCronologiaAcquisti(int codiceCliente) {
		// FUNZIONE NON IMPLEMENTATA

		List<CronologiaAcquisti> lista_cronologia = new ArrayList<CronologiaAcquisti>();
		Corsa corsa;
		Biglietto biglietto;

		lista_cronologia = gestisciCorsa.visualizzazioneCronologiaAcquisti(codiceCliente);
		
		if (lista_cronologia.size() == 0) {
			System.out.println("\nLa tua cronologia � vuota.");
			return;
		}
		
		System.out.println("\nCRONOLOGIA:");

		for (CronologiaAcquisti c : lista_cronologia) {
			
			corsa = c.getCorsa();
			biglietto = c.getBiglietto();
			
			System.out.println("\tCorsa: " + corsa.getCodiceCorsa());
			
			if (biglietto.getCodiceBiglietto() == 0) {
				System.out.println("\tBiglietto: non ancora emesso");				
			}
			else {
				System.out.println("\tBiglietto: " + biglietto.getCodiceBiglietto());				
			}
			
			System.out.println("\tRicevuta: " + c.getRicevuta());
			System.out.println("\t-------------------------");
		}

	}

	protected static java.io.BufferedReader inputReader;
}
