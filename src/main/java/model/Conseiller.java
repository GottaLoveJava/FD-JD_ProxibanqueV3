package model;

public class Conseiller extends Personne {

	private static final long serialVersionUID = -4531068739236802110L;
	private String login, password;

	public Conseiller() {
		super();
	}

	public Conseiller(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
