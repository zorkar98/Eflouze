package eFlouz.bo;

public class User {

	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private int code_postal;
	private String ville;
	private String mot_de_passe;
	private int credit;
	private boolean administrateur;
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


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
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


	public boolean getAdministrateur() {
		return administrateur;
	}

	
	
	public User(String email, String mot_de_passe) {
		super();
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}
	


	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}


	public User(String pseudo, String nom, String prenom, String email, String telephone, String rue, int code_postal,
			String ville, String mot_de_passe) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
	}


	public User(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			int code_postal, String ville, String mot_de_passe, int credit) {
		super();
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
	}


	public User(Object attribute) {
		// TODO Auto-generated constructor stub
	}



	public User(String pseudo2, String nom2, String prenom2, String email2, String telephone2, String rue2,
			String codePostal, String ville2, String motDePasse) {
		// TODO Auto-generated constructor stub
	}


	public User() {
		// TODO Auto-generated constructor stub
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMotDePasse() {
		return mot_de_passe;
	}


	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	
}
