package service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import dao.IClientDao;
import model.Client;

@Dependent
public class ClientService implements IClientService, Serializable {

	private static final long serialVersionUID = -2153567868395626691L;
	@Inject
	private IClientDao studentDao;

	@PostConstruct
	public void initService() {
		System.out.println(this.getClass().getName() + "je suis aps construit !" + studentDao);
	}

	@Override
	public List<Client> listeClients() throws Exception {
		List<Client> clients = studentDao.listeClients();
		return clients;
	}

	@Override
	public void ajouterClient(Client client) throws Exception {
		studentDao.ajouterClient(client);

	}

	@Override
	public Client afficherClient(int clientId) throws Exception {
		Client student = studentDao.afficherClient(clientId);
		return student;
	}

	@Override
	public void modifierClient(Client client) throws Exception {
		studentDao.modifierClient(client);

	}

	@Override
	public void supprimerClient(int id) throws Exception {
		studentDao.supprimerClient(id);
	}

}
