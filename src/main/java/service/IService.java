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
	 * @param compteInitial compte d�bit�
	 * @param compteDestinataire  compte cr�dit�
	 * @param montant � virer
	 * @return bool�en pour tester le bon fonctionnement de la m�thode
	 * @throws Exception � la cr�ation de session
	 */
	boolean effectuerVirement(Compte compteInitial, Compte compteDestinataire, double montant) throws Exception;

	/**
	 * @param clientId : r�cup�re les informations du client par son id
	 * @return la liste de ses comptes
	 */
	List<Compte> afficherComptes(long clientId);

	/**
	 * @param clientId :r�cup�re les informations du client par son id
	 * @return son compte �pargne
	 * @throws Exception � la cr�ation de session
	 */
	Compte afficherCompteEpargne(long clientId) throws Exception;
	/**
	 * @param clientId :r�cup�re les informations du client par son id
	 * @return son compte courant
	 * @throws Exception � la cr�ation de session
	 */
	Compte afficherCompteCourant(long clientId) throws Exception;

}