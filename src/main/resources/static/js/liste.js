window.onload

/*document.getElementById("idBtnCategorie").addEventListener("click", test)

function test() {
    console.log("Entree test")

    document.getElementById("idBtnCategorie").insertAdjacentHTML("afterbegin","<div th:text=${enchere.nomArticle}></div>");
}*/

init()


function init() {

document.getElementById("idBtnCategorie").addEventListener("click", Recherche)
for (i = 0; i < document.getElementsByClassName("Enchere").length; i++) {
    document.getElementsByClassName("Enchere")[i].setAttribute("id",("idEnch"+i))
    document.getElementsByClassName("Categorie")[i].setAttribute("id",(document.getElementsByClassName("Categorie")[i].textContent))
    console.log(document.getElementsByClassName("Enchere")[i]);
    console.log(document.getElementsByClassName("Categorie")[i]);
    console.log(document.getElementsByClassName("Categorie")[i].textContent);
}
}

function Recherche() {
    let Categ = "Catégorie : " + document.getElementById("idSelectCategorie").value;
    console.log(Categ);
    if (Categ == "Catégorie : Defaut") {
        console.log("entrée Defaut")
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            console.log(document.getElementsByClassName("Categorie")[i].getAttribute("id"));
            console.log(document.getElementsByClassName("Categorie")[i]);
            document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style");
        }
    }
    else {

    for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
        console.log(document.getElementsByClassName("Categorie")[i].getAttribute("id"));
    if (document.getElementsByClassName("Categorie")[i].getAttribute("id") != Categ) {
        console.log("Entrée suppression");
        console.log(document.getElementsByClassName("Categorie")[i]);
        document.getElementsByClassName("Categorie")[i].parentElement.style.display = "none";
    }
    if (document.getElementsByClassName("Categorie")[i].getAttribute("id") == Categ) {
        console.log("Entrée réaffichage");
        console.log(document.getElementsByClassName("Categorie")[i]);
        console.log(document.getElementsByClassName("Categorie")[i].parentElement);
        document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style");
    }
    }
    }
    let Filtre = document.getElementById("idInputFiltre").value
    console.log(Filtre);
    if (Filtre == "") {
        console.log("Entree affichage tout")
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style");
        }
    }
    else {

    for (i=0; i < document.getElementsByClassName("Categorie").length; i++) {
        console.log(document.getElementById("idDivNom").textContent.slice(10))
        console.log()
        if (document.getElementById("idDivNom").textContent.slice(10) == Filtre) {
            console.log("Entree Filtre")
        }
        else {
            console.log("Entree suppression")
            document.getElementsByClassName("Categorie")[i].parentElement.style.display = "none";
        }
    }
    }
}
