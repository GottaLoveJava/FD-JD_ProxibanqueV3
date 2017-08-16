package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Client extends Personne implements Serializable {

	private static final long serialVersionUID = 4075611119300579731L;
	private String email;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;
	@OneToOne
	@JoinColumn(name = "COMPTE_COURANT", referencedColumnName = "id")
	private CompteCourant compteCourant;
//	private CompteEpargne compteEpargne;
	private boolean isEntreprise;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

//	public CompteEpargne getCompteEpargne() {
//		return compteEpargne;
//	}
//
//	public void setCompteEpargne(CompteEpargne compteEpargne) {
//		this.compteEpargne = compteEpargne;
//	}

	public boolean isEntreprise() {
		return isEntreprise;
	}

	public void setEntreprise(boolean isEntreprise) {
		this.isEntreprise = isEntreprise;
	}

}
