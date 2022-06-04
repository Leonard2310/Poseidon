package poseidon.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import poseidon.entity.*;
import poseidon.DAO.*;

import poseidon.external.SistemaDiPagamento;


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
		
		int disponibilita = calcolaDisponibilità(codiceCorsa, tipoBiglietto);
		if (disponibilita > 0) {
			System.out.println("La corsa è disponibile");
			System.out.println("Il prezzo è ");		// corsa.getprezzo() ??
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
			System.out.println("Il codice ricevuta è: " +bool_ricevuta[1]);
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

	public static int calcolaDisponibilità(int codiceCorsa, String tipoBiglietto) {

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

	public static Biglietto emissioneBiglietto() {

		// TODO: FRANCESCO
	}

	public static int verificaAcquisti() {
		// TODO: FRANCESCO
	}

	public static void modificaCorsa() {

	}

	public static boolean cancellaCorsa() {

	}

}
