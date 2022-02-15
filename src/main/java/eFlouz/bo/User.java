package eFlouz.bo;

public class User {

	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private float telephone;
	private String rue;
	private int code_postal;
	private String ville;
	private String mot_de_passe;
	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public float getTelephone() {
		return telephone;
	}


	public void setTelephone(float telephone) {
		this.telephone = telephone;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public int getCode_postal() {
		return code_postal;
	}


	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public int getNo_utilisateur() {
		return no_utilisateur;
	}


	public int getCredit() {
		return credit;
	}


	public boolean isAdministrateur() {
		return administrateur;
	}


	private int credit;
	private boolean administrateur;
	
	
	public User(String email, String mot_de_passe) {
		super();
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMot_de_passe() {
		return mot_de_passe;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	
	
	
}
