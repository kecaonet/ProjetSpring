package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Retrait;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetraitDAOImpl implements RetraitDAO{
    @Override
    public void createRetrait(Retrait retrait) {

    }

    @Override
    public Retrait readRetrait(int noArticleVendu) {
        return null;
    }

    @Override
    public List<Retrait> readAllRetrait() {
        return List.of();
    }

    @Override
    public void updateRetrait(Retrait retrait) {

    }

    @Override
    public void deleteRetrait(int noArticleVendu) {

    }
}
