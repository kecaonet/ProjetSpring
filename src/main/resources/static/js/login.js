window.onload
console.log(localStorage.key(0))
console.log("hello world")
if (localStorage.key(0) != null) {
        let lettre1
        let lettre2

    for (i=0;i < localStorage.key.length;i++) {

        lettre1 = localStorage.key(i)
        lettre2 = (lettre2 + lettre1)
        console.log(lettre2)
    }
        lettre2 = lettre2.slice(9)
        console.log(lettre2)
    console.log(JSON.parse(localStorage.getItem(lettre2)))

    const userStock = JSON.parse(localStorage.getItem(lettre2));
    let Nom = userStock.nom
    let mdp = userStock.mdp;
    console.log("Entree local")
    let user = document.getElementById("username").value = Nom
    let psw = document.getElementById("password").value = mdp

    login()
}

    function login() {
        document.getElementById("idBtnLog").click()

    }
document.getElementById("idBtnLog").addEventListener("click",register)

function register() {
    if (document.getElementById("idCheck").checked) {

        const user = {
            nom: document.getElementById("username").value,
            mdp: document.getElementById("password").value,
        };
    localStorage.setItem(document.getElementById("username").value, JSON.stringify(user));
    }
}