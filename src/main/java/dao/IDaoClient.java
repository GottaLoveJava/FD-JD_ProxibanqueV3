package dao;

import java.util.List;

import model.Client;

public interface IDaoClient {

	List<Client> listeClients() throws Exception;

	boolean ajouterClient(Client client) throws Exception;

	Client afficherClient(int clientId) throws Exception;

	boolean modifierClient(Client client) throws Exception;

	boolean supprimerClient(int clientId) throws Exception;

}