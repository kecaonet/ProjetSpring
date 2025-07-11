package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    void create(Enchere enchere);

    Enchere findById(int id);

    List<Enchere> findAll();

    void update(int id);

    void delete(int id);
}
