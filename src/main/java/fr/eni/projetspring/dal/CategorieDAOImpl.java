package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Categorie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO{
    @Override
    public void createCategorie(Categorie categorie) {

    }

    @Override
    public Categorie readCategorie(int noCategorie) {
        return null;
    }

    @Override
    public List<Categorie> readAllCategorie() {
        return List.of();
    }

    @Override
    public void updateCategorie(Categorie categorie) {

    }

    @Override
    public void deleteCategorie(int noCategorie) {

    }
}
