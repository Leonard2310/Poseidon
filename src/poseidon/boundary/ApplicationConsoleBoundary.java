package poseidon.boundary;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import poseidon.DAO.NaveDAO;
import poseidon.control.*;
import poseidon.entity.*;

public class ApplicationConsoleBoundary {
	public static int login(int flag) {
		// PRECONDITIONS: l'utente ha premuto il pulsante per l'autenticazione
		// POSTCONDITIONS: se le informazioni inserite dall'utente sono corrette, viene visualizzato un messaggio
		// 	di successo con il suo codice, che viene restituito; altrimenti, viene visualizzato un messaggio
		// 	di errore viene restituito valore 1;

		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String nome = null;
		String cognome = null;
		String password = null;
		int codice = -1;

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
			} else {
				System.out.println("Autenticazione fallita.");
			}
		} else {
			codice = gestisciCorsa.loginCliente(cognome, nome, password);
			if (codice > 0) {
				System.out.println("Cliente " + codice + " autenticato con successo.");
			} else {
				System.out.println("Autenticazione fallita.");
			}
		}
		
		return codice;
	}

	public static void registra(char flag) {
		// PRECONDITIONS: il flag passato in ingresso è uguale a "y" o a "n"
		// POSTCONDITIONS: se la registrazione del nuovo utente è andata a buon fine, viene visualizzato un messaggio
		// 		di successo; altrimenti, viene visualizzato un messaggio di fallimento

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
		// FUNZIONE NON RICHIESTA
		
		System.out.println("Hai effettuato il logout.\n");
	}

	public static void ricercaCorsa() {
		// FUNZIONE NON RICHIESTA
		
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		
		String answer = null;
		int codiceCorsa = 0;
		String portoPartenza = null;
		String portoArrivo = null;
		LocalTime orarioPartenza = null;
		LocalTime orarioArrivo = null;
		Double prezzo = 0.00;
		
		List<Corsa> listaCorsa = null;
		List<Nave> listaNave = null;
		
		try {
			listaNave = NaveDAO.readallNave();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Vuoi inserire dei filtri o il codice della corsa cercata? [filtri/codice]");
		try {
			answer = inputReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (answer.equals("filtri")) {
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
			
			System.out.println("La lista delle corse con tali filtri sono:: ");
			for (Corsa c : listaCorsa) {
				System.out.println("CODICE CORSA:  "+c.getCodiceCorsa());
				System.out.println("\tPorto Partenza:  "+c.getPortoPartenza());
				System.out.println("\tOrario Partenza:  "+c.getOrarioPartenza());
				System.out.println("\tPorto Arrivo:  "+c.getPortoArrivo());
				System.out.println("\tOrario Arrivo:  "+c.getOrarioArrivo());
				System.out.println("\tPrezzo base:  "+c.getPrezzo()+"€");
				for (Nave n : listaNave) {
					if(c.getCodiceCorsa() == n.getCodiceCorsa()) {
						System.out.println("\tTipo Nave:  "+n.getNome());
						System.out.println("\tCapienza passeggeri:  "+n.getCapienzaPassegeri());
						System.out.println("\tCapienza autoveicoli:  "+n.getCapienzaAutoveicoli());
					}
				}
				System.out.println("------------------------------------------------------------------------------------------\n");
			}
		}

		else if (answer.equals("codice")) {
			
			System.out.print("Inserisci il codice della corsa:  ");
			try {
				codiceCorsa = Integer.parseInt(inputReader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			listaCorsa = gestisciCorsa.ricercaCorsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			
			System.out.println("La corsa con tale codice è: \t");
			for (Corsa c : listaCorsa) {
				System.out.println("CODICE CORSA:  "+c.getCodiceCorsa());
				System.out.println("\tPorto Partenza:  "+c.getPortoPartenza());
				System.out.println("\tOrario Partenza:  "+c.getOrarioPartenza());
				System.out.println("\tPorto Arrivo:  "+c.getPortoArrivo());
				System.out.println("\tOrario Arrivo:  "+c.getOrarioArrivo());
				System.out.println("\tPrezzo base:  "+c.getPrezzo()+"€");
				for (Nave n : listaNave) {
					if(c.getCodiceCorsa() == n.getCodiceCorsa()) {
						System.out.println("\tTipo Nave:  "+n.getNome());
						System.out.println("\tCapienza passeggeri:  "+n.getCapienzaPassegeri());
						System.out.println("\tCapienza autoveicoli:  "+n.getCapienzaAutoveicoli());
					}
				}
				System.out.println("------------------------------------------------------------------------------------------\n");
			}
		}
		
		else {
			listaCorsa= gestisciCorsa.ricercaCorsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			System.out.println("ERRORE INSERIMENTO\n");
			System.out.println("La lista di tutte le corse disponibili è la seguente: ");
			for (Corsa c : listaCorsa) {
				System.out.println("CODICE CORSA:  "+c.getCodiceCorsa());
				System.out.println("\tPorto Partenza:  "+c.getPortoPartenza());
				System.out.println("\tOrario Partenza:  "+c.getOrarioPartenza());
				System.out.println("\tPorto Arrivo:  "+c.getPortoArrivo());
				System.out.println("\tOrario Arrivo:  "+c.getOrarioArrivo());
				System.out.println("\tPrezzo base:  "+c.getPrezzo()+"€");
				for (Nave n : listaNave) {
					if(c.getCodiceCorsa() == n.getCodiceCorsa()) {
						System.out.println("\tTipo Nave:  "+n.getNome());
						System.out.println("\tCapienza passeggeri:  "+n.getCapienzaPassegeri());
						System.out.println("\tCapienza autoveicoli:  "+n.getCapienzaAutoveicoli());
					}
				}
				System.out.println("------------------------------------------------------------------------------------------\n");
			}
		}
	}

	protected static java.io.BufferedReader inputReader;

}
