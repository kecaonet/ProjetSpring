package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Retrait;

import java.util.List;

public interface RetraitDAO {

    void createRetrait(Retrait retrait);

    Retrait readRetrait(int noArticleVendu);

    List<Retrait> readAllRetrait();

    void updateRetrait(Retrait retrait);

    void deleteRetrait(int noArticleVendu);
}
