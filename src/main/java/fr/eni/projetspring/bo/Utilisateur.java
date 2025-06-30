package fr.eni.projetspring.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    private int noUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    public String email;
    private String telephone;
    private String rue;
    private String codePostal;
    private String ville;
    private int credit;
    private boolean administrateur;

    private List<Enchere> enchereList = new ArrayList<>();
    private List<ArticleVendu> articleVenduList = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, int credit, boolean administrateur, List<Enchere> enchereList, List<ArticleVendu> articleVenduList) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.credit = credit;
        this.administrateur = administrateur;
        this.enchereList = enchereList;
        this.articleVenduList = articleVenduList;
    }

    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, int credit, boolean administrateur, List<Enchere> enchereList, List<ArticleVendu> articleVenduList) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.credit = credit;
        this.administrateur = administrateur;
        this.enchereList = enchereList;
        this.articleVenduList = articleVenduList;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<Enchere> getEnchereList() {
        return enchereList;
    }

    public void setEnchereList(List<Enchere> enchereList) {
        this.enchereList = enchereList;
    }

    public List<ArticleVendu> getArticleVenduList() {
        return articleVenduList;
    }

    public void setArticleVenduList(List<ArticleVendu> articleVenduList) {
        this.articleVenduList = articleVenduList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilisateur{");
        sb.append("pseudo='").append(pseudo).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", rue='").append(rue).append('\'');
        sb.append(", codePostal='").append(codePostal).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", credit=").append(credit);
        sb.append(", administrateur=").append(administrateur);
        sb.append(", enchereList=").append(enchereList);
        sb.append(", articleVenduList=").append(articleVenduList);
        sb.append('}');
        return sb.toString();
    }
}
