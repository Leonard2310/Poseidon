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
import poseidon.entity.BigliettoVeicolo;
import poseidon.entity.Corsa;
import poseidon.entity.CronologiaAcquisti;

class acquistoBigliettoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CronologiaDAO.deleteallCronologia();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		CronologiaDAO.deleteallCronologia();
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
	void testAcquistoBiglietto_1() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10001;
		int codiceCorsa = 101;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 101, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertEquals(targa, v.getTarga());
		assertNotNull(ricevuta);
	}
	
	@Test
	void testAcquistoBiglietto_2() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10002;
		int codiceCorsa = 101;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10002, "Leonardo", "Catello", "passeggero", 101, null, "bancomat");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertNull(v.getTarga());
		assertNotNull(ricevuta);
	}
	
	@Test
	void testAcquistoBiglietto_3() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10003;
		int codiceCorsa = 3;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10003, "Daiana", "Cipollaro", "passeggero", 3, null, "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 12);
		assertNull(v.getTarga());
		assertNotNull(ricevuta);
	}
	
	@Test
	void testAcquistoBiglietto_4() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto biglietto = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10003, corsa, biglietto, "10001VEI1019UO");
		CronologiaDAO.creaCronologia(cronologia);

		/* Output attesi */
		int codiceCorsa = 101;
		int codiceCliente = 1003;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10003, "Daiana", "Cipollaro", "veicolo", 101, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertEquals(targa, v.getTarga());
		assertNotNull(ricevuta);
	}
	
	@Test
	void testAcquistoBiglietto_5() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(103);
		Biglietto biglietto = new BigliettoVeicolo(0, null, null, 0, 0, "AA000AA");
		CronologiaAcquisti cronologia = new CronologiaAcquisti(10001, corsa, biglietto, "10001VEI1019UO");
		CronologiaDAO.creaCronologia(cronologia);

		/* Output attesi */
		int codiceCorsa = 103;
		int codiceCliente = 1001;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10003, "Leonardo", "Catello", "passeggero", 103, null, "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertNull(v.getTarga());
		assertNotNull(ricevuta);
	}

	
	
	@Test
	void testEmissioneBiglietto_15() throws SQLException {
		
		/* Before all error tests */
		BigliettoDAO.deleteallBiglietto();

		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(-1, 101, "AA000AA", "veicolo", 10001, 'y', "10001VEI1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}

	@Test
	void testEmissioneBiglietto_16() throws SQLException {
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		List<CronologiaAcquisti> lista_cronologie = new ArrayList<CronologiaAcquisti>(0);
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(5000, 101, "AA000AA", "veicolo", 10001, 'y', "10001VEI1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		List<CronologiaAcquisti> lista_c = CronologiaDAO.readallCronologia();

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(lista_cronologie, lista_c);
	}
	
	
	@Test
	void testEmissioneBiglietto_23() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 101, 0, "AA000AA");
		CronologiaAcquisti crono = new CronologiaAcquisti(10001, corsa, big, "10001VEI1019UO");
		CronologiaDAO.creaCronologia(crono);
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		CronologiaAcquisti cronologia = crono;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'n', null);
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, 101, "10001VEI1019UO");

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(cronologia, c);
	}
	
	@Test
	void testEmissioneBiglietto_24() throws SQLException {
		
		/* Inizializzazione precondizioni */
		Corsa corsa = CorsaDAO.readCorsa(101);
		Biglietto big = new BigliettoVeicolo(0, null, null, 101, 0, "AA000AA");
		CronologiaAcquisti crono = new CronologiaAcquisti(10001, corsa, big, "10001VEI1019UO");
		CronologiaDAO.creaCronologia(crono);
		
		/* Output attesi */
		Biglietto biglietto = null;
		List<Biglietto> lista_biglietti = new ArrayList<Biglietto>(0);
		CronologiaAcquisti cronologia = crono;
		
		/* Eseguo la funzione */
		Biglietto b = gestisciCorsa.emissioneBiglietto(1001, 101, "AA000AA", "veicolo", 10001, 'n', "50001VEI1019UO");
		List<Biglietto> lista_b = BigliettoDAO.readallBiglietto();
		CronologiaAcquisti c = CronologiaDAO.readCronologia(10001, 101, "10001VEI1019UO");

		/* Controllo l'output */
		assertEquals(biglietto, b);
		assertEquals(lista_biglietti, lista_b);
		assertEquals(cronologia, c);
	}
}
