import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import classes.handlers;
import classes.hashing;
import classes.operacoes;

import java.io.BufferedReader;
import java.io.FileReader;

public class Request {
    public static String Handle_Request(String request) throws IOException, NoSuchAlgorithmException {
        if (request != "") {
            
            String[] dat = request.split(",");
            String[] data = {"","","",""};
            String response;

            for (int c=0; c < dat.length; c++)
            {
                data[c] = dat[c].split(":")[1];
            }
            
            //verificar tipo
            if (data[0].equals("login")) {//type:value,username:value,pw:value
                response = autenticar(data[1], data[2]);
                return "0" + response;
            }

            else if (data[0].equals("pedirPagina"))
            {
                if (handlers.confirmarToken(data[1], data[2]))
                {      
                    return "1SimuBank/html/" + data[3] + ".html";
                }
                else return "0Sessao Terminada";
            }

            else if (data[0].equals("fazerOperacao"))
            {
                if (handlers.confirmarToken(data[1], data[2]))
                {   

                    //criar funcionario
                    if (data[3].startsWith("criarFuncionario/"))
                    {
                        data = operacoes.criarFunc(data[1], data[3]);
                        if (!(data[0].equals("-1"))) {
                            String[] out = {"status", "Funcionario", "id", data[0], "nome", data[1], "pw", data[2], "idConta", data[3]};
                            return "0" + parseJson(out);
                        }                       
                    }

                    //criar cliente
                    else if (data[3].startsWith("criarCliente/"))
                    {
                        data = operacoes.criarCliente(data[1], data[3]);
                        if (!(data[0].equals("-1"))) {
                            String[] out = {"status", "Cliente", "id", data[0], "nome", data[1], "pw", data[2], "NumConta", data[3], "dataNascimento", data[4], "cni", data[5], "email", data[6]};
                            return "0" + parseJson(out);
                        }
                    }

                    //mudarPw
                    else if (data[3].startsWith("mudarSenha"))
                    {
                        data = operacoes.mudarSenha(data[1], data[3]);
                        
                        if (!(data[0].equals("-1"))) {
                            String[] out = {"status", "SenhaAlterada"};
                            return "0" + parseJson(out);
                        }
                    }

                    //mudarNome
                    else if (data[3].startsWith("AlterarDados"))
                    {
                        data = operacoes.mudarNome(data[1], data[3]);
                        if (!(data[0].equals("-1"))){String[] out = {"status", "DadosAlterados"}; return "0"+parseJson(out);}
                    }

                    //tranferencia
                    else if (data[3].startsWith("transferencia"))
                    {
                        data = operacoes.transferencia(data[1], data[3]);
                        if (!(data[0].equals("-1"))){String[] out = {"status", data[0]}; return "0"+parseJson(out);}
                    }

                    //calcular saldo
                    else if (data[3].startsWith("calcularSaldo"))
                    {
                        data = operacoes.calcularSaldo(data[1]);
                        return "0"+parseJson(data);
                    }

                    //levantamento
                    else if (data[3].startsWith("levantamento"))
                    {
                        data = operacoes.levantamento(data[1], data[3].split("/"));
                        String[] out = {"status", data[0]};
                        return "0"+parseJson(out);
                    }

                    //getUsers
                    else if (data[3].startsWith("getUsers"))
                    {
                        data = operacoes.getUsers(data[1], data[3]);
                        String[] out = {"status", data[0]};
                        return "0" + parseJson(out);
                    }

                    //get User details
                    else if (data[3].startsWith("getUserDetails"))
                    {
                        data = operacoes.getUser(data[1], data[3]);
                        String[] out = {"status", data[0], "nome", data[1], "email", data[2]};
                        return "0" + parseJson(out);
                    }

                    //getLogs
                    else if (data[3].startsWith("getLogs"))
                    {
                        data = operacoes.getLogs(data[1], data[3]);
                        String[] out = {"status", data[0]};
                        return "0" + parseJson(out);
                    }

                    // historico de transações
                    else if (data[3].startsWith("getTransactions"))
                    {
                        data = operacoes.getTrans(data[1]);
                        return "0" + parseJson(data);
                    }

                    else if (data[3].startsWith("super_getTransactions"))
                    {
                        data = operacoes.super_getTransactions(data[1], data[3]);
                        String[] out = {"status", data[0]};
                        return "0" + parseJson(out);
                    }

                    // delete user
                    else if (data[3].startsWith("deleteUser"))
                    {
                        data = operacoes.deleteUser(data[1], data[3]);
                        String[] out = {"status", data[0]};
                        return "0" + parseJson(out);
                    }

                    //log out
                    else if (data[3].startsWith("logout"))
                    {
                        data = operacoes.logout(data[1]);
                        String[] out = {"status", data[0]};
                        return "0" + parseJson(out);
                    }
                
                    //caso de erro
                    String[] out = {"status", "falhou"};
                    return "0" + parseJson(out);
                }

                else return "0Sessao Terminada";
            }
        }

        return null;
    }

    private static String autenticar(String username, String pw) throws IOException, NoSuchAlgorithmException
    {
        //verificar username na base de dados
        String[] user = verificarDB("DATA/mainData.dat", username);
        if (user[0] == null)
            return "ERROR";

        //transformar pw em hash
        // comparar hashes
        String hash = hashing.hash256(pw);
        if (user[2].equals(hash))
        {
            String type = user[3];

            //gerar token
            String token = tokens();
            handlers.adicionarToken(user[0], token);
            handlers.adicionarTemp(user[0]);
            //retornar dados
            String[] data = {"type", type, "id", user[0], "username", user[1], "token", token};
            
            return parseJson(data);
        }    

        else
            return "ERROR";

       
    }

    public static String[] verificarDB(String path, String arg) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        System.out.println("verificando DB");
        String linha = "";
        
        while (true) {
            linha = buffRead.readLine();
            
            if (linha == null) 
                break;

            String[] data = linha.split(",");

            if (arg.equals(data[0]) || arg.equals(data[1]))
            {
                buffRead.close();
                return data;   
            }
            
        }

        buffRead.close();
        String[] data = {null};
        return  data;
    }

    public static void main(String[] args) throws IOException{
        String[] data= {"nome", "gabriel", "morada", "Alto Peixinho", "signo", "libra"};
        System.out.println(parseJson(data));
        String[] data1 = {"nome", "gabriel", "morada"};
        System.out.println(parseJson(data1));
    }

    public static String parseJson(String[] args){
        if (args.length % 2 == 0) {
            String json = "{";
            String pt = "";
            pt += '"';
            for (int c=0; c < args.length; c++)
            {
                json += pt + args[c] + pt + ":" + pt + args[c+1] + pt;
                c++;

                if (c != args.length - 1)  json += ",";
            }

            json += "}";
            return json;
        }

        else return "JSON nao possivel";       
    }

    public static String tokens() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Random gerador = new Random();
        int num = gerador.nextInt(9999);
        int num0 = gerador.nextInt(99);
        return hashing.hash256("" + (num * num0));
    }
}

