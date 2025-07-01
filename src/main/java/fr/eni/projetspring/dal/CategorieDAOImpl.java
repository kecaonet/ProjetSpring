package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO{

    private final String INSERT = "INSERT INTO CATEGORIE(NO_CATEGORIE, LIBELLE) " +
            "VALUES (:noCategorie, :libelle)";

    private final String READ_BY_ID = "SELECT NO_CATEGORIE, LIBELLE " +
            "FROM CATEGORIE WHERE NO_CATEGORIE = :noCategorie";

    private final String READ_ALL = "SELECT NO_CATEGORIE, LIBELLE FROM CATEGORIE";

    private final String UPDATE = "";

    private final String DELETE = "";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void createCategorie(Categorie categorie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("Libelle", categorie.getLibelle());

        jdbcTemplate.update(INSERT, parameterSource, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            categorie.setNoCategorie(keyHolder.getKey().intValue());
        }
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

    @Override
    public void updateCategorie(Categorie categorie) {}

    @Override
    public void deleteCategorie(int noCategorie) {}
}
