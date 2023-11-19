function login() {
    document.title = "Login"

    let div0 = document.createElement("div")
    div0.id = "login"
    document.body.appendChild(div0)

    let h1 = document.createElement("h1")
    h1.className = "login"
    h1.innerText = "SIMUBANK LOGIN"
    div0.appendChild(h1)

    //nome ou ID
    let nome = document.createElement("p")
    let nome0 = document.createElement("input")
    nome0.id = "id"
    nome0.type = "Text"
    nome0.placeholder = "ID ou Nome de Usuario"
    nome0.className = "box0"
    nome.appendChild(nome0)
    div0.appendChild(nome)

    //password
    let pw1 = document.createElement("p")
    let pw = document.createElement("input")
    pw.type = "password"
    pw.id = "password"
    pw.className = "box0"
    pw.placeholder = "Password"
    pw1.appendChild(pw)
    div0.appendChild(pw1)

    //autenticar
    let aut = document.createElement("p")
    let aut0 = document.createElement("input")
    aut0.type = "button"
    aut0.id = "autenticar"
    aut0.className = "botao0"
    aut0.value = "Login"
    aut0.onclick = function() {if (nome0.value != "" || pw.value != "") {document.body.removeChild(div0); autenticar(nome0.value, pw.value);}else {alert("Preencha os campos");}}
    aut.appendChild(aut0)
    div0.appendChild(aut)

}

function autenticar(name, pw) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var data = this.responseText
            data = data.split(",")
            console.log(data); //testando 
            if (data[0] == "type:root")
                root()

            else if (data[0] == "type:funcionario")
                F_home() 
                
            else
                alert("Não Funcionou!")
        }
    };

    /*
    let data = {
        "type":"login",
        "username": name,
        "pw": pw
    }
    */
   let data = `type:login,username:${name},pw:${pw}`

    xhr.open('POST', "/login", true);
    xhr.send(data)
} 