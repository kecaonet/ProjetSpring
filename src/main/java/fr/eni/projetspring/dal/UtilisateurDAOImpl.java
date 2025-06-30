package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class UtilisateurDAOImpl implements UtilisateurDAO{

    private final String INSERT = "INSERT INTO UTILISATEURS(PSEUDO, NOM, PRENOM, EMAIL, TELEPHONE, RUE, CODE_POSTAL, VILLE, MOT_DE_PASSE, CREDIT, ADMINISTRATEUR) "
            + " VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, : codePostal, :ville, : motDePasse, : credit, :administrateur)";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("codePostal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("motDePasse", utilisateur.getMotDePasse());
        namedParameters.addValue("credit", utilisateur.getCredit());
        namedParameters.addValue("administrateur", utilisateur.isAdministrateur());


        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            utilisateur.setNoUtilisateur((int) keyHolder.getKey().longValue());
        }

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
