package classes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class block {
    private int num;
    private String prevHash;
    private int emissor;
    private double valor;
    private int receptor;
    private String hash;
    private String path = "DATA/historico.dat";
    private String clPath = "DATA/clientes/";

        public int getNum() {
            return num;
        }
        
        public String getHash() {
            return hash;
        }

        public String blockHash() throws NoSuchAlgorithmException, UnsupportedEncodingException{
            String[] blockStr = {""+this.num, this.prevHash, ""+this.emissor, ""+this.valor, ""+this.receptor}; 
            String hash = hashing.hash256(handlers.to_cvsString(blockStr));
            return hash;
        }

        public boolean verificarHash() throws IOException, NoSuchAlgorithmException
        {
            if (this.hash.equals(blockHash())) return true;
            else {
                String o = "Registro de Transacoes foi indevidamente alterado";
                handlers.log(-1, "system", "root", o.toUpperCase());
                System.out.println(o.toUpperCase());
                return true;
            }
        }

        public block(int num, String prevHash, int emissor, double valor, int receptor, String hash) throws NoSuchAlgorithmException, UnsupportedEncodingException
        {
            this.num = num;
            this.prevHash = prevHash;
            this.emissor = emissor;
            this.valor = valor;
            this.receptor = receptor;
            this.hash= blockHash();
        }

        public boolean guardarBlock(String type) throws IOException
        {
            String[] aux = {""+this.num, this.prevHash, ""+this.emissor, ""+this.valor, ""+this.receptor, this.hash};
            String data =  handlers.to_cvsString(aux);

            String[] aux0 = data.split("\n");
            if (aux0.length == 2){
                String[] aux1 = aux0[1].split(",");
                data = aux0[0] + "," + aux1[1] + "," + aux1[2] +","+ aux1[3]+ "," +aux1[4];
            }
            
            //guardar no historico de transações geral
            blockchain.guardar(path, data);

            //guardar no historico do cliente
            if (this.receptor > 0) blockchain.guardar(clPath+this.receptor+".dat", data);

            if (this.emissor > 0) blockchain.guardar(clPath+this.emissor+".dat", data);

            return true;
        }
}
