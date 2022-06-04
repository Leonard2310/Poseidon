package poseidon.boundary;

import java.io.IOException;

import poseidon.control.gestisciCorsa;
import poseidon.entity.Biglietto;
import poseidon.entity.CronologiaAcquisti;

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
		// PRECONDITIONS: il dipendente ha scelto di emettere un biglietto
		// POSTCONDITIONS: se il biglietto è stato emesso correttamente, viene visualizzato un messaggio 
		// che indica il codice del biglietto emesso; altrimenti, viene visualizzato un messaggio che indica
		// che il biglietto non è stato emesso
		
		Biglietto biglietto = null;
		int codiceImpiegato = 0;
		int codiceCorsa = 0;
		String tipoBiglietto = null;
		String targa = null;
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		try {
			System.out.println("Inserisci il tuo codice impiegato");
			codiceImpiegato = Integer.parseInt(inputReader.readLine());
			
			System.out.println("Inserisci il codice della corsa");
			codiceCorsa = Integer.parseInt(inputReader.readLine());
			
			System.out.println("Inserisci il tipo del biglietto");
			tipoBiglietto = inputReader.readLine();
			
			if (tipoBiglietto.equals("veicolo")) {
				System.out.println("Inserisci la targa dell'autoveicolo");
				targa = inputReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		biglietto = gestisciCorsa.emissioneBiglietto(codiceImpiegato, codiceCorsa, targa, tipoBiglietto);
		
		if (biglietto != null) {
			System.out.println("Biglietto emesso correttamente con codice = "
								+ biglietto.getCodiceBiglietto());
		}
		else {
			System.out.println("Impossibile emettere il biglietto.");
		}
	}
	
	public static void verificaAcquisti() {
		// PRECONDITIONS: il dipendente ha scelto di verificare se ci sono acquisti per i quali ancora non è
		// stato emesso un biglietto
		// POSTCONDITIONS: viene visualizzato un messaggio che indica la ricevuta, il codice della corsa
		// e il codice del cliente che ha effettuato l'acquisto, in caso non sia stato ancora emesso 
		// il biglietto; altrimenti, viene visualizzato un messaggio che indica l'assenza di nuovi acquisti
		
		CronologiaAcquisti c = null;
		
		c = gestisciCorsa.verificaAcquisti();
		
		if (c != null) {
			System.out.println("Trovato un nuovo acquisto:  numero ricevuta = "
								+ c.getRicevuta() + "  codice corsa = " 
								+ c.getCorsa().getCodiceCorsa() + "  codice cliente = " 
								+ c.getCodiceCliente());
		}
		else {
			System.out.println("Non ci sono nuovi acquisti.");
		}
	}
	
	public static void modificaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	public static void cancellaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}
	
	
	protected static java.io.BufferedReader inputReader;
}
