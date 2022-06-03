package poseidon.control;

import java.util.ArrayList;
import java.util.List;
import poseidon.entity.*;
import poseidon.DAO.*;

public class gestisciCorsa {

	// UTENTE

	public static int login(String cognome, String nome, String password) {
		// FUNZIONE NON IMPLEMENTATA

		int codiceCliente = 1;

		return codiceCliente;
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

	public static int acquistaBiglietto(int codiceCliente, String nome, String cognome, String categoria, String codiceCorsa) {

		int ricevuta = 0;

		// TODO: Leonardo

		return ricevuta;
	}

	public static void aggiuntaAcquistoCronologia(int codiceCliente, String nome, String cognome, int ricevuta) {

		// TODO: Leonardo
	}

	public static int calcolaDisponibilità(String codiceNave, String categoria) {

		// TODO: Leonardo

		int disponibilità = 10;

		return disponibilità;
	}

	public static Corsa inserisciTipologiaBiglietto(String codiceNave, String categoria) {

		// TODO: Leonardo

		Corsa c;

		return c;
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
