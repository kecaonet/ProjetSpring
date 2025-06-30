package fr.eni.projetspring.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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
    private int administrateur;

    private List<Enchere> enchereList = new ArrayList<>();

    public Utilisateur() {
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

    public int getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(int administrateur) {
        this.administrateur = administrateur;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Utilisateur.class.getSimpleName() + "[", "]")
                .add("pseudo='" + pseudo + "'")
                .add("nom='" + nom + "'")
                .add("prenom='" + prenom + "'")
                .add("email='" + email + "'")
                .add("telephone='" + telephone + "'")
                .add("rue='" + rue + "'")
                .add("codePostal='" + codePostal + "'")
                .add("ville='" + ville + "'")
                .add("credit=" + credit)
                .add("administrateur=" + administrateur)
                .toString();
    }



}
