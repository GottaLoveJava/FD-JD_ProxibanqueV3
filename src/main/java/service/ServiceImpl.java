package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import dao.IDaoClient;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
/**
 * @author François Destremau, Jean Deglaire
 * @version v3.0
 * Cette classe gère les méthodes métier, on injecte un objet dao avec
 * CDI pour découpler la dao et le service
 */
public class ServiceImpl implements IService, Serializable {

	private static final long serialVersionUID = -2153567868395626691L;
	@Inject
	private IDaoClient clientDao;

	@PostConstruct
	public void initService() {
		System.out.println(this.getClass().getName() + "je ne suis pas construit !" + clientDao);
	}

	@Override
	public List<Client> listeClients() throws Exception {
		List<Client> clients = clientDao.listeClients();
		return clients;
	}

	@Override
	public void ajouterClient(Client client) throws Exception {
		clientDao.ajouterClient(client);

	}

	@Override
	public Client afficherClient(long clientId) throws Exception {
		Client client = clientDao.afficherClient(clientId);
		return client;
	}

	@Override
	public void modifierClient(Client client) throws Exception {
		clientDao.modifierClient(client);

	}

	@Override
	public void supprimerClient(long id) throws Exception {
		clientDao.supprimerClient(id);
	}

	@Override
	public boolean effectuerVirement(Compte compteInitial, Compte compteDestinataire, double montant) throws Exception {
		compteInitial.setSolde(compteInitial.getSolde() - montant);
		compteDestinataire.setSolde(compteDestinataire.getSolde() + montant);
		modifierClient(retrouverClient(compteInitial));
		modifierClient(retrouverClient(compteDestinataire));
		return false;
	}

	private Client retrouverClient(Compte compte) {
		Client client = new Client();
		if ("model.CompteCourant".equals(compte.getClass().getName())) {
			client = ((CompteCourant) compte).getClient();
		}
		if ("model.CompteEpargne".equals(compte.getClass().getName())) {
			client = ((CompteEpargne) compte).getClient();
		}
		return client;
	}

	@Override
	public List<Compte> afficherComptes(long clientId) {
		Client client;
		List<Compte> comptes = new ArrayList<>();
		try {
			client = clientDao.afficherClient(clientId);
			comptes.add(client.getCompteCourant());
			comptes.add(client.getCompteEpargne());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comptes;
	}

	@Override
	public Compte afficherCompteEpargne(long clientId) throws Exception {
		Client client;
		client = clientDao.afficherClient(clientId);
		Compte compte = client.getCompteEpargne();
		return compte;
	}

	@Override
	public Compte afficherCompteCourant(long clientId) throws Exception {
		Client client;
		client = clientDao.afficherClient(clientId);
		Compte compte = client.getCompteCourant();
		return compte;
	}

}
