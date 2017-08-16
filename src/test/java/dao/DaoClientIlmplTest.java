package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.Client;
import model.CompteCourant;
import model.CompteEpargne;

public class DaoClientIlmplTest {
	IDaoClient dao = new DaoClientImpl();
	
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
	
	@Test
	public void testAjouterClient_DevraitAjouterUnClientAvecSonCompteCourantEtSonCompteEpargne() throws Exception {
		Client monClient = new Client();
		monClient.setNom("Cyril");
		monClient.setPrenom("Rabineau");
		CompteCourant courant = new CompteCourant();
		courant.setSolde(10000);
		courant.setDateOuverture("25 janvier");
		courant.setLimiteDecouvert(-4000);
		CompteEpargne epargne = new CompteEpargne();
		epargne.setSolde(10000);
		epargne.setDateOuverture("25 janvier");
		epargne.setTauxRemuneration(90);
		monClient.setCompteCourant(courant);
		monClient.setCompteEpargne(epargne);
		boolean result = dao.ajouterClient(monClient);
		assertTrue(result);
	}
	
	//TODO Supprimer les tests moches
	
//	@Test
//	public void testAfficherClient() throws Exception {
//		Client monClient = new Client();
//		monClient.setNom("Rabineau");
//		monClient.setPrenom("Cyril");
//		monClient.setAdresse("21 rue de la Paix");
//		monClient.setCodePostal("75008");
//		monClient.setEmail("cyril.rab@gmail.com");
//		monClient.setTelephone("0845645621");
//		monClient.setVille("Paris");
//		monClient.setEntreprise(true);
//		CompteCourant courant = new CompteCourant();
//		courant.setSolde(10000);
//		courant.setDateOuverture("25 janvier");
//		courant.setLimiteDecouvert(-4000);
//		CompteEpargne epargne = new CompteEpargne();
//		epargne.setSolde(10000);
//		epargne.setDateOuverture("25 janvier");
//		epargne.setTauxRemuneration(90);
//		dao.ajouterClient(monClient);
//		Client clientRecu = dao.afficherClient(1);
//		System.out.println(clientRecu.getNom());
//		assertEquals("Cyril", clientRecu.getPrenom());
//	}
	
	@Test
	public void testAfficherClients() throws Exception {
		Client monClient = new Client();
		monClient.setNom("Rabineau");
		monClient.setPrenom("Cyril");
		monClient.setAdresse("21 rue de la Paix");
		monClient.setCodePostal("75008");
		monClient.setEmail("cyril.rab@gmail.com");
		monClient.setTelephone("0845645621");
		monClient.setVille("Paris");
		monClient.setEntreprise(true);
		CompteCourant courant = new CompteCourant();
		courant.setSolde(10000);
		courant.setDateOuverture("25 janvier");
		courant.setLimiteDecouvert(-4000);
		CompteEpargne epargne = new CompteEpargne();
		epargne.setSolde(10000);
		epargne.setDateOuverture("25 janvier");
		epargne.setTauxRemuneration(90);
		dao.ajouterClient(monClient);
		List<Client> clientRecu = dao.listeClients();
		System.out.println("avant la boucle");
		for (Client client : clientRecu) {
			System.out.println(client.getPrenom() + " " + client.getNom() + " ca marche !");
		}
		System.out.println("après la boucle");
		assertEquals("Cyril", clientRecu.get(0).getPrenom());
	}

}
