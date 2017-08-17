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
import model.Compte;
import service.IService;

@Named
@SessionScoped
public class ClientControleur implements Serializable {

	private static final long serialVersionUID = 3774463683041113840L;

	private List<Client> clients;
	private Logger logger = Logger.getLogger(getClass().getName());
	private Compte compteInitial = null;
	private Compte compteDestinataire = null;
	private List<Compte> listeComptes = new ArrayList<>();

	@Inject
	private IService service;

	public ClientControleur() throws Exception {
		clients = new ArrayList<>();
	}

	@PostConstruct
	public void initService() throws Exception {
		chargerClients();
		for (Client client : clients) {
			if (null != client.getCompteCourant()) {
				listeComptes.add(client.getCompteCourant());
			}
			if(null!=client.getCompteEpargne()) {
				listeComptes.add(client.getCompteEpargne());
			}
		}
		System.out.println(this.getClass().getName() + "je suis construit ! " + service);
	}

	public List<Client> getClients() {
		return clients;
	}

	public void chargerClients() {
		logger.info("Chargement clients");
		clients.clear();
		try {
			clients = service.listeClients();
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Erreur de chargement", exc);
			afficherErreur(exc);
		}
	}

	public String ajouterClient(Client client) {
		logger.info("Ajout du client: " + client);
		try {
			service.ajouterClient(client);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Erreur pendant l'ajout", exc);
			afficherErreur(exc);
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
			afficherErreur(exc);
			return null;
		}

		return "modifier-client-form.xhtml";
	}

	public String modifierClient(long idClient) throws Exception {
		Client client = service.afficherClient(idClient);

		logger.info("Modification client: " + client);
		try {
			service.modifierClient(client);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error updating client: " + client, exc);
			afficherErreur(exc);
			return null;
		}

		return "liste-clients?faces-redirect=true";
	}

	public String supprimerClient(long clientId) {
		logger.info("Deleting client id: " + clientId);

		try {
			service.supprimerClient(clientId);

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error deleting client id: " + clientId, exc);
			afficherErreur(exc);
			return null;
		}

		return "liste-clients";
	}

	public List<Compte> afficherComptes(long clientId) {
		return service.afficherComptes(clientId);

	}

	public Compte afficherCompteEpargne(long clientId) throws Exception {
		return service.afficherCompteEpargne(clientId);
	}

	public Compte afficherCompteCourant(long clientId) throws Exception {
		return service.afficherCompteCourant(clientId);
	}

	public Compte getCompteInitial() {
		return compteInitial;
	}

	public void setCompteInitial(Compte compteInitial) {
		this.compteInitial = compteInitial;
	}

	public Compte getCompteDestinataire() {
		return compteDestinataire;
	}

	public void setCompteDestinataire(Compte compteDestinataire) {
		this.compteDestinataire = compteDestinataire;
	}

	public List<Compte> getListeComptes() {
		return listeComptes;
	}

	public void setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
	}

	private void afficherErreur(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public void effectuerVirement(Compte compteInitial, Compte compteDestinataire, double montant) throws Exception {
		service.effectuerVirement(compteInitial, compteDestinataire, montant);
	}
}
