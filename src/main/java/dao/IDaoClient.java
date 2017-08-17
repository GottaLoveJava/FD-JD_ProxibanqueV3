package dao;

import java.util.List;

import model.Client;

/**
 * @author Francois Destremau, Jean Deglaire
 *
 */
public interface IDaoClient {

	/**
	 * @return liste de tous les clients de la BDD
	 * @throws Exception � la cr�ation de session
	 */
	List<Client> listeClients() throws Exception;
	/**
	 * @param client : ajoute le client � la BDD
	 * @return bool�en permettant de tester le bon fonctionnement de la cr�ation
	 * @throws Exception � la cr�ation de session
	 */
	boolean ajouterClient(Client client) throws Exception;

	/**
	 * @param clientId : permet de r�cup�rer le client par son id dans la bdd
	 * @return le client d�sir�
	 * @throws Exception � la cr�ation de session
	 */
	Client afficherClient(long clientId) throws Exception;

	/**
	 * @param client
	 * @return bool�en permettant de tester le bon fonctionnement de la modification
	 * @throws Exception  � la cr�ation de session
	 */
	boolean modifierClient(Client client) throws Exception;

	/**
	 * @param clientId : r�cup�re le client par son id dans la base de donn�es
	 * @return bool�en permettant de tester le bon fonctionnement de la modification
	 * @throws Exception  � la cr�ation de session
	 */
	boolean supprimerClient(long clientId) throws Exception;

}