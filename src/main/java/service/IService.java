package service;

import java.util.List;

import model.Client;

public interface IService {

	List<Client> listeClients() throws Exception;

	void ajouterClient(Client client) throws Exception;

	Client afficherClient(int id) throws Exception;

	void modifierClient(Client client) throws Exception;

	void supprimerClient(int id) throws Exception;

}