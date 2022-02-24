package eFlouz.bo;

public class User {

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private int codePostal;
	private String ville;
	private String motDePasse;
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


	public int getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public int getCredit() {
		return credit;
	}


	public boolean getAdministrateur() {
		return administrateur;
	}

	
	
	public User(String email, String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}
	


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public User(String pseudo, String nom, String prenom, String email, String telephone, String rue, int codePostal,
			String ville, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
	}


	public User(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
			int codePostal, String ville, String motDePasse, int credit) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
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

		return motDePasse;

	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	
	
	
	
	
}
