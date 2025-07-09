package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class UtilisateurDAOImpl implements UtilisateurDAO{

    private final String FIND_BY_EMAIL = "SELECT count(email) FROM UTILISATEURS WHERE EMAIL = :email";
    private final String FIND_BY_PSEUDO = "SELECT count(pseudo) FROM UTILISATEURS WHERE PSEUDO = :pseudo";
    private final String READ_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo = :pseudo";

    @Override
    public boolean findByEmail(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);

        int count = jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters, Integer.class);
        return count>=1;
    }

    @Override
    public boolean findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        int count = jdbcTemplate.queryForObject(FIND_BY_PSEUDO, namedParameters, Integer.class);
        return count>=1;
    }

    /**
     * @param pseudo le pseudo
     * @return l'utilisateur en fonction de son pseudo
     */
    @Override
    public Utilisateur findUtilByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);
        return jdbcTemplate.queryForObject(READ_BY_PSEUDO, namedParameters, new UtilisateurRowMapper());
    }

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

// =================================== Create Utilisateur ===================================

    private final String INSERT = "INSERT INTO UTILISATEURS(PSEUDO, NOM, PRENOM, EMAIL, TELEPHONE, RUE, CODE_POSTAL, VILLE, MOT_DE_PASSE, CREDIT, ADMINISTRATEUR) "
            + " VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :credit, :administrateur)";

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

        System.out.println(utilisateur);
        jdbcTemplate.update(INSERT, namedParameters, keyHolder);
        System.out.println(keyHolder.getKey());

        if (keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            utilisateur.setNoUtilisateur((int) keyHolder.getKey().longValue());
        }

    }

// =================================== ReadAll Utilisateur ===================================

    private final String READ_ALL = "SELECT NO_UTILISATEUR, PSEUDO, NOM, PRENOM, EMAIL, TELEPHONE, RUE, " +
            "CODE_POSTAL, VILLE, CREDIT, ADMINISTRATEUR, IS_DESACTIVE FROM UTILISATEURS";

    @Override
    public List<Utilisateur> readAll() {
        return jdbcTemplate.query(READ_ALL, new UtilisateurRowMapperForAdmin());
    }

// ================================== ReadById Utilisateur ==================================

    private final String READ_BY_ID = "SELECT NO_UTILISATEUR, PSEUDO, NOM, PRENOM, EMAIL, TELEPHONE, RUE, " +
            "CODE_POSTAL, VILLE, MOT_DE_PASSE, CREDIT, ADMINISTRATEUR, IS_DESACTIVE FROM UTILISATEURS WHERE NO_UTILISATEUR = :noUtilisateur";

    @Override
    public Utilisateur readById(int noUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noUtilisateur", noUtilisateur);
        return jdbcTemplate.queryForObject(READ_BY_ID, namedParameters, new UtilisateurRowMapper());

    }

// =================================== Update Utilisateur ===================================

    @Override
    public void update(Utilisateur utilisateur) {

        String UPDATE = "UPDATE UTILISATEURS SET ";
        if (utilisateur.getPseudo() != null) UPDATE += "PSEUDO = :pseudo, ";

        UPDATE += "NOM = :nom, "
                + "PRENOM = :prenom, ";
        if (utilisateur.getEmail() != null) UPDATE += "EMAIL = :email, ";

        UPDATE += "TELEPHONE = :telephone, "
                + "RUE = :rue, "
                + "CODE_POSTAL = :codePostal, "
                + "VILLE = :ville";


        if(utilisateur.getMotDePasse().isEmpty()) {
            UPDATE += " WHERE NO_UTILISATEUR = :noUtilisateur";
        }else {
            UPDATE += ", MOT_DE_PASSE = :motDePasse WHERE NO_UTILISATEUR = :noUtilisateur";
        };

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("rue", utilisateur.getRue());
        namedParameters.addValue("codePostal", utilisateur.getCodePostal());
        namedParameters.addValue("ville", utilisateur.getVille());
        namedParameters.addValue("motDePasse", utilisateur.getMotDePasse());
        jdbcTemplate.update(UPDATE, namedParameters);
    }

// =================================== Delete Utilisateur ===================================

    private final String DELETE_BY_ID = "DELETE FROM UTILISATEURS WHERE NO_UTILISATEUR = :noUtilisateur";

    @Override
    public void deleteById(int noUtilisateur) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noUtilisateur", noUtilisateur);
        System.out.println("DAO: deleteById");
        jdbcTemplate.update(DELETE_BY_ID, namedParameters);
        System.out.println("BDD: check");
    }

// =================================== Desactivate Utilisateur ===================================

    private String DESACTIVATE_BY_ID = "UPDATE UTILISATEURS SET IS_DESACTIVE = :isDesactive WHERE NO_UTILISATEUR = :noUtilisateur";

    @Override
    public void desactivateById(int noUtilisateur, boolean activeOrDesactive) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noUtilisateur", noUtilisateur);
        namedParameters.addValue("isDesactive", activeOrDesactive);
        System.out.println("DAO: desactivateById check");
        jdbcTemplate.update(DESACTIVATE_BY_ID, namedParameters);
        System.out.println("BDD: desactivateById check");
    }

// ===================================== Row Mapper =====================================

    class UtilisateurRowMapper implements RowMapper<Utilisateur> {
        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur u = new Utilisateur();
            u.setNoUtilisateur(rs.getInt("no_Utilisateur"));
            u.setPseudo(rs.getString("pseudo"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setTelephone(rs.getString("telephone"));
            u.setRue(rs.getString("rue"));
            u.setCodePostal(rs.getString("code_Postal"));
            u.setVille(rs.getString("ville"));
            u.setMotDePasse(rs.getString("mot_De_Passe"));
            u.setCredit(rs.getInt("credit"));
            u.setAdministrateur(rs.getBoolean("administrateur"));
            u.setDesactive(rs.getBoolean("is_Desactive"));
            return u;
        }
    }

    class UtilisateurRowMapperForAdmin implements RowMapper<Utilisateur> {
        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur u = new Utilisateur();
            u.setNoUtilisateur(rs.getInt("no_Utilisateur"));
            u.setPseudo(rs.getString("pseudo"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setTelephone(rs.getString("telephone"));
            u.setRue(rs.getString("rue"));
            u.setCodePostal(rs.getString("code_Postal"));
            u.setVille(rs.getString("ville"));
            u.setCredit(rs.getInt("credit"));
            u.setAdministrateur(rs.getBoolean("administrateur"));
            u.setDesactive(rs.getBoolean("is_Desactive"));
            return u;
        }
    }
}
