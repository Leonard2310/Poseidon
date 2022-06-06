package poseidon.boundary;

import java.io.IOException;
import java.util.List;

import poseidon.control.gestisciCorsa;
import poseidon.entity.Biglietto;
import poseidon.entity.CronologiaAcquisti;

public class DipendenteConsoleBoundary {
	public static void showDipendenteConsoleBoundary(int codiceImpiegato) {
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
				case 1: { inserimentoCorsa(codiceImpiegato); break; }
				case 2: { modificaCorsa(codiceImpiegato); break; }
				case 3: { cancellaCorsa(codiceImpiegato); break; }
				case 4: { emissioneBiglietto(codiceImpiegato); break; }
				case 5: { verificaAcquisti(codiceImpiegato); break; }
				case 6: { ApplicationConsoleBoundary.logout(); break; }
				default: { System.out.println("Carattere inserito non riconosciuto!\n"); }
			}
		} while (option != 6);
	}
	
	public static void inserimentoCorsa(int codiceImpiegato) {
		//TODO: Daiana
	}
	
	public static void emissioneBiglietto(int codiceImpiegato) {
		// PRECONDITIONS: il dipendente ha scelto di emettere un biglietto
		// POSTCONDITIONS: se il biglietto è stato emesso correttamente, viene visualizzato un messaggio 
		// che indica il codice del biglietto emesso; altrimenti, viene visualizzato un messaggio che indica
		// che il biglietto non è stato emesso
		
		Biglietto biglietto = null;
		int codiceCorsa = 0;
		String tipoBiglietto = null;
		String targa = null;
		int codiceCliente = 0;
		char risposta = 'n';
		int ricevuta = 0;
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			System.out.println("Inserisci il codice della corsa");
			try {
				codiceCorsa = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				codiceCorsa = 0;
			}
			
			System.out.println("Inserisci il tipo del biglietto");
			tipoBiglietto = inputReader.readLine();
			
			if (tipoBiglietto.equals("veicolo")) {
				System.out.println("Inserisci la targa dell'autoveicolo");
				targa = inputReader.readLine();
			}
			
			System.out.println("Inserisci il codice del cliente che ha acquistato il biglietto");
			try {
				codiceCliente = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				codiceCliente = 0;
			}
			
			do {
				System.out.println("Generare una nuova ricevuta d'acquisto? [y/n]");
				risposta = inputReader.readLine().charAt(0);
				if (risposta != 'y' && risposta != 'n')
					System.out.println("Errore: carattere inserito non valido.");
			} while(risposta != 'y' && risposta != 'n');
			
			if (risposta == 'n') {
				System.out.println("Inserisci la ricevuta d'acquisto");
				try {
					ricevuta = Integer.parseInt(inputReader.readLine());
				} catch (NumberFormatException e) {
					ricevuta = 0;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		biglietto = gestisciCorsa.emissioneBiglietto(codiceImpiegato, codiceCorsa, targa, tipoBiglietto, 
														codiceCliente, risposta, ricevuta);
		
		if (biglietto != null) {
			System.out.println("Biglietto emesso correttamente con codice = "
								+ biglietto.getCodiceBiglietto());
		}
		else {
			System.out.println("Impossibile emettere il biglietto.");
		}
	}
	
	public static void verificaAcquisti(int codiceImpiegato) {
		// PRECONDITIONS: il dipendente ha scelto di verificare se ci sono acquisti per i quali ancora non è
		// stato emesso un biglietto
		// POSTCONDITIONS: viene visualizzato un messaggio che indica la ricevuta, il codice della corsa
		// e il codice del cliente che ha effettuato l'acquisto, in caso non sia stato ancora emesso 
		// il biglietto; altrimenti, viene visualizzato un messaggio che indica l'assenza di nuovi acquisti
		
		List<CronologiaAcquisti> lista = null;
		
		lista = gestisciCorsa.verificaAcquisti();
		
		if (lista != null && lista.size() > 0) {
			for (CronologiaAcquisti c : lista) {
				System.out.println("Trovato acquisto:  numero ricevuta = "
									+ c.getRicevuta() + "  codice corsa = " 
									+ c.getCorsa().getCodiceCorsa() + "  codice cliente = " 
									+ c.getCodiceCliente());
			}
		}
		else {
			System.out.println("Non ci sono nuovi acquisti.");
		}
	}
	
	public static void modificaCorsa(int codiceImpiegato) {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	public static void cancellaCorsa(int codiceImpiegato) {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	
	protected static java.io.BufferedReader inputReader;
}
