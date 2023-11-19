function cliente()
{
    let dv = document.getElementById("criarDIV")

    let d = document.createElement("div")
    d.id = "criarDIV"

    let p4 = document.createElement("p") 

    let nome = document.createElement("input")
    nome.type = "text"
    nome.id = "nome"
    nome.placeholder = "Nome"
    let p0 = document.createElement("p")
    p0.appendChild(nome)
    d.appendChild(p0)

    let dataNascimento = document.createElement("input")
    dataNascimento.type = "date"
    dataNascimento.id = "dataNascimento"
    dataNascimento.placeholder = "Data de Nascimento"
    let p1 = document.createElement("p")
    p1.appendChild(dataNascimento)
    d.appendChild(p1)

    let cni = document.createElement("input")
    cni.type = "Number"
    cni.id = "cni"
    cni.placeholder = "NIF"
    let p2 = document.createElement("p")
    p2.appendChild(cni)
    d.appendChild(p2)

    let email = document.createElement("input")
    email.type = "email"
    email.id = "email"
    email.placeholder = "email"
    let p5 = document.createElement("p")
    p5.appendChild(email)
    d.appendChild(p5)

    let btn = document.createElement('input')
    btn.type = "button"
    btn.id = "criar"
    btn.value = "Criar Cliente"
    btn.onclick = function() {if (nome.value=="" || dataNascimento.value=="" || cni.value=="" || email.value=="") p4.innerHTML = "<b>Campos Vazios</b>"; else finalizar0(nome.value, dataNascimento.value, cni.value, email.value)}
    let p3 = document.createElement("p")
    p3.appendChild(btn)
    d.appendChild(p3)

    d.appendChild(p4)

    dv.replaceWith(d)
}

function finalizar0(nome, dataNascimento, cni, email)
{
    dataNascimento= dataNascimento.split("-")
    let dat = `criarCliente/${nome}/${dataNascimento[2]}-${dataNascimento[1]}-${dataNascimento[0]}/${cni}/${email}`
    let dados = fazerOperacao(dat)
    let nomes = ["ID:","Nome:", "Senha:", "Numero de Conta", "NIF", "Data de Nascimento", "Email"]
    let args = [dados.id, dados.nome, dados.pw, dados.NumConta, dados.cni, dados.dataNascimento, dados.email]

    final("Cliente", nomes, args, dados)
}

function final(nome, nomes, args, dados)
{
    // o div principal é 'criar'
    //nome: pode ser cliente ou funcionario

    let div = document.createElement("div")
    div.id = "criar"

    if (dados.status == nome){
        let p0 = document.createElement("p")
        p0.innerHTML = `<b>${nome} criado com Sucesso!</div>`
        div.appendChild(p0)

        for (let c=0; c < nomes.length; c++)
        {
            let p = document.createElement("p")
            p.id = "dados"
            p.innerHTML = `<b>${nomes[c]}</b>: ${args[c]}`
            div.appendChild(p)
        }
    }
    else {
        let p0 = document.createElement("p")
        p0.innerHTML = `<b>A criação do ${nome} falhou</b>`
        div.appendChild(p0)
    }

    document.getElementById("criar").replaceWith(div)
}

function info() {
    let data = getCookie()
    let infoP = document.getElementById("info")

    let btn =  document.createElement("input")
    btn.type = "button"
    btn.value = "Log Out"

    infoP.innerHTML = `Nome:<b>${data.username}</b> ID:${data.id}     `
    infoP.appendChild(btn)

    btn.addEventListener("click", logout)
}


function criarCookie(cookie) {
    let d = new Date()
    d = new Date(d.getFullYear,d.getMonth,d.getDay,(d.getHours+1))
    document.cookie = `nome:${cookie};expires:${d.toGMTString()};path:/SimuBank`
    //console.log("Cookie Criado")
}

function getCookie()
{
    let cookie = document.cookie 
    //console.log(cookie)
    cookie = cookie.substring(5)

    //document.cookie = "cookiename=cookievalue; expires= Thu, 21 Aug 2014 20:00:00 UTC; path=/ "
    
    return JSON.parse(cookie)
}

function pedirPagina(page)
{
    let data = getCookie()

    window.location.href = `${data.type}/${page}`
    /*
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var rsp = this.responseText;
            document.close()
            document.write(rsp)
   
        }
    };

    let path = `${data.type}/${page}`
    let content = `type:pedirPagina,id:${data.id},token:${data.token},data:${path}`
    xhr.open('POST', "/SimuBank" , true);
    xhr.send(content)
    */
}

function fazerOperacao3()
{
    var obj = {}
    let data = getCookie()

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var rsp = this.responseText;
            if (rsp != "Sessao Terminada")
            {
                obj = JSON.parse(rsp)
            }
            else SessaoTerminada();
        }
    }

    let content = `type:fazerOperacao,id:${data.id},token:${data.token},data:deleteUser/3`
    xhr.open('POST', "/SimuBank" , false);
    xhr.send(content)

    return obj

}

function fazerOperacao(dat)
{
    var obj = {}
    let data = getCookie()

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var rsp = this.responseText;
            if (rsp != "Sessao Terminada")
            {
                obj = JSON.parse(rsp)
            }
            else SessaoTerminada();
        }
    }

    let content = `type:fazerOperacao,id:${data.id},token:${data.token},data:${dat}`
    xhr.open('POST', "/SimuBank" , false);
    xhr.send(content)

    return obj

}

function SessaoTerminada()
{
    console.log("Sessão Terminada!")
    window.location.href = "/"
}

function voltar()
{
    pedirPagina("../")
}

function mudarSenha(senhaAtual, senhaNova) {
    let Ndiv = document.createElement("div")
    Ndiv.id = "mudarSenha"
    let div = document.getElementById("mudarSenha")
    let p = document.createElement("p")

    let response = fazerOperacao(`mudarSenha/${senhaAtual}/${senhaNova}`)
    if (response.status == "SenhaAlterada") 
        p.innerHTML = "<b>Senha Alterada com Sucesso!</b>";

    else p.innerHTML = "<b>Não foi possivel alterar a Senha!</b>";
    
    Ndiv.appendChild(p)
    div.replaceWith(Ndiv)
}

function transferenciaDIV()
{
    let Sdiv = document.getElementById("transferencia")

    //num conta
    let p0 = document.createElement("p")
    p0.innerText = "Numero de Conta: "
    let numConta = document.createElement("input")
    numConta.id = "numConta"
    numConta.type = "text"
    numConta.placeholder="xxxxxxxx"
    p0.appendChild(numConta)
    Sdiv.appendChild(p0)

    //valor
    let p1 = document.createElement("p")
    p1.innerText = "Valor: "
    let valor = document.createElement("input")
    valor.id = "valor"
    valor.type = "Number"
    valor.placeholder="$CVE"
    p1.appendChild(valor)
    Sdiv.appendChild(p1)

    //btn confirmar
    let p2 = document.createElement("p")
    let btn = document.createElement("input")
    btn.type = "button"
    btn.id = "confirmar"
    btn.value = "Confirmar"
    btn.onclick = transferencia;
    Sdiv.appendChild(btn)

    //warning
    let p3 = document.createElement("p")
    p3.id = "warning"
    Sdiv.appendChild(p3)

}

function transferencia()
{
    let num = document.getElementById("numConta").value
    let valor = document.getElementById("valor").value

    if (num == "" || valor == "")  document.getElementById("warning").innerHTML = "<b>Verifique os Campos</b>";

    else 
    {
        let status = fazerOperacao(`transferencia/${valor}/${num}`).status

        let div = document.getElementById("transferencia")
        let nDiv = document.createElement("div")
        nDiv.id = "transferencia"
        let p = document.createElement("p")

        if (status == "SaldoInsuficiente") p.innerHTML = "<b>Tranferencia não Possivel devido a Saldo Insuficiente na Conta!</b>";
        else if (status == "transacaoEfectuada")
        {
            let h = document.createElement("h4")
            h.innerText = "Transferecia feita com Sucesso"
            nDiv.appendChild(h)
            let p0 = document.createElement("p")
            p0.innerText = `Conta Destino: ${num}`
            nDiv.appendChild(p0)
            p.innerText = `Valor: ${valor}`
        } 

        else p.innerHTML = "<b>Erro na Tranferencia!</b>";


        nDiv.appendChild(p)

        let p2 = document.createElement("p")
        p2.innerHTML = (`<input type="button" value="Voltar" onclick="voltar('../')">`)
        nDiv.appendChild(p2)

        div.replaceWith(nDiv)
    }
    
}

function convertor(saldo)
{
    qtd = ' '

    if (saldo.search("E")) {
        saldo = Number.parseFloat(saldo)
             
        if ( saldo / 1   <= 999999 ) {qtd = ''; saldo = saldo / 1  }
     
        else if ( saldo / 1000000 <  1000 ) {qtd = 'M'; saldo = saldo / 1000000 }
        else if ( saldo / 1000000000 <= 1000 ) {qtd = 'Bi'; saldo = saldo / 1000000000 }
    }
    
    out = "" + `${saldo} ${qtd}`
    return out
}

function Show_history(historico)
    {
        let tbody = document.querySelector(".history")

        //mostrar dados
        for (let i=1; i < historico.length; i++)
        {
            let trans = historico[i].split(",")
            let mov = "-"

            //definir tipo de operaçao
            if (trans[4] == "-1") operacao = "Levantamento";
            else if (trans[2] == getCookie().id) operacao = `Transferencia para ID${trans[4]}`;
            else if (trans[2]== "-1") {operacao = "Depósito"; mov = "+"}
            else {operacao = `Transferencia de ID${trans[2]}`; mov = "+"}
            
            let tr = `<tr><td>${i}</td><td>${trans[0]}</td><td>${operacao}</td><td>${mov}${convertor(trans[3])}</td></tr>`
            tbody.innerHTML += tr
        }

    } 

function logout(){
    //document.cookie = "cookiename= ; expires = Thu, 01 Jan 1970 00:00:00 GMT"
    
    fazerOperacao('logout')
     
    document.cookie = ""
    window.location.href = "/"
}