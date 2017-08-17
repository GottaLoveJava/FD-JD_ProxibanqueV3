package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import service.IService;

@Named
@SessionScoped
public class VirementControleur implements Serializable{

	private static final long serialVersionUID = -3807674103411580336L;
	private Compte compteInitial = null;
	private Compte compteDestinataire = null;
	
	private List<Compte> listeComptes = new ArrayList<>();
	
	@Inject
	private IService service;

	public List<Compte> getListeComptes() {
		return listeComptes;
	}
	public VirementControleur() throws Exception {
		List<Client>clients = service.listeClients();
		

		
		
		System.out.println(clients);
		for (Client client : clients) {
			try {
				listeComptes.add(client.getCompteCourant());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			try {
				listeComptes.add(client.getCompteEpargne());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	public void setListeComptes(List<Compte> listeComptes) {
		this.listeComptes = listeComptes;
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

}
