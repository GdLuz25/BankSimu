import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class reverse_dataLogs {
    public static void main(String[] args) throws IOException {
        //obter dados de log
        BufferedReader file = new BufferedReader(new FileReader("DATA/log.dat"));

        String data = "";
        int c = 0;
        
        while (true)
        {
            String linha = file.readLine();
            if (linha == null) break;

            if (c > 0) data = linha + "\n" + data;
            else data = linha;

            c++;
            
        }
        file.close();

        BufferedWriter fileW = new BufferedWriter(new FileWriter("DATA/log.dat"));
        fileW.write(data);
        fileW.close();

    }
}
