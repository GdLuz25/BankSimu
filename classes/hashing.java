package classes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;

public class hashing {
    public static String hash256(String pal) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = algorithm.digest(pal.getBytes("UTF-8"));

        return byteToHex(messageDigest);
    }

    public static String byteToHex(byte[] in)
    {
        //Converter de BytesArray para hexadecimal
        StringBuilder  hexString = new StringBuilder();
        for (byte b: in) hexString.append(String.format("%02X", 0xFF & b));
        
        return hexString.toString();
    }

    public static byte[] HEXtoByte(String in)
    {
        // converter de hexadecimal para byteArray
        BigInteger bi = new BigInteger(in, 16);
        return bi.toByteArray();
    }

}
