package fr.eni.projetspring.bo;

public class Retrait {

    private String rue;
    private String codePostal;
    private String ville;
    private int noArticle;

    public Retrait() {
    }

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Retrait(String rue, String codePostal, String ville, int noArticle) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.noArticle = noArticle;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setArticleVendu(int noArticle) {
        this.noArticle = noArticle;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", noArticle=" + noArticle +
                '}';
    }
}
