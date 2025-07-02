package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleVenduDAOImpl implements ArticleVenduDAO{

    private final String INSERT = "INSERT INTO ARTICLES_VENDUS(NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE) " +
            "VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :prixInitial, " +
            ":prixVente, :utilisateur, :categorie)";

    private final String READ_BY_ID = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLES_VENDUS WHERE no_article = :noArticle";

    private final String READ_ALL = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLES_VENDUS";

    //à compléter
    private final String UPDATE = "";

    //à compléter
    private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_Article = :id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        parameterSource.addValue("utilisateur", articleVendu.getUtilisateurid());
        parameterSource.addValue("categorie", articleVendu.getCategorie());

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
        List<ArticleVendu> list = jdbcTemplate.query(READ_ALL, new ArticleVenduRowMapper());

        return list;
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {

    }

    @Override
    public void deleteArticleVendu(int noArticle) {


        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", noArticle);
        namedParameterJdbcTemplate.update(DELETE, map);
    }

    class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {
        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleVendu a = new ArticleVendu();
            a.setNoArticle(rs.getInt("no_article"));
            a.setNomArticle(rs.getString("nom_article"));
            a.setDescription(rs.getString("description"));
            a.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
            a.setDateFinEncheres(rs.getDate("date_fin_encheres"));
            a.setPrixInitial(rs.getInt("prix_initial"));
            a.setPrixVente(rs.getInt("prix_vente"));
            a.setUtilisateur(rs.getInt("no_utilisateur"));
            a.setCategorie(rs.getInt("no_categorie"));





            return a;
        }
    }
}
