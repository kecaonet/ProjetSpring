package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO{

    private final String INSERT = "INSERT INTO CATEGORIES(LIBELLE) " +
            "VALUES (:libelle)";

    private final String READ_BY_ID = "SELECT NO_CATEGORIE, LIBELLE " +
            "FROM CATEGORIES WHERE NO_CATEGORIE = :noCategorie";

    private final String READ_ALL = "SELECT NO_CATEGORIE, LIBELLE FROM CATEGORIES";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void createCategorie(Categorie categorie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("libelle", categorie.getLibelle());

        jdbcTemplate.update(INSERT, parameterSource, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            categorie.setNoCategorie(keyHolder.getKey().intValue());
        }
    }
    private String READ_BY_LIBELLE = "Select * from Categories where libelle = :Libelle";
    @Override
    public Categorie readCategorieByLibelle(String Libelle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("Libelle", Libelle);
        return jdbcTemplate.queryForObject(READ_BY_LIBELLE,parameterSource, new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public Categorie readCategorie(int noCategorie) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("noCategorie", noCategorie);

        return jdbcTemplate.queryForObject(READ_BY_ID, parameterSource,
                new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public List<Categorie> readAllCategorie() {
        return jdbcTemplate.query(READ_ALL, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
