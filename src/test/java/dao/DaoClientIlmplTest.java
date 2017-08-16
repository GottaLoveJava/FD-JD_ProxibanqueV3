package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Client;

public class DaoClientIlmplTest {
	DaoClientIlmpl dao = new DaoClientIlmpl();
	
	@Test
	public void testAjouterClient() throws Exception {
		Client monClient = new Client();
		monClient.setNom("Cyril");
		monClient.setPrenom("Rabineau");
		boolean result = dao.ajouterClient(monClient);
		assertTrue(result);
	}

}
