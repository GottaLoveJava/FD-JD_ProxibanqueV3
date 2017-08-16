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
import service.IClientService;

@Named
@SessionScoped
public class ClientControleur implements Serializable{

	private static final long serialVersionUID = 3774463683041113840L;

	private List<Client> students;
	private Logger logger = Logger.getLogger(getClass().getName());

	@Inject
	private IClientService service;

	public ClientControleur() throws Exception {
		students = new ArrayList<>();
	
	}

	@PostConstruct
	public void initService() {
		System.out.println(this.getClass().getName()+"je suis ;;construit !"+service);
	}

	public List<Client> getStudents() {
		return students;
	}

	public void loadStudents() {

		logger.info("Loading students");

		students.clear();

		try {

			students = service.listeClients();

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading students", exc);
			addErrorMessage(exc);
		}
	}

	public String addStudent(Client theStudent) {

		logger.info("Adding student: " + theStudent);

		try {
			service.ajouterClient(theStudent);

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error adding students", exc);
			addErrorMessage(exc);

			return null;
		}

		return "liste-clients?faces-redirect=true";
	}

	public String loadStudent(int clientId) {

		logger.info("Chargement du client numéro : " + clientId);

		try {
			Client client = service.afficherClient(clientId);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("student", client);

		} catch (Exception exc) {
			logger.log(Level.SEVERE, "Error loading student id:" + clientId, exc);
			addErrorMessage(exc);

			return null;
		}

		return "modifier-client-form.xhtml";
	}

	public String updateStudent(Client client) {

		logger.info("Modification client: " + client);

		try {

			// update student in the database
			service.modifierClient(client);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating student: " + client, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-students?faces-redirect=true";
	}

	public String deleteStudent(int studentId) {

		logger.info("Deleting student id: " + studentId);

		try {

			// delete the student from the database
			service.supprimerClient(studentId);

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting student id: " + studentId, exc);

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-students";
	}

	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
