package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import model.Compte;

@Named
@SessionScoped
public class CompteControleur implements Serializable{
	private static final long serialVersionUID = -6402711777959979780L;
	private List<Compte> comptes = new 	ArrayList<>();
	
	

	
}
