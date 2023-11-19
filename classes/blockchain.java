package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class blockchain {
    public static String path = "DATA/historico.dat";
    public static String clPath = "DATA/clientes/";

    public static void guardar(String dir, String data) throws IOException
    {
        try {
            BufferedReader file = new BufferedReader(new FileReader(dir));
            data += "\n" + handlers.readData("-2", file, 0);
            file.close();

        } catch (Exception e) {
            handlers.log(-1, "system", "root", "Criando ficheiro: " + dir);
        }

        BufferedWriter file0 = new BufferedWriter(new FileWriter(dir));
        file0.write(data);
        file0.close();

    }

    //verificar chain e calcular saldo
    public static String[] verificarChain(String id) throws IOException
    {
        double saldo = 0;

        BufferedReader file = new BufferedReader(new FileReader(path));
        String aux0 = handlers.readData("-2", file, 0);
        file.close();

        String prevHash = "";
        String[] out = {"","",""};//{num,prevhash,saldo}
        String[] data = aux0.split("\n");

        if (!(aux0.equals("")))
        {
            for (int i=0; i < data.length; i++)
            {
                String[] aux = data[i].split(",");

                //verificar hash do bloco com ele mesmo

                prevHash = aux[5];
                if (i == 0) {out[0] = aux[0]; out[1] = aux[5];}
                else {
                    if (!(prevHash.equals(aux[5])))
                    {
                        String o = "Registro de Transacoes foi indevidamente alterado na linha " + i;
                        handlers.log(-1, "system", "root", o.toUpperCase());
                        System.out.println(o.toUpperCase());
                        out[0] = "-1";
                        return out;
                    }

                    else if (aux[2].equals(id)) saldo -= Double.parseDouble(aux[3]);
                    else if (aux[4].equals(id)) saldo += Double.parseDouble(aux[3]);
                }
            }
        }
        

        out[2] = ""+saldo;
        return out;
    }

    //depositar e tranferencia
    public static String[] transferencia(String type, String eID, double valor, int receptorID) throws IOException, NumberFormatException, NoSuchAlgorithmException
    {
        String[] out = new String[1];
        String[] aux = new String[3];
        valor = Math.abs(valor);
        int emissorID = Integer.parseInt(eID);
        //System.out.println(valor);
        
        
        if (emissorID > -1)
        {//verificar saldo do emissor e registros
            aux = verificarChain(eID);
            if (aux[0].equals("-1")) {out[0] = aux[0]; return out;}
            else if (Double.parseDouble(aux[2]) < valor) {out[0] = "SaldoInsuficiente"; return out;}
        } else aux = verificarChain("-2");


        //criar bloco
        block b = new block(Integer.parseInt(aux[0])+1, aux[1], emissorID, valor, receptorID, "");
        
        //guardar bloco
        if (b.guardarBlock(type)) out[0] = "transacaoEfectuada";
        else out[0] = "transacaoErro";

        //retornar status
        return out;
    }

    public static double calcularSaldo(String id, String type) throws IOException{
        double saldo = 0;

        BufferedReader file = new BufferedReader(new FileReader(clPath + id + ".dat"));
        String aux0 = handlers.readData("-2", file, 0);
        file.close();

        String[] data = aux0.split("\n");
        
        for (int i=0; i < data.length; i++)
        {
            String[] aux = data[i].split(",");

            if (aux[2].equals(id)) saldo -= Double.parseDouble(aux[3]);
            else if (aux[4].equals(id)) saldo += Double.parseDouble(aux[3]);
            
        }
        
        return saldo;
    }
}
