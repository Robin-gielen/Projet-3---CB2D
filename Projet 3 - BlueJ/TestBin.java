
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
// import java.io.UnsupportedEncodingException;
// 
// public class Test
// {
//     public static void main (String[] args) throws UnsupportedEncodingException
//     {
//         byte[] infoBin = null;
//         String test = "this is plain text";
//         infoBin = test.getBytes("UTF-8");
//         for (int i = 0; i < infoBin.length();i++)
//         {
//             System.out.println("c:" + (char) 
//         }
//     }
// }
import java.io.UnsupportedEncodingException;

public class TestBin {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] infoBin = null;
        infoBin = "this is plain text".getBytes("UTF-8");
        for (byte b : infoBin) {
            System.out.println("c:" + (char) b + "-> "
                    + Integer.toBinaryString(b));
        }
    }
}