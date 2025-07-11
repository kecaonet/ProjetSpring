package fr.eni.projetspring.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {
    private int noCategorie;
    private String libelle;

    public Categorie() {
    }

    public Categorie(String libelle, List<ArticleVendu> categorieArticle) {
        this.libelle = libelle;
    }

    public Categorie(int noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
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

    @Override
    public String toString() {
        return "Categorie{" +
                "noCategorie=" + noCategorie +
                ", libelle='" + libelle + '\'' +
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
