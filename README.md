# BankSimu
Sistema de Banco Online

`Este projeto é um sistema de banco online, onde há 3 tipos de players involvidos, os clientes, funcionarios do banco e gerente:
-Os clientes podem fazer transferencia entre si, consultar transações e saldo disponivel, mudar sua senha.
-Os funcionarios podem adicionar clientes ao sistema, fazer levantamentos ou depositos para os clientes
-O Gerente(root) tem o poder de ver todos os clientes e funcionarios do sistema, podendo adicionar, deletar ou modificar os clientes ou funcionarios.

Para executar o Sistema:
- Compila o server.java "javac server.java"
a classe main do projeto esta localizado em server.java
- executa o server "java server"
No linux é necessario executar com sudo "sudo java server"
Enquanto que windows, so permitir a aplicação no firewall
- Agora no navegador/ browser, digite o seu IP local ou "localhost:80"


Sobre o sistema:
- O Sistema opera um servidor HTTP na porta 80
- O sistema utliza java no backend
- Para apresentação dos dados no browser utiliza HTML, CSS e Javascript puro 
- As transações feitas neste banco são mantidos em blockchain e utilizando sha256
- As palavra-passe são guardadas em sha256 

Base de dados do Sistema:
- Este sistema utiliza no-sql propria, onde os dados são armazenados em CSV (comma separated value)
- Os dados de todos os usuario do sistema é armazenado em ./DATA/mainData.dat
- as transações globais são guardas no ./DATA/historico.dat e em cada transaçõe é feita um verificação para validar a cadeia de blocos
- quando é referenciado um cliente, uma copia dessa transação é guardada no historico do cliente em ./DATA/clientes/_ID_CLIENTE.dat
e esse historico pessoal de cada cliente é utilizado somente acelerar apresentação do historico das transação para o respectivo cliente
- os clientes deletados do sistema, seus dados e historico são armazenados em ./deleted/users.dat
- *** Para mais informações sobre o base de dados consulta "notas.txt" ***
