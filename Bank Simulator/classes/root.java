package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class root extends funcionario{

    public root(int id, String nome, String pw, int IDconta, int s) throws IOException {
        super(id, nome, pw, IDconta, 0);
        setType("root");
        if (s == 1) handlers.log(id, nome, getType(), "logou no sistema");
    }

    //remover cliente ou funcionario
    public boolean remover(int id) throws IOException{
        if (id == 0) return false;

        //abrir dados
        BufferedReader file = new BufferedReader(new FileReader(handlers.pathData));
        String dados = "";
        String rem = "";
        int c = 0;
        boolean check = false;

        while (true)
        {
            String linha = file.readLine();
            if (linha == null) break;
            if (linha.split(",")[0].equals(""+id)) {check = true; rem = linha;}
            else {
                if (c > 0) dados += "\n";
                c++;
                dados += linha;
            }
            
        }
        file.close();

            
        if (check)
        {
            String[] rem0 = rem.split(",");
            String ocorrencia = "removeu usuario " + rem0[1] + ",id:" + rem0[0];
            handlers.log(getId(), getNome(), getType(), ocorrencia);

            // guardar usuario removido
            file = new BufferedReader(new FileReader(handlers.pathDeletedUsers));
            String aux0 = handlers.readData("-2", file, 0);
            file.close();

            BufferedWriter file1 = new BufferedWriter(new FileWriter(handlers.pathData));
            file1.write(aux0 + rem);
            file1.close();
        }

        //gravar dados
        BufferedWriter file0 = new BufferedWriter(new FileWriter(handlers.pathData));
        file0.write(dados);
        file0.close();
        
        return check;
    }

    //criar funcionario
    public String[] criarFuncionario(String nome, int idConta)throws IOException, NoSuchAlgorithmException{
        //gerar senha aleatorio:
        Random gerador = new Random();
        int aux = gerador.nextInt(10);
        String pw = "" + (gerador.nextInt(1000) + 1000 * aux);
        int id = handlers.varsHandler(0, true);
        funcionario func = new funcionario(id, nome, hashing.hash256(pw), idConta, 0);
        boolean check = func.saveData();

        String ocorrencia = "Criou usuario " + func.getNome() + " com id: " + func.getId();
        handlers.log(getId(), getNome(), "root", ocorrencia);
        
        if (check) {
            String[] out = {""+id, nome, pw, ""+idConta};
            return out;
        }
        else
        {
            String[] out = {"-1"};
            return out;
        }  

        
    }

    //aprovar emprestimos

    //calcular saldo do banco

    //usuarios
    public String[] getUsers() throws IOException
    {
        
        String data = "";
        int max_id = handlers.varsHandler(0, false);

        for (int i = 1; i < max_id; i++)
        {
            String id = i + "";
            try {
                String[] rawData = handlers.loadInfo(id);
                data += ";" + rawData[0] + "," + rawData[1] + "," + rawData[3];
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        String[] out = {data};

        return out;
    }

    // get transations usuario

    //modificar usuario

    //mostar logs
    public String[] getLogs(int inicio) throws IOException
    {
        String data = handlers.getLogs(inicio);
        String[] out = {data};

        return out;
    }

}
