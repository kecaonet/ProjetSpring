window.onload

/*document.getElementById("idBtnCategorie").addEventListener("click", test)

function test() {
    console.log("Entree test")

    document.getElementById("idBtnCategorie").insertAdjacentHTML("afterbegin","<div th:text=${enchere.nomArticle}></div>");
}*/

init()


function init() {

document.getElementById("idBtnCategorie").addEventListener("click", Categorie)
for (i = 0; i < document.getElementsByClassName("Enchere").length; i++) {
    document.getElementsByClassName("Enchere")[i].setAttribute("id",("idEnch"+i))
    document.getElementsByClassName("Categorie")[i].setAttribute("id",(document.getElementsByClassName("Categorie")[i].textContent))
    console.log(document.getElementsByClassName("Enchere")[i]);
    console.log(document.getElementsByClassName("Categorie")[i]);
    console.log(document.getElementsByClassName("Categorie")[i].textContent);
}
}

function Categorie() {
    for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
        document.getElementsByClassName("Categorie")[i].parentElement.style.display = "auto";
    }

    Categ = "Catégorie : " + document.getElementById("idSelectCategorie").value;
    console.log(Categ);
    if (Categ == "Catégorie : Defaut") {
        for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
            console.log(document.getElementsByClassName("Categorie")[i].getAttribute("id"));

            document.getElementsByClassName("Categorie")[i].parentElement.style.display = "auto";
        }
    }
    else {

    for (i = 0; i < document.getElementsByClassName("Categorie").length; i++) {
        console.log(document.getElementsByClassName("Categorie")[i].getAttribute("id"));
    if (document.getElementsByClassName("Categorie")[i].getAttribute("id") != Categ) {

    document.getElementsByClassName("Categorie")[i].parentElement.style.display = "none";
    }
    }

    }
}
