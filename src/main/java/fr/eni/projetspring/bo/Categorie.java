package fr.eni.projetspring.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {
    private int noCategorie;
    private String libelle;
    private List<ArticleVendu> categorieArticle;

    public Categorie() {
    }

    public Categorie(String libelle, List<ArticleVendu> categorieArticle) {
        this.libelle = libelle;
        this.categorieArticle = categorieArticle;
    }

    public Categorie(int noCategorie, String libelle, List<ArticleVendu> categorieArticle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.categorieArticle = categorieArticle;
    }

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ArticleVendu> getCategorieArticle() {
        return categorieArticle;
    }

    public void setCategorieArticle(List<ArticleVendu> categorieArticle) {
        this.categorieArticle = categorieArticle;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "libelle='" + libelle + '\'' +
                ", categorieArticle=" + categorieArticle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return getNoCategorie() == categorie.getNoCategorie();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNoCategorie());
    }
}
