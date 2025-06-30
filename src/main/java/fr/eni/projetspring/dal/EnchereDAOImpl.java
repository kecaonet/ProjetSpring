package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Enchere;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class EnchereDAOImpl implements EnchereDAO {


    @Override
    public void create(Enchere enchere) {

    }

    @Override
    public Enchere findById(int id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        return List.of();
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
