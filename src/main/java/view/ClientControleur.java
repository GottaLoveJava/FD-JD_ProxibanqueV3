package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.Client;
import service.IService;

@Named
@SessionScoped
public class ClientControleur implements Serializable{

	private static final long serialVersionUID = 3774463683041113840L;

	private List<Client> clients;
	private Logger logger = Logger.getLogger(getClass().getName());

	@Inject
	private IService service;

	public ClientControleur() throws Exception {
		clients = new ArrayList<>();
	
	}

	@PostConstruct
	public void initService() {
		System.out.println(this.getClass().getName()+"je suis construit ! "+service);
	}

	public List<Client> getClients() {
		return clients;
	}

	public void loadclients() {

		logger.info("Loading clients");

		clients.clear();

		try {

			clients = service.listeClients();

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading clients", exc);
			addErrorMessage(exc);
		}
	}

	public String addClient(Client client) {

		logger.info("Adding client: " + client);

		try {
			service.ajouterClient(client);

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding clients", exc);
			addErrorMessage(exc);

			return null;
		}

		return "liste-clients?faces-redirect=true";
	}

	public String loadClient(int clientId) {

		logger.info("Chargement du client numéro : " + clientId);

		try {
			Client client = service.afficherClient(clientId);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("client", client);

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading client id:" + clientId, exc);
			addErrorMessage(exc);

			return null;
		}

		return "modifier-client-form.xhtml";
	}

	public String updateClient(Client client) {

		logger.info("Modification client: " + client);

		try {

			// update client in the database
			service.modifierClient(client);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating client: " + client, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-clients?faces-redirect=true";
	}

	public String deleteClient(int clientId) {

		logger.info("Deleting client id: " + clientId);

		try {

			// delete the client from the database
			service.supprimerClient(clientId);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting client id: " + clientId, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-clients";
	}

	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
