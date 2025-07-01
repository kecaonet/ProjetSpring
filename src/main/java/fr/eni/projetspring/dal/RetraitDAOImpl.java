package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RetraitDAOImpl implements RetraitDAO{

    private final String INSERT = "INSERT INTO RETRAITS(RUE, CODEPOSTAL, VILLE, NO_ARTICLE) " +
            "VALUES (:rue, :codepostal, :ville, :no_article)";

    private final String READ_BY_ID = "SELECT RUE, CODEPOSTAL, VILLE, NO_ARTICLE " +
            "FROM RETRAITS WHERE NO_ARTICLE = :noArticle";

    private final String READ_ALL = "SELECT RUE, CODEPOSTAL, VILLE, NO_ARTICLE " +
            "FROM RETRAITS";

    private final String UPDATE = "";

    private final String DELETE = "";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void createRetrait(Retrait retrait) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("rue", retrait.getRue());
        parameterSource.addValue("codepostal", retrait.getCodePostal());
        parameterSource.addValue("ville", retrait.getVille());
        parameterSource.addValue("noArticle", retrait.getNoArticle());

        jdbcTemplate.update(INSERT, parameterSource);
    }

    @Override
    public Retrait readRetrait(int noArticle) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("noArticle", noArticle);

        return jdbcTemplate.queryForObject(READ_BY_ID, parameterSource,
                new BeanPropertyRowMapper<>(Retrait.class));
    }

    @Override
    public List<Retrait> readAllRetrait() {
        return jdbcTemplate.query(READ_ALL, new BeanPropertyRowMapper<>(Retrait.class));
    }

    @Override
    public void updateRetrait(Retrait retrait) {

    }

    @Override
    public void deleteRetrait(int noArticleVendu) {

    }
}
