package poseidon.boundary;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import poseidon.control.*;
import poseidon.entity.*;

public class ApplicationConsoleBoundary {
	public static int login(int flag) {
		// PRECONDITIONS: l'utente ha premuto il pulsante per l'autenticazione
		// POSTCONDITIONS: se le informazioni inserite dall'utente sono corrette, viene
		// visualizzato un messaggio
		// di successo con il suo codice e viene restituito valore 0; altrimenti, viene
		// visualizzato un messaggio
		// di errore viene restituito valore 1;

		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String nome = null;
		String cognome = null;
		String password = null;
		int codice = 0;

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
				return (0);
			} else {
				System.out.println("Autenticazione fallita.");
				return (1);
			}
		} else {
			codice = gestisciCorsa.loginCliente(cognome, nome, password);
			if (codice > 0) {
				System.out.println("Cliente " + codice + " autenticato con successo.");
				return (0);
			} else {
				System.out.println("Autenticazione fallita.");
				return (1);
			}
		}
	}

	public static void registra(char flag) {
		// PRECONDITIONS: il flag passato in ingresso � uguale a "y" o a "n"
		// POSTCONDITIONS: se la registrazione del nuovo utente � andata a buon fine,
		// viene visualizzato un messaggio
		// di successo; altrimenti, viene visualizzato un messaggio di fallimento

		String cognome = null;
		String nome = null;
		String password_1 = null;
		String password_2 = null;
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int codice = 0;

		do {
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

			System.out.println("Inserisci una password");
			try {
				password_1 = inputReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Inserisci nuovamente la password");
			try {
				password_2 = inputReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!password_1.equals(password_2)) {
				System.out.println("Le password inserite non corrispondono, riprova.");
			}

		} while (!password_1.equals(password_2));

		if (flag == 'y') {
			codice = gestisciCorsa.registraDipendente(cognome, nome, password_1);
		} else {
			codice = gestisciCorsa.registraCliente(cognome, nome, password_2);
		}

		if (codice > 0) {
			System.out.println("Benvenuto nella nostra famiglia. Il codice che ti � stato assegnato � " + codice);
		} else {
			System.out.println("Errore di registrazione.");
		}
	}

	public static void logout() {
		// FUNZIONE NON IMPLEMENTATA
	}

	public static void ricercaCorsa() {
		// TODO: DA ERRORE NELL'ESECUZIONE (PREZZO)
		// FUNZIONE NON IMPLEMENTATA
		String answer = null;
		int codiceCorsa = 0;
		String portoPartenza = null;
		String portoArrivo = null;
		LocalTime orarioPartenza = null;
		LocalTime orarioArrivo = null;
		Double prezzo = 0.00;
		
		List<Corsa> listaCorsa = null;
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		System.out.println("Vuoi inserire dei filtri o il codice della corsa cercata? [filtri/codice]");
		try {
			answer = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (answer == "filtri") {
			System.out.println("Inserisci il porto di partenza");
			try {
				portoPartenza = inputReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Inserisci il porto di arrivo");
			try {
				portoArrivo = inputReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Inserisci l'orario di partenza");
			try {
				DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm");
				String time = inputReader.readLine();
				orarioPartenza = LocalTime.parse(time, parseFormat);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Inserisci l'orario di arrivo");
			try {
				DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm");
				String time = inputReader.readLine();
				orarioArrivo = LocalTime.parse(time, parseFormat);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("Inserisci una soglia di prezzo massimo");
			try {
				prezzo = Double.parseDouble(inputReader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}

			listaCorsa = gestisciCorsa.ricercaCorsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			System.out.println("La lista delle corse è: \n" + listaCorsa);
		}

		else if (answer == "codice") {
			
			System.out.println("Inserisci il codice della corsa");
			try {
				codiceCorsa = Integer.parseInt(inputReader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			listaCorsa = gestisciCorsa.ricercaCorsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			System.out.println("La lista delle corse è: \n" + listaCorsa);
		}
		
		else {
			listaCorsa= gestisciCorsa.ricercaCorsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			System.out.println("La lista delle corse è: \n" + listaCorsa);
		}
	}

	protected static java.io.BufferedReader inputReader;

}
