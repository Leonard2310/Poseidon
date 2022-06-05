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
		// FUNZIONE NON IMPLEMENTATA

		int codiceCliente = 1;

		return codiceCliente;
	}
	
	public static int loginDipendente(String cognome, String nome, String password) {
		// FUNZIONE NON IMPLEMENTATA

		int codiceDipendente = 1;

		return codiceDipendente;
	}

	public static boolean logout() {
		// FUNZIONE NON IMPLEMENTATA

		return true;
	}

	public static List<Corsa> ricercaCorsa(int codiceCorsa) {
		// FUNZIONE NON IMPLEMENTATA
		
		List<Corsa> listaCorsa = new ArrayList<Corsa>();
		Corsa c = CorsaDAO.readCorsa(codiceCorsa);
		
		listaCorsa.add(c);
		
		return listaCorsa;
	}

	// CLIENTE

	public static List<CronologiaAcquisti> visualizzazioneCronologiaAcquisti(int codiceCliente) {
		// FUNZIONE NON IMPLEMENTATA
		
		List<CronologiaAcquisti> listaCronologia = new ArrayList<CronologiaAcquisti>();
		CronologiaAcquisti c = CronologiaDAO.readCronologia(codiceCliente);
		
		listaCronologia.add(c);
		
		return listaCronologia;
	}

	public static int acquistaBiglietto(int codiceCliente, String nome, String cognome, String tipoBiglietto, int codiceCorsa) {

		Corsa corsa;
		
		corsa = inserisciTipologiaBiglietto(codiceCorsa, tipoBiglietto);
		
		int disponibilita = calcolaDisponibilit√†(codiceCorsa, tipoBiglietto);
		if (disponibilita > 0) {
			System.out.println("La corsa √® disponibile");
			System.out.println("Il prezzo √® ");		// corsa.getprezzo() ??
			int bool_ricevuta[];
			bool_ricevuta= elaborazioneAcquisto();
			if(bool_ricevuta[0] == 0) {
				aggiuntaAcquistoCronologia(codiceCliente, nome, cognome, bool_ricevuta[1]);
				return bool_ricevuta[1];
			}
			else {
				System.out.println("Elaborazione d'acquisto fallita");
				break;
			}

		}
		else {
			System.out.println("Assenza di posti disponibili");
			break;
		}
		// TODO: Leonardo
	}

	public static int[] elaborazioneAcquisto() {
		// TODO Auto-generated method stub
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Scegli la tipologia di pagamento: \t");
			String tipologiaPagamento = input.nextLine();
			
			int bool_ricevuta[]; 
			bool_ricevuta[1] = SistemaDiPagamento.generateRicevuta();
			System.out.println("Il codice ricevuta √®: " +bool_ricevuta[1]);
			if(bool_ricevuta[1] !=  0) {
				bool_ricevuta[0] = 0;

			}
			else {
				bool_ricevuta[0] = 1;
			}
			return bool_ricevuta;
		}
		
	}

	public static void aggiuntaAcquistoCronologia(int codiceCliente, String nome, String cognome, int ricevuta) {

		// TODO: Leonardo, come modifico la cronologia? (sql?)
	}

	public static int calcolaDisponibilit√†(int codiceCorsa, String tipoBiglietto) {

		// TODO: Leonardo, come accedo alle informazioni di corsa? (sql?)

		int disponibilita = 10;

		return disponibilita;
	}

	public static Corsa inserisciTipologiaBiglietto(int codiceCorsa, String tipoBiglietto) {


		Corsa corsa;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Scegli la tipologia di biglietto: \t");
		String tipologiaBiglietto = input.nextLine();
		
		// TODO: LEONARDO, come accedo alla corsa? (sql?)


		return corsa;
	}

	// DIPENDENTE

	public static Corsa inserimentoCorsa(String portoArrivo, String portoPartenza,) {
		
		// TODO: DAIANA SCEMA
		
	}

	public static Biglietto emissioneBiglietto(int codiceImpiegato, int codiceCorsa, String targa, String tipoBiglietto) {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se la creazione del nuovo biglietto va a buon fine, viene restituito
		// un riferimento all'oggetto della classe Biglietto contenente i dati del nuovo biglietto e la
		// cronologia acquisti del cliente viene aggiornata; altrimenti, viene restituito un riferimento null
		
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
		}
		else if (tipoBiglietto.equals("passeggero")) {
			biglietto = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
		}
		else {
			System.out.println("Il tipo di biglietto inserito non Ë valido.");
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
			System.out.println("Al momento non Ë possibile effettuare la stampa del biglietto." +
								"\nRiprovare tra 10 minuti");
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
		
		System.out.println("Generare una nuova ricevuta d'acquisto? [y/n]");
		try {
			risposta = inputReader.readLine().charAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(risposta == 'n') {
			System.out.println("Inserisci la ricevuta d'acquisto");
			try {
				ricevuta = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				ricevuta = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				c = CronologiaDAO.readCronologia(codiceCliente, codiceCorsa, ricevuta);
				if (c != null) {
					c.setBiglietto(biglietto);
					CronologiaDAO.updateCronologia(c);
				}
				else {
					System.out.println("L'acquisto selezionato non esiste.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (risposta == 'y') {
			//ricevuta = generaRicevuta();
			try {
				//corsa = CorsaDAO.readCorsa(codiceCorsa);
				if (corsa != null) {
					c = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
					CronologiaDAO.creaCronologia(c);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Errore: carattere inserito non valido.");
		}
		
		return biglietto;
	}

	public static CronologiaAcquisti verificaAcquisti() {
		// PRECONDITIONS: -
		// POSTCONDITIONS: se ci sono acquisti per i quali non Ë ancora stato emesso un biglietto,
		// viene restituito un riferimento all'oggetto della classe CronologiaAcquisti contenente i dati
		// del nuovo acquisto; altrimenti, viene restituito un riferimento null
		
		List<CronologiaAcquisti> lista = null;
		try {
			lista = CronologiaDAO.readallCronologia();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (lista == null) return null;
		
		for (CronologiaAcquisti c : lista) {
			if (c.getBiglietto().getCodiceBiglietto() == 0) {
				return c;
			}
		}
		
		return null;
	}

	public static void modificaCorsa() {

	}

	public static boolean cancellaCorsa() {

	}

	protected static java.io.BufferedReader inputReader;
}
