package fr.eni.projetspring.dal;

import fr.eni.projetspring.bo.ArticleVendu;
import fr.eni.projetspring.bo.Retrait;
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
public class ArticleVenduDAOImpl implements ArticleVenduDAO{

    @Autowired
    UtilisateurDAO utilisateurDAO;
    @Autowired
    CategorieDAO categorieDAO;
    @Autowired
    RetraitDAO RetraitDAO;


    private final String READ_BY_ID = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLES_VENDUS WHERE no_article = :id";


    //à compléter
    private final String UPDATE = "";

    //à compléter
    private final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_Article = :id";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String INSERT = "INSERT INTO ARTICLES_VENDUS(NOM_ARTICLE, DESCRIPTION, NO_CATEGORIE, " +
            " PRIX_INITIAL, PRIX_VENTE, DATE_DEBUT_ENCHERES, DATE_FIN_ENCHERES, NO_UTILISATEUR ) " +
            "VALUES (:nomArticle, :description, :categorie , :prixInitial, :prixVente, :dateDebutEncheres, :dateFinEncheres, " +
            ":utilisateur)";

    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        System.out.println(articleVendu);
        parameterSource.addValue("nomArticle", articleVendu.getNomArticle());
        parameterSource.addValue("description", articleVendu.getDescription());
        parameterSource.addValue("categorie", articleVendu.getCategorie().getNoCategorie());
        parameterSource.addValue("prixInitial", articleVendu.getPrixInitial());
        parameterSource.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
        System.out.println("Date début enchère" + articleVendu.getDateDebutEncheres());
        parameterSource.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
        System.out.println("Date fin enchère" + articleVendu.getDateFinEncheres());
        parameterSource.addValue("utilisateur", articleVendu.getUtilisateur().getNoUtilisateur());
        parameterSource.addValue("prixVente", articleVendu.getPrixVente());
        System.out.println("ARTICLE VENDU NOUVEAU AVANT INSERT" + articleVendu);
        jdbcTemplate.update(INSERT, parameterSource,  keyHolder, new String[]{"NO_ARTICLE"});
        System.out.println("Creation key holder" + keyHolder.getKey());
        if(keyHolder != null && keyHolder.getKey() != null) {
            articleVendu.setNoArticle(keyHolder.getKey().intValue());
        }
    }

    @Override
    public ArticleVendu read(int NoArticle) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", NoArticle);
        return jdbcTemplate.queryForObject(READ_BY_ID, namedParameters, new ArticleVenduDAOImpl.ArticleVenduRowMapper());
    }
    private final String READ_ALL = "SELECT NO_ARTICLE, NOM_ARTICLE, DESCRIPTION, DATE_DEBUT_ENCHERES, " +
            "DATE_FIN_ENCHERES, PRIX_INITIAL, PRIX_VENTE, NO_UTILISATEUR, NO_CATEGORIE "
            + "FROM ARTICLES_VENDUS";

    @Override
    public List<ArticleVendu> readAllArticleVendu() {
        List<ArticleVendu> list = jdbcTemplate.query(READ_ALL, new ArticleVenduRowMapper());

        return list;
    }

    @Override
    public void updateArticleVendu(ArticleVendu articleVendu) {

            String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET NOM_ARTICLE = :nomArticle, DESCRIPTION = :description , PRIX_INITIAL = :prixInitial, PRIX_VENTE = :prixVente,DATE_DEBUT_ENCHERES = :dateDebutEncheres, DATE_FIN_ENCHERES = :dateFinEncheres WHERE NO_ARTICLE = :noArticle";

            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        System.out.println("Article en cours" + articleVendu);
            namedParameters.addValue("noArticle", articleVendu.getNoArticle());
            namedParameters.addValue("nomArticle", articleVendu.getNomArticle());
            namedParameters.addValue("description", articleVendu.getDescription());
            namedParameters.addValue("prixInitial", articleVendu.getPrixInitial());
            namedParameters.addValue("prixVente", articleVendu.getPrixVente());
            namedParameters.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
            namedParameters.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());

            jdbcTemplate.update(UPDATE_ARTICLE, namedParameters);

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
            a.setDateDebutEncheresSpe(rs.getDate("date_debut_encheres"));
            a.setDateFinEncheresSpe(rs.getDate("date_fin_encheres"));
            a.setPrixInitial(rs.getInt("prix_initial"));
            a.setPrixVente(rs.getInt("prix_vente"));
            a.setUtilisateur(utilisateurDAO.readById(rs.getInt("no_utilisateur")));
            a.setCategorie(categorieDAO.readCategorie(rs.getInt("no_categorie")));


            return a;
        }
    }
}
