package service;

import java.util.List;

import model.Client;
import model.Compte;

public interface IService {

	List<Client> listeClients() throws Exception;

	void ajouterClient(Client client) throws Exception;

	Client afficherClient(long id) throws Exception;

	void modifierClient(Client client) throws Exception;

	void supprimerClient(int id) throws Exception;

	boolean effectuerVirement(Compte compteInitial, Compte compteDestinataire, double montant);

}