package fr.eni.projetspring.ihm.converter;

import fr.eni.projetspring.bo.Categorie;
import fr.eni.projetspring.dal.CategorieDAO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategorieConverter implements Converter<String,Categorie> {
CategorieDAO categorieDAO;

    public CategorieConverter(CategorieDAO categorieDAO) {this.categorieDAO=categorieDAO;}

    @Override
    public Categorie convert(String source) {

        return categorieDAO.readCategorie(Integer.parseInt(source));
    }
}
