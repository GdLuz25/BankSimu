import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        String linha;
        BufferedReader buffRead = new BufferedReader(new FileReader("DATA/mainData.dat"));
        while (true) {
            linha = buffRead.readLine();
            
            if (linha == null) 
                break;


            System.out.println(linha);
            
        }

        buffRead.close();
    }
}
