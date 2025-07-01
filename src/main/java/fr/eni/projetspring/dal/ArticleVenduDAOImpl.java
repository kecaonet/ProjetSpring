package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduDAOImpl implements ArticleVenduDAO{

    private final String INSERT = "INSERT INTO ARTICLEVENDU(NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE) " +
            "VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :prixInitial, " +
            ":prixVente, :utilisateur.getNoUtilisateur(), :categorie.getNoCategorie())";

    private final String READ_BY_ID = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLEVENDU WHERE no_article = :noArticle";

    private final String READ_ALL = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLEVENDU";

    //à compléter
    private final String UPDATE = "";

    //à compléter
    private final String DELETE = "";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nomArticle", articleVendu.getNomArticle());
        parameterSource.addValue("description", articleVendu.getDescription());
        parameterSource.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        parameterSource.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        parameterSource.addValue("prixInitial", articleVendu.getPrixInitial());
        parameterSource.addValue("prixVente", articleVendu.getPrixVente());
        parameterSource.addValue("prixVente", articleVendu.getPrixVente());
        parameterSource.addValue("utilisateur", articleVendu.getUtilisateur().getNoUtilisateur());
        parameterSource.addValue("categorie", articleVendu.getCategorie().getNoCategorie());

        jdbcTemplate.update(INSERT, parameterSource,  keyHolder);

        if(keyHolder != null && keyHolder.getKey() != null) {
            articleVendu.setNoArticle(keyHolder.getKey().intValue());
        }
    }

    @Override
    public ArticleVendu read(int noArticle) {

        return null;
    }

    @Override
    public List<ArticleVendu> readAllArticleVendu() {
        List<ArticleVendu> list = jdbcTemplate.query(READ_ALL, new BeanPropertyRowMapper<>(ArticleVendu.class));

        return list;
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {

    }

    @Override
    public void deleteArticleVendu(int noArticle) {

    }
}
