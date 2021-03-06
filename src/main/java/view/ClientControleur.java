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
import javax.servlet.http.HttpSession;

import model.Client;
import model.Compte;
import service.IService;

/**
 * Cette classe permet de g�rer l'ensemble des vues
 * 
 * @author Fran�ois Destremau, Jean Deglaire
 * @version v3.0 
 *
 */
@Named
@SessionScoped
public class ClientControleur implements Serializable {

	private static final long serialVersionUID = 3774463683041113840L;

	private List<Client> clients;
	private Logger logger = Logger.getLogger(getClass().getName());
	private List<Compte> listeComptes = new ArrayList<>();
	private double montant;
	private Long idCompteInitial;
	private Long idCompteDestinataire;

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
			if (null != client.getCompteEpargne()) {
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

	public String chargerClient(long idClient) {
	
		logger.info("Chargement du client num�ro : " + idClient);
		try {
			Client client = service.afficherClient(idClient);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("client", client);
		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading client id:" + idClient, exc);
			afficherErreur(exc);
			return null;
		}
	
		return "modifier-client-form.xhtml";
	}

	public String compteClient(long clientId) {

		logger.info("Chargement du client num�ro : " + clientId);
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

		return "liste-comptes.xhtml";
	}

	public String modifierClient(Client client) throws Exception {
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

	/**
	 * M�thode permettant d'effectuer des virements de compte � compte
	 * @throws Exception lors de la cr�ation de session
	 */
	public void effectuerVirement() throws Exception {
		Compte compteInitial = null;
		Compte compteDestinataire = null;
		try {
			System.out
					.println("init : " + idCompteInitial + " dest : " + idCompteDestinataire + " montant : " + montant);
			for (Compte compte : listeComptes) {
				if (compte.getId() == idCompteInitial) {
					compteInitial = compte;
				}
				if (compte.getId() == idCompteDestinataire) {
					compteDestinataire = compte;
				}
			}
			System.out.println(compteInitial.getId());
			System.out.println(compteDestinataire.getId());

			service.effectuerVirement(compteInitial, compteDestinataire, montant);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Long getIdCompteInitial() {
		return idCompteInitial;
	}

	public void setIdCompteInitial(Long idCompteInitial) {
		this.idCompteInitial = idCompteInitial;
	}

	public Long getIdCompteDestinataire() {
		return idCompteDestinataire;
	}

	public void setIdCompteDestinataire(Long idCompteDestinataire) {
		this.idCompteDestinataire = idCompteDestinataire;
	}

	/**
	 * M�thode permettant de se d�logger
	 */
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("loggedUser");
		return "WEB-INF/login.html";
	}

}
