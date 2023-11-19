document.addEventListener("keypress", function(e) {
    if(e.key === 'Enter') {
    
        let btn = document.querySelector("#login");
      
      btn.click();
    
    }
  });


function login()
{
    let name = document.getElementById("username")
    let pw = document.getElementById("pw")

    if (name.value == "" || pw.value == "")
    {
        let al = document.getElementById("warning")
        al.innerHTML = "<b>Campos Vazios</b>"
    }

    else autenticar(name.value, pw.value)

}


function autenticar(name, pw) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var rsp = this.responseText;
            //console.log(rsp)
            if (rsp != "ERROR") {
                data = JSON.parse(rsp)
                console.log(data)
                
                //apagar cookies anteriores
                document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });

                criarCookie(rsp)
                pedirPagina("")
            }
             
            else {
                let al = document.getElementById("warning")
                al.innerHTML = "<b>username ou password errados</b>"
            }
                
        }
    };


   let dat = `type:login,username:${name},pw:${pw}`

    xhr.open('POST', "/login", true);
    xhr.send(dat)
} 