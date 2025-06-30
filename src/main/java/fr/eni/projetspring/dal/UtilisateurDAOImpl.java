package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UtilisateurDAOImpl implements UtilisateurDAO{

    @Override
    public void create(Utilisateur utilisateur) {

    }

    @Override
    public Utilisateur readById(int id) {
        return null;
    }

    @Override
    public List<Utilisateur> readAll() {
        return List.of();
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }




}
