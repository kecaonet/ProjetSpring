package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    void createCategorie(Categorie categorie);

    Categorie readCategorie(int noCategorie);

    List<Categorie> readAllCategorie();

    void updateCategorie(Categorie categorie);

    void deleteCategorie(int noCategorie);
}
