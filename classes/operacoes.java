package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class operacoes {
    public static String[] criarFunc(String id, String dados) throws IOException, NumberFormatException, NoSuchAlgorithmException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
        //fazer operaçãos
        String[] dat = dados.split("/");
        String[] response = r.criarFuncionario(dat[1], Integer.parseInt(dat[2]));
        
        //retornar dados
        return response;
    }

    public static String[] criarCliente(String id, String dados) throws IOException, NumberFormatException, NoSuchAlgorithmException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        if (data[3].equals("root")) 
        {
            //inicializar objeto
            root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);

            //fazer operação
            String[] dat = dados.split("/");
            String[] aux0 = dat[2].split("-");
    
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            String[] response = r.criarCliente(dat[1], dataNascimento, Integer.parseInt(dat[3]), dat[4]);

            //retornar dados
            return response;

        }
        else if (data[3].equals("funcionario")) 
        {
            //inicializar objeto
            funcionario func = new funcionario(id0, data[1], data[2], Integer.parseInt(data[4]), 1);

            //fazer operação
            String[] dat = dados.split("/");
            String[] aux0 = dat[2].split("-");
    
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            String[] response = func.criarCliente(dat[1], dataNascimento, Integer.parseInt(dat[3]), dat[4]);

            //retornar dados
            return response;
        }
        
        else {
            String[] out = {"-1"};
            return out;
        }
    }

    public static String[] mudarSenha(String id, String dados) throws IOException, NoSuchAlgorithmException
    {
        //carregar dados
        String[] dat = dados.split("/");
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;
        System.out.println(dat[2]);
        System.out.println(data[3]);
        
        if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 1);
            
            if (cl.mudarSenha(hashing.hash256(dat[1]), hashing.hash256(dat[2]))) {String[] out = {"Senha Alterada"}; return out;}
        }

        if (data[3].equals("funcionario"))
        {
            
            funcionario func = new funcionario(id0, data[1], data[2], Integer.parseInt(data[4]), 1);

            if (func.mudarSenha(hashing.hash256(dat[1]), hashing.hash256(dat[2]))) {String[] out = {"Senha Alterada"}; return out;}
        }

        String[] out = {"-1"};
        return out;
    }

    public static String[] mudarNome(String id, String dados) throws IOException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 1);
            
            String[] dat = dados.split("/");

            if (cl.mudarDados(dat[1], dat[2])) {String[] out = {"Nome Alterada"}; return out;}

        }

        String[] out = {"-1"};
        return out;
    }

    public static String[] transferencia(String id, String dados) throws IOException, NumberFormatException, NoSuchAlgorithmException
    {
        //carregar dados
        String[] dat = dados.split("/");
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 1);
            //System.out.println("before class");
            String[] response = cl.transferencia(dat[1], dat[2]);
            return response;

        }

        else if (data[3].equals("funcionario"))
        {
            
            //inicializar objeto
            funcionario func = new funcionario(id0, data[1], data[2], Integer.parseInt(data[4]), 1);

            //fazer operação
            //pesquisar id
            String[] response = func.deposito(Double.parseDouble(dat[1]), Integer.parseInt(dat[2]));

            //retornar dados
            return response;
        }

        String[] out = {"-1"};
        return out;
    }

    public static String[] levantamento(String id, String[] dat) throws NumberFormatException, NoSuchAlgorithmException, IOException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        String[] out = {"-1"};

        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        if (data[3].equals("funcionario"))
        {
            
            //inicializar objeto
            funcionario func = new funcionario(id0, data[1], data[2], Integer.parseInt(data[4]), 1);

            //fazer operação
            //pesquisar id
            String[] response = func.levantamento(Double.parseDouble(dat[1]), dat[2]);

            //retornar dados
            return response;
        }

        return out;
        
    }

    public static String[] calcularSaldo(String id) throws IOException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 0);
            String[] out = cl.calcSaldo();

            return out;
        }

        String[] out = {"-1"};
        return out;
    }

    
    public static String[] getUsers(String id, String dados) throws IOException
    {
        //carregar dados    
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
        //fazer operaçãos
        String[] dat = dados.split("/");

        //filtrar
      
        String[] response = r.getUsers();
        
        //retornar dados
        return response;
    }

    public static String[] getUser(String id, String dados) throws IOException
    {
        String[] response = {"",null, null};
        
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        if (data[3].equals("root"))
        {
            root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
            //fazer operaçãos
            String[] dat = dados.split("/");
            
            //filtrar
        
            response[0] = r.getUser(dat[1]);
        }

        else if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 0);
            response[1] = cl.getNome();
            response[2] = cl.getEmail();
           
        }

        else if (data[3].equals("funcionario"))
        {
            funcionario func = new funcionario(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
            //fazer operaçãos
            String[] dat = dados.split("/");
        
            response[0] = func.getUser(dat[1]);
        }
        
        return response;
        
    }

    public static String[] getLogs(String id, String dados) throws IOException
    {
         //carregar dados
         String[] data = handlers.loadInfoTemp(id);
         int id0 = Integer.parseInt(data[0]);
         if (id0 == -1) return data;
 
         //inicializar objeto
         root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
     
         //fazer operaçãos
         String[] dat = dados.split("/");
         int inicio = Integer.parseInt(dat[1]);
        
         String[] response = r.getLogs(inicio);
         
         //retornar dados
         return response;
    }


    public static String[] getTrans(String id) throws IOException
    {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        if (data[3].equals("cliente"))
        {
            String[] aux0 = data[4].split("/");
            int[] dataNascimento = {Integer.parseInt(aux0[0]), Integer.parseInt(aux0[1]), Integer.parseInt(aux0[2])};
            
            cliente cl = new cliente(id0, data[1], data[2],Integer.parseInt(data[5]), Double.parseDouble(data[6]), Integer.parseInt(data[7]), dataNascimento, data[8], 1);
            String[] out = cl.getTrans();

            return out;
        }

        String[] out = {"-1"};
        return out;
    }

    public static String[] deleteUser(String id, String dados) throws IOException {
        String[] out = {"NAO FOI POSSIVEL REMOVER USUÁRIO"};

        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
        //fazer operaçãos
        String[] dat = dados.split("/");
        int id_ = Integer.parseInt(dat[1]);

        boolean response = r.remover(id_);
        
        // retornar dados
        if (response) out[0] = "Usuario Removido com Sucesso ";

        return out;      
    }

    public static String[] super_getTransactions(String id, String dados) throws IOException {
        //carregar dados
        String[] data = handlers.loadInfoTemp(id);
        int id0 = Integer.parseInt(data[0]);
        if (id0 == -1) return data;

        //inicializar objeto
        root r = new root(id0, data[1], data[2], Integer.parseInt(data[4]), 1);
    
        //fazer operaçãos
        

        

        String[] out = {"-1"};
        return out;
        
    }


    public static String[] logout(String id) throws IOException {
        String[] out = handlers.logout(id);
        return out;
        
    }
}

