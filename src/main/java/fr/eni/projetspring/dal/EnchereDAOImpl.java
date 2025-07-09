package fr.eni.projetspring.dal;

import fr.eni.projetspring.bll.UtilisateurService;
import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    ArticleVendu articleVendu;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Autowired
    ArticleVenduDAO articleVenduDAO;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final String SELECT_ALL = "select * from encheres";
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private String INSERT ="INSERT INTO Encheres (date_enchere,montant_enchere,no_article,no_utilisateur) Values(:date_enchere,:montant_enchere,:no_article,:no_utilisateur)";
    private String UPDATEVENTE = "update articles_vendus set prix_vente = :prixvente where no_article = :noarticle";

    @Override
    public void create(Enchere enchere) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        System.out.println(enchere.getArticleVendu());
        System.out.println(enchere.getUtilisateur());
        parameterSource.addValue("date_enchere", enchere.getDateEnchere());
        System.out.println(enchere.getDateEnchere());
        parameterSource.addValue("montant_enchere", enchere.getMontantEnchere());
        System.out.println(enchere.getMontantEnchere());
        parameterSource.addValue("no_article", enchere.getArticleVendu());
        parameterSource.addValue("no_utilisateur", enchere.getUtilisateur());
        System.out.println("ARTICLE VENDU NOUVEAU AVANT INSERT" + enchere);
        jdbcTemplate.update(INSERT, parameterSource,  keyHolder, new String[]{"NO_ARTICLE"});
        MapSqlParameterSource parameterSource2 = new MapSqlParameterSource();
        parameterSource2.addValue("prixvente",enchere.getMontantEnchere());
        parameterSource2.addValue("noarticle",enchere.getArticleVendu());
        jdbcTemplate.update(UPDATEVENTE, parameterSource2);
        System.out.println("Set enchère");
        System.out.println(enchere);
        utilisateurService.consulterUtilisateur(enchere.getUtilisateur()).setEnchereList(keyHolder.getKey().intValue(),enchere);
        articleVenduDAO.read(enchere.getArticleVendu()).setEnchereList(enchere);
        System.out.println(utilisateurService.consulterUtilisateur(enchere.getUtilisateur()).getEnchereList());
        System.out.println("ArticleVenduDAO.read");
        System.out.println(articleVenduDAO.read(enchere.getArticleVendu()).getEnchereList());
        System.out.println("Creation key holder" + keyHolder.getKey());
        if(keyHolder != null && keyHolder.getKey() != null) {
            enchere.setNoEnchere(keyHolder.getKey().intValue());
        }
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
    String DELETE = "DELETE FROM enchere where id = :id";
    @Override
    public void delete(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

            jdbcTemplate.getJdbcTemplate().update(DELETE, parameterSource);
        }
    }