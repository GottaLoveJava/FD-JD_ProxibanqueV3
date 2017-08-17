package model;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_COMPTE")
@DiscriminatorValue("GENERIQUE")
@Named
public class Compte implements Serializable{
	
	private static final long serialVersionUID = -5720932766072858902L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private double solde;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public String getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}
	private String dateOuverture;


}
