public class test2 {
    public static void main(String[] args) {
        String teste1 = "a\\b\\c\\d\\e";
        System.out.println(teste1);
        String teste2 = teste1.replace("\\", "");
        System.out.println(teste2);
        String teste3 = teste1.replaceAll("\\\\", "");
        System.out.println(teste3);
    }
} 
