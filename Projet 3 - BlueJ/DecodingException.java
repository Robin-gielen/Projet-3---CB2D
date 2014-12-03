
/**
 * Cette classe renvoie un message en cas de DecodingException
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux   
 * @version : 27/11/2014
 */
public class DecodingException extends Exception{
  public DecodingException(){
    System.out.println("Le code barre ne peut pas etre decode!");
  }  
}
