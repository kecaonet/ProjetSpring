package fr.eni.projetspring.bo;

import java.time.LocalDate;

public class Enchere {

    private int noEnchere;
    private LocalDate dateEnchere;
    private int montantEnchere;
    private int utilisateur;
    private int articleVendu;

    public Enchere() {
    }

    public Enchere(int noEnchere, LocalDate dateEnchere, int montantEnchere, int utilisateur, int articleVendu) {
        this.noEnchere = noEnchere;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
    }

    public Enchere(LocalDate dateEnchere, int montantEnchere, int utilisateur, int articleVendu) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
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

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(int articleVendu) {
        this.articleVendu = articleVendu;
    }

    public int getNoEnchere() {
        return noEnchere;
    }

    public void setNoEnchere(int noEnchere) {
        this.noEnchere = noEnchere;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enchere{");
        sb.append("dateEnchere=").append(dateEnchere);
        sb.append(", montantEnchere=").append(montantEnchere);
        sb.append(", utilisateur=").append(utilisateur);
        sb.append(", articleVendu=").append(articleVendu);
        sb.append('}');
        return sb.toString();
    }
}
