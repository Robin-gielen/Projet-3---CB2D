/**
 * Cette classe renvoie un message en cas de EncodingException
 * 
 * @author : Julien Banken, Robin Gielen, Jeremy Gossiaux  
 * @version : 27/11/2014
 */
class EncodingException extends Exception{ 
  public EncodingException(){
    System.out.println("Votre message ne peut etre encode!");
  }  
}