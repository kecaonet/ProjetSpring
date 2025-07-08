package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.Enchere;
import fr.eni.projetspring.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

public class EnchereDAOImpl implements EnchereDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String SELECT_ALL = "select * from encheres";

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private String INSERT ="INSERT INTO Encheres (date_enchere,montant_enchere,no_article,no_utilisateur) Values(:date_enchere,:montant_enchere,:articleVendu,:no_utilisateur)";
    private String UPDATEVENTE = "update articles_vendus set prix_vente = :prixvente where no_article = :noarticle";

    @Override
    public void create(Enchere enchere) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        System.out.println(enchere);
        parameterSource.addValue("date_enchere", enchere.getDateEnchere());
        System.out.println(enchere.getDateEnchere());
        parameterSource.addValue("montant_enchere", enchere.getMontantEnchere());
        System.out.println(enchere.getMontantEnchere());
        parameterSource.addValue("articleVendu", enchere.getArticleVendu().getNoArticle());
        parameterSource.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());
        System.out.println("ARTICLE VENDU NOUVEAU AVANT INSERT" + enchere);
        jdbcTemplate.update(INSERT, parameterSource,  keyHolder, new String[]{"NO_ARTICLE"});
        MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
        parameterSource2.addValue("prixvente",enchere.getMontantEnchere());
        parameterSource2.addValue("noarticle",enchere.getArticleVendu().getNoArticle());
        jdbcTemplate.update(UPDATEVENTE, parameterSource2);

        System.out.println("Creation key holder" + keyHolder.getKey());
        if(keyHolder != null && keyHolder.getKey() != null) {
            enchere.setNoEnchere(keyHolder.getKey().intValue());
        }
        enchere.getUtilisateur().getEnchereList();
    }

    @Override
    public Enchere findById(int id) {
        return null;
    }

    @Override
    public List<Enchere> findAll() {
        return jdbcTemplate.getJdbcTemplate().query(SELECT_ALL, new BeanPropertyRowMapper<>(Enchere.class));
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

        }
    }