package fr.eni.projetspring.bo;

import fr.eni.projetspring.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class ArticleVendu implements Serializable {

    private static final long serialVersionUID = 1L;

    private int noArticle;
    private String nomArticle;
    private String description;
    private Date dateDebutEncheres;
    private Date dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private int utilisateur;
    private Categorie categorie;
    private Retrait lieuRetrait;
    private List<Enchere> enchereList;

    /**
     * Constructeur vide
     */
    public ArticleVendu() {
    }

    /**
     * Constructeur sans "id"
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param prixVente
     * @param utilisateur
     * @param categorie
     * @param lieuRetrait
     * @param enchereList
     */
    public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, int utilisateur, Categorie categorie, Retrait lieuRetrait, List<Enchere> enchereList) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.lieuRetrait = lieuRetrait;
        this.enchereList = enchereList;
    }

    /**
     * Constructeur complet
     * @param noArticle
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param prixVente
     * @param utilisateur
     * @param categorie
     * @param lieuRetrait
     * @param enchereList
     */
    public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, int utilisateur, Categorie categorie, Retrait lieuRetrait, List<Enchere> enchereList) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.lieuRetrait = lieuRetrait;
        this.enchereList = enchereList;
    }

    /**
     * Constructeur sans "id" et sans "lieuRetrait"
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param prixVente
     * @param utilisateur
     * @param categorie
     * @param enchereList
     */
    public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, int utilisateur, Categorie categorie, List<Enchere> enchereList) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.enchereList = enchereList;
    }

    /**
     * Constructeur sans "lieuRetrait"
     * @param noArticle
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param prixVente
     * @param utilisateur
     * @param categorie
     * @param enchereList
     */
    public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int prixVente, int utilisateur, Categorie categorie, List<Enchere> enchereList) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.enchereList = enchereList;
    }
    /**
     * Constructeur sans "lieuRetrait"
     * @param noArticle
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param utilisateur
     * @param categorie
     * @param enchereList
     */
    public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, int prixInitial, int utilisateur, Categorie categorie, List<Enchere> enchereList) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.enchereList = enchereList;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(Date dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public Date getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(Date dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public int getUtilisateurid() { return utilisateur; }

    public void setUtilisateur(int id) {
        this.utilisateur = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public List<Enchere> getEnchereList() {
        return enchereList;
    }

    public void setEnchereList(List<Enchere> enchereList) {
        this.enchereList = enchereList;
    }

    @Override
    public String toString() {
        return "ArticleVendu{" +
                "nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", utilisateur=" + utilisateur +
                ", categorie=" + categorie +
                ", lieuRetrait=" + lieuRetrait +
                ", enchereList=" + enchereList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVendu that = (ArticleVendu) o;
        return getNoArticle() == that.getNoArticle();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNoArticle());
    }
}

