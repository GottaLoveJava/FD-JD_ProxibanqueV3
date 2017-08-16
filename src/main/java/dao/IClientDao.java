package dao;

import java.util.List;

import model.Client;

public interface IClientDao {

	List<Client> listeClients() throws Exception;

	void ajouterClient(Client client) throws Exception;

	Client afficherClient(int clientId) throws Exception;

	void modifierClient(Client client) throws Exception;

	void supprimerClient(int clientId) throws Exception;

}