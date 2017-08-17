package service;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;


public class IServiceTest {

	IService serv = new ServiceImpl();

	@Test
	public void testEffectuerVirement() throws Exception {
		
		//Ajouter un client avec son compte Client et son Compte epargne
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
		serv.ajouterClient(monClient);
		
		
		Compte compteInitial = new CompteCourant();
		compteInitial.setSolde(1000);
		Compte compteDestinataire = new CompteEpargne();
		compteDestinataire.setSolde(0);
		serv.effectuerVirement(compteInitial, compteDestinataire, 500);
		fail("Not yet implemented");
	}
	
	@Test
	public void testAjouterClient() throws Exception {
		//Ajouter un client avec son compte Client et son Compte epargne
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
		serv.ajouterClient(monClient);
		fail("Not yet implemented");
	}

}
