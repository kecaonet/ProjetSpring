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
    let Filtre = document.getElementById("idInputFiltre").value
    console.log(Filtre);
    console.log(Categ);



    if (Categ == "Catégorie : Defaut" && Filtre == "") {
        console.log("Entree affichage Defaut et sans filtre")
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style")
        }
    } else if (Categ == "Catégorie : Defaut") {
        console.log("Entree affichage Defaut");
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style");
            console.log(document.getElementById("idDivNom").textContent.slice(10));
            if (document.getElementsByClassName("NomArticle")[i].textContent.slice(10) != Filtre) {
                document.getElementsByClassName("Categorie")[i].parentElement.style.display = "none";
            }
        }
    } else if (Filtre == "") {
        console.log("Entree affichage sans filtre")
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            if (Categ == document.getElementsByClassName("Categorie")[i].textContent) {
                document.getElementsByClassName("Categorie")[i].parentElement.removeAttribute("style");

            } else {
                document.getElementsByClassName("Categorie")[i].parentElement.style.display = "none";
            }
        }
    }
}
