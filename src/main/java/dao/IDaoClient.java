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
	 * @throws Exception à la création de session
	 */
	List<Client> listeClients() throws Exception;
	/**
	 * @param client : ajoute le client à la BDD
	 * @return booléen permettant de tester le bon fonctionnement de la création
	 * @throws Exception à la création de session
	 */
	boolean ajouterClient(Client client) throws Exception;

	/**
	 * @param clientId : permet de récupérer le client par son id dans la bdd
	 * @return le client désiré
	 * @throws Exception à la création de session
	 */
	Client afficherClient(long clientId) throws Exception;

	/**
	 * @param client
	 * @return booléen permettant de tester le bon fonctionnement de la modification
	 * @throws Exception  à la création de session
	 */
	boolean modifierClient(Client client) throws Exception;

	/**
	 * @param clientId : récupère le client par son id dans la base de données
	 * @return booléen permettant de tester le bon fonctionnement de la modification
	 * @throws Exception  à la création de session
	 */
	boolean supprimerClient(long clientId) throws Exception;

}