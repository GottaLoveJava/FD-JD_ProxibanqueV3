package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Compte;

@ManagedBean
@SessionScoped
public class CompteControleur implements Serializable{
	private static final long serialVersionUID = -6402711777959979780L;
	private List<Compte> comptes = new 	ArrayList<>();
	
	

	
}
