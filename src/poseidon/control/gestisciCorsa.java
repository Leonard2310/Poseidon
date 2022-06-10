package poseidon.control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import poseidon.entity.*;
import poseidon.DAO.*;

import poseidon.external.SistemaDiPagamento;
import poseidon.external.Stampante;

public class gestisciCorsa {

	/*
	 * 
	 ************ UTENTE ************
	 *
	 */

	public static int loginCliente(String cognome, String nome, String password) {
		// PRECONDITIONS: i parametri passati in input sono valide stringhe testuali
		// POSTCONDITIONS: se il cliente identificato dai parametri in ingresso viene trovato nel database,
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
		// POSTCONDITIONS: se il dipendente identificato dai parametri in ingresso viene trovato nel database,
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
		// POSTCONDITIONS: se il cliente identificato dai parametri in ingresso non viene trovato nel database,
		// viene inserito e viene restituito il suo codice; altrimenti, viene restituito valore -1

		List<Cliente> lista_clienti = null;
		int codiceCliente = 10001;

		try {
			lista_clienti = ClienteDAO.readallCliente();

			for (Cliente c : lista_clienti) {
				if (c.getCognome().equals(cognome) && c.getNome().equals(nome) && c.getPassword().equals(password)) {
					System.out.println("Errore: cliente già esistente.");
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
		// POSTCONDITIONS: se il dipendente identificato dai parametri in ingresso non viene trovato nel database,
		// viene inserito e viene restituito il suo codice; altrimenti, viene restituito valore -1

		List<Dipendente> lista_dipendenti = null;
		int codiceImpiegato = 10001;

		try {
			lista_dipendenti = DipendenteDAO.readallDipendente();

			for (Dipendente d : lista_dipendenti) {
				if (d.getCognome().equals(cognome) && d.getNome().equals(nome) && d.getPassword().equals(password)) {
					System.out.println("Errore: dipendente gi� esistente.");
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
		// FUNZIONE NON RICHIESTA

		return true;
	}

	public static List<Corsa> ricercaCorsa(int codiceCorsa, LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza,
			String portoArrivo, double prezzo) {
		// FUNZIONE NON RICHIESTA

		List<Corsa> listaCorsa = new ArrayList<Corsa>();
		List<Corsa> listaFiltri = new ArrayList<Corsa>();

		Corsa corsa = null;
		int flag = 1;

		if (codiceCorsa != 0) {
			try {
				corsa = CorsaDAO.readCorsa(codiceCorsa);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			listaCorsa.add(corsa);
			return listaCorsa;
		} else if (orarioPartenza != null && orarioArrivo != null && portoPartenza != null && portoArrivo != null && prezzo != 0.0) {
			try {
				listaCorsa = CorsaDAO.readallCorsa();
				for (Corsa c : listaCorsa) {
					if (orarioArrivo.equals(c.getOrarioArrivo()) && orarioPartenza.equals(c.getOrarioPartenza())
							&& portoPartenza.equals(c.getPortoPartenza()) && portoArrivo.equals(c.getPortoArrivo())
							&& c.getPrezzo() <= prezzo) {
						listaFiltri.add(c);
						flag = 0;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			if (flag == 0) {
				return listaFiltri;
			} else {
				System.out.println("Nessuna corsa per i filtri specificati");
				return listaCorsa;
			}
		} else {
			try {
				listaCorsa = CorsaDAO.readallCorsa();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return listaCorsa;
		}
	}

	/*
	 * 
	 ************ CLIENTE ************
	 *
	 */

	public static List<CronologiaAcquisti> visualizzazioneCronologiaAcquisti(int codiceCliente) {
		// FUNZIONE NON RICHIESTA

		List<CronologiaAcquisti> lista = new ArrayList<CronologiaAcquisti>();
		List<CronologiaAcquisti> lista_cronologia = new ArrayList<CronologiaAcquisti>();

		try {
			lista = CronologiaDAO.readallCronologia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (CronologiaAcquisti c : lista) {
			if (codiceCliente == c.getCodiceCliente()) {
				lista_cronologia.add(c);
			}
		}

		return lista_cronologia;
	}

	/**
	 * @param codiceCliente: codice identificativo del cliente che si è loggato per acquistare un biglietto.
	 * @param nome: nome dell'intestatario del biglietto in acquisto.
	 * @param cognome: cognome dell'intestatario del biglietto in acquisto.
	 * @param tipoBiglietto: tipologia del biglietto in acquisto da parte del cliente [passeggero/veicolo].
	 * @param codiceCorsa: codice identificativo della corsa selezionata dall'utente.
	 * @param targa: targa identificativa del veicolo per il biglietto [tipologiaBiglietto = "veicolo"].
	 * @param tipologiaPagamento: tipologia del pagamento per l'elaborazione dell'acquisto.
	 * @return stringa identificativa dell'acquisto effettuato (null se acquisto fallito).
	 */
	public static String acquistaBiglietto(int codiceCliente, String nome, String cognome, String tipoBiglietto, int codiceCorsa,
			String targa, String tipologiaPagamento) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se c'è disponibilità nella corsa selezionata e l'elaborazione dell'acquisto avviene con successo viene
		// restituita una ricevuta e viene aggiunto l'acquisto in cronologia, in caso contrario viene restituita una stringa null.

		Corsa corsa = new Corsa(codiceCorsa, null, null, null, null, 0.0);
		boolean bool_pagamento = false;
		String ricevuta = null;

		// CONTROLLO INPUT
		if (codiceCliente <= 0) {
			System.out.println("Errore: il codice cliente deve essere > 0.");
			return null;
		}
		try {
			if (ClienteDAO.readCliente(codiceCliente) == null) {
				System.out.println("Errore: il cliente selezionato non esiste.");
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		if (nome == null || nome.equals("")) {
			System.out.println("Errore: necessario inserire il nome.");
			return null;
		}

		if (cognome == null || cognome.equals("")) {
			System.out.println("Errore: necessario inserire il cognome.");
			return null;
		}

		if (tipoBiglietto == null || (!tipoBiglietto.equals("veicolo") && !tipoBiglietto.equals("passeggero"))) {
			System.out.println("Errore: il tipo di biglietto inserito non è valido.");
			return null;
		}

		if (codiceCorsa <= 0) {
			System.out.println("Errore: il codice corsa deve essere > 0.");
			return null;
		}
		try {
			if (CorsaDAO.readCorsa(codiceCorsa) == null) {
				System.out.println("Errore: la corsa selezionata non esiste.");
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		if (tipoBiglietto.equals("veicolo") && (targa == null || targa.equals(""))) {
			System.out.println("Errore: necessario inserire la targa.");
			return null;
		}

		if (tipologiaPagamento == null || tipologiaPagamento.equals("")) {
			System.out.println("Errore: necessario inserire la tipologia del pagamento.");
			return null;
		}

		double prezzo_finale = inserisciTipologiaBiglietto(codiceCorsa, tipoBiglietto);

		if (prezzo_finale > 0.0) {

			int disponibilita = calcolaDisponibilitaCronologia(codiceCorsa, tipoBiglietto);

			if (disponibilita > 0) {
				System.out.println("La corsa è disponibile (" + disponibilita + " posti rimanenti)");
				System.out.println("Il prezzo finale è " + prezzo_finale +"0€");

				bool_pagamento = SistemaDiPagamento.elaborazioneAcquisto(tipologiaPagamento);

				if (bool_pagamento == true) {
					ricevuta = generateRicevuta(codiceCliente, codiceCorsa, tipoBiglietto);

					try {
						corsa = CorsaDAO.readCorsa(codiceCorsa);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					aggiuntaAcquistoCronologia(codiceCliente, ricevuta, corsa, tipoBiglietto, targa);
					return ricevuta;
				} else {
					System.out.println("Errore: elaborazione d'acquisto fallita");
					return null;
				}
			} else {
				System.out.println("Errore: assenza di posti disponibili");
				return null;
			}
		} else {
			System.out.println("Errore: prezzo non valido");
			return null;
		}

	}

	/**
	 * @param codiceCliente: codice identificativo del cliente che si è loggato per acquistare un biglietto.
	 * @param codiceCorsa: codice identificativo della corsa selezionata dall'utente.
	 * @param tipoBiglietto: tipologia del biglietto in acquisto da parte del cliente [passeggero/veicolo].
	 * @return stringa alfanumerica identificativa dell'acquisto effettuato formata da codiceCliente, codiceCorsa, 
	 * 			identificativo del tipoBiglietto e tre caratteri alfanumerici casuali.
	 */
	public static String generateRicevuta(int codiceCliente, int codiceCorsa, String tipoBiglietto) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene generata una stringa formata dal codice cliente, dal codice della corsa che si sta acquistando,
		// da un riferimento alla tipologia del biglietto e da tre caratteri alfanumerici casuali.

		String vocabolarioAlfaNumerico;
		StringBuilder builder;
		String ricevuta = null;

		int i = 3; // Grandezza stringa alfanumerica randomica
		vocabolarioAlfaNumerico = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"; // Vocabolario alfanumerico
		builder = new StringBuilder(i); // String buffer

		for (int m = 0; m < i; m++) {
			int myindex = (int) (vocabolarioAlfaNumerico.length() * Math.random());

			builder.append(vocabolarioAlfaNumerico.charAt(myindex));
			builder.toString();
		}

		if (tipoBiglietto.equals("passeggero")) {
			ricevuta = codiceCliente + "PAS" + codiceCorsa + builder;
		} else if (tipoBiglietto.equals("veicolo")) {
			ricevuta = codiceCliente + "VEI" + codiceCorsa + builder;
		}

		return ricevuta;
	}

	/**
	 * @param codiceCliente
	 * @param ricevuta
	 * @param corsa
	 * @param tipoBiglietto
	 * @param targa
	 */
	public static void aggiuntaAcquistoCronologia(int codiceCliente, String ricevuta, Corsa corsa, String tipoBiglietto, String targa) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene aggiunto l'acquisto in cronologia mediante il codice cliente, l'istanza della corsa la ricevuta e,
		// nel caso dell'acquisto di un biglietto con opzione veicolo, la targa.

		Biglietto b = null;

		if (tipoBiglietto.equals("passeggero")) {
			b = new BigliettoPasseggero(0, null, null, 0, 0);
		} else if (tipoBiglietto.equals("veicolo")) {
			b = new BigliettoVeicolo(0, null, null, 0, 0, targa);
		}

		CronologiaAcquisti c = new CronologiaAcquisti(codiceCliente, corsa, b, ricevuta);
		try {
			CronologiaDAO.creaCronologia(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @param codiceCorsa
	 * @param tipoBiglietto
	 * @return
	 */
	public static int calcolaDisponibilitaCronologia(int codiceCorsa, String tipoBiglietto) {
		// PRECONDITIONS: creazione delle Navi effettuata
		// POSTCONDITIONS: viene calcolata la disponibilità in base alla capacità della nave e ai posti occupati
		// effettuando un controllo sulle ricevute di acquisto per la corsa specificata e per tale tipologia.

		List<CronologiaAcquisti> lista_cronologia = new ArrayList<CronologiaAcquisti>();
		List<Nave> lista_nave = new ArrayList<Nave>();
		
		Corsa corsa = new Corsa(0, null, null, null, null, 0);

		String ricevuta = null;
		int capienza = 0;
		int count = 0;

		try {
			lista_nave = NaveDAO.readallNave();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Nave n : lista_nave) {
			if (codiceCorsa == n.getCodiceCorsa()) {
				if (tipoBiglietto.equals("passeggero")) {
					capienza = n.getCapienzaPassegeri();
				} else if (tipoBiglietto.equals("veicolo")) {
					capienza = n.getCapienzaAutoveicoli();
				}
			}
		}

		try {
			lista_cronologia = CronologiaDAO.readallCronologia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (CronologiaAcquisti c : lista_cronologia) {
			corsa = c.getCorsa();
			if (codiceCorsa == corsa.getCodiceCorsa()) {
				ricevuta = c.getRicevuta();
				if (ricevuta.contains("VEI") && tipoBiglietto.equals("veicolo")) {
					count += 1;
				} else if (ricevuta.contains("PAS") && tipoBiglietto.equals("passeggero")) {
					count += 1;
				}
			}
		}

		int disponibilita = capienza - count;

		return disponibilita;
	}

	/**
	 * @param codiceCorsa
	 * @param tipoBiglietto
	 * @return
	 */
	public static double inserisciTipologiaBiglietto(int codiceCorsa, String tipoBiglietto) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene calcolato il prezzo finale in base alla tipologia scelta dall'utente

		Corsa corsa = new Corsa(0, null, null, null, null, 0);
		double prezzo_finale = 0.0;

		try {
			corsa = CorsaDAO.readCorsa(codiceCorsa);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (corsa != null) {

			if (tipoBiglietto.equals("passeggero")) {
				prezzo_finale = corsa.getPrezzo();
			} else {
				prezzo_finale = 50.0 + corsa.getPrezzo();

			}
		}

		return prezzo_finale;
	}

	/*
	 * 
	 ************ DIPENDENTE ************
	 *
	 */

	/**
	 * @param orarioPartenza
	 * @param orarioArrivo
	 * @param portoPartenza
	 * @param portoArrivo
	 * @param prezzo
	 * @param nomeNave
	 * @return
	 */
	public static Corsa inserimentoCorsa(LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza, String portoArrivo,
			Double prezzo, String nomeNave) {
		// PRECONDIZIONE: -
		// POSTOCONDIZIONE: se l'inserimento della corsa e' avvenuta con successo viene estituito un riferimento all'oggetto
		// della classe Corsa contenente i dati della nuova corsa. Altrimenti viene restituita l'istanza = null.

		Corsa corsa = null;
		Nave nave = null;
		int codiceCorsa = 1;
		List<Corsa> lista_corsa = new ArrayList<Corsa>();

		// Verifica se gia' esiste la nave (ad ogni nave e' associata una determinata corsa)
		try {
			nave = NaveDAO.readNave(nomeNave);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (nave == null) {

			try {
				lista_corsa = CorsaDAO.readallCorsa();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			for (Corsa c : lista_corsa) {
				codiceCorsa = c.getCodiceCorsa() + 1;
			}

			corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			
			try {
				CorsaDAO.creaCorsa(corsa);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else {
			System.out.println("Errore: la nave inserita e' gia' esistente.");
			return null;
		}

		return corsa;
	}

	/**
	 * @param nomeNave
	 * @param categoria
	 * @param capienzaPasseggeri
	 * @param capienzaAutoveicoli
	 * @param codiceCorsa
	 * @return
	 */
	public static Nave inserimentoNave(String nomeNave, String categoria, int capienzaPasseggeri, int capienzaAutoveicoli,
			int codiceCorsa) {
		// PRECONDIZIONE: deve essere stata inserita con successo una nuova corsa
		// POSTOCONDIZIONE: se l'inserimento della nave e' avvenuta con successo viene restituito un riferimento all'oggetto
		// della classe Nave contenente i dati della nuova nave. Altrimenti viene restituita l'istanza = null.

		Nave nave = null;

		nave = new Nave(nomeNave, capienzaAutoveicoli, capienzaPasseggeri, categoria, codiceCorsa);

		try {
			NaveDAO.creaNave(nave);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return nave;
	}

	/**
	 * @param citta
	 * @return
	 */
	public static Porto inserimentoPorto(String citta) {
		// PRECONDIZIONE: deve essere stata inserita con successo una nuova nave
		// POSTOCONDIZIONE: se l'inserimento del porto e' avvenuta con successo viene restituito un riferimento all'oggetto
		// della classe Porto contenente i dati del nuovo Porto. Altrimenti viene restituita l'istanza = null.

		Porto porto = null;

		try {
			porto = PortoDAO.readPorto(citta);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (porto == null) {
			porto = new Porto(citta);

			try {
				PortoDAO.creaPorto(porto);
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		} else {
			// System.out.println("Il porto inserito e' gia' esistente.");
			return null;
		}

		return porto;
	}
	
	/**
	 * @param codiceCorsa
	 * @param tipoBiglietto
	 * @return
	 */
	public static int calcolaDisponibilitaBiglietti(int codiceCorsa, String tipoBiglietto) {
		// PRECONDITIONS: creazione delle Navi effettuata
		// POSTCONDITIONS: viene calcolata la disponibilità in base alla capacità della nave e ai posti occupati
		// effettuando un controllo sui biglietti acquistati per la corsa specificata e per tale tipologia.

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
				if (tipoBiglietto.equals("passeggero")) {
					capienza = n.getCapienzaPassegeri();
				} else if (tipoBiglietto.equals("veicolo")) {
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
				if (tipoBiglietto.equals("veicolo")) {
					if ((b instanceof BigliettoVeicolo) == true) {
						count += 1;
					}
				} else if (tipoBiglietto.equals("passeggero")) {
					if ((b instanceof BigliettoPasseggero) == true) {
						count += 1;
					}
				}
			}
		}

		int disponibilita = capienza - count;

		return disponibilita;
	}

	/**
	 * @param codiceImpiegato
	 * @param codiceCorsa
	 * @param targa
	 * @param tipoBiglietto
	 * @param codiceCliente
	 * @param flag
	 * @param ricevuta
	 * @return
	 */
	public static Biglietto emissioneBiglietto(int codiceImpiegato, int codiceCorsa, String targa, String tipoBiglietto,
			int codiceCliente, char flag, String ricevuta) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se la creazione del nuovo biglietto va a buon fine, viene restituito un riferimento all'oggetto
		// della classe Biglietto contenente i dati del nuovo biglietto e la cronologia acquisti del cliente viene aggiornata;
		// altrimenti, viene restituito un riferimento null

		Biglietto biglietto = null;
		LocalDate data = null;
		LocalTime ora = null;
		int esito = 0;
		CronologiaAcquisti c = null;
		List<Biglietto> lista = null;
		int codiceBiglietto = 1;
		Corsa corsa = null;

		/* Controllo input */
		if (codiceImpiegato <= 0) {
			System.out.println("Errore: il codice impiegato deve essere > 0.");
			return null;
		}
		try {
			if (DipendenteDAO.readDipendente(codiceImpiegato) == null) {
				System.out.println("Errore: l'impiegato selezionato non esiste.");
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		if (codiceCorsa <= 0) {
			System.out.println("Errore: il codice corsa deve essere > 0.");
			return null;
		}
		try {
			if (CorsaDAO.readCorsa(codiceCorsa) == null) {
				System.out.println("Errore: la corsa selezionata non esiste.");
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		if (tipoBiglietto == null || (!tipoBiglietto.equals("veicolo") && !tipoBiglietto.equals("passeggero"))) {
			System.out.println("Errore: il tipo di biglietto inserito non � valido.");
			return null;
		}

		if (tipoBiglietto.equals("veicolo") && (targa == null || targa.equals(""))) {
			System.out.println("Errore: necessario inserire la targa.");
			return null;
		}

		if (codiceCliente <= 0) {
			System.out.println("Errore: il codice cliente deve essere > 0.");
			return null;
		}
		try {
			if (ClienteDAO.readCliente(codiceCliente) == null) {
				System.out.println("Errore: il cliente selezionato non esiste.");
				return null;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}

		if (flag == 'n') {
			if (ricevuta == null || ricevuta.equals("")) {
				System.out.println("Errore: necessario inserire la ricevuta.");
				return null;
			}
			try {
				CronologiaAcquisti cronologia = CronologiaDAO.readCronologia(codiceCliente, codiceCorsa, ricevuta);
				if (cronologia == null) {
					System.out.println("Errore: l'acquisto selezionato non esiste.");
					return null;
				} else if (tipoBiglietto.equals("veicolo")) {
					if (!(cronologia.getBiglietto() instanceof BigliettoVeicolo)) {
						System.out.println("Errore: l'acquisto selezionato riguarda un biglietto passeggero.");
						return null;
					} else {
						BigliettoVeicolo v = (BigliettoVeicolo) cronologia.getBiglietto();
						if (!v.getTarga().equals(targa)) {
							System.out.println("Errore: l'acquisto selezionato presenta una targa diversa.");
							return null;
						}
					}
				} else if (!(cronologia.getBiglietto() instanceof BigliettoPasseggero)) {
					System.out.println("Errore: l'acquisto selezionato riguarda un biglietto veicolo.");
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		if (calcolaDisponibilitaBiglietti(codiceCorsa, tipoBiglietto) <= 0) {
			System.out.println("Errore: non ci sono pi� biglietti disponibili di questo tipo per questa corsa.");
			return null;
		}

		try {
			lista = BigliettoDAO.readallBiglietto();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		for (Biglietto b : lista) {
			if (b.getCodiceCorsa() == codiceCorsa) {
				codiceBiglietto = b.getCodiceBiglietto() + 1;
			}
		}

		data = LocalDate.now();
		ora = LocalTime.now();
		/* Elimino i nanosecondi perch� non � richiesto che vengano registati */
		ora = LocalTime.of(ora.getHour(), ora.getMinute(), ora.getSecond());

		if (tipoBiglietto.equals("veicolo")) {
			biglietto = new BigliettoVeicolo(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato, targa);
		} else if (tipoBiglietto.equals("passeggero")) {
			biglietto = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
		}

		try {
			BigliettoDAO.creaBiglietto(biglietto);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		esito = Stampante.stampa(data, ora, targa);
		if (esito != 0) {
			System.out.println("Al momento non è possibile effettuare la stampa del biglietto." + "\nRiprovare tra 10 minuti");
		}

		if (flag == 'n') {
			try {
				c = CronologiaDAO.readCronologia(codiceCliente, codiceCorsa, ricevuta);
				if (c != null) {
					c.setBiglietto(biglietto);
					CronologiaDAO.updateCronologia(c);
				} else {
					System.out.println("L'acquisto selezionato non esiste.");
					BigliettoDAO.deleteBiglietto(codiceBiglietto, codiceCorsa);
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (flag == 'y') {
			ricevuta = generateRicevuta(codiceCliente, codiceCorsa, tipoBiglietto);
			try {
				corsa = CorsaDAO.readCorsa(codiceCorsa);
				if (corsa != null) {
					c = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
					CronologiaDAO.creaCronologia(c);
				} else {
					System.out.println("La corsa selezionata non esiste.");
					BigliettoDAO.deleteBiglietto(codiceBiglietto, codiceCorsa);
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return biglietto;
	}

	/**
	 * @return
	 */
	public static List<CronologiaAcquisti> verificaAcquisti() {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se ci sono acquisti per i quali non è ancora stato emesso un biglietto, viene restituito un
		// riferimento all'oggetto della classe CronologiaAcquisti contenente i dati del nuovo acquisto;
		// altrimenti, viene restituito un riferimento null.

		List<CronologiaAcquisti> lista = null;
		List<CronologiaAcquisti> lista_result = new ArrayList<CronologiaAcquisti>();

		try {
			lista = CronologiaDAO.readallCronologia();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (lista == null)
			return null;

		for (CronologiaAcquisti c : lista) {
			if (c.getBiglietto().getCodiceBiglietto() == 0) {
				lista_result.add(c);
			}
		}
		return lista_result;
	}

	public static void modificaCorsa() {
		// FUNZIONE NON RICHIESTA
	}

	public static boolean cancellaCorsa() {
		return false;
		// FUNZIONE NON RICHIESTA
	}

	protected static java.io.BufferedReader inputReader;
}
