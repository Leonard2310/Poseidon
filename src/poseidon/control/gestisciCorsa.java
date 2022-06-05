package poseidon.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import poseidon.entity.*;
import poseidon.DAO.*;

import poseidon.external.SistemaDiPagamento;
import poseidon.external.Stampante;

public class gestisciCorsa {

	// UTENTE

	public static int loginCliente(String cognome, String nome, String password) {
		// PRECONDITIONS: i parametri passati in input sono valide stringhe testuali
		// POSTCONDITIONS: se il cliente identificato dai parametri in ingresso viene
		// trovato nel database,
		// viene restituito il suo codice; altrimenti, viene restituito valore -1

		List<Cliente> lista_clienti = null;
		try {
			lista_clienti = ClienteDAO.readallCliente();

			for (Cliente c : lista_clienti) {
				if (c.getCognome().equals(cognome) && c.getNome().equals(nome) && c.getPassword().equals(password))
					return c.getCodiceCliente();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static int loginDipendente(String cognome, String nome, String password) {
		// PRECONDITIONS: i parametri passati in input sono valide stringhe testuali
		// POSTCONDITIONS: se il dipendente identificato dai parametri in ingresso viene
		// trovato nel database,
		// viene restituito il suo codice; altrimenti, viene restituito valore -1

		List<Dipendente> lista_dipendenti;
		try {
			lista_dipendenti = DipendenteDAO.readallDipendente();

			for (Dipendente d : lista_dipendenti) {
				if (d.getCognome().equals(cognome) && d.getNome().equals(nome) && d.getPassword().equals(password))
					return d.getCodiceImpiegato();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static int registraCliente(String cognome, String nome, String password) {
		// PRECONDITIONS: i parametri passati in input sono valide stringhe testuali
		// POSTCONDITIONS: se il cliente identificato dai parametri in ingresso non
		// viene trovato nel database,
		// viene inserito e viene restituito il suo codice; altrimenti, viene restituito
		// valore -1

		List<Cliente> lista_clienti = null;
		int codiceCliente = 10001;

		try {
			lista_clienti = ClienteDAO.readallCliente();

			for (Cliente c : lista_clienti) {
				if (c.getCognome().equals(cognome) && c.getNome().equals(nome) && c.getPassword().equals(password)) {
					System.out.println("Errore: cliente gi� esistente.");
					return -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista_clienti.size() > 0)
			codiceCliente = lista_clienti.get(lista_clienti.size() - 1).getCodiceCliente() + 1;

		Cliente c = new Cliente(cognome, nome, password, codiceCliente);
		try {
			ClienteDAO.creaCliente(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c.getCodiceCliente();
	}

	public static int registraDipendente(String cognome, String nome, String password) {
		// PRECONDITIONS: i parametri passati in input sono valide stringhe testuali
		// POSTCONDITIONS: se il dipendente identificato dai parametri in ingresso non
		// viene trovato nel database,
		// viene inserito e viene restituito il suo codice; altrimenti, viene restituito
		// valore -1

		List<Dipendente> lista_dipendenti = null;
		int codiceImpiegato = 10001;

		try {
			lista_dipendenti = DipendenteDAO.readallDipendente();

			for (Dipendente d : lista_dipendenti) {
				if (d.getCognome().equals(cognome) && d.getNome().equals(nome) && d.getPassword().equals(password)) {
					System.out.println("Errore: cliente gi� esistente.");
					return -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista_dipendenti.size() > 0)
			codiceImpiegato = lista_dipendenti.get(lista_dipendenti.size() - 1).getCodiceImpiegato() + 1;

		Dipendente d = new Dipendente(cognome, nome, password, codiceImpiegato);
		try {
			DipendenteDAO.creaDipendente(d);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d.getCodiceImpiegato();
	}

	public static boolean logout() {
		// FUNZIONE NON IMPLEMENTATA

		return true;
	}

	public static List<Corsa> ricercaCorsa(int codiceCorsa, LocalTime orarioPartenza, LocalTime orarioArrivo,
			String portoPartenza, String portoArrivo, double prezzo) {
		// FUNZIONE NON IMPLEMENTATA

		List<Corsa> listaCorsa = new ArrayList<Corsa>();
		Corsa corsa = null;

		if (codiceCorsa != 0) {
			try {
				corsa = CorsaDAO.readCorsa(codiceCorsa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			listaCorsa.add(corsa);
			return listaCorsa;
		} else if (orarioPartenza != null && orarioArrivo != null && portoPartenza != null && portoArrivo != null) {

			try {
				listaCorsa = CorsaDAO.readallCorsa();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for (Corsa c : listaCorsa) {
				if (c.getOrarioArrivo() == orarioArrivo && c.getOrarioPartenza() == orarioPartenza
						&& c.getPortoPartenza() == portoPartenza && c.getPortoArrivo() == portoArrivo) {
					listaCorsa.add(c);
				}
			}

			return listaCorsa;
		} else {
			System.out.println("Nessuna corsa per i filtri specificati");
			try {
				listaCorsa = CorsaDAO.readallCorsa();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listaCorsa;
		}

		// TODO: PREZZO

	}

	// CLIENTE

	public static List<CronologiaAcquisti> visualizzazioneCronologiaAcquisti(int codiceCliente, int codiceCorsa,
			int ricevuta) {
		// FUNZIONE NON IMPLEMENTATA

		List<CronologiaAcquisti> listaCronologia = new ArrayList<CronologiaAcquisti>();
		CronologiaAcquisti c = null;
		try {
			c = CronologiaDAO.readCronologia(codiceCliente, codiceCorsa, ricevuta);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		listaCronologia.add(c);

		return listaCronologia;
	}

	public static int acquistaBiglietto(int codiceCliente, String nome, String cognome, String tipoBiglietto, int codiceCorsa) {
		// PRECONDIZIONE:
		// POSTCONDIZIONE:

		Corsa corsa = null;
		int bool_ricevuta[] = null;

		double prezzo_finale = inserisciTipologiaBiglietto(codiceCorsa, tipoBiglietto);
		int disponibilita = calcolaDisponibilità(codiceCorsa, tipoBiglietto);

		if (disponibilita > 0) {
			System.out.println("La corsa è disponibile");
			System.out.println("Il prezzo è " + prezzo_finale);
			bool_ricevuta = elaborazioneAcquisto();
			if (bool_ricevuta[0] == 0) {
				try {
					corsa = CorsaDAO.readCorsa(codiceCorsa);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				aggiuntaAcquistoCronologia(codiceCliente, nome, cognome, bool_ricevuta[1], corsa);
				return bool_ricevuta[1];
			} else {
				System.out.println("Elaborazione d'acquisto fallita");
			}
		} else {
			System.out.println("Assenza di posti disponibili");
		}
		// TODO: Leonardo
		return bool_ricevuta[1];
	}

	@SuppressWarnings("null") // TODO: LEONARDO CONTROLLARE
	public static int[] elaborazioneAcquisto() {
		// PRECONDIZIONE:
		// POSTCONDIZIONE:
		int bool_ricevuta[] = null;

		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Scegli la tipologia di pagamento: \t");
			@SuppressWarnings("unused")
			String tipologiaPagamento = input.nextLine();

			bool_ricevuta[1] = SistemaDiPagamento.generateRicevuta();
			System.out.println("Il codice ricevuta è: " + bool_ricevuta[1]);
			if (bool_ricevuta[1] != 0) {
				bool_ricevuta[0] = 0;

			} else {
				bool_ricevuta[0] = 1;
			}
			return bool_ricevuta;
		}

	}

	public static void aggiuntaAcquistoCronologia(int codiceCliente, String nome, String cognome, int ricevuta, Corsa corsa) {
		// PRECONDIZIONE:
		// POSTCONDIZIONE:

		Biglietto b = new Biglietto(0, null, null, 0, 0);

		CronologiaAcquisti c = new CronologiaAcquisti(codiceCliente, corsa, b, ricevuta);
		try {
			CronologiaDAO.creaCronologia(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int calcolaDisponibilità(int codiceCorsa, String tipoBiglietto) {
		// PRECONDIZIONE:
		// POSTCONDIZIONE:

		List<Nave> lista_nave = new ArrayList<Nave>();
		List<Biglietto> lista_biglietto = new ArrayList<Biglietto>();

		int capienza = 0;
		int count = 0;

		try {
			lista_nave = NaveDAO.readallNave();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Nave n : lista_nave) {
			if (codiceCorsa == n.getCodiceCorsa()) {
				if (tipoBiglietto.equals("passeggeri")) {
					capienza = n.getCapienzaPassegeri();
				} else if (tipoBiglietto.equals("veicoli")) {
					capienza = n.getCapienzaAutoveicoli();
				}
			}
		}

		try {
			lista_biglietto = BigliettoDAO.readallBiglietto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Biglietto b : lista_biglietto) {
			if (codiceCorsa == b.getCodiceCorsa()) {
				if (tipoBiglietto.equals("veicoli")) {
					if ((b instanceof BigliettoVeicolo) == true) { // TODO: Leonardo testare
						count += 1;
					}
				} else if (tipoBiglietto.equals("passeggeri")) {
					if ((b instanceof BigliettoPasseggero) == true) { // TODO: Leonardo testare
						count += 1;
					}
				}
			}
		}

		int disponibilita = capienza - count;

		return disponibilita;
	}

	public static double inserisciTipologiaBiglietto(int codiceCorsa, String tipoBiglietto) {
		// PRECONDIZIONE:
		// POSTCONDIZIONE:

		Corsa corsa = null;
		double prezzo_finale = 0.00;

		try (Scanner input = new Scanner(System.in)) {
			try {
				corsa = CorsaDAO.readCorsa(codiceCorsa);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (tipoBiglietto.equals("veicolo")) {
				prezzo_finale = corsa.getPrezzo();
			} else {
				prezzo_finale = 50 + corsa.getPrezzo();

			}
		}

		return prezzo_finale;
	}

	// DIPENDENTE

	public static Corsa inserimentoCorsa(LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza,
			String portoArrivo) {
		Corsa corsa = null;

		// int codiceCorsa = (int) Math.random();

		// TODO: DAIANA SCEMA
		return corsa;

	}

	public static Biglietto emissioneBiglietto(int codiceImpiegato, int codiceCorsa, String targa,
			String tipoBiglietto) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se la creazione del nuovo biglietto va a buon fine, viene
		// restituito
		// un riferimento all'oggetto della classe Biglietto contenente i dati del nuovo
		// biglietto e la
		// cronologia acquisti del cliente viene aggiornata; altrimenti, viene
		// restituito un riferimento null

		Biglietto biglietto = null;
		LocalDate data = null;
		LocalTime ora = null;
		int esito = 0;
		int codiceCliente = 0;
		int ricevuta = 0;
		CronologiaAcquisti c = null;
		List<Biglietto> lista = null;
		int codiceBiglietto = 1;
		char risposta = 'n';
		Corsa corsa = null;

		try {
			lista = BigliettoDAO.readallBiglietto();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (Biglietto b : lista) {
			if (b.getCodiceCorsa() == codiceCorsa) {
				codiceBiglietto = b.getCodiceBiglietto() + 1;
			}
		}

		data = LocalDate.now();
		ora = LocalTime.now();

		if (tipoBiglietto.equals("veicolo")) {
			biglietto = new BigliettoVeicolo(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato, targa);
		} else if (tipoBiglietto.equals("passeggero")) {
			biglietto = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
		} else {
			System.out.println("Il tipo di biglietto inserito non è valido.");
			return null;
		}

		try {
			BigliettoDAO.creaBiglietto(biglietto);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		esito = Stampante.stampa(data, ora, targa);
		if (esito != 0) {
			System.out.println(
					"Al momento non � possibile effettuare la stampa del biglietto." + "\nRiprovare tra 10 minuti");
		}

		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		System.out.println("Inserisci il codice del cliente che ha acquistato il biglietto");
		try {
			codiceCliente = Integer.parseInt(inputReader.readLine());
		} catch (NumberFormatException e) {
			codiceCliente = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (codiceCliente <= 0) {
			System.out.println("Errore: il codice cliente deve essere > 0.");
			try {
				BigliettoDAO.deleteBiglietto(codiceBiglietto, codiceCorsa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		System.out.println("Generare una nuova ricevuta d'acquisto? [y/n]");
		try {
			risposta = inputReader.readLine().charAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (risposta == 'n') {
			System.out.println("Inserisci la ricevuta d'acquisto");
			try {
				ricevuta = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				ricevuta = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (ricevuta <= 0) {
				System.out.println("Errore: la ricevuta deve essere > 0.");
				try {
					BigliettoDAO.deleteBiglietto(codiceBiglietto, codiceCorsa);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}

			try {
				c = CronologiaDAO.readCronologia(codiceCliente, codiceCorsa, ricevuta);
				if (c != null) {
					c.setBiglietto(biglietto);
					CronologiaDAO.updateCronologia(c);
				} else {
					System.out.println("L'acquisto selezionato non esiste.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (risposta == 'y') {
			// ricevuta = generazioneRicevuta();
			try {
				corsa = CorsaDAO.readCorsa(codiceCorsa);
				if (corsa != null) {
					c = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
					CronologiaDAO.creaCronologia(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Errore: carattere inserito non valido.");
		}

		return biglietto;
	}

	public static CronologiaAcquisti verificaAcquisti() {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se ci sono acquisti per i quali non � ancora stato emesso un
		// biglietto,
		// viene restituito un riferimento all'oggetto della classe CronologiaAcquisti
		// contenente i dati
		// del nuovo acquisto; altrimenti, viene restituito un riferimento null

		List<CronologiaAcquisti> lista = null;
		try {
			lista = CronologiaDAO.readallCronologia();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista == null)
			return null;

		for (CronologiaAcquisti c : lista) {
			if (c.getBiglietto().getCodiceBiglietto() == 0) {
				return c;
			}
		}

		return null;
	}

	public static void modificaCorsa() {
		// FUNZIONE NON IMPLEMENTATA
	}

	public static boolean cancellaCorsa() {
		return false;
		// FUNZIONE NON IMPLEMENTATA
	}

	protected static java.io.BufferedReader inputReader;
}
