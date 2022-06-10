package poseidon.control;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import poseidon.DAO.CronologiaDAO;
import poseidon.entity.BigliettoVeicolo;
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
	
	/*
	 * 		TEST INPUT VALIDI
	 */
	
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
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 102, "AA000AA", "contante");
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
	void testAcquistoBiglietto_3() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10001;
		int codiceCorsa = 101;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "passeggero", 101, "AA000AA", "contante");
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
	void testAcquistoBiglietto_4() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10002;
		int codiceCorsa = 102;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10002, "Leonardo", "Catello", "passeggero", 102, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertNull(v.getTarga());
		assertNotNull(ricevuta);
	}
	
	
	
	/*
	 * 		TEST SINGLE
	 */
	
	@Test
	void testAcquistoBiglietto_5() throws SQLException {

		/* Output attesi */
		int codiceCliente = 10001;
		int codiceCorsa = 103;
		String targa = "AA000AA";
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 103, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(ricevuta.length(), 14);
		assertEquals(targa, v.getTarga());
		assertNotNull(ricevuta);
		assertNotNull(c);
	}
	
	
	
	/*
	 * 		TEST ERRORE
	 */
	
	@Test
	void testAcquistoBiglietto_6() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", -1, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_7() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_8() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(-1, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_9() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(50000, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_10() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", null, 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_11() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, null, "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_12() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, null, "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_13() throws SQLException {
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", null, "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_14() throws SQLException {
		
		/* Before each error tests */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		CronologiaAcquisti cronologia = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, "AA000AA", null);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals(cronologia, c);
		assertEquals(exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
	}

}
