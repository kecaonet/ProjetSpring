package fr.eni.projetspring.bo;

import java.time.LocalDate;

public class Enchere {

    private int noEnchere;
    private LocalDate dateEnchere;
    private int montantEnchere;

    private Utilisateur utilisateur;
    private ArticleVendu articleVendu;

    public Enchere() {
    }

    public Enchere(int noEnchere, LocalDate dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
        this.noEnchere = noEnchere;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
    }

    public Enchere(LocalDate dateEnchere, int montantEnchere, Utilisateur utilisateur, ArticleVendu articleVendu) {
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
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
