function F_home() {
    
    document.title = "SIMUBank Funcionario"

    let div0 = document.createElement("div")
    div0.id = "funcionario"
    document.body.appendChild(div0)

    let h1 = document.createElement("h1")
    h1.innerText = "SIMUBank Gerencia"
    div0.appendChild(h1)

    //criar novo Cliente
    var cr = document.createElement("div")
    cr.id = "criar_Cliente"
    div0.appendChild(cr)

    let cr0 = document.createElement("p")
    let cr1 = document.createElement("input")
    cr1.type = "button"
    cr1.class = "botao1"
    cr1.value = "Criar novo Cliente"
    cr1.id = "criarCliente"
    cr1.onclick = function(){document.body.removeChild(div0); criarCliente();}
    cr.appendChild(cr0.appendChild(cr1))

}


function criarCliente() {
    document.title = "Criar Cliente SIMUBank"
    
    let div = document.createElement("div")
    div.id = "criarCliente"
    document.body.appendChild(div)

    let h1 = document.createElement("h1")
    h1.innerText = "Criar Novo Cliente"
    div.appendChild(h1)

    //nome
    let nome = document.createElement("p")
    let nome0 = document.createElement("input")
    nome0.id = "id"
    nome0.type = "Text"
    nome0.placeholder = "Nome Completo"
    nome0.className = "box0"
    nome.appendChild(nome0)
    div.appendChild(nome)

    //dataNascimento
    let dat = document.createElement("p")
    let dat0 = document.createElement("input")
    dat0.type = "date"
    dat0.id = "data"
    dat.appendChild(dat0)
    div.appendChild(dat)

    //CNI
    let cni = document.createElement("p")
    let cni0 = document.createElement("input")
    cni0.id = "id"
    cni0.type = "Text"
    cni0.placeholder = "CNI"
    cni0.className = "box0"
    cni.appendChild(cni0)
    div.appendChild(cni)

}