
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import classes.handlers;

public class server {

    public static void main(String[] args) throws Exception, IOException {
        int port = 80;
        handlers.inicializarToken();
        handlers.inicializarTemp();

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 5);

        String[] paths = loadPaths();
        String[] path;
        for (int c=0; c < paths.length; c++)
        {
            path = paths[c].split(",");
            server.createContext(path[0], new MyHandler(path[1], Integer.parseInt(path[2])));
        }
        
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("servidor iniciado");
    }

    public static class MyHandler implements HttpHandler {
        private final String path;
        private final int type;

        public MyHandler(String path, int type) {
            this.path = path;
            this.type = type;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {          
            byte[] response;

           if (type == 1) {
                String request = RequestReader(t.getRequestBody());
                System.out.println(request);
                String rsp = "0ERROR";
                try {
                    rsp = Request.Handle_Request(request);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    handlers.log(0, "system", "root", "Erro em handle http, mais detalhes..." + e);
                }
                System.out.println(rsp);
                if (rsp.startsWith("1")) response = Loaddados(rsp.substring(1));
                else response = rsp.substring(1).getBytes();
               
           }

           else{
                System.out.println(t.getRequestURI());
                //System.out.println(path);
                response = Loaddados(path);
            }

            t.sendResponseHeaders(200, response.length);
            OutputStream os = t.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    public static String[] loadPaths() throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("paths.dat"));
        String data = "";
        String linha;
        int c = 0;

        while (true)
        {
            linha = file.readLine();
            if (linha == null) break;
            if (c > 0) data += "!";
            c++;
            data += linha;
        }

        file.close();
        return data.split("!");
    }

    public static byte[] Loaddados(String path) throws IOException {
        InputStream file = new FileInputStream(path);
        byte[] dados = file.readAllBytes();
        file.close();

        return dados;
    }

    public static String RequestReader(InputStream request) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(request));
        String req = "";

        while (true) {
            String line = in.readLine();
            if (line == null) 
                break;

            req += line;
        }
        in.close();
        return req;
    }
}