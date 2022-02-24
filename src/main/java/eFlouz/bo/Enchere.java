package eFlouz.bo;

import java.time.LocalDate;

public class Enchere {


	private int noEnchere = 0;
	private LocalDate dateEnchere = null;
	private int montantEnchere = 0;
	private int noArticle = 0;
	private int noUtilisateur = 0;
	
	public Enchere(int noEnchere, LocalDate dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		
		this.noEnchere = noEnchere;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}
	
public Enchere(LocalDate dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}

	public Enchere() {
	// TODO Auto-generated constructor stub
}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	

	
}
