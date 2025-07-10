package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.ihm.converter.CategorieConverter;

import java.util.List;

public interface CategorieDAO {

    void createCategorie(Categorie categorie);

    Categorie readCategorie(int noCategorie);

    Categorie readCategorieByLibelle(String Libelle);

    List<Categorie> readAllCategorie();

}
