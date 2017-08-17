package service;

import java.util.List;

import model.Client;
import model.Compte;

public interface IService {

	List<Client> listeClients() throws Exception;

	void ajouterClient(Client client) throws Exception;

	Client afficherClient(long id) throws Exception;

	void modifierClient(Client client) throws Exception;

	void supprimerClient(long clientId) throws Exception;

	/**
	 * @param compteInitial compte débité
	 * @param compteDestinataire  compte crédité
	 * @param montant à virer
	 * @return booléen pour tester le bon fonctionnement de la méthode
	 * @throws Exception à la création de session
	 */
	boolean effectuerVirement(Compte compteInitial, Compte compteDestinataire, double montant) throws Exception;

	/**
	 * @param clientId : récupère les informations du client par son id
	 * @return la liste de ses comptes
	 */
	List<Compte> afficherComptes(long clientId);

	/**
	 * @param clientId :récupère les informations du client par son id
	 * @return son compte épargne
	 * @throws Exception à la création de session
	 */
	Compte afficherCompteEpargne(long clientId) throws Exception;
	/**
	 * @param clientId :récupère les informations du client par son id
	 * @return son compte courant
	 * @throws Exception à la création de session
	 */
	Compte afficherCompteCourant(long clientId) throws Exception;

}