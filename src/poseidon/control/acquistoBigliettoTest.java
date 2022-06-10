package poseidon.control;

import static org.junit.Assert.assertEquals;
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
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 102, "AA000AA", "contante");
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
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 103, "AA000AA", "contante");
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
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", -1, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: il codice corsa deve essere > 0.", exp_ricevuta, ricevuta);
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
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: la corsa selezionata non esiste.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_8() throws SQLException {	// 101, -1, passeggero, AA000AA, Leonardo, Catello, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(-1, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: il codice cliente deve essere > 0.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_9() throws SQLException {	// 101, 50000, passeggero, AA000AA, Leonardo, Catello, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(50000, "Leonardo", "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: il cliente selezionato non esiste.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_10() throws SQLException {	// 101, 10001, null, AA000AA, Leonardo, Catello, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", null, 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: il tipo di biglietto inserito non è valido.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_11() throws SQLException {	// 101, 10001, passeggero, null, Leonardo, Catello, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, null, "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: necessario inserire la targa.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_12() throws SQLException {	// 101, 10001, passeggero, AA000AA, null, Catello, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, null, "Catello", "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: necessario inserire il nome.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_13() throws SQLException {	// 101, 10001, passeggero, AA000AA, Leonardo, null, contante
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", null, "veicolo", 500, "AA000AA", "contante");
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: necessario inserire il cognome.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	@Test
	void testAcquistoBiglietto_14() throws SQLException {	// 101, 10001, passeggero, AA000AA, Leonardo, Catello, null
		
		/* Prima di ogni test di errore */
		CronologiaDAO.deleteallCronologia();
		
		/* Output attesi */
		int codiceCliente = 0;
		int codiceCorsa = 0;
		String exp_ricevuta = null;
		
		/* Eseguo la funzione */
		String ricevuta = gestisciCorsa.acquistaBiglietto(10001, "Leonardo", "Catello", "veicolo", 500, "AA000AA", null);
		CronologiaAcquisti c = CronologiaDAO.readallCronologia().get(0);
		BigliettoVeicolo v = (BigliettoVeicolo) c.getBiglietto();
				
		/* Controllo l'output */
		assertEquals(codiceCorsa, c.getCorsa().getCodiceCorsa());
		assertEquals(codiceCliente, c.getCodiceCliente());
		assertEquals("Errore: necessario inserire la tipologia del pagamento.", exp_ricevuta, ricevuta);
		assertNull(v.getTarga());
		
	}
	
	
	
	
	
//	DA CONTROLLARE
	@Test
	void testAcquistoBiglietto_15() throws SQLException {
		
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
	void testAcquistoBiglietto_16() throws SQLException {
		
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

	
// DA RIVEDERE	

	
	
	@Test
	void testEmissioneBiglietto_23() throws SQLException {
		
		/* Before each error tests */
		BigliettoDAO.deleteallBiglietto();
		
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
		
		/* Before each error tests */
		BigliettoDAO.deleteallBiglietto();
		
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
