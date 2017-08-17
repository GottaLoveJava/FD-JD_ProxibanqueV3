package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "COMPTE_EPARGNE")
@PrimaryKeyJoinColumn(name = "id")
public class CompteEpargne extends Compte implements Serializable {

	private static final long serialVersionUID = 4306572688406482151L;
	private double tauxRemuneration;
	@OneToOne(mappedBy="compteEpargne")
	private Client client;

	public double getTauxRemuneration() {
		return tauxRemuneration;
	}

	public void setTauxRemuneration(double tauxRemuneration) {
		this.tauxRemuneration = tauxRemuneration;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Compte n° : "+this.getId()+" / Propriétaire : "+this.client.getNom()+" / Solde : "+ this.getSolde();
	}

}
