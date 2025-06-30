package fr.eni.projetspring.bo;

import java.util.Objects;

public class Retrait {

    private String rue;
    private String codePostal;
    private String ville;
    private ArticleVendu articleVendu;

    public Retrait() {
    }

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Retrait(String rue, String codePostal, String ville, ArticleVendu articleVendu) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.articleVendu = articleVendu;
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

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Retrait retrait = (Retrait) o;
        return Objects.equals(getArticleVendu(), retrait.getArticleVendu());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getArticleVendu());
    }
}
