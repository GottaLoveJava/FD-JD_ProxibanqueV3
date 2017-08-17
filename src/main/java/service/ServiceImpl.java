package service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import dao.IDaoClient;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;


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
	public void supprimerClient(int id) throws Exception {
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
		if("model.CompteCourant".equals(compte.getClass().getName())) {
			client = ((CompteCourant) compte).getClient();
		}
		if("model.CompteEpargne".equals(compte.getClass().getName())) {
			client = ((CompteEpargne) compte).getClient();
		}
		return client;
	}

	
	

}
