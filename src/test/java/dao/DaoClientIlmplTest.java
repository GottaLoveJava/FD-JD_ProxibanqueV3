package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Client;
import model.CompteCourant;

public class DaoClientIlmplTest {
	DaoClientIlmpl dao = new DaoClientIlmpl();
	
	@Test
	public void testAjouterClient_DevraitAjouterUnClient() throws Exception {
		Client monClient = new Client();
		monClient.setNom("Cyril");
		monClient.setPrenom("Rabineau");
		boolean result = dao.ajouterClient(monClient);
		assertTrue(result);
	}
	
	@Test
	public void testAjouterClient_DevraitAjouterUnClientAvecSonCompteCourant() throws Exception {
		Client monClient = new Client();
		monClient.setNom("Cyril");
		monClient.setPrenom("Rabineau");
		CompteCourant courant = new CompteCourant();
		courant.setSolde(10000);
		courant.setDateOuverture("25 janvier");
		courant.setLimiteDecouvert(-4000);
		courant.setClient(monClient);
		monClient.setCompteCourant(courant);
		boolean result = dao.ajouterClient(monClient);
		assertTrue(result);
	}

}
