function root() {
    document.title = "root"
    let div = document.createElement("div")
    div.id = "root"
    document.body.appendChild(div)

    let h1 = document.createElement("h1")
    h1.innerText = "SIMUBank ROOT"
    div.appendChild(h1)

    let p = document.createElement("p")
    p.id = "opçoes"
    div.appendChild(p)

    //criar Cliente
    let bot1 = document.createElement("input")
    bot1.type = "button"
    bot1.id = "criarCliente"
    bot1.className = "botao1"
    bot1.value = "Criar Cliente"
    bot1.onclick = function() {document.body.removeChild(div); criarCliente();}
    p.appendChild(bot1)

    //criar funcionario
    let bot2 = document.createElement("input")
    bot2.type = "button"
    bot2.id = "criarFuncionario"
    bot2.className = "botao1"
    bot2.value = "Criar Funcionario"
    bot2.onclick = function() {}
    p.appendChild(bot2)

    //ver clientes e funcionarios
    let bot3 = document.createElement("input")
    bot3.type = "button"
    bot3.id = "verPlayers"
    bot3.className = "botao1"
    bot3.value = "Ver Clientes e Funcionarios"
    bot3.onclick = function() {}
    p.appendChild(bot3)

    //conta
    let bot4 = document.createElement("input")
    bot4.type = "button"
    bot4.id = "Conta"
    bot4.className = "botao1"
    bot4.value = "Conta"
    bot4.onclick =  function() {}

    //autorizar emprestimos


    //ver emprestimos (grafico)
    

    //ver saldo do banco (grafico)

    // ver clientes ativos
}

