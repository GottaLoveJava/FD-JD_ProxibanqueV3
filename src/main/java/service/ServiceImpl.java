package service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import dao.IDaoClient;
import model.Client;

@Dependent
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
	public Client afficherClient(int clientId) throws Exception {
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

}
