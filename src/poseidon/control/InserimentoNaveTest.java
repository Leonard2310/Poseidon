package poseidon.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poseidon.DAO.*;
import poseidon.entity.*;
import poseidon.control.*;

class InserimentoNaveTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		NaveDAO.deleteallNave();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		NaveDAO.deleteallNave();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void InserimentoNaveTest_1() throws SQLException {
		fail("Not yet implemented");
	}

}
