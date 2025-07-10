package fr.eni.projetspring.bo;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ArticleVendu implements Serializable {

    private static final long serialVersionUID = 1L;

    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int prixInitial;
    private int prixVente;
    private Utilisateur utilisateur; //objet
    private Categorie categorie; //ca doit être l'objet
    private Retrait lieuRetrait;
    private List<Enchere> enchereList = new ArrayList<>();


    /**
     * Constructeur vide
     */
    public ArticleVendu() {
    }

    /**
     * Constructeur sans "id"
     *
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
    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie, Retrait lieuRetrait, List<Enchere> enchereList) {
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
     *
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
    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie, Retrait lieuRetrait, List<Enchere> enchereList) {
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
     *
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
    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie, List<Enchere> enchereList) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.categorie = categorie;
        this.prixInitial = prixInitial;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;

    }

    /**
     * Constructeur sans "lieuRetrait"
     *
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
    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Utilisateur utilisateur, Categorie categorie, List<Enchere> enchereList) {
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
     *
     * @param nomArticle
     * @param description
     * @param dateDebutEncheres
     * @param dateFinEncheres
     * @param prixInitial
     * @param utilisateur
     * @param categorie
     * @param enchereList
     */
    public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, Utilisateur utilisateur, Categorie categorie, List<Enchere> enchereList) {
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

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {

        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur util) {
        this.utilisateur = util;
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

    public void setEnchereList(Enchere enchere) {
        System.out.println(enchereList.size());
        System.out.println(enchere);
        System.out.println(enchereList);

        this.enchereList.add(enchereList.size(), enchere);
        System.out.println(this.enchereList);
        System.out.println(enchereList);

    }

    public void setDateDebutEncheresSpe(Date date) {
        Date dateDeb = date;
        LocalDate dateDef = Instant.ofEpochMilli(dateDeb.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.dateDebutEncheres = dateDef;
    }

    public void setDateFinEncheresSpe(Date date) {
        Date dateDeb = date;
        LocalDate dateDef = Instant.ofEpochMilli(dateDeb.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        this.dateFinEncheres = dateDef;
    }

    public void setUtilisateurSpe(int Categ) {


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

