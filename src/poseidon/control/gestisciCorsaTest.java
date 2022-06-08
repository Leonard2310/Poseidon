package poseidon.control;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import poseidon.DAO.BigliettoDAO;
import poseidon.DAO.CorsaDAO;
import poseidon.DAO.CronologiaDAO;
import poseidon.entity.Biglietto;
import poseidon.entity.BigliettoPasseggero;
import poseidon.entity.BigliettoVeicolo;
import poseidon.entity.Corsa;
import poseidon.entity.CronologiaAcquisti;

class gestisciCorsaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CronologiaDAO.deleteallCronologia();
		BigliettoDAO.deleteallBiglietto();
		// insert di corse, clienti e dipendenti se non presenti
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		CronologiaDAO.deleteallCronologia();
		BigliettoDAO.deleteallBiglietto();
		// delete di corse, clienti e dipendenti (forse no)
	}

	@BeforeEach
	void setUp() throws Exception {
		CronologiaDAO.deleteallCronologia();
	}

	@AfterEach
	void tearDown() throws Exception {
		CronologiaDAO.deleteallCronologia();
	}
	
	@Test
	void testEmissioneBiglietto_1() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 1;
		int codiceCorsa = 101;
		int codiceImpiegato = 1001;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}

	@Test
	void testEmissioneBiglietto_2() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 2;
		int codiceCorsa = 101;
		int codiceImpiegato = 1002;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 101, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_3() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 1;
		int codiceCorsa = 102;
		int codiceImpiegato = 1001;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 102, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_4() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 2;
		int codiceCorsa = 102;
		int codiceImpiegato = 1002;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 102, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_5() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 3;
		int codiceCorsa = 101;
		int codiceImpiegato = 1001;
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "passeggero", 10001, 'y', "10001V1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}

	@Test
	void testEmissioneBiglietto_6() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 4;
		int codiceCorsa = 101;
		int codiceImpiegato = 1002;
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 101, "AA000AA", "passeggero", 10001, 'y', "10001V1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_7() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 3;
		int codiceCorsa = 102;
		int codiceImpiegato = 1001;
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 102, "AA000AA", "passeggero", 10001, 'y', "10001V1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_8() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 4;
		int codiceCorsa = 102;
		int codiceImpiegato = 1002;
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 102, "AA000AA", "passeggero", 10001, 'y', "10001V1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_9() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001V1019UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 5;
		int codiceCorsa = 101;
		int codiceImpiegato = 1001;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'n', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001V1019UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}

	@Test
	void testEmissioneBiglietto_10() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001V1019UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 6;
		int codiceCorsa = 101;
		int codiceImpiegato = 1002;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 101, "AA000AA", "veicolo", 10001, 'n', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001V1019UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_11() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(102);
		Biglietto big = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001V1029UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 5;
		int codiceCorsa = 102;
		int codiceImpiegato = 1001;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 102, "AA000AA", "veicolo", 10001, 'n', "10001V1029UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001V1029UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_12() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(102);
		Biglietto big = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001V1029UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 6;
		int codiceCorsa = 102;
		int codiceImpiegato = 1002;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 102, "AA000AA", "veicolo", 10001, 'n', "10001V1029UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001V1029UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_13() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoPasseggero(0, null, null, 0, 0);
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001P1019UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 7;
		int codiceCorsa = 101;
		int codiceImpiegato = 1001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "passeggero", 10001, 'n', "10001P1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001P1019UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}

	@Test
	void testEmissioneBiglietto_14() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoPasseggero(0, null, null, 0, 0);
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001P1019UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 8;
		int codiceCorsa = 101;
		int codiceImpiegato = 1002;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 101, "AA000AA", "passeggero", 10001, 'n', "10001P1019UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001P1019UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_15() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(102);
		Biglietto big = new BigliettoPasseggero(0, null, null, 0, 0);
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001P1029UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 7;
		int codiceCorsa = 102;
		int codiceImpiegato = 1001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 102, "AA000AA", "passeggero", 10001, 'n', "10001P1029UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001P1029UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_16() throws SQLException {

		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(102);
		Biglietto big = new BigliettoPasseggero(0, null, null, 0, 0);
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, big, "10001P1029UO");
		CronologiaDAO.creaCronologia(cronologia);
		
		/* Output attesi */
		int codiceBiglietto = 8;
		int codiceCorsa = 102;
		int codiceImpiegato = 1002;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1002, 102, "AA000AA", "passeggero", 10001, 'n', "10001P1029UO");
		BigliettoPasseggero biglietto = (BigliettoPasseggero) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, codiceCorsa, "10001P1029UO");
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoPasseggero);
		assertEquals(b, biglietto);
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_17() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 9;
		int codiceCorsa = 101;
		int codiceImpiegato = 1003;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1003, 101, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_18() throws SQLException {

		/* Output attesi */
		int codiceBiglietto = 1;
		int codiceCorsa = 103;
		int codiceImpiegato = 1001;
		String targa = "AA000AA";
		int codiceCliente = 10001;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 103, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		BigliettoVeicolo v = (BigliettoVeicolo)b;
		BigliettoVeicolo biglietto = (BigliettoVeicolo) BigliettoDAO.readBiglietto(codiceBiglietto, codiceCorsa);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		Corsa corsa = CorsaDAO.readCorsa(codiceCorsa);
				
		/* Controllo l'output */
		assertEquals(codiceBiglietto, b.getCodiceBiglietto());
		assertEquals(codiceCorsa, b.getCodiceCorsa());
		assertEquals(codiceImpiegato, b.getCodiceImpiegato());
		assertTrue(b instanceof BigliettoVeicolo);
		assertEquals(targa, v.getTarga());
		assertEquals(b, biglietto);
		assertEquals(corsa, c.getCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(b, c.getBiglietto());
	}
	
	@Test
	void testEmissioneBiglietto_19() throws SQLException {
		
		/* Before all error tests */
		BigliettoDAO.deleteallBiglietto();

		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(-1, 101, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}

	@Test
	void testEmissioneBiglietto_20() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(5000, 101, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_21() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, -1, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_22() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 500, "AA000AA", "veicolo", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_23() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "animale", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_24() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, null, "veicolo", 10001, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_25() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", -1, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_26() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 50000, 'y', "10001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	@Test
	void testEmissioneBiglietto_27() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 101, 0, "AA000AA");
		CronologiaAcquisti crono = new CronologiaAcquisti(10001, corsa, big, "10001V1019UO");
		CronologiaDAO.creaCronologia(crono);
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		CronologiaAcquisti cronologia = crono;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'n', null);
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, 101, "10001V1019UO");

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(cronologia, c);
	}
	
	@Test
	void testEmissioneBiglietto_28() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 101, 0, "AA000AA");
		CronologiaAcquisti crono = new CronologiaAcquisti(10001, corsa, big, "10001V1019UO");
		CronologiaDAO.creaCronologia(crono);
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		CronologiaAcquisti cronologia = crono;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'n', "50001V1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, 101, "10001V1019UO");

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(cronologia, c);
	}
}
